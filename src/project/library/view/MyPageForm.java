package project.library.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.library.Library;
import project.library.dao.UserDAO;
import project.library.dao.UserDAOImpl;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPageForm extends JFrame {

	private JPanel contentPane;
	private static UserDAO userDAO;

	public MyPageForm() {
		initialize();
	}

	private void initialize() {

		userDAO = UserDAOImpl.getInstance();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("My Page");
		lblNewLabel.setFont(new Font("휴먼모음T", Font.PLAIN, 24));
		lblNewLabel.setBounds(149, 22, 104, 45);
		contentPane.add(lblNewLabel);

		JButton info = new JButton("INFO");
		info.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UserInfo userInfo = new UserInfo();
				userInfo.setVisible(true);

			}
		});
		info.setBounds(26, 306, 97, 56);
		contentPane.add(info);

		JButton modify = new JButton("수정");
		modify.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UserModify userModify = new UserModify();
				userModify.setVisible(true);

			}
		});
		modify.setBounds(156, 306, 97, 56);
		contentPane.add(modify);

		JButton delete = new JButton("탈퇴");
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				userDelete();
			}
		});
		delete.setBounds(288, 306, 97, 56);
		contentPane.add(delete);

	}


	public void userDelete() {

		int result =
//				JOptionPane.showConfirmDialog(null, "정말 탈퇴 하시겠습니까?");
				JOptionPane.showConfirmDialog(null, "정말 탈퇴 하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {

			userDAO.deleteUser(Library.sessionUserDTO.getId(), Library.sessionUserDTO.getPw());
			JOptionPane.showMessageDialog(null, "삭제 완료되었습니다");
			setVisible(false);
			Login.Panel.setVisible(true);
		} else if (result == JOptionPane.NO_OPTION) {
			JOptionPane.showMessageDialog(null, "취소 되었습니다");
		}

	}

}
