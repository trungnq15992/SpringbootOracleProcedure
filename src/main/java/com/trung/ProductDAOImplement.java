package com.trung;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import oracle.jdbc.OracleTypes;
@Service
public class ProductDAOImplement implements ProductDAO{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Product> getListProduct() throws SQLException {
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableStmt = null;
		String result ="";
	    ResultSet rs = null;
	    List<Product> listProduct = new ArrayList<>();
	    
	    callableStmt = connection.prepareCall("{call PKG_PRODUCT.GET_LIST_PRODUCT(?,?,?,?)}");
	    callableStmt.setString(1, null);
	    callableStmt.setString(2, null);
		callableStmt.registerOutParameter(3, OracleTypes.INTEGER);
		callableStmt.registerOutParameter(4, OracleTypes.CURSOR);
		
		callableStmt.execute();
		rs = (ResultSet) callableStmt.getObject(4);
		int count = callableStmt.getInt(3);
		while (rs.next()) {
			String name = rs.getString("NAME");
			Product pd = new Product();
			pd.setName(name);
			
			listProduct.add(pd);
		}
		
		
		return listProduct;
	}

}
