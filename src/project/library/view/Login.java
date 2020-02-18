package project.library.view;


import project.library.Library;
import project.library.dao.DeskDAO;
import project.library.dao.DeskDAOImpl;
import project.library.dao.UserDAO;
import project.library.dao.UserDAOImpl;
import project.library.dto.DeskDTO;
import project.library.dto.UserDTO;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Login extends JFrame {

	public static JPanel Panel;
	private JTextField idField;
	private JPasswordField pwField;
	
	private UserDAO userDAO;
	private DeskDAO deskDAO;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the Panel.
	 */
	private void initialize() {
		
		userDAO = UserDAOImpl.getInstance();
		deskDAO = DeskDAOImpl.getInstance();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 417, 527);
		Panel = new JPanel();
		setContentPane(Panel);
		//Panel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("HCL Library");
		lblNewLabel.setFont(new Font("휴먼모음T", Font.PLAIN, 24));
		lblNewLabel.setBounds(126, 441, 139, 37);
		Panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Login");
		lblNewLabel_1.setFont(new Font("휴먼모음T", Font.PLAIN, 23));
		lblNewLabel_1.setBounds(149, 33, 132, 50);
		Panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("ID:");
		lblNewLabel_2.setFont(new Font("휴먼모음T", Font.PLAIN, 23));
		lblNewLabel_2.setBounds(50, 132, 63, 44);
		Panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("PW:");
		lblNewLabel_3.setFont(new Font("휴먼모음T", Font.PLAIN, 23));
		lblNewLabel_3.setBounds(50, 269, 57, 37);
		Panel.add(lblNewLabel_3);
		
		idField = new JTextField();
		idField.setBounds(143, 139, 158, 37);
		Panel.add(idField);
		idField.setColumns(10);
		
		pwField = new JPasswordField();
		pwField.setColumns(10);
		pwField.setBounds(143, 269, 158, 37);
		Panel.add(pwField);
		
		JButton login = new JButton("\uB85C\uADF8\uC778");
		login.setFont(new Font("휴먼모음T", Font.PLAIN, 12));
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				login();
				
			}
		});
		login.setBounds(50, 372, 97, 23);
		Panel.add(login);
		
		JButton signUp = new JButton("\uD68C\uC6D0\uAC00\uC785");
		signUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SignUp signUp = new SignUp();
				signUp.setVisible(true);
			}
		});
		signUp.setFont(new Font("휴먼모음T", Font.PLAIN, 12));
		signUp.setBounds(204, 372, 97, 23);
		Panel.add(signUp);
	
	}
	
	public void login() {
		String id = idField.getText();
		String pw = pwField.getText();
		
		if(id.equals("")) {
			JOptionPane.showMessageDialog(null,"아이디를 입력해주세요");
			return;
		}else if(pw.equals("")) {
			JOptionPane.showMessageDialog(null,"비밀 번호를 입력해주세요");
			return;
		}
		boolean result = userDAO.loginUser(id, pw);
		UserDTO userDTO = userDAO.userInfo(id,pw);
		Library.sessionUserDTO = userDTO;
		Library.loginCheck = true;
		
		ArrayList<DeskDTO> list = ((DeskDAOImpl) deskDAO).selectAllDesk();
		String occupied;
		int seat;
		String stringSeat = null;
		
		for (int i = 0; i < list.size(); i++) {
			occupied = list.get(i).getOccupiedId();
			if (id.equals(occupied)) {
				System.out.println(occupied);
				seat = list.get(i).getNumber();
				stringSeat = Integer.toString(seat);
				
				break;
			}
		}
		
		if(result) {
			JOptionPane.showMessageDialog(null, "Login 성공!");
			
			Library.txtId.setText(id);
			Library.txtId.repaint();
			Library.txtSeat.setText(stringSeat);
			Library.txtSeat.repaint();
			Library.txtRemainTime.setText(Integer.toString(Library.sessionUserDTO.getHour()) + "분");
			Library.txtRemainTime.repaint();
			
			setVisible(false);
		}else {
			JOptionPane.showMessageDialog(null, "Login 실패!");
		}
		
	}
	
	public void loginCheck() {
		if(!Library.loginCheck) {
			JOptionPane.showMessageDialog(null, "로그인이 필요합니다");
		}else {
			idField.setText("");
			pwField.setText("");
		}
	}
	
	
}
