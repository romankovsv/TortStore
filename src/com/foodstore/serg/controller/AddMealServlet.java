package com.foodstore.serg.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodstore.serg.model.Meal;
import com.foodstore.serg.service.MealService;
import com.foodstore.serg.utils.MealUtils;
import static com.foodstore.serg.constants.ServletConstants.*;

@WebServlet(name = "AddMealServlet", urlPatterns = "/add")
public class AddMealServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;   
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		response.setContentType(CONTENT_TYPE);
		
		final String title = request.getParameter(TITLE);
		final String description = request.getParameter(DESCRIPTION);
		final String type = request.getParameter(TYPE);
		final String price = request.getParameter(PRICE);
		final boolean available = Boolean.parseBoolean(request.getParameter(AVAILABLE));
		final String owner = request.getParameter(OWNER);
		
	
		
		final Meal meal = MealUtils.create(title, description, type, available, price, owner);
	
		if(meal == null){
			out.write(ERROR);
		}else{
			if(MealService.add(meal)){
				out.write(SUCCESS);
			}else{
				out.write(NOT_ADDED);
			}
		}	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
