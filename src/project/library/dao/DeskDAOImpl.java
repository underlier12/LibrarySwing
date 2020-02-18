package project.library.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import oracle.jdbc.OracleDriver;
import project.library.dto.DeskDTO;
import project.library.query.DeskQuery;
import project.library.query.OracleQuery;

public class DeskDAOImpl implements DeskDAO, OracleQuery, DeskQuery {

	// ========Singleton Pattern====================
    private static DeskDAOImpl instance = null;
		
    private DeskDAOImpl(){
        try{
            DriverManager.registerDriver(new OracleDriver());
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static DeskDAOImpl getInstance(){
        if(instance == null){
            instance = new DeskDAOImpl();
        }
        return instance;
    }
		
	// =============================================
	
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;


    // DESK 
    
    @Override
	public ArrayList<DeskDTO> selectAllDesk() {
		ArrayList<DeskDTO> list = new ArrayList<DeskDTO>();
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB Connection Success");
			
			pstmt = conn.prepareStatement(SQL_SELECT_ALL_DESK);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int number = rs.getInt(DESK_NUM);
				int vacant = rs.getInt(DESK_VACANT);
				String occupiedId = rs.getString(DESK_OCCUPIEDID);
				Timestamp startAt = rs.getTimestamp(DESK_STARTAT);
				
				DeskDTO ddto = new DeskDTO(number, vacant, occupiedId, startAt);
				list.add(ddto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt, rs);
		}
		
		return list;
	}
    
	@Override
	public DeskDTO selectDesk(int index) {
		DeskDTO ddto = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB Connection Success");
			
			pstmt = conn.prepareStatement(SQL_SELECT_DESK);
			pstmt.setInt(1, index);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int number = rs.getInt(DESK_NUM);
				int vacant = rs.getInt(DESK_VACANT);
				String occupiedId = rs.getString(DESK_OCCUPIEDID);
				Timestamp startAt = rs.getTimestamp(DESK_STARTAT);
				
				ddto = new DeskDTO(number, vacant, occupiedId, startAt);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt, rs);
		}
		return ddto;
	}

	@Override
	public int updateDesk(int num, DeskDTO ddto) {
		int result = 0;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB Connection Success");
			System.out.println(ddto.getStartAt());
			pstmt = conn.prepareStatement(SQL_UPDATE_DESK);
			pstmt.setInt(1, ddto.getVacant());
			pstmt.setString(2, ddto.getOccupiedId());
			pstmt.setTimestamp(3, ddto.getStartAt());
			pstmt.setInt(4, num);
			
			rs = pstmt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt, rs);
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
