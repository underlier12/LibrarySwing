package project.library.view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import project.library.dao.UserDAO;
import project.library.dao.UserDAOImpl;
import project.library.dto.UserDTO;

import javax.swing.JButton;
import java.awt.Font;

public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField idField;
	private JTextField pwField;
	private JTextField nameField;
	private JTextField telField;
	private JButton idCheck, signUp;
	private static UserDAO userDAO;

	public SignUp() {
		initialize();
	}

	private void initialize() {

		userDAO = UserDAOImpl.getInstance();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 437, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		idCheck = new JButton("Check");
		idCheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userCheck();

			}
		});
		contentPane.setLayout(null);
		idCheck.setBounds(317, 89, 92, 23);
		contentPane.add(idCheck);

		signUp = new JButton("\uAC00\uC785 \uD558\uAE30");
		signUp.setFont(new Font("휴먼모음T", Font.PLAIN, 12));
		signUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertUser();
			}
		});
		signUp.setBounds(152, 376, 97, 31);
		contentPane.add(signUp);
	
		JLabel label = new JLabel("HCL Library");
		label.setFont(new Font("휴먼모음T", Font.PLAIN, 24));
		label.setBounds(140, 417, 139, 37);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\uD68C\uC6D0 \uAC00\uC785");
		label_1.setFont(new Font("휴먼모음T", Font.PLAIN, 24));
		label_1.setBounds(152, 21, 139, 37);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("ID:");
		label_2.setFont(new Font("휴먼모음T", Font.PLAIN, 23));
		label_2.setBounds(22, 75, 63, 44);
		contentPane.add(label_2);
		
		JLabel lblPw = new JLabel("PW:");
		lblPw.setFont(new Font("휴먼모음T", Font.PLAIN, 23));
		lblPw.setBounds(22, 148, 63, 44);
		contentPane.add(lblPw);
		
		JLabel lblName = new JLabel("NAME:");
		lblName.setFont(new Font("휴먼모음T", Font.PLAIN, 23));
		lblName.setBounds(22, 226, 92, 44);
		contentPane.add(lblName);
		
		JLabel lblTel = new JLabel("TEL:");
		lblTel.setFont(new Font("휴먼모음T", Font.PLAIN, 23));
		lblTel.setBounds(22, 301, 63, 44);
		contentPane.add(lblTel);
		
		idField = new JTextField();
		idField.setColumns(10);
		idField.setBounds(124, 82, 167, 37);
		contentPane.add(idField);
		
		pwField = new JTextField();
		pwField.setColumns(10);
		pwField.setBounds(124, 155, 167, 37);
		contentPane.add(pwField);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(126, 233, 165, 37);
		contentPane.add(nameField);
		
		telField = new JTextField();
		telField.setColumns(10);
		telField.setBounds(124, 308, 167, 37);
		contentPane.add(telField);

	}

	public void insertUser() {
		String id = idField.getText();
		String pw = pwField.getText();
		String name = nameField.getText();
		String phone = telField.getText();

		if (id.equals("")) {
			JOptionPane.showMessageDialog(null, "아이디를 입력해주세요");
		} else if (pw.equals("")) {
			JOptionPane.showMessageDialog(null, "비밀 번호를 입력해주세요");
		} else if (name.equals("")) {
			JOptionPane.showMessageDialog(null, "이름을 입력해주세요");
		} else if (phone.equals("")) {
			JOptionPane.showMessageDialog(null, "연락처를 입력해주세요");
		} else {
			UserDTO userDTO = new UserDTO(id, pw, name, phone);
			int result = userDAO.insertUser(userDTO);
			
			if (result > 0) {
				JOptionPane.showMessageDialog(null, "회원 가입 Success");
				setVisible(false);
			} else {
				JOptionPane.showMessageDialog(null, "회원 가입 Fail");
			}			
		}


	}

	public void userCheck() {
		String id = idField.getText();
		boolean result = userDAO.checkUser(id);

		if (result) {
			JOptionPane.showMessageDialog(null, "사용 가능한 아이디");
		} else {
			JOptionPane.showMessageDialog(null, "이미 존재하는 아이디 입니다");
		}
	}
	
}
