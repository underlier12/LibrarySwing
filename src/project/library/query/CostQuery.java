package project.library.query;

// JDBC���� ���� ��� �� SQL�� ����
public interface CostQuery {

	// ���̺� ��
	public static final String TABLE_COST = "COST";
	
	// TABLE COST
	public static final String COST_PRICE = "PRICE";
	public static final String COST_HOUR = "HOUR";
	
	// COST DML
	public static final String SQL_SELECT_COST =
			"SELECT * FROM " + TABLE_COST + " WHERE " + COST_PRICE + " = ?";
	
	public static final String SQL_SELECT_COST_ORDER_BY_PRICE =
			"SELECT * FROM " + TABLE_COST + " ORDER BY PRICE";
	
	public static final String SQL_INSERT_COST =
			"INSERT INTO " + TABLE_COST + " VALUES (?, ?)";
	
	public static final String SQL_UPDATE_COST =
			"UPDATE " + TABLE_COST + 
			" SET " + COST_HOUR + " = ?" +
			" WHERE " + COST_PRICE + " = ?";
	
	public static final String SQL_DELETE_COST =
			"DELETE FROM " + TABLE_COST + " WHERE " + COST_PRICE + " = ?";
	
}