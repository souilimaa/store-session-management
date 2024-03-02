package DAO;

import java.sql.SQLException;
import java.util.List;

import models.command;

public interface CommandDAO extends dao<command> {
	List<command>getAll(int userID) throws SQLException;
}
