package project.library.dto;

public class UserDTO {
    private String id;
    private String pw;
    private String name;
    private String tel;
    private int hour;
  
	public UserDTO(String id, String pw, String name, String tel) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.tel = tel;
	}
    
	public UserDTO(String id, String pw, String name, String tel, int hour) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.tel = tel;
		this.hour = hour;
	}
	
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	@Override
    public String toString(){
        return "User : " + "[" + id + "," + name
                + "," + tel + "," + hour +"]";
    }

}