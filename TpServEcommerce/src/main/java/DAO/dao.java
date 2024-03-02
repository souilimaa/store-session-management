package DAO;

import java.sql.SQLException;

public interface dao<T> {
	int insert(T t) throws SQLException;
}
