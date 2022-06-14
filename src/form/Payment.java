package form;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import helper.DatabaseHelper;

public class Payment implements ActionListener{
	private JFrame jf = new JFrame("Ngân hàng TPBank");
	private JLabel jlb = new JLabel("Thanh toán hóa đơn tiền điện");
	private JLabel jlb1 = new JLabel("Mã khách hàng");
	private JTextField jtf = new JTextField();
	private JLabel jlb2 = new JLabel("Số điện thoại người thanh toán");
	private JTextField jtf1 = new JTextField();
	private JButton jbt = new JButton("Kiểm tra");
	private ImageIcon image = new ImageIcon("tpbank.jpg");
	
	public static String code;
	
	public Payment(){
		jf.setLocation(600, 250);
		jf.setLayout(null);
		
		jf.setIconImage(image.getImage());
		
		jf.getContentPane().setBackground(new Color(128,0,128));
		jlb.setForeground(new Color(0,255,0));
		jlb1.setForeground(new Color(0,255,0));
		jlb2.setForeground(new Color(0,255,0));
		jbt.setForeground(new Color(255,0,0));
		
		jlb.setBounds(110, 30, 200, 30);
		jlb1.setBounds(30, 70, 120, 30);
		jtf.setBounds(30, 110, 280, 30);
		jlb2.setBounds(30, 140, 180, 30);
		jtf1.setBounds(30, 180, 280, 30);
		jbt.setBounds(140, 240, 90, 30);
		
		Container cont = jf.getContentPane();
		cont.add(jlb);
		cont.add(jlb1);
		cont.add(jtf);
		cont.add(jlb2);
		cont.add(jtf1);
		cont.add(jbt);
		
		jf.setSize(400, 350);
		jf.setVisible(true);
		
		jbt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn = DatabaseHelper.openConnection();
					String sql = "select * from Data2 where makhachhang = '" + jtf.getText() + "' and sodienthoai = '" + jtf1.getText() + "'";
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					code = jtf.getText();
					if(jbt == e.getSource()) {
						if(rs.next()) {
							new Payment2();
						}else {
							JOptionPane.showMessageDialog(cont, "Dữ liệu khách hàng không tồn tại");
						}
					}
					conn.close();
					stmt.close();
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
