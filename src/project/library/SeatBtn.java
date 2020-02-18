package project.library;

import java.awt.Color;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import project.library.dao.DeskDAO;
import project.library.dao.DeskDAOImpl;
import project.library.dao.UserDAO;
import project.library.dao.UserDAOImpl;
import project.library.dto.DeskDTO;
import project.library.dto.UserDTO;

public class SeatBtn extends JButton{
	
	private UserDAO udao;
	private DeskDAO ddao;
	
	private Color gray = new Color(240, 240, 240);
	private Color darkGray = new Color(190, 190, 190);
	
	private int startPointX = 10;
	private int startPointY = 10;
	private int defaultWidth = 100;
	private int defaultHeight = 100;
	
	private int gapX = 175;
	private int gapY = 250;
	
	public void SetSeats(JPanel pnl, int seatNumber) {
		
		udao = UserDAOImpl.getInstance();
		ddao = DeskDAOImpl.getInstance();
		
		for(int i = 0; i < seatNumber; i++) {
			
			int x;
			int y;

			String btnName;
			String floor = pnl.getToolTipText();
			
			int rowIncrement = i % 5;
			int columnIncrement = i / 5;
			System.out.println("row : " + rowIncrement + " column : " + columnIncrement);
			
			x = startPointX + gapX * rowIncrement;
			y = startPointY + gapY * columnIncrement;
			
			if(i < 9) {
				btnName = floor + "0" + Integer.toString(i+1);				
			} else {
				btnName = floor + Integer.toString(i+1);
			}
			System.out.println("btnName : " + btnName);
			
			JButton btn = new JButton(btnName);
			checkVacant(btn);
			btn.addActionListener((e)->{
				btnClicked(btn);
			});
			btn.setBounds(x, y, defaultWidth, defaultHeight);
			pnl.add(btn);
		}
	}
	
	private void checkVacant(JButton btn) {
		int index = Integer.parseInt(btn.getText());
		
		System.out.println("index : " + index);
		
		DeskDTO ddto = ddao.selectDesk(index);
		int vacant = ddto.getVacant();
		
		System.out.println(vacant);
		
		if(vacant == 1) {
			btn.setBackground(gray);
		} else {
			btn.setBackground(darkGray);
		}
		
	}
	
	private void btnClicked(JButton btn) {
		
		if(!isLoggedin(btn)) return;
		
		if (btn.getBackground() == gray) {
			
			occupy(btn);
			
		} else {
			
			vacant(btn);
		}
	}

	private boolean isLoggedin(JButton btn) {
		if(Library.sessionUserDTO == null) {
			return false;
		}
		return true;
	}

	private void occupy(JButton btn) {
		
		int isOcc = isOccupiedUser(Library.sessionUserDTO.getId());
		
		if (isOcc == 1) {
			System.out.println("이미 좌석을 사용중인 회원입니다.");
			return;
		}
		
		int result = JOptionPane.showConfirmDialog(null, 
				"사용하시겠습니까",
				"네",
				JOptionPane.YES_NO_OPTION);
		
		if (result == 0) {
			int number = Integer.parseInt(btn.getText());			
			Timestamp startAt = new Timestamp(new Date().getTime());

			DeskDTO ddto = new DeskDTO(number, 0, Library.sessionUserDTO.getId(), startAt);
			
			int finalResult = ((DeskDAOImpl)ddao).updateDesk(number, ddto);
			btn.setBackground(darkGray);

			Library.txtSeat.setText(btn.getText());
			Library.txtSeat.repaint();
			
			if(finalResult == 0) {
				System.out.println("좌석 " + btn.getText() + "번을 예약했습니다.");
			} else {
				System.out.println("좌석 예약에 실패했습니다.");
			}
			
			
		}
		
	}

	private int isOccupiedUser(String id) {
		int result = 0;
		String occupied;
		ArrayList<DeskDTO> list = ddao.selectAllDesk();
		
		for (int i = 0; i < list.size(); i++) {
			occupied = list.get(i).getOccupiedId();

			if (Library.sessionUserDTO.getId().equals(occupied)) {
				result = 1;
				break;
			}
		}
		return result;
	}

	private void vacant(JButton btn) {
		
		int index = Integer.parseInt(btn.getText());
		DeskDTO ddto = ddao.selectDesk(index);
		String occupiedId = ddto.getOccupiedId();
		System.out.println(occupiedId);
		
		if (occupiedId.equals(Library.sessionUserDTO.getId())) {
			int result = JOptionPane.showConfirmDialog(null, 
					"사용 종료하시겠습니까",
					"네",
					JOptionPane.YES_NO_OPTION);
			
			if(result == 0) {
				updateHour(index, ddto);
				
				ddto = new DeskDTO(index, 1, null, null);
				int finalResult = ((DeskDAOImpl)ddao).updateDesk(index, ddto);
				
				if(finalResult == 0) {
					System.out.println("좌석 " + btn.getText() + "번 사용 종료합니다.");
				} else {
					System.out.println("좌석 사용 종료에 실패했습니다.");
				}
				btn.setBackground(gray);
			}
		}
		
	}


	private void updateHour(int index, DeskDTO ddto) {
		Timestamp startTime = ddto.getStartAt();
		System.out.println("startTime : " + startTime);
		
		Date endTime = new Date();
		long timeDifference = endTime.getTime() - startTime.getTime();
		
		int deductionTime = (int) timeDifference/(60*1000);
		System.out.println("deductionTime : " + deductionTime);
		
		String id = ddto.getOccupiedId();
		UserDTO udto = udao.selectUser(id);
		int recordedTime = udto.getHour();
		int updatedTime = recordedTime - deductionTime;
		
		int result = 0;
		
		if (updatedTime >= 0) {
			result = udao.updateUserHour(id, updatedTime, true);			
		} else {
			result = udao.updateUserHour(id, 0, true);
		}
		
		if (result == 1) {
			System.out.println("시간 차감이 완료되었습니다.");
		} else {
			System.out.println("시간 차감 오류");
		}
		
		udto = udao.selectUser(id);
		Library.sessionUserDTO = udto;
		
		Library.txtSeat.setText(" ");
		Library.txtSeat.repaint();
		Library.txtRemainTime.setText(Integer.toString(Library.sessionUserDTO.getHour()) + "분");
		Library.txtRemainTime.repaint();
		
	}
	
}
