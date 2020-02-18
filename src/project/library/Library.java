package project.library;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import project.library.dto.UserDTO;
import project.library.view.Login;
import project.library.view.MyPageForm;
import project.library.view.PayDialog;

public class Library {
	
	public static UserDTO sessionUserDTO = null;
	public static boolean loginCheck = false;
	
	public static JTextField txtId;
	public static JTextField txtSeat;
	public static JTextField txtRemainTime;

	private JFrame frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Library window = new Library();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Library() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1112, 733);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
				
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 25, 820, 659);
		frame.getContentPane().add(tabbedPane);
		
		JPanel pnl1F = new JPanel();
		pnl1F.setToolTipText("1");
		tabbedPane.addTab("1 층", null, pnl1F, null);
		pnl1F.setLayout(null);
		
		SeatBtn seatBtn1 = new SeatBtn();
		seatBtn1.SetSeats(pnl1F, 15); 
		
		JPanel pnl2F = new JPanel();
		pnl2F.setToolTipText("2");
		tabbedPane.addTab("2 층", null, pnl2F, null);
		pnl2F.setLayout(null);
		
		SeatBtn seatBtn2 = new SeatBtn();
		seatBtn2.SetSeats(pnl2F, 15);
		
		JButton btnLogin = new JButton("로그인");
		btnLogin.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		btnLogin.setBounds(844, 51, 240, 59);
		frame.getContentPane().add(btnLogin);
		
		JButton btnLogout = new JButton("로그아웃");
		btnLogout.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				logout();
			}
		});
		btnLogout.setBounds(844, 120, 240, 59);
		frame.getContentPane().add(btnLogout);
		
		JButton btnPay = new JButton("결제");
		btnPay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pay();
			}
		});
		btnPay.setBounds(844, 189, 240, 59);
		frame.getContentPane().add(btnPay);
		
		JButton btnMypage = new JButton("마이페이지");
		btnMypage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mypage();
			}
		});
		btnMypage.setBounds(844, 258, 240, 59);
		frame.getContentPane().add(btnMypage);

		JLabel lblId = new JLabel("현재 아이디");
		lblId.setBounds(844, 405, 89, 32);
		frame.getContentPane().add(lblId);
		
		JPanel pnlId = new JPanel();
		pnlId.setBounds(945, 405, 139, 32);
		frame.getContentPane().add(pnlId);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		pnlId.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblSeat = new JLabel("현재 좌석");
		lblSeat.setBounds(844, 463, 89, 32);
		frame.getContentPane().add(lblSeat);
		
		JPanel pnlSeat = new JPanel();
		pnlSeat.setBounds(945, 463, 139, 32);
		frame.getContentPane().add(pnlSeat);
		
		txtSeat = new JTextField();
		txtSeat.setEditable(false);
		txtSeat.setColumns(10);
		pnlSeat.add(txtSeat);
		
		JLabel label = new JLabel("남은 시간");
		label.setBounds(844, 522, 89, 32);
		frame.getContentPane().add(label);
		
		JPanel panel = new JPanel();
		panel.setBounds(945, 522, 139, 32);
		frame.getContentPane().add(panel);
		
		txtRemainTime = new JTextField();
		txtRemainTime.setEditable(false);
		txtRemainTime.setColumns(10);
		panel.add(txtRemainTime);
	
	}

	protected void login() {
		if (!loginCheck) {
			Login login = new Login();
			login.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "이미 로그인 된 상태입니다");
		}
	}
	
	protected void logout() {
		sessionUserDTO = null;
		JOptionPane.showMessageDialog(null, "로그 아웃 되었습니다");
		loginCheck = false;
		
		txtId.setText(" ");
		txtId.repaint();
		txtSeat.setText(" ");
		txtSeat.repaint();
		txtRemainTime.setText(" ");
		txtRemainTime.repaint();
		
	}
	
	protected void pay() {
		if (loginCheck) {
			PayDialog pay = new PayDialog();
			pay.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			pay.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "로그인이 필요합니다");
		}
	}

	protected void mypage() {
		if (loginCheck) {
			MyPageForm pageForm = new MyPageForm();
			pageForm.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "로그인이 필요합니다");
		}
	}
}

