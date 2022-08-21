package com.trung;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {
	List<Product> getListProduct() throws SQLException;
	
	void deleteById(Long id) throws SQLException;
	
	void addProduct(Product product) throws SQLException;
}
