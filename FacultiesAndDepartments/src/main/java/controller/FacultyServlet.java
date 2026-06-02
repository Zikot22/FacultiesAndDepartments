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
import dao.FacultyDbDAO;
import dao.ConnectionProperty;

@WebServlet("/faculty")
public class FacultyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ConnectionProperty prop;

	public FacultyServlet() throws FileNotFoundException, IOException {
		super();
		prop = new ConnectionProperty();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String userPath;
		List<Faculty> faculties;
		FacultyDbDAO dao = new FacultyDbDAO();

		try {
			faculties = dao.findAll();
			request.setAttribute("faculties", faculties);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userPath = request.getServletPath();
		if ("/faculty".equals(userPath)) {
			request.getRequestDispatcher("/views/faculty.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FacultyDbDAO dao = new FacultyDbDAO();

		String name = request.getParameter("facName");
		String shortName = request.getParameter("shortName");
		String dean = request.getParameter("dean");
		String phoneNumber = request.getParameter("phoneNumber");
		
		Faculty newFaculty = new Faculty(name, shortName, dean, phoneNumber);

		try {
			Long index = dao.insert(newFaculty);
			System.out.println("Adding result: " + index);
		} catch (Exception e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}
}