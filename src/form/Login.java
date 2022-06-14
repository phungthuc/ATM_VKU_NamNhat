package form;

import java.awt.Color;

import java.awt.Container;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import helper.DatabaseHelper;

public class Login implements ActionListener{
	private JFrame jfLogin = new JFrame("Ngân hàng TPBank");
	private JLabel jlLoginStk = new JLabel("Số tài khoản");
	private JLabel jlLoginMk = new JLabel("Mật khẩu");
	private JTextField jtfStk = new JTextField();
	private JPasswordField jpfMk = new JPasswordField();
	private JButton jbtLogin = new JButton("Đăng nhập");
	private ImageIcon image = new ImageIcon("tpbank.jpg");
	
	public static String stk;
	
	public Login() {
		jfLogin.setLocation(550, 300);
		jfLogin.setLayout(null);
		
		jfLogin.setIconImage(image.getImage());
		
		jfLogin.getContentPane().setBackground(new Color(128,0,128));
		jlLoginStk.setForeground(new Color(0,255,0));
		jlLoginMk.setForeground(new Color(0,255,0));
		
		jlLoginStk.setBounds(20, 40, 80, 20);
		jlLoginMk.setBounds(20, 100, 80, 20);
		jtfStk.setBounds(120, 40, 130, 20);
		jpfMk.setBounds(120, 100, 130, 20);
		jbtLogin.setBounds(100, 150, 100, 30);

		jbtLogin.setBackground(new Color(0,255,0));
		
		
		Container cont = jfLogin.getContentPane();
		cont.add(jlLoginStk);
		cont.add(jlLoginMk);
		cont.add(jtfStk);
		cont.add(jpfMk);
		cont.add(jbtLogin);
		
		jfLogin.setSize(300, 230);
		jfLogin.setVisible(true);
		
		jbtLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					stk = jtfStk.getText();
					Connection conn = DatabaseHelper.openConnection();
					String sql = "select * from Data where sotaikhoan = ? and matkhau = ?";
					PreparedStatement pstm = conn.prepareStatement(sql);
					pstm.setString(1, stk);
					pstm.setString(2, jpfMk.getText());
					ResultSet rs = pstm.executeQuery();
					if(jbtLogin == e.getSource()) {
						if(rs.next()) {
							AtmManagerForm aMF = new AtmManagerForm();
							aMF.form();
							jtfStk.setText("");
							jpfMk.setText("");
						}else {
							JOptionPane.showMessageDialog(cont, "Số tài khoản hoặc mật khẩu không chính xác, mời bạn nhập lại!");
						}
					}
					conn.close();
					pstm.close();
					rs.close();
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
			
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

