package Controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.accountDao;
import Model.account;
import Model.accountInsertErr;
import Model.userInfo;

/**
 * Servlet implementation class signupServlet
 */
@WebServlet("/signupServlet")
public class signupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String showInsertErr = "signup.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		accountInsertErr errors = new accountInsertErr();
		String username = request.getParameter("txtUsername");
		String password = request.getParameter("txtPassword");
		String confirm = request.getParameter("txtConfirm");
		String id = request.getParameter("id");
		String fullname = request.getParameter("fullname");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String gender = request.getParameter("gender");
	
//		String url = showInsertErr;
		try {
			boolean error = false;
			if (username.trim().length() < 3 || username.trim().length() > 10) {
				error = true;
				errors.setUsernameLengthErr("Username phai tu 3-10 ky tu");
			}
			if (password.trim().length() < 3|| password.trim().length() > 10) {
				error = true;
				errors.setPasswordLengthErr("Password phai tu 3-10 ky tu");
			} else if (!confirm.trim().equals(password.trim()))

			{
				error = true;
				errors.setConfirmNotMatch("Password va Confirm phai khop nhau");
			}
			if (error) {
				request.setAttribute("INSERTTERR", errors);
			} else {
				accountDao dao = new accountDao();
				account acc = dao.checkAccount(username);
				userInfo a = dao.checkUserinfo(id);
				if (acc == null|| a==null) {
					dao.signUpAccount(username, password, fullname, phone, address, gender);

					request.setAttribute("messe", "Signing is successful !!!");
					RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
					rd.forward(request, response);

				} else {
					request.setAttribute("mess", "This account exists !!!");
					RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
					rd.forward(request, response);
				}

			}
//			RequestDispatcher rd = request.getRequestDispatcher(url);
//			rd.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
			e.printStackTrace();
		}

	}

}
