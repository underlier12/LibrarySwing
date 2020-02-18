package project.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import oracle.jdbc.OracleDriver;
import project.library.dto.CostDTO;
import project.library.query.CostQuery;
import project.library.query.OracleQuery;

public class CostDAOImpl implements CostDAO, OracleQuery, CostQuery {

	// ========Singleton Pattern====================
    private static CostDAOImpl instance = null;
		
    private CostDAOImpl(){
        try{
            DriverManager.registerDriver(new OracleDriver());
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static CostDAOImpl getInstance(){
        if(instance == null){
            instance = new CostDAOImpl();
        }
        return instance;
    }
		
	// =============================================
	
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

	// COST
	

	@Override
	public CostDTO selectCost(int price) {
		CostDTO cdto = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB Connection Success");
			
			pstmt = conn.prepareStatement(SQL_SELECT_COST);
			pstmt.setInt(1, price);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int hour = rs.getInt(COST_HOUR);
				
				cdto = new CostDTO(price, hour);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt, rs);
		}
		
		return cdto;
	}
	
	@Override
	public ArrayList<CostDTO> selectCost() {
		ArrayList<CostDTO> list = new ArrayList<CostDTO>();
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB Connection Success");
			
			pstmt = conn.prepareStatement(SQL_SELECT_COST_ORDER_BY_PRICE);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int price = rs.getInt(COST_PRICE);
				int hour = rs.getInt(COST_HOUR);
				
				CostDTO cdto = new CostDTO(price, hour);
				list.add(cdto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt, rs);
		}
		
		return list;
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
