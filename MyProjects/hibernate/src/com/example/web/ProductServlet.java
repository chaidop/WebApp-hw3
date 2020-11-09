package com.example.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.*;
import com.example.model.*;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 */

@WebServlet("/")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDao productDao;
	
	public void init() {
		productDao = new ProductDao();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			insertProduct(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void listProducts(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Products> listProducts = productDao.getAllProducts();
		request.setAttribute("listProducts", listProducts);
		RequestDispatcher dispatcher = request.getRequestDispatcher("product-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String barcode = request.getParameter("barcode");
		Products existingProduct = productDao.getProduct(barcode);
		RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");
		request.setAttribute("product", existingProduct);
		dispatcher.forward(request, response);

	}

	private void insertProduct(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String barcode = request.getParameter("barcode");
		String name = request.getParameter("name");
		String color = request.getParameter("color");
		String description = request.getParameter("description");
		Products newProduct = new Products();
		newProduct.setBarcode(barcode);
		newProduct.setName(name);
		newProduct.setColor(color);
		newProduct.setDescription(description);
		
		Products temp =productDao.getProduct(barcode);//check whether product already exists
		if(temp == null) {//it doesnt exist, so save it
			
			productDao.saveProduct(newProduct);
		}
		else {
			newProduct.setName("***ERROR***");
		}
		//response.sendRedirect("list");
		productDao.saveProduct(newProduct);
		request.setAttribute("styles", newProduct);
		RequestDispatcher view = request.getRequestDispatcher("product-list.jsp");
		try {
			view.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updateProduct(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String barcode = request.getParameter("barcode");
		String name = request.getParameter("name");
		String color = request.getParameter("color");
		String descritpion = request.getParameter("descritpion");

		Products product = new Products(barcode, name, color, descritpion);
		productDao.updateProduct(product);
		response.sendRedirect("list");
	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String barcode = request.getParameter("barcode");
		productDao.deleteProduct(barcode);
		response.sendRedirect("list");
	}
}
