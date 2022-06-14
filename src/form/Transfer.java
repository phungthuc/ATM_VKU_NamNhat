package form;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import helper.DatabaseHelper;

public class Transfer implements ActionListener{
	private JFrame jf = new JFrame("Ngân hàng TPBank");
	private JLabel jlb = new JLabel("Xin vui lòng nhập số tài khoản nhận tiền!");
	private JLabel jlb1 = new JLabel("Số tài khoản");
	private JTextField jtf = new JTextField();
	private JButton jbt = new JButton("Kiểm tra");
	private JLabel jlb2 = new JLabel("Tên chủ tài khoản");
	private JTextField jtf1 = new JTextField();
	private JLabel jlb3 = new JLabel("Nhập số tiền");
	private JTextField jtf2 = new JTextField();
	private JButton jbt1 = new JButton("Chuyển");
	private ImageIcon image = new ImageIcon("tpbank.jpg");
	
	public Transfer() {
		jf.setLocation(600,250);
		jf.setLayout(null);
		
		jf.setIconImage(image.getImage());
		
		jf.getContentPane().setBackground(new Color(128,0,128));
		jlb.setForeground(new Color(192,192,192));
		jlb1.setForeground(new Color(192,192,192));
		jlb2.setForeground(new Color(192,192,192));
		jlb3.setForeground(new Color(192,192,192));
		jbt.setForeground(new Color(255,0,0));
		jbt1.setForeground(new Color(255,0,0));
		
		
		jlb.setBounds(70, 20, 250, 30);
		jlb1.setBounds(30, 70, 90, 30);
		jtf.setBounds(140, 70, 120, 30);
		jbt.setBounds(270, 70, 100, 30);
		jlb2.setBounds(30, 110, 120, 30);
		jtf1.setBounds(140, 110, 120, 30);
		jlb3.setBounds(30, 170, 120, 30);
		jtf2.setBounds(140, 170, 120, 30);
		jbt1.setBounds(150, 230, 100, 30);
		
		Container cont = jf.getContentPane();
		cont.add(jlb);
		cont.add(jlb1);
		cont.add(jtf);
		cont.add(jbt);
		cont.add(jlb2);
		cont.add(jtf1);
		cont.add(jlb3);
		cont.add(jtf2);
		cont.add(jbt1);
		
		jf.setSize(400,330); 
		jf.setVisible(true);
		
		jbt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn = DatabaseHelper.openConnection();
					String sql = "select * from Data where sotaikhoan = ?";
					PreparedStatement pstm = conn.prepareStatement(sql);
					pstm.setString(1, jtf.getText());
					ResultSet rs = pstm.executeQuery();
					String transfer = jtf.getText();
					if(jbt == e.getSource()) {
						if(rs.next() && transfer != Login.stk) {
							Connection conn1 = DatabaseHelper.openConnection();
							PreparedStatement pstm1 = conn1.prepareStatement(sql);
							pstm1.setString(1, jtf.getText());
							ResultSet rs1 = pstm1.executeQuery();
							while(rs1.next()) {
								jtf1.setText(rs1.getString("ten"));
							}
							conn1.close();
							pstm1.close();
							rs1.close();
						}else {
							JOptionPane.showMessageDialog(cont, "Số tài khoản không chính xác!");
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
		jbt1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(jbt1 == e.getSource()) {
					try {
						Connection conn2 = DatabaseHelper.openConnection();
						String sql1 = "select sodu from Data where sotaikhoan = '" + Login.stk + "'";
						int surplus = 0;
						Statement stmt = conn2.createStatement();
						ResultSet rs2 = stmt.executeQuery(sql1);
						while(rs2.next()) {
							surplus = rs2.getInt("sodu");
						}
						int transfer1 = Integer.parseInt(jtf2.getText());
						if(transfer1 > surplus) {
							JOptionPane.showMessageDialog(cont, "Số tiền bạn nhập quá số dư của tài khoản!");
						}else {
							try {
								int remain = surplus - transfer1;
								Connection conn3 = DatabaseHelper.openConnection();
								String sql2 = "update Data set sodu = ? where sotaikhoan = '" + Login.stk + "'";
								String sql3 = "select * from Data where ten = '" + jtf1.getText() + "'";
								String sql4 = "update Data set sodu = ? where sotaikhoan = '" + jtf.getText() + "'";
								Statement stmt1 = conn3.createStatement();
								ResultSet rs3 = stmt1.executeQuery(sql3);
								int surplus1 = 0;
								while(rs3.next()) {
									surplus1 = rs3.getInt("sodu");
								}
								int remain1;
								remain1 = surplus1 + transfer1;
								PreparedStatement pstm2 = conn3.prepareStatement(sql2);
								PreparedStatement pstm3 = conn3.prepareStatement(sql4);
								pstm2.setInt(1, remain);
								pstm3.setInt(1, remain1);
								int rs4 = pstm2.executeUpdate();
								int rs5 = pstm3.executeUpdate();
								JOptionPane.showMessageDialog(cont, "Chuyển tiền thành công!");
								conn3.close();
								pstm2.close();
								stmt1.close();
								pstm3.close();
								rs3.close();
								
							} catch (Exception e2) {
								System.out.println(e2);
							}
						}
						conn2.close();
						stmt.close();
						rs2.close();
					} catch (Exception e2) {
						System.out.println(e2);
					}
				}
			}
			
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
