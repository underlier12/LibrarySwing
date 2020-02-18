package project.library.dto;

import java.sql.Timestamp;

public class DeskDTO {
    private int number;
    private int vacant;
    private String occupiedId;
    private Timestamp startAt;
    
	public DeskDTO() {}

	public DeskDTO(int number, int vacant, String occupiedId, Timestamp startAt) {
		this.number = number;
		this.vacant = vacant;
		this.occupiedId = occupiedId;
		this.startAt = startAt;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getVacant() {
		return vacant;
	}

	public void setVacant(int vacant) {
		this.vacant = vacant;
	}

	public String getOccupiedId() {
		return occupiedId;
	}

	public void setOccupiedId(String occupiedId) {
		this.occupiedId = occupiedId;
	}

	public Timestamp getStartAt() {
		return startAt;
	}

	public void setStartAt(Timestamp startAt) {
		this.startAt = startAt;
	}
    
}