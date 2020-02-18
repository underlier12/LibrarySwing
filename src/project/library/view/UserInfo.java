package project.library.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.library.Library;
import project.library.dao.UserDAO;
import project.library.dao.UserDAOImpl;
import project.library.dto.UserDTO;

import javax.swing.JLabel;
import java.awt.Font;

public class UserInfo extends JFrame {

	private JPanel contentPane;
	private static UserDAO userDAO;

	public UserInfo() {
		initialize();
	}
	
	private void initialize() {
		
		userDAO = UserDAOImpl.getInstance();
		UserDTO userDTO = null;
		userDTO = Library.sessionUserDTO;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel info = new JLabel("Information");
		info.setFont(new Font("ÈÞ¸Õ¸ðÀ½T", Font.PLAIN, 24));
		info.setBounds(134, 33, 132, 45);
		contentPane.add(info);
		
		JLabel label = new JLabel("HCL Library");
		label.setFont(new Font("ÈÞ¸Õ¸ðÀ½T", Font.PLAIN, 24));
		label.setBounds(142, 418, 139, 37);
		contentPane.add(label);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("ÈÞ¸Õ¸ðÀ½T", Font.PLAIN, 23));
		lblId.setBounds(52, 101, 63, 44);
		contentPane.add(lblId);
		
		JLabel lblPw = new JLabel("PW:");
		lblPw.setFont(new Font("ÈÞ¸Õ¸ðÀ½T", Font.PLAIN, 23));
		lblPw.setBounds(52, 168, 63, 44);
		contentPane.add(lblPw);
		
		JLabel lblName = new JLabel("NAME:");
		lblName.setFont(new Font("ÈÞ¸Õ¸ðÀ½T", Font.PLAIN, 23));
		lblName.setBounds(52, 231, 87, 44);
		contentPane.add(lblName);
		
		JLabel lblTel = new JLabel("TEL:");
		lblTel.setFont(new Font("ÈÞ¸Õ¸ðÀ½T", Font.PLAIN, 23));
		lblTel.setBounds(52, 295, 63, 44);
		contentPane.add(lblTel);
		
		JLabel lblHour = new JLabel("HOUR:");
		lblHour.setFont(new Font("ÈÞ¸Õ¸ðÀ½T", Font.PLAIN, 23));
		lblHour.setBounds(52, 360, 73, 44);
		contentPane.add(lblHour);
		
		JLabel userId = new JLabel();
		String idStr = userDTO.getId();
		userId.setFont(new Font("ÈÞ¸Õ¸ðÀ½T", Font.PLAIN, 15));
		userId.setText(idStr);
		userId.setBounds(182, 101, 118, 44);
		contentPane.add(userId);
		
		JLabel userPw = new JLabel();
		String pwStr = userDTO.getPw();
		userPw.setFont(new Font("ÈÞ¸Õ¸ðÀ½T", Font.PLAIN, 15));
		userPw.setText(pwStr);
		userPw.setBounds(182, 168, 109, 44);
		contentPane.add(userPw);
		
		JLabel userName = new JLabel();
		String nameStr = userDTO.getName();
		userName.setFont(new Font("ÈÞ¸Õ¸ðÀ½T", Font.PLAIN, 15));
		userName.setText(nameStr);
		userName.setBounds(182, 241, 99, 34);
		contentPane.add(userName);
		
		JLabel userTel = new JLabel();
		String telStr = userDTO.getTel();
		userTel.setText(telStr);
		userTel.setFont(new Font("ÈÞ¸Õ¸ðÀ½T", Font.PLAIN, 15));
		userTel.setBounds(182, 295, 84, 44);
		contentPane.add(userTel);
		
		JLabel userHour = new JLabel();
		int hour = userDTO.getHour();
		String hourStr = Integer.toString(hour);
		userHour.setFont(new Font("ÈÞ¸Õ¸ðÀ½T", Font.PLAIN, 15));
		userHour.setText(hourStr);
		userHour.setBounds(182, 360, 84, 48);
		contentPane.add(userHour);
	}

}
