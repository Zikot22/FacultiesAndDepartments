package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import domain.Faculty;
import domain.Department;
import dao.FacultyDbDAO;
import dao.DepartmentDbDAO;
import dao.ConnectionProperty;

@WebServlet("/department")
public class DepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ConnectionProperty prop;

	public DepartmentServlet() throws FileNotFoundException, IOException {
		super();
		prop = new ConnectionProperty();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String userPath;
		List<Faculty> faculties;
		List<Department> departments;
		FacultyDbDAO daoFaculty = new FacultyDbDAO();
		DepartmentDbDAO dao = new DepartmentDbDAO();

		try {
			departments = dao.findAll();
			faculties = daoFaculty.findAll();
			for (Department demartment : departments) {
				demartment.setFaculty(daoFaculty.FindById(demartment.getFacultyId(), faculties));
			}
			request.setAttribute("departments", departments);
		} catch (Exception e) {
			e.printStackTrace();
		}

		userPath = request.getServletPath();
		if ("/department".equals(userPath)) {
			request.getRequestDispatcher("/views/department.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}