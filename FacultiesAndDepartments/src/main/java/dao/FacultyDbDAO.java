package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import domain.Faculty;

public class FacultyDbDAO implements RepositoryDAO<Faculty> {
	public FacultyDbDAO() {
		// TODO Auto-generated constructor stub
	}

	private static final String select_all_faculty = "SELECT id, name, short_name,dean,phone_number FROM faculties ORDER BY name ASC";
	private static final String select_faculty_ById = "SELECT id, name, short_name,dean,phone_number FROM faculties WHERE id=?";
	private static final String insert_faculty = "INSERT INTO faculties(name, short_name, dean, phone_number) VALUES(?, ?, ?, ?)";
	private static final String edit_faculty = "UPDATE faculties SET name = ?, short_name=?,dean=?,phone_number=? WHERE id = ?";
	private static final String delete_faculty = "DELETE FROM faculties WHERE id = ?";

	private ConnectionBuilder builder = new DbConnectionBuilder();

	private Connection getConnection() throws SQLException {
		return builder.getConnection();
	}

	@Override
	public Long insert(Faculty faculty) throws Exception {
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(insert_faculty, new String[] { "id" })) {
			Long Id = -1L;
			pst.setString(1, faculty.getName());
			pst.setString(2, faculty.getShortName());
			pst.setString(3, faculty.getDean());
			pst.setString(4, faculty.getPhoneNumber());
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
	public void update(Faculty faculty) throws Exception {
		try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(edit_faculty)) {
			pst.setString(1, faculty.getName());
			pst.setString(2, faculty.getShortName());
			pst.setString(3, faculty.getDean());
			pst.setString(4, faculty.getPhoneNumber());
			pst.setLong(5, faculty.getId());
			pst.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public void delete(Long Id) throws Exception {
		try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(delete_faculty)) {
			pst.setLong(1, Id);
			pst.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public Faculty findById(Long Id) throws Exception {
		Faculty faculty = null;
		try (Connection con = getConnection()) {
			PreparedStatement pst = con.prepareStatement(select_faculty_ById);
			pst.setLong(1, Id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				faculty = fillFaculty(rs);
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			throw new Exception(e);
		}
		return faculty;
	}

	@Override
	public List<Faculty> findAll() throws Exception {
		List<Faculty> list = new LinkedList<>();
		try (Connection con = getConnection();
				PreparedStatement pst = con.prepareStatement(select_all_faculty);
				ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				list.add(fillFaculty(rs));
			}
			rs.close();
		} catch (Exception e) {
			throw new Exception(e);
		}
		return list;
	}

	private Faculty fillFaculty(ResultSet rs) throws SQLException {
		Faculty faculty = new Faculty(rs.getLong("id"), rs.getString("name"), rs.getString("short_name"),
				rs.getString("dean"), rs.getString("phone_number"));
		return faculty;
	}

	public Faculty FindById(Long id, List<Faculty> faculties) {
		if (faculties != null) {
			for (Faculty f : faculties) {
				if ((f.getId()).equals(id)) {
					return f;
				}
			}
		} else {
			return null;
		}
		return null;
	}
}