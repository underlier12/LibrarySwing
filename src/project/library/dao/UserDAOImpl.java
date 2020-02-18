package project.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import project.library.Library;
import oracle.jdbc.OracleDriver;
import project.library.dto.UserDTO;
import project.library.query.OracleQuery;
import project.library.query.UserQuery;

public class UserDAOImpl implements UserDAO, OracleQuery, UserQuery {

	// ========Singleton Pattern====================
    private static UserDAOImpl instance = null;
		
    private UserDAOImpl(){
        try{
            DriverManager.registerDriver(new OracleDriver());
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static UserDAOImpl getInstance(){
        if(instance == null){
            instance = new UserDAOImpl();
        }
        return instance;
    }
		
	// =============================================
	
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;



    // USER
    
    @Override
    public int insertUser(UserDTO userDTO) {
        int result = 0;
        conn = null;
        pstmt = null;
        
        try {
        	conn = DriverManager.getConnection(URL,USER,PASSWORD);
        	pstmt = conn.prepareStatement(SQL_INSERT_USER);
        	
        	pstmt.setString(1,userDTO.getId());
        	pstmt.setString(2,userDTO.getPw());
        	pstmt.setString(3,userDTO.getName());
        	pstmt.setString(4,userDTO.getTel());
        	
        	result = pstmt.executeUpdate();
        	
        }catch(SQLException e) {
        	e.printStackTrace();
        }finally {
        	closeResource(conn, pstmt);
        }
        
       
        return result;
    }
    
	@Override
	public UserDTO selectUser(String id) {
		UserDTO userDTO = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(SQL_CHECK_USER);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String userId = rs.getString(USER_ID);
    			String userPw = rs.getString(USER_PW);
    			String userName = rs.getString(USER_NAME);
    			String userTel = rs.getString(USER_TEL);
    			int userHour = rs.getInt(USER_HOUR);
    			
    			userDTO = new UserDTO(userId,userPw,userName,userTel,userHour);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource(conn,pstmt);
		}
		
		return userDTO;
	}
    
    @Override
	public UserDTO userInfo(String id, String pw) {
		conn = null;
		pstmt = null;
		UserDTO userDTO = null;
		try {
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
    		pstmt = conn.prepareStatement(SQL_LOGIN_USER);
    		
    		pstmt.setString(1, id.trim());
    		pstmt.setString(2, pw.trim());
    		rs = pstmt.executeQuery();
    		
    		if(rs.next()) {
    			String userId = rs.getString(USER_ID);
    			String userPw = rs.getString(USER_PW);
    			String userName = rs.getString(USER_NAME);
    			String userTel = rs.getString(USER_TEL);
    			int userHour = rs.getInt(USER_HOUR);
    			
    			userDTO = new UserDTO(userId,userPw,userName,userTel,userHour);
    			
    		}
    		
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeResource(conn,pstmt);
		}
		return userDTO;
	}
    
    @Override
    public boolean checkUser(String id) {
    	boolean check = true;
    	conn = null;
    	pstmt = null;
    	try {
    		conn = DriverManager.getConnection(URL,USER,PASSWORD);
    		pstmt = conn.prepareStatement(SQL_CHECK_USER);
    		pstmt.setString(1,id.trim());
    		rs = pstmt.executeQuery();
    		if(rs.next()) {
    			check = false;
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}finally {
    		closeResource(conn, pstmt);
    	}
    	return check;
    }
    
    @Override
    public boolean loginUser(String id, String pw) {
    	boolean check = false;
    	conn = null;
    	pstmt = null;
    	
    	try {
    		conn = DriverManager.getConnection(URL,USER,PASSWORD);
    		pstmt = conn.prepareStatement(SQL_LOGIN_USER);
    		pstmt.setString(1, id.trim());
    		pstmt.setString(2, pw.trim());
    		rs = pstmt.executeQuery();
    		
    		if(rs.next()) {
    			check = true;
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}finally {
    		closeResource(conn, pstmt);
    	}
    	
    	return check;
    }

//    @Override
//    public UserDTO selectUser(int index) {
//        return null;
//    }

    @Override
    public int updateUser(UserDTO userDTO) {
        int result = 0;
    	conn = null;
    	pstmt = null;
    	
    	try {
    		conn = DriverManager.getConnection(URL,USER,PASSWORD);
    		pstmt = conn.prepareStatement(SQL_UPDATE_USER_ALL);
    		
    		pstmt.setString(1, userDTO.getId());
    		pstmt.setString(2, userDTO.getPw());
    		pstmt.setString(3, userDTO.getName());
    		pstmt.setString(4, userDTO.getTel());
    		pstmt.setString(5, Library.sessionUserDTO.getId());
    		pstmt.setString(6, Library.sessionUserDTO.getPw());
    		System.out.println("log: " +userDTO.toString());
    		
    		rs = pstmt.executeQuery();
    		
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}finally {
    		closeResource(conn,pstmt);
    	}
    	result = 1;
    	
    	return result;
    }
    
	@Override
	public int updateUserHour(String id, int hour, boolean deduct) {
		int result = 0;
		int currentHour = 0;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB Connection Success");
			
			pstmt = conn.prepareStatement(SQL_CHECK_USER);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				currentHour = rs.getInt(USER_HOUR);
			}
			
			if(deduct == false) {
				pstmt = conn.prepareStatement(SQL_UPDATE_USER_HOUR);
				pstmt.setInt(1, hour + currentHour);
				pstmt.setString(2, id);	
			} else {
				pstmt = conn.prepareStatement(SQL_UPDATE_USER_HOUR);
				pstmt.setInt(1, hour);
				pstmt.setString(2, id);	
			}
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt);
		}
		
		return result;
	}

	@Override
    public int deleteUser(String id,String pw) {
    	int result = 0;
    	conn = null;
    	pstmt = null;
    	
    	try {
    		conn = DriverManager.getConnection(URL,USER,PASSWORD);
    		pstmt = conn.prepareStatement(SQL_DELETE_USER);
    		
    		pstmt.setString(1, id.trim());
    		pstmt.setString(2, pw.trim());
    		
    		result = pstmt.executeUpdate();
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
    		closeResource(conn,pstmt);
    	}
    	
    	return result;
    }



    private void closeResource(Connection conn, Statement stmt){
        try{
            stmt.close();
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void closeResource(Connection conn,Statement stmt,ResultSet rs){
        try{
            rs.close();
            conn.close();
            stmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
