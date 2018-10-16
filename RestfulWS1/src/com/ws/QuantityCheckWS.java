package com.ws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.beans.Product;


@Path("/CheckQuantity")
public class QuantityCheckWS {
	
	
	@GET
	@Path("/in")
	@Produces(MediaType.TEXT_PLAIN)
	public String check(@QueryParam("id") int id,@QueryParam("quantity") int quantity) {
	
		System.out.println("Inside check..!");
        String status=null;
        System.out.println("Requested ID: "+id);
        System.out.println("quantity required: "+quantity);
//        int id1= Integer.parseInt(id);
//        int quantity1= Integer.parseInt(quantity);
        try{
       
                        System.out.println("beginning transaction");
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://10.6.184.246:3306/springdemo","root1","root1"); 
                        String query = "select * from productlist where id=?";
                          PreparedStatement ps=con.prepareStatement(query);  
                    		ps.setInt(1, id);
                    		
                    	      ResultSet rs=ps.executeQuery();
                    	      Product p = new Product();
                    	      while(rs.next()){  
                    	    	 
                    	      	p.setId(rs.getInt(1));
                    	      	p.setPname(rs.getString(2));
                    	      	p.setPrice(rs.getInt(3));
                    	      	p.setQuantity(rs.getInt(4));
                        if(p.getQuantity()>=quantity){
                        	
                                        status= "available";
                                        
                        }else{
                        	status= "not available";
                        }
                        System.out.println("status "+status);
                    	      }  
        }
        catch(Exception e){
        System.out.print(e);
        e.printStackTrace();
        }
//                    System.out.println(prod);
        System.out.println("ending transaction");
		return status;
	}

}
