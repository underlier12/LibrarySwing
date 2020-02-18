package project.library.dao;

import project.library.dto.UserDTO;

public interface UserDAO {

	// User
	public int insertUser(UserDTO userDTO);
	
	public UserDTO selectUser(String id);
    
    public boolean checkUser(String id);

    public int updateUser(UserDTO userDTO);

    public int deleteUser(String id,String pw);
    
    public boolean loginUser(String id,String pw);
    
    public UserDTO userInfo(String id,String pw);

	int updateUserHour(String id, int hour, boolean deduct);
       
}