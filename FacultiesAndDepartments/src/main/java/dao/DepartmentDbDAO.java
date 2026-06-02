package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import domain.Faculty;
import domain.Department;

public class DepartmentDbDAO implements RepositoryDAO<Department> {
	private static final String select_all_department =
	    "SELECT id, faculty_id, name, short_name, head, phone_number FROM departments ORDER BY name ASC";
	private static final String select_department_ById =
	    "SELECT id, faculty_id, name, short_name, head, phone_number FROM departments WHERE id = ?";

	private static final String insert_department =
	    "INSERT INTO departments(faculty_id, name, short_name, head, phone_number) VALUES(?,?,?,?,?)";
	private static final String edit_department =
	    "UPDATE departments SET faculty_id = ?, name = ?, short_name = ?, head = ?, phone_number = ? WHERE id = ?";
	private static final String delete_department =
	    "DELETE FROM departments WHERE id = ?";

	private ConnectionBuilder builder = new DbConnectionBuilder();

	private Connection getConnection() throws SQLException {
	    return builder.getConnection();
	}

	private FacultyDbDAO facultyDao = new FacultyDbDAO();

	public DepartmentDbDAO() {
	    // TODO Auto-generated constructor stub
	}

	@Override
	public Long insert(Department department) throws Exception {
	    try (Connection con = getConnection();
	         PreparedStatement pst = con.prepareStatement(insert_department, new String[] { "id" })) {
	        Long Id = -1L;
	        pst.setLong(1, department.getFacultyId());
	        pst.setString(2, department.getName());
	        pst.setString(3, department.getShortName());
	        pst.setString(4, department.getHead());
	        pst.setString(5, department.getPhoneNumber());
	        pst.executeUpdate();
	        ResultSet gk = pst.getGeneratedKeys();
	        if (gk.next()) {
	            Id = gk.getLong("id");
	        }
	        gk.close();
	        return Id;
	    } catch (Exception e) {
	        throw new Exception(e);
	    }
	}

	@Override
	public void update(Department department) throws Exception {
	    try (Connection con = getConnection();
	         PreparedStatement pst = con.prepareStatement(edit_department)) {
	        pst.setLong(1, department.getFacultyId());
	        pst.setString(2, department.getName());
	        pst.setString(3, department.getShortName());
	        pst.setString(4, department.getHead());
	        pst.setString(5, department.getPhoneNumber());
	        pst.setLong(6, department.getId());
	        pst.executeUpdate();
	    } catch (Exception e) {
	        throw new Exception(e);
	    }
	}

	@Override
	public void delete(Long Id) throws Exception {
	    try (Connection con = getConnection();
	         PreparedStatement pst = con.prepareStatement(delete_department)) {
	        pst.setLong(1, Id);
	        pst.executeUpdate();
	    } catch (Exception e) {
	        throw new Exception(e);
	    }
	}

	@Override
	public Department findById(Long Id) throws Exception {
	    Department department = null;
	    try (Connection con = getConnection()) {
	        PreparedStatement pst = con.prepareStatement(select_department_ById);
	        pst.setLong(1, Id);
	        ResultSet rs = pst.executeQuery();
	        if (rs.next()) {
	            department = fillDepartment(rs);
	        }
	        rs.close();
	        pst.close();
	    } catch (Exception e) {
	        throw new Exception(e);
	    }
	    return department;
	}

	@Override
	public List<Department> findAll() throws Exception {
	    List<Department> list = new LinkedList<>();
	    try (Connection con = getConnection();
	         PreparedStatement pst = con.prepareStatement(select_all_department);
	         ResultSet rs = pst.executeQuery()) {
	        while (rs.next()) {
	            list.add(fillDepartment(rs));
	        }
	        rs.close();
	    } catch (Exception e) {
	        throw new Exception(e);
	    }
	    return list;
	}

	private Department fillDepartment(ResultSet rs) throws SQLException, Exception {
	    Department department = new Department(
	        rs.getLong("id"),
	        rs.getString("name"),
	        rs.getString("short_name"),
	        rs.getString("head"),
	        rs.getString("phone_number"),
	        rs.getLong("faculty_id"),
	        null
	    );
	    Long facultyId = rs.getLong("faculty_id");
	    if (facultyId != null && facultyId != 0L) {
	        try {
	            Faculty f = facultyDao.findById(facultyId);
	            department.setFaculty(f);
	        } catch (Exception ex) {
	        }
	        department.setFacultyId(facultyId);
	    }
	    return department;
	}

	public Department FindById(Long id, List<Department> departments) {
	    if (departments != null) {
	        for (Department d : departments) {
	            if ((d.getId()).equals(id)) {
	                return d;
	            }
	        }
	    } else {
	        return null;
	    }
	    return null;
	}
}
