package form;

import java.awt.Color;




import java.awt.Container;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

  
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AtmManagerForm implements ActionListener{
	private JFrame jf = new JFrame("Ngân hàng TPBank xin kính chào quý khách!");
	private JLabel jl = new JLabel("Quý khách vui lòng chọn giao dịch");
	private JButton jbTracuu = new JButton("Tra cứu thông tin");
	private JButton jbDoi = new JButton("Đổi mật mã");
	private JButton jbRut = new JButton("Rút tiền mặt");
	private JButton jbChuyen = new JButton("Chuyển tiền");
	private JButton jbNap = new JButton("Nạp tiền điện thoại");
	private JButton jbThanhtoan = new JButton("Thanh toán tiền điện");
	private JButton jbThoat = new JButton("Thoát");
	private ImageIcon image = new ImageIcon("tpbank.jpg");
	
	public void form() {
		jf.setLocation(500, 200);
		jf.setLayout(null);
		
		jf.setIconImage(image.getImage());
		
		jf.getContentPane().setBackground(new Color(128,0,128));
		jbTracuu.setForeground(new Color(0,128,0));
		jbDoi.setForeground(new Color(0,128,0));
		jbRut.setForeground(new Color(0,128,0));
		jbChuyen.setForeground(new Color(0,128,0));
		jbNap.setForeground(new Color(0,128,0));
		jbThanhtoan.setForeground(new Color(0,128,0));
		jbThoat.setForeground(new Color(0,128,0));
		
		jl.setBounds(160, 40, 200, 15);
		jbTracuu.setBounds(30, 80, 160, 50);
		jbDoi.setBounds(30, 170, 160, 50);
		jbRut.setBounds(30, 260, 160, 50);
		jbChuyen.setBounds(300, 80, 160, 50);
		jbNap.setBounds(300, 170, 160, 50);
		jbThanhtoan.setBounds(300, 260, 160, 50);
		jbThoat.setBounds(160, 350, 160, 50);
		
		Container cont = jf.getContentPane();
		cont.add(jl);
		cont.add(jbTracuu);
		cont.add(jbDoi);
		cont.add(jbRut);
		cont.add(jbChuyen);
		cont.add(jbNap);
		cont.add(jbThanhtoan);
		cont.add(jbThoat);
		
		jf.setSize(500, 480);
		jf.setVisible(true);
		
		jbTracuu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(jbTracuu == e.getSource()) {
					new Output();
				}
			}
			
		});
		jbDoi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(jbDoi == e.getSource()) {
					new Change();
				}
			}
			
		});
		jbRut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(jbRut == e.getSource()) {
					new WithDrawal();
				}
			}
			
		});
		jbChuyen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(jbChuyen == e.getSource()) {
					new Transfer();
				}
			}
			
		});
		jbThoat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(jbThoat == e.getSource()) {
					System.exit(0);
				}
			}
			
		});
		jbNap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(jbNap == e.getSource()) {
					new Load();
				}
			}
			
		});
		jbThanhtoan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(jbThanhtoan == e.getSource()) {
					new Payment();
				}
			}
			
		});
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	} 
}
