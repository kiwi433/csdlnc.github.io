package Controller;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.productDao;
import Model.product;

/**
 * Servlet implementation class detailProductServlet
 */
@WebServlet("/detailProductServlet")
public class detailProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		DecimalFormat dcf = new DecimalFormat();
		request.setAttribute("dcf", dcf);
		int id=Integer.parseInt( request.getParameter("id"));
		productDao dao = new productDao();
		product p= dao.getspByID(id);
		request.setAttribute("detail", p);
		request.getRequestDispatcher("productsDetail.jsp").forward(request, response);
	}

	

}
