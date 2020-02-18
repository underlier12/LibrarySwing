package project.library.dao;

import java.util.ArrayList;

import project.library.dto.DeskDTO;

public interface DeskDAO {

    // Desk  
	public ArrayList<DeskDTO> selectAllDesk();
	
    public DeskDTO selectDesk(int index);
    
    public int updateDesk(int num, DeskDTO ddto);
   
}