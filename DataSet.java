package com.otherutil;

import java.sql.*;
import java.util.*;

public class DataSet {
	
private String conurl;
private  String username;
private  String dburl;
private  String password;
private  Connection con;
private Statement smt;
	
	public DataSet(String conurl,String dburl,String username,String password){
		this.conurl=conurl;
		this.username=username;
		this.dburl=dburl;
		this.password=password;
	}
	
	public  HashMap<Integer, String> ExecQuery(int colIndex,String Query) throws Exception
	{
		HashMap<Integer, String> columnVal=new HashMap<Integer, String>();
		int i=0;
	   // Connection conn = getDatabaseConnection();
		    Statement smt =getStatement();
		    ResultSet rslt =
		         smt.executeQuery(Query);
		    while (rslt.next()) 
		    {
		    	columnVal.put(i, rslt.getString(colIndex));
		    	i++;
		    }
		    smt.close();
		    
		return columnVal;
	}
	
	public String  ExecDbQuery(String query,int colindex){
		Statement stmt = getStatement();
		String result=null;
		try 
		{
			ResultSet rslt = stmt.executeQuery(query);
			while(rslt.next())
			{
				result = rslt.getString(colindex);
			}
		} catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private Statement getStatement()
	{
		Connection c = getDatabaseConnection();
		if(c!= null){
		    try {
				 smt = c.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("could not get connection");
		}
		return smt;
		
	}
	
private  Connection getDatabaseConnection(){

		try {
			Class.forName(dburl);	

		} catch(java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}

		try
		{
		   this.con = DriverManager.getConnection(conurl,username,password);
		}
		catch(SQLException ex) 
		{
			System.err.println("SQLException: " + ex.getMessage());
		}

		return con;
	}

}
