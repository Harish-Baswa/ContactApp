package com.edubridge.ContactApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes.Name;

import com.edubridge.ContactApp.model1.Contact;
import com.edubridge.ContactApp.util.DBUtils;
import com.mysql.cj.QueryReturnType;
import com.mysql.cj.jdbc.CallableStatement;

public class ContactDaoImpl implements ContactDao {

	@Override
	public int addContact(Contact c) {
		String INSERT ="insert into (name,email,mobile) values(?,?,?)";
		Connection con=DBUtils.getConnection();
		int status=0;
		try {
			PreparedStatement ps=con.prepareStatement(INSERT);
			ps.setString(1,c.getName());
			ps.setString(2,c.getEmail());
			ps.setLong(3,c.getMobile());
			status=ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Contact> getAllContacts() {
		String SELECT="select * from contact";
		Connection con=DBUtils.getConnection();
		List<Contact> contacts=new ArrayList<Contact>();
		try {
			PreparedStatement ps=con.prepareStatement(SELECT);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Contact c=new Contact();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setEmail(rs.getString("email"));
				c.setMobile(rs.getLong("mobile"));
				contacts.add(c);
				}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return contacts;
	}

	@Override
	public Contact getContact(String name) {
		String SELECT = "SELECT * FROM contact WHERE name = ?";
		Connection con=DBUtils.getConnection();
		 try {
	            PreparedStatement ps = con.prepareStatement(SELECT);
	            ps.setString(1, name);
	            ResultSet rs = ps.executeQuery();
	            
	            if (rs.next()) {
	            	Contact   c1=new Contact();
	                c1.setId(rs.getInt("id"));
	                c1.setName(rs.getString("name"));
	                c1.setEmail(rs.getString("email"));
	                c1.setMobile(rs.getLong("mobile"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
		return getContact(name);
	}
	@Override
	public int updateContact(Contact c) {
		 String UPDATE = "UPDATE contact SET name = ?, email = ?, mobile = ? WHERE id = ?";
		    Connection con = DBUtils.getConnection();
		    int status = 0;
		    try {
		        PreparedStatement ps = con.prepareStatement(UPDATE);
		        ps.setString(1, c.getName());
		        ps.setString(2, c.getEmail());
		        ps.setLong(3, c.getMobile());
		        ps.setInt(4, c.getId());
		        status = ps.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return status;
		}

	@Override
	public int deleteContact(String name) {
		String DELETE = "Delete from contact where name = ?";
	    Connection con = DBUtils.getConnection();
	    int status = 0;
	    try {
	        PreparedStatement ps = con.prepareStatement(DELETE);
	        ps.setString(1, name);
	        status = ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return status;
	}

	@Override
	public void deleteAllContacts() {
		  String DELETEALL = "Delete from contact";
		    Connection con = DBUtils.getConnection();
		    try {
		        PreparedStatement ps = con.prepareStatement(DELETEALL);
		        ps.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		
	}
	

}
