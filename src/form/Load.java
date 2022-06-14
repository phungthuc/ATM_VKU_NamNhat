package form;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import helper.DatabaseHelper;


public class Load implements ActionListener{
	private JFrame jf = new JFrame("Ngân hàng TPBank");
	private JLabel jlb = new JLabel("Nạp tiền điện thoại");
	private JLabel jlb1 = new JLabel("Vui lòng điền đầy đủ thông tin!");
	private JLabel jlb2 = new JLabel("Số điện thoại");
	private JTextField jtf = new JTextField();
	private JLabel jlb3 = new JLabel("Chọn thuê bao");
	private String list[] = {"Thuê bao trả trước", "Thuê bao trả sau"};
	private String list1[] = {"10000", "20000", "50000", "100000", "200000", "500000"};
	private JComboBox jcb = new JComboBox(list);
	private JLabel jlb4 = new JLabel("Chọn mệnh giá");
	private JComboBox jcb1 = new JComboBox(list1);
	private JButton jbt = new JButton("Nạp");
	private ImageIcon image = new ImageIcon("tpbank.jpg");
	
	public Load(){
		
		
		jf.setLocation(500, 200);
		jf.setLayout(null);
		
		jf.setIconImage(image.getImage());
		
		jf.getContentPane().setBackground(new Color(128,0,128));
		jlb.setForeground(new Color(0,255,0));
		jlb1.setForeground(new Color(0,255,0));
		jlb2.setForeground(new Color(0,255,0));
		jlb3.setForeground(new Color(0,255,0));
		jlb4.setForeground(new Color(0,255,0));
		jbt.setForeground(new Color(255,0,0));
		
		jlb.setBounds(130, 30, 120, 30);
		jlb1.setBounds(105, 70, 170, 30);
		jlb2.setBounds(40, 120, 90, 30);
		jtf.setBounds(160, 120, 150, 30);
		jlb3.setBounds(40, 160, 90, 30);
		jcb.setBounds(160, 160, 150, 30);
		jlb4.setBounds(40, 200, 90, 30);
		jcb1.setBounds(160, 200, 150, 30);
		jbt.setBounds(140, 270, 100, 30);
		
		Container cont = jf.getContentPane();
		cont.add(jlb);
		cont.add(jlb1);
		cont.add(jlb2);
		cont.add(jtf);
		cont.add(jlb3);
		cont.add(jcb);
		cont.add(jlb4);
		cont.add(jcb1);
		cont.add(jbt);
		
		jf.setSize(400, 380);
		jf.setVisible(true);
		
		jbt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn = DatabaseHelper.openConnection();
					String sql = "select sodu from Data where sotaikhoan = '" + Login.stk + "'";
					PreparedStatement pstm = conn.prepareStatement(sql);
					ResultSet rs = pstm.executeQuery();
					int surplus = 0;
					int load = Integer.parseInt(jcb1.getSelectedItem().toString());
					int sdt = Integer.parseInt(jtf.getText());
					while(rs.next()) {
						surplus = rs.getInt("sodu");
					}
					int surplus1 = surplus - load;
					if(jbt == e.getSource()) {
						if(sdt < 100000000 || sdt > 999999999) {
							JOptionPane.showMessageDialog(cont, "Số điện thoại không hợp lệ!");
						}else if(load > surplus) {
							JOptionPane.showMessageDialog(cont, "Tài khoản của bạn không đủ!");
						}else {
							Connection conn1 = DatabaseHelper.openConnection();
							String sql1 = "update Data set sodu = '" + surplus1 + "' where sotaikhoan = '" + Login.stk + "'";
							PreparedStatement pstm1 = conn1.prepareStatement(sql1);
							int rs1 = pstm1.executeUpdate();
							JOptionPane.showMessageDialog(cont, "Nạp tiền thành công!");
							conn1.close();
							pstm1.close();
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
