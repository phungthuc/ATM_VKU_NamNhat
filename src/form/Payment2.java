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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import helper.DatabaseHelper;

public class Payment2 implements ActionListener{
	private JFrame jf = new JFrame("Ngân hàng TPBank");
	private JLabel jlb = new JLabel("Thanh toán hóa đơn tiền điện");
	private JLabel jlb1 = new JLabel("Thông tin khách hàng");
	private JTable jtb = new JTable();
	private JScrollPane jsp = new JScrollPane(jtb);
	private DefaultTableModel dftm = (DefaultTableModel)jtb.getModel();
	private JLabel jlb2 = new JLabel("Tổng số tiền quý khách phải thanh toán");
	private JTextField jtf = new JTextField();
	private JButton jbt = new JButton("Thanh toán");
	private ImageIcon image = new ImageIcon("tpbank.jpg");
	
	public Payment2() {
		jf.setLocation(440, 200);
		jf.setLayout(null);
		
		jf.setIconImage(image.getImage());
		
		jf.getContentPane().setBackground(new Color(128,0,128));
		jf.getContentPane().setBackground(new Color(128,0,128));
		jlb.setForeground(new Color(0,255,0));
		jlb1.setForeground(new Color(0,255,0));
		jlb2.setForeground(new Color(0,255,0));
		jbt.setForeground(new Color(255,0,0));
		
		jlb.setBounds(190, 30, 200, 30);
		jlb1.setBounds(210, 70, 150, 30);
		jsp.setBounds(30, 120, 530, 80);
		jlb2.setBounds(30, 210, 250, 30);
		jtf.setBounds(290, 210, 200, 30);
		jbt.setBounds(200, 280, 120, 30);
		
		Container cont = jf.getContentPane();
		cont.add(jlb);
		cont.add(jlb1);
		cont.add(jsp);
		cont.add(jlb2);
		cont.add(jtf);
		cont.add(jbt);
		
		jf.setSize(600, 400);
		jf.setVisible(true);
		
		try {
			Connection conn = DatabaseHelper.openConnection();
			String sql = "select * from Data2 where makhachhang = '" + Payment.code + "'";
			String col_num[] = {"Mã khách hàng", "Tên khách hàng", "Địa chỉ", "Số điện thoại", "Tổng tiền"};
			String data[] = new String[5];
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			dftm.setColumnIdentifiers(col_num);
			while(rs.next()) {
				data[0] = rs.getString("makhachhang");
				data[1] = rs.getString("ten");
				data[2] = rs.getString("diachi");
				data[3] = rs.getString("sodienthoai");
				data[4] = rs.getString("tongtien");
				dftm.addRow(data);
			}
			jtf.setText(data[4] + " VND");
			int total = Integer.parseInt(data[4]);
			int remain;
			remain = Output.surplus - total;
			jbt.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if(jbt == e.getSource()) {
						try {
							Connection conn1 = DatabaseHelper.openConnection();
							Connection conn2 = DatabaseHelper.openConnection();
							String sql1 = "update Data2 set tongtien = '0' where makhachhang = '" + Payment.code + "'";
							String sql2 = "update Data set sodu = '" + remain + "' where sotaikhoan = '" + Login.stk + "'";
							System.out.println(remain + " " + Output.surplus + " " + total);
							PreparedStatement pstm = conn1.prepareStatement(sql1);
							PreparedStatement pstm1 = conn2.prepareStatement(sql2);
							int rs1 = pstm.executeUpdate();
							int rs2 = pstm1.executeUpdate();
							JOptionPane.showMessageDialog(cont, "Thanh toán thành công!");
							conn1.close();
							conn2.close();
							pstm.close();
							pstm1.close();
						} catch (Exception e2) {
							System.out.println(e2);
						}
					}
				}
				
			});
			conn.close();
			stmt.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
		

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
