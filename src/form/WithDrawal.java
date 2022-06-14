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
import javax.swing.JTextField;

import helper.DatabaseHelper;

public class WithDrawal implements ActionListener{
	private JFrame jf = new JFrame("Ngân hàng TPBank");
	private JLabel jlb1 = new JLabel("ATM chỉ trả được số tiền là bội số của ");
	private JLabel jlb2 = new JLabel("50000");
	private JLabel jlb3 = new JLabel("Xin hãy nhập số tiền");
	private JTextField jtf = new JTextField();
	private JLabel jlb4 = new JLabel("VND");
	private JButton jbtRut = new JButton("Rút");
	private JButton jbtReset = new JButton("Xóa");
	private JLabel jlb5 = new JLabel("(Số tiền nhập vào phải là VND)");
	private ImageIcon image = new ImageIcon("tpbank.jpg");

	
	public WithDrawal() {
		jf.setLocation(600, 250);
		jf.setLayout(null);
		
		jf.setIconImage(image.getImage());
		
		jf.getContentPane().setBackground(new Color(128,0,128));
		jlb1.setForeground(new Color(0,255,0));
		jlb2.setForeground(new Color(0,255,0));
		jlb3.setForeground(new Color(0,255,0));
		jlb4.setForeground(new Color(0,255,0));
		jbtRut.setForeground(new Color(255,0,0));
		jbtReset.setForeground(new Color(255,0,0));
		
		jlb1.setBounds(70, 40, 280, 30);
		jlb2.setBounds(160, 70, 120, 30);
		jlb3.setBounds(120, 90, 200, 30);
		jtf.setBounds(70, 130, 180, 30);
		jlb4.setBounds(260, 130, 50, 30);
		jbtRut.setBounds(70, 180, 80, 30);
		jbtReset.setBounds(200, 180, 80, 30);
		jlb5.setBounds(90, 280, 220, 30);
		
		Container cont = jf.getContentPane();
		cont.add(jlb1);
		cont.add(jlb2);
		cont.add(jlb3);
		cont.add(jtf);
		cont.add(jlb4);
		cont.add(jbtRut);
		cont.add(jbtReset);
		cont.add(jlb5);
		
		jf.setSize(380, 380);
		jf.setVisible(true);
		
		jbtReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(jbtReset == e.getSource()) {
					jtf.setText("");
				}
			}
			
		});
		jbtRut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(jbtRut == e.getSource()) {
					try {
						Connection conn = DatabaseHelper.openConnection();
						String sql = "select sodu from Data where sotaikhoan = '" + Login.stk + "'";
						int surplus = 0;
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
						while(rs.next()) {
							surplus = rs.getInt("sodu");
						}
						int withdrawal = Integer.parseInt(jtf.getText());
						if(withdrawal > surplus) {
							JOptionPane.showMessageDialog(cont, "Số tiền bạn nhập quá số dư của tài khoản!");
						}else if(withdrawal % 50000 != 0){
							JOptionPane.showMessageDialog(cont, "Số tiền bạn nhập không phải bội số của 50000!");
						}else {
							int remain = surplus - withdrawal;
							try {
								Connection conn1 = DatabaseHelper.openConnection();
								String sql1 = "update Data set sodu = ? where sotaikhoan = '" + Login.stk + "'";
								PreparedStatement pstm = conn1.prepareStatement(sql1);
								pstm.setInt(1, remain);
								int rs1 = pstm.executeUpdate();
								JOptionPane.showMessageDialog(cont, "Rút tiền thành công!");
								conn1.close();
								pstm.close();
							} catch (Exception e2) {
								System.out.println(e2);
							}
						}
						conn.close();
						stmt.close();
						rs.close();
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
