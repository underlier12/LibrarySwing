package project.library.dto;

public class CostDTO {
    private int price;
    private int hour;
    
    public CostDTO() {}
    
    public CostDTO(int price, int hour) {
		super();
		this.price = price;
		this.hour = hour;
	}
    
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	
	@Override
	public String toString() {
		return "CostDTO [price=" + price + ", hour=" + hour + "]";
	}
}