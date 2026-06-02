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
import dao.FacultyDbDAO;
import dao.ConnectionProperty;

@WebServlet("/editfaculty")
public class EditFacultyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ConnectionProperty prop;

	public EditFacultyServlet() throws FileNotFoundException, IOException {
		super();
		prop = new ConnectionProperty();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		List<Faculty> faculties;
		Faculty editFaculty = null;
		FacultyDbDAO dao = new FacultyDbDAO();

		try {
			faculties = dao.findAll();
			request.setAttribute("faculties", faculties);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String strId = request.getParameter("id");
		Long id = null;
		if (strId != null && !strId.isEmpty()) {
			id = Long.parseLong(strId);
			try {
				editFaculty = dao.findById(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		request.setAttribute("facultyEdit", editFaculty);

		String userPath = request.getServletPath();
		if ("/editfaculty".equals(userPath)) {
			request.getRequestDispatcher("/views/editfaculty.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		FacultyDbDAO dao = new FacultyDbDAO();

		String strId = request.getParameter("id");
		Long id = null;
		if (strId != null && !strId.isEmpty()) {
			id = Long.parseLong(strId);
		}

		String name = request.getParameter("name");
		String shortName = request.getParameter("shortName");
		String dean = request.getParameter("dean");
		String phoneNumber = request.getParameter("phoneNumber");

		Faculty faculty;
		if (id != null) {
			faculty = new Faculty(id, name, shortName, dean, phoneNumber);
			try {
				dao.update(faculty);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			faculty = new Faculty(name, shortName, dean, phoneNumber);
			try {
				dao.insert(faculty);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		response.sendRedirect(request.getContextPath() + "/faculty");
	}
}