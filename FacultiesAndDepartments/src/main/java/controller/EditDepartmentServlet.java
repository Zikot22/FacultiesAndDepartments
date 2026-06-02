package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import domain.Faculty;
import domain.Department;
import dao.FacultyDbDAO;
import dao.DepartmentDbDAO;
import dao.ConnectionProperty;

@WebServlet("/editdepartment")
public class EditDepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ConnectionProperty prop;

	public EditDepartmentServlet() throws FileNotFoundException, IOException {
		super();
		prop = new ConnectionProperty();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String userPath;

		List<Faculty> faculties;
		List<Department> departments = null;
		Department editDepartment = null;
		DepartmentDbDAO dao = new DepartmentDbDAO();
		FacultyDbDAO daoFaculty = new FacultyDbDAO();

		try {
			faculties = daoFaculty.findAll();
			request.setAttribute("faculties", faculties);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			departments = dao.findAll();
			faculties = daoFaculty.findAll();

			for (Department department : departments) {
				department.setFaculty(daoFaculty.FindById(department.getFacultyId(), faculties));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String strId = request.getParameter("id");
		Long id = null;
		if (strId != null && !strId.isEmpty()) {
			id = Long.parseLong(strId);
			try {
				editDepartment = dao.findById(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		request.setAttribute("editDepartment", editDepartment);
		request.setAttribute("departments", departments);

		userPath = request.getServletPath();
		if ("/editdepartment".equals(userPath)) {
			request.getRequestDispatcher("/views/editdepartment.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DepartmentDbDAO dao = new DepartmentDbDAO();

		String strId = request.getParameter("id");
		Long id = null;
		if (strId != null && !strId.isEmpty()) {
			id = Long.parseLong(strId);
		}

		String name = request.getParameter("name");
		String shortName = request.getParameter("shortName");
		String head = request.getParameter("head");
		String phoneNumber = request.getParameter("phoneNumber");

		String faculty = request.getParameter("selectFaculty");
		int index1 = faculty.indexOf('=');
		int index2 = faculty.indexOf("Name");
		String f1 = faculty.substring(index1 + 1, index2);
		Long idFaculty = Long.parseLong(f1.trim());
		
		Department updateDepartment = new Department(id, name, shortName, head, phoneNumber, idFaculty);
		try {
			dao.update(updateDepartment);
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.sendRedirect(request.getContextPath()+"/department");
	}
}