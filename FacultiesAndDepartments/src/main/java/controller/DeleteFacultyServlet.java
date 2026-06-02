package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

import dao.ConnectionProperty;
import dao.FacultyDbDAO;

@WebServlet("/deletefaculty")
public class DeleteFacultyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ConnectionProperty prop;

	public DeleteFacultyServlet() throws FileNotFoundException, IOException {
		super();
		prop = new ConnectionProperty();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FacultyDbDAO dao = new FacultyDbDAO();

		String strId = request.getParameter("id");
		Long deleteid = null;
		if (strId != null) {
			deleteid = Long.parseLong(strId);
		}

		try {
			dao.delete(deleteid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/faculty");
	}
}
