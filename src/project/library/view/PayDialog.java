package project.library.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import oracle.net.ns.SessionAtts;
import project.library.Library;
import project.library.dao.CostDAO;
import project.library.dao.CostDAOImpl;
import project.library.dao.UserDAO;
import project.library.dao.UserDAOImpl;
import project.library.dto.CostDTO;
import project.library.dto.UserDTO;

public class PayDialog extends JDialog {
	
	private CostDAO cdao;
	private UserDAO udao;

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PayDialog() {
		cdao = CostDAOImpl.getInstance();
		udao = UserDAOImpl.getInstance();
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 434, 228);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JButton btn60 = new JButton("60분  1000원");
		btn60.addActionListener((e)->{
			btnClicked(btn60);
		});
		btn60.setBounds(12, 10, 185, 60);
		contentPanel.add(btn60);
		
		JButton btn120 = new JButton("120분  1800원");
		btn120.addActionListener((e)->{
			btnClicked(btn120);
		});
		btn120.setBounds(237, 10, 185, 60);
		contentPanel.add(btn120);
		
		JButton btn180 = new JButton("180분 2500원");
		btn180.addActionListener((e)->{
			btnClicked(btn180);
		});
		btn180.setBounds(12, 80, 185, 60);
		contentPanel.add(btn180);
		
		JButton btn240 = new JButton("240분  3300원");
		btn240.addActionListener((e)->{
			btnClicked(btn240);
		});
		btn240.setBounds(237, 80, 185, 60);
		contentPanel.add(btn240);
		
		JButton btn300 = new JButton("300분  4200원");
		btn300.addActionListener((e)->{
			btnClicked(btn300);
		});
		btn300.setBounds(12, 150, 185, 60);
		contentPanel.add(btn300);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 228, 434, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);

			{
				JButton closeButton = new JButton("종  료");
				closeButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				buttonPane.add(closeButton);
			}
		}
	}

	private void btnClicked(JButton btn) {
		String btnText = btn.getText();
		String priceText = btnText.substring(btnText.length()-5, btnText.length()-1);
		String id = Library.sessionUserDTO.getId();
		int price = Integer.parseInt(priceText);
		int costHour;
		
		CostDTO cdto =cdao.selectCost(price);
		costHour = cdto.getHour();
		udao.updateUserHour(id, costHour, false);
		
		UserDTO udto = udao.selectUser(id);
		Library.sessionUserDTO = udto;
		
		Library.txtRemainTime.setText(Integer.toString(Library.sessionUserDTO.getHour()) + "분");
		Library.txtRemainTime.repaint();
		
		System.out.println(costHour +"분(시간)이 충전되었습니다.");
	}	
	
}
