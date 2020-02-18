package project.library.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import project.library.Library;
import project.library.dao.UserDAO;
import project.library.dao.UserDAOImpl;
import project.library.dto.UserDTO;

public class UserModify extends JFrame {

	private JPanel modifyPanel;
	private JTextField pwField;
	private JTextField nameField;
	private JTextField telField;
	
	private UserDAO userDAO;

	public UserModify() {
		initialize();
	}


	private void initialize() {
		// JFrame.EXIT_ON_CLOSE : 프로그램 전체 종료
		// JFrame.DISPOSE_ON_CLOSE : 현재 창만 종료
		
		userDAO = UserDAOImpl.getInstance();
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 420, 548);
		modifyPanel = new JPanel();
		modifyPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(modifyPanel);
		modifyPanel.setLayout(null);
		
		JLabel label = new JLabel("HCL Library");
		label.setFont(new Font("휴먼모음T", Font.PLAIN, 24));
		label.setBounds(125, 448, 139, 37);
		modifyPanel.add(label);
		
		JLabel label_1 = new JLabel("ID:");
		label_1.setFont(new Font("휴먼모음T", Font.PLAIN, 23));
		label_1.setBounds(45, 126, 63, 44);
		modifyPanel.add(label_1);
		
		JLabel lblPw = new JLabel("PW:");
		lblPw.setFont(new Font("휴먼모음T", Font.PLAIN, 23));
		lblPw.setBounds(45, 197, 63, 44);
		modifyPanel.add(lblPw);
		
		JLabel lblName = new JLabel("NAME:");
		lblName.setFont(new Font("휴먼모음T", Font.PLAIN, 23));
		lblName.setBounds(27, 271, 81, 44);
		modifyPanel.add(lblName);
		
		JLabel lblTel = new JLabel("TEL:");
		lblTel.setFont(new Font("휴먼모음T", Font.PLAIN, 23));
		lblTel.setBounds(45, 333, 63, 44);
		modifyPanel.add(lblTel);
		
		JLabel title = new JLabel("회원 수정");
		title.setFont(new Font("휴먼모음T", Font.PLAIN, 23));;
		title.setBounds(132, 37, 132, 50);
		modifyPanel.add(title);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("휴먼모음T", Font.PLAIN, 23));
		String id = Library.sessionUserDTO.getId();
		lblNewLabel.setText(id);
		lblNewLabel.setBounds(125, 126, 158, 33);
		modifyPanel.add(lblNewLabel);
		
		pwField = new JTextField();
		pwField.setColumns(10);
		pwField.setBounds(125, 204, 158, 37);
		modifyPanel.add(pwField);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(125, 278, 158, 37);
		modifyPanel.add(nameField);
		
		telField = new JTextField();
		telField.setColumns(10);
		telField.setBounds(125, 340, 158, 37);
		modifyPanel.add(telField);
		
		JButton modifyBtn = new JButton("완료");
		modifyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateUser();
			}
		});
		modifyBtn.setFont(new Font("휴먼모음T", Font.PLAIN, 13));
		modifyBtn.setBounds(148, 400, 97, 23);
		modifyPanel.add(modifyBtn);
		
		
	} 
	
	public void updateUser() {
		
		String id = Library.sessionUserDTO.getId();
		String pw = pwField.getText();
		String name = nameField.getText();
		String phone = telField.getText();
		

		UserDTO userDTO = new UserDTO(id, pw, name, phone);
		int result = userDAO.updateUser(userDTO);
		
		if (id.equals("")) {
			JOptionPane.showMessageDialog(null, "아이디를 입력해주세요");
			return;
		} else if (pw.equals("")) {
			JOptionPane.showMessageDialog(null, "비밀 번호를 입력해주세요");
			return;
		} else if (name.equals("")) {
			JOptionPane.showMessageDialog(null, "이름을 입력해주세요");
			return;
		} else if (phone.equals("")) {
			JOptionPane.showMessageDialog(null, "연락처를 입력해주세요");
			return;
		}
		
		if (result > 0) {
			JOptionPane.showMessageDialog(null, "수정 완료");
			setVisible(false);
		} else {
			JOptionPane.showMessageDialog(null, "수정 실패");
		}
		
	}
}
