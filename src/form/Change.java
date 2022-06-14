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

public class Change implements ActionListener{
	private JFrame jf = new JFrame("Ngân hàng TPBank xin kính chào quý khách!");
	private JLabel jlbDoi = new JLabel("Đổi mật khẩu");
	private JLabel jlbMk = new JLabel("Mật khẩu cũ");
	private JPasswordField jpfMk = new JPasswordField();
	private JLabel jlbMk1 = new JLabel("Mật khẩu mới");
	private JPasswordField jpfMk1 = new JPasswordField();
	private JLabel jlbMk2 = new JLabel("Nhập lại mật khẩu");
	private JPasswordField jpfMk2 = new JPasswordField();
	private JButton jbt = new JButton("Đổi");
	private ImageIcon image = new ImageIcon("tpbank.jpg");
	
	public Change() {
		jf.setLocation(600, 250);
		jf.setLayout(null);
		
		jf.setIconImage(image.getImage());
		
		jf.getContentPane().setBackground(new Color(128,0,128));
		jlbDoi.setForeground(new Color(0,255,0));
		jlbMk1.setForeground(new Color(0,255,0));
		jlbMk2.setForeground(new Color(0,255,0));
		jlbMk.setForeground(new Color(0,255,0));
		jbt.setForeground(new Color(255,0,0));
		
		jlbDoi.setBounds(110, 20, 120, 30);
		jlbMk.setBounds(20, 60, 80, 30);
		jpfMk.setBounds(125, 60, 150, 30);
		jlbMk1.setBounds(20, 100, 80, 30);
		jpfMk1.setBounds(125, 100, 150, 30);
		jlbMk2.setBounds(20, 140, 100, 30);
		jpfMk2.setBounds(125, 140, 150, 30);
		jbt.setBounds(110, 180, 80 , 30);
		
		Container cont = jf.getContentPane();
		cont.add(jlbDoi);
		cont.add(jlbMk);
		cont.add(jpfMk);
		cont.add(jlbMk1);
		cont.add(jpfMk1);
		cont.add(jlbMk2);
		cont.add(jpfMk2);
		cont.add(jbt);
		
		jf.setSize(300, 280);
		jf.setVisible(true);
		
		jbt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "select * from Data where matkhau = ?";
					Connection conn = DatabaseHelper.openConnection();
					PreparedStatement pstm = conn.prepareStatement(sql);
					pstm.setString(1, jpfMk.getText());
					ResultSet rs = pstm.executeQuery();
					if(jbt == e.getSource()) {
						if(rs.next()) {
							if(jpfMk1.getText().equals(jpfMk2.getText())){
								String sql1 = "update Data set matkhau = ? where sotaikhoan = '" + Login.stk + "'" ;
								Connection conn1 = DatabaseHelper.openConnection();
								PreparedStatement pstm1 = conn1.prepareStatement(sql1);
								pstm1.setString(1, jpfMk2.getText());
								int rs1 = pstm1.executeUpdate();
								JOptionPane.showMessageDialog(cont, "Đổi mật khẩu thành công!");
								conn1.close();
								pstm1.close();
							}else {
								JOptionPane.showMessageDialog(cont, "Mật khẩu mới không khớp!");
							}
						}else {
							JOptionPane.showMessageDialog(cont, "Mật khẩu bạn nhập không chính xác!");
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
