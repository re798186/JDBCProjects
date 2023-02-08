package in.reethu.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.reethu.dto.Student;
import in.reethu.factory.StudentServiceFactory;
import in.reethu.service.IStudentService;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/controller/*")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IStudentService studentService = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		studentService = StudentServiceFactory.getStudentService();
		String requestURI = request.getRequestURI();
		System.out.println(requestURI);

		if (requestURI.endsWith("add")) {
			saveStudent(request, response);
		} else if (requestURI.endsWith("search")) {
			searchStudent(request, response);
		} else if (requestURI.endsWith("edit")) {
			editStudent(request, response);
		} else if (requestURI.endsWith("update")) {
			updateStudent(request, response);
		} else if (requestURI.endsWith("delete")) {
			deleteStudent(request, response);
		}
	}

	private void editStudent(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String sid = request.getParameter("sid");
		Student student = studentService.findById(Integer.parseInt(sid));
		if (student != null) {
			response.setContentType("text/html");

			// display editpage using html
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>OUTPUT</title></head>");
			out.println("<body bgcolor='lightblue'>");
			out.println("<br/><br/><br/>");
			out.println("<form method='post' action='./update'>");
			out.println("<table align='center'>");
			out.println("<tr><th>ID</th><td>" + student.getSid() + "</td></tr>");
			out.println("<input type='hidden' name='sid' value='" + student.getSid() + "' />");
			out.println("<tr><th>NAME</th><td><input type='text' name='sname' value='" + student.getSname()
					+ "'/></td></tr>");
			out.println(
					"<tr><th>AGE</th><td><input type='text' name='sage' value='" + student.getSage() + "'/></td></tr>");
			out.println("<tr><th>ADDRESS</th><td><input type='text' name='saddr' value='" + student.getSaddr()
					+ "'/></td></tr>");
			out.println("<tr><td></td><td><input type='submit' value='update'/></td></tr>");
			out.println("</table>");
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
			out.close();

		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/notfound.html");
			rd.forward(request, response);
		}
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd;
		String status = studentService.deleteById(Integer.parseInt(request.getParameter("sid")));
		System.out.println(status);
		if (status.equals("Success")) {
			rd = request.getRequestDispatcher("/success.html");
			rd.forward(request, response);
		} else if (status.equals("Failure")) {
			rd = request.getRequestDispatcher("/failure.html");
			rd.forward(request, response);
		} else {
			rd = request.getRequestDispatcher("/notfound.html");
			rd.forward(request, response);
		}
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sid = request.getParameter("sid");
		String sname = request.getParameter("sname");
		String sage = request.getParameter("sage");
		String saddr = request.getParameter("saddr");

		Student student = new Student();
		student.setSid(Integer.parseInt(sid));
		student.setSname(sname);
		student.setSage(Integer.parseInt(sage));
		student.setSaddr(saddr);

		String status = studentService.updateById(student);
		System.out.println(status);
		RequestDispatcher rd;
		if (status.equals("Success")) {
			rd = request.getRequestDispatcher("/success.html");
			rd.forward(request, response);
		} else if (status.equals("Failure")) {
			rd = request.getRequestDispatcher("/failure.html");
			rd.forward(request, response);
		}
	}

	private void searchStudent(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher rd;
		Student student = studentService.findById(Integer.parseInt(request.getParameter("sid")));
		System.out.println(student);
		if (student != null) {
			response.setContentType("text/html");

			PrintWriter out = response.getWriter();
			out.println("<html><head><title>STUDENT DATA</title></head>");
			out.println("<body bgcolor='lightblue'>");
			out.println("<br/><br/><br/>");
			out.println("<table align='center' border='1'>");
			out.println("<tr>");
			out.println("<th>SID</th>");
			out.println("<th>SNAME</th>");
			out.println("<th>SAGE</th>");
			out.println("<th>SADDRESS</th>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<td>" + student.getSid() + "</td>");
			out.println("<td>" + student.getSname() + "</td>");
			out.println("<td>" + student.getSage() + "</td>");
			out.println("<td>" + student.getSaddr() + "</td>");
			out.println("</tr>");

			out.println("</table>");
			out.println("</body>");
			out.println("</html>");

			out.close();

		} else {
			rd = request.getRequestDispatcher("/notfound.html");
			rd.forward(request, response);
		}
	}

	private void saveStudent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd;
		Student student = new Student();
		student.setSname(request.getParameter("sname"));
		student.setSage(Integer.parseInt(request.getParameter("sage")));
		student.setSaddr(request.getParameter("saddr"));
		String status = studentService.save(student);
		System.out.println(status);
		if (status.equals("Success")) {
			rd = request.getRequestDispatcher("/success.html");
			rd.forward(request, response);
		} else {
			rd = request.getRequestDispatcher("/failure.html");
			rd.forward(request, response);
		}
	}

}
