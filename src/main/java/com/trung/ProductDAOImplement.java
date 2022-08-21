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
	    callableStmt = connection.prepareCall("{call PKG_PRODUCT.GET_LIST_PRODUCT(?,?)}");
		callableStmt.registerOutParameter(1, OracleTypes.INTEGER);
		callableStmt.registerOutParameter(2, OracleTypes.CURSOR);
		callableStmt.execute();
		rs = (ResultSet) callableStmt.getObject(2);
		int count = callableStmt.getInt(1);
		while (rs.next()) {
			Product pd = new Product();
			Long id = rs.getLong("ID");
			pd.setId(id);
			String name = rs.getString("NAME");
			pd.setName(name);
			String brand = rs.getString("BRAND");
			pd.setBrand(brand);
			String madein = rs.getString("MADEIN");
			pd.setMadein(madein);
			float price = rs.getFloat("PRICE");
			pd.setPrice(price);
			listProduct.add(pd);
		}
		return listProduct;
	}

	@Override
	public void deleteById(Long id) throws SQLException {
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableStmt = null;
	    callableStmt = connection.prepareCall("{call PKG_PRODUCT.DELETE_PRODUCT(?)}");
		callableStmt.setLong(1,id);
		callableStmt.execute();
	}

	@Override
	public void addProduct(Product product) throws SQLException {
		Connection connection = jdbcTemplate.getDataSource().getConnection();
		CallableStatement callableStmt = null;
	    callableStmt = connection.prepareCall("{call PKG_PRODUCT.ADD_PRODUCT(?,?,?,?)}");
	    callableStmt.setString(1,product.getName());
	    callableStmt.setString(2,product.getBrand());
	    callableStmt.setString(3,product.getMadein());
	    callableStmt.setFloat(4,product.getPrice());
		callableStmt.execute();
	}

}
