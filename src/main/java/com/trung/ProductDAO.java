package com.trung;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {
	List<Product> getListProduct() throws SQLException;
}
