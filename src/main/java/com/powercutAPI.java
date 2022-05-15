package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


import model.Powercut;


@WebServlet("/powercutAPI")
public class powercutAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public powercutAPI() {
        super();
        // TODO Auto-generated constructor stub
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = powercutObj.insertPowercut(request.getParameter("powercutCode"),
				            request.getParameter("name"),
				            request.getParameter("group"),
				            request.getParameter("dayStartTime"),
				            request.getParameter("dayEndTime"),
				            request.getParameter("nightStartTime"),
				            request.getParameter("nightEndTime"));
			
		response.getWriter().write(output); 

	}
	
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request)
		{
		 	Map<String, String> map = new HashMap<String, String>();
		 	try
		 	{
				 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
				 String queryString = scanner.hasNext() ?
				 scanner.useDelimiter("\\A").next() : "";
				 scanner.close();
				 String[] params = queryString.split("&");
				 for (String param : params)
				 { 
					 String[] p = param.split("=");
					 map.put(p[0], p[1]);
				 }
		    }
			catch (Exception e)
			{
			}
			return map;
	}


	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request);
		
		String output = powercutObj.updatePowercut(paras.get("hidIDSave").toString(),
							paras.get("name"),
				            paras.get("group"),
				            paras.get("dayStartTime"),
				            paras.get("dayEndTime"),
				            paras.get("nightStartTime"),
				            paras.get("nightEndTime"));
					
		
		response.getWriter().write(output);
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request);
		
		String output = powercutObj.deletePowercut(paras.get("id").toString());
		
		response.getWriter().write(output);
	}

}
