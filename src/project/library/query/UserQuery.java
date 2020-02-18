package project.library.query;

// JDBC에서 사용될 상수 및 SQL문 정의
public interface UserQuery {
	// 상수 정의
	public static final String TABLE_USER = "MEMBER";
	
	// TABLE USER
	public static final String USER_ID = "ID";
	public static final String USER_PW = "PW";
	public static final String USER_NAME = "NAME";
	public static final String USER_TEL = "TEL";
	public static final String USER_HOUR = "HOUR";
		
	public static final String SQL_CHECK_USER =
			"SELECT * FROM " + TABLE_USER + " WHERE " + USER_ID + " =?";

	public static final String SQL_INSERT_USER =
			"INSERT INTO " + TABLE_USER + " VALUES (?, ?, ?, ?, 0)";
	
	public static final String SQL_UPDATE_USER_PW =
			"UPDATE " + TABLE_USER + 
			" SET " + USER_PW + " = ?" +
			" WHERE " + USER_ID + " = ?";
	
	public static final String SQL_UPDATE_USER_ALL =
			"UPDATE " + TABLE_USER +
			" SET " + USER_ID + " = ?" +
			"," + USER_PW + " = ?" +
			"," + USER_NAME + " = ?" +
			"," + USER_TEL + " = ?" +
			" WHERE " + USER_ID + " = ?"+
			" AND " + USER_PW + " = ?";
	
	public static final String SQL_UPDATE_USER_HOUR =
			"UPDATE " + TABLE_USER + 
			" SET " + USER_HOUR + " = ?" +
			" WHERE " + USER_ID + " = ?";
	
	public static final String SQL_DELETE_USER =
			"DELETE FROM " + TABLE_USER + " WHERE " + USER_ID + " = ?" + " AND "
	+ USER_PW + " =?";
	
	public static final String SQL_LOGIN_USER =
			"SELECT * FROM " + TABLE_USER + " WHERE " + USER_ID + " =?"
			+ " AND " + USER_PW + " =?";
	
}