package form;

import java.awt.Color;
import java.awt.Container;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import helper.DatabaseHelper;

public class Output {
	private JFrame jfOutput = new JFrame("Ngân hàng TPBank");
	private JLabel jlOutput = new JLabel("Thông tin khách hàng");
	private JTable jtOutput = new JTable();
	private JScrollPane jsOutput = new JScrollPane(jtOutput);
	private DefaultTableModel dftmOutput = (DefaultTableModel)jtOutput.getModel();
	private ImageIcon image = new ImageIcon("tpbank.jpg");
	
	public static int surplus;
	
	public Output() {
		jfOutput.setLocation(440,200);
		jfOutput.setLayout(null);
		jfOutput.setIconImage(image.getImage());
		
		jlOutput.setForeground(new Color(0,255,0));
		jfOutput.getContentPane().setBackground(new Color(128,0,128));
		
		jlOutput.setBounds(270, 50, 400, 40);
		jsOutput.setBounds(20, 100, 650, 200);
		Container cont = jfOutput.getContentPane();
		cont.add(jlOutput);
		cont.add(jsOutput);
		
		jfOutput.setSize(700,400);
		jfOutput.setVisible(true);
		
		try {
			String sql = "select * from Data where sotaikhoan = '" + Login.stk + "'";
			String col_num[] = {"Số tài khoản","Mật khẩu", "Tên", "Ngân hàng", "Ngày kích hoạt", "Số dư"};
			String data[] = new String[6];
			Connection conn = DatabaseHelper.openConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			dftmOutput.setColumnIdentifiers(col_num);
			while(rs.next()) {
				data[0] = rs.getString("sotaikhoan");
				data[1] = rs.getString("matkhau");
				data[2] = rs.getString("ten");
				data[3] = rs.getString("nganhang");
				data[4] = rs.getString("ngaykichhoat");
				data[5] = rs.getString("sodu");
				dftmOutput.addRow(data);
			}
			surplus = Integer.parseInt(data[5]);
			conn.close();
			stmt.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}
