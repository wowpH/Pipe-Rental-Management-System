package LeasingSystem;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Modifyorder extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JPanel JP = new JPanel();
	
	/*标签*/
	private JLabel JLorderid = new JLabel("订单编号");
	private JLabel JLcustomerid = new JLabel("客户编号");
	private JLabel JLday = new JLabel("  日期");
	private JLabel JLstate = new JLabel("  状态");
	private JLabel JLm6 = new JLabel("  6米");
	private JLabel JLm5 = new JLabel("  5米");
	private JLabel JLm4 = new JLabel("  4米");
	private JLabel JLm3 = new JLabel("  3米");
	private JLabel JLm2 = new JLabel("  2米");
	private JLabel JLm1 = new JLabel("  1米");
	private JLabel JLbackground;	/*背景*/
	
	/*输入框*/
	private JTextField JTorderid = new JTextField();
	private JTextField JTcustomerid = new JTextField();
	private JTextField JTday = new JTextField();
	private JTextField JTstate = new JTextField();
	private JTextField JTm6 = new JTextField();
	private JTextField JTm5 = new JTextField();
	private JTextField JTm4 = new JTextField();
	private JTextField JTm3 = new JTextField();
	private JTextField JTm2 = new JTextField();
	private JTextField JTm1 = new JTextField();
	
	/*按钮*/
	private JButton JBsave = new JButton("保存"); 
	private JButton JBback = new JButton("取消");

	Image imgtitle,imgbackground;	/*标题栏图标，背景图片*/
	
	/*要修改的数据，并且保存修改后的数据*/
	String orderidn,customeridn,dayn,staten;
	int m6n,m5n,m4n,m3n,m2n,m1n;
	
	/*窗口大小*/
	int width = 400,height = 600;
	
	/*数据库操作全局变量*/
	Connection c;
	Statement s;
	String updatedata;

	public Modifyorder(String orderid,String customerid,String day,String state,int m6,int m5,int m4,int m3,int m2,int m1) {
		
		/*初始化全局变量*/
		orderidn = orderid;
		customeridn = customerid;
		dayn = day;
		staten =state;
		m6n = m6;
		m5n = m5;
		m4n = m4;
		m3n = m3;
		m2n = m2;
		m1n = m1;
		
		/*初始化容器JP*/
		init();
		
		/*标题栏图标*/
		imgtitle = new ImageIcon("guan.png").getImage();
		this.setIconImage(imgtitle);
		
		/*初始化窗口*/
		this.add(JP);
		this.setVisible(true);
		this.setSize(width, height);
		this.setTitle("修改订单");
		this.setResizable(false);
		this.setLocation(300, 300);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == JBback) {
			dispose();
		}else if(e.getSource() == JBsave) {
			istextnull();	/*判断输入框是否为空，若为空，设置为0*/
			getnewdata();	/*获取新的数据*/
			isallzero();	/*判断是否全部为0，不全为0，则更新数据库*/
		}
		
	}
	
	/*初始化JP*/
	private void init() {	
		
		JP.setLayout(null);
		
		/*背景图片*/
		imgbackground = new ImageIcon("background2.jpg").getImage();
		JLbackground = new JLabel(new ImageIcon(imgbackground));
		
		JLorderid.setFont(Main.f20);
		JLcustomerid.setFont(Main.f20);
		JLday.setFont(Main.f20);
		JLstate.setFont(Main.f20);
		JLm6.setFont(Main.f20);
		JLm5.setFont(Main.f20);
		JLm4.setFont(Main.f20);
		JLm3.setFont(Main.f20);
		JLm2.setFont(Main.f20);
		JLm1.setFont(Main.f20);
		
		JTorderid.setFont(Main.f20);
		JTcustomerid.setFont(Main.f20);
		JTday.setFont(Main.f20);
		JTstate.setFont(Main.f20);
		JTm6.setFont(Main.f20);
		JTm5.setFont(Main.f20);
		JTm4.setFont(Main.f20);
		JTm3.setFont(Main.f20);
		JTm2.setFont(Main.f20);
		JTm1.setFont(Main.f20);
		
		JBsave.setFont(Main.f25bi);
		JBback.setFont(Main.f25bi);
		
		/*标签文字颜色*/
		JLorderid.setForeground(Color.WHITE);
		JLcustomerid.setForeground(Color.WHITE);
		JLday.setForeground(Color.WHITE);
		JLstate.setForeground(Color.WHITE);
		JLm6.setForeground(Color.WHITE);
		JLm5.setForeground(Color.WHITE);
		JLm4.setForeground(Color.WHITE);
		JLm3.setForeground(Color.WHITE);
		JLm2.setForeground(Color.WHITE);
		JLm1.setForeground(Color.WHITE);
		/*按钮文字颜色*/
		JBsave.setForeground(Color.WHITE);
		JBback.setForeground(Color.WHITE);
		
		/*位置*/
		int left = 45;
		int up = 35;
		int width1 = 90;
		int height1 = 20;
		int width2 = 200;
		int height2 = 30;
		int hs = 100;	/*水平间距*/
		int vs = 45;	/*垂直间距*/
		JLorderid.setBounds(left, up, width1, height1);
		JLcustomerid.setBounds(left, up+vs, width1, height1);
		JLday.setBounds(left, up+vs*2, width1, height1);
		JLstate.setBounds(left, up+vs*3, width1, height1);
		JLm6.setBounds(left, up+vs*4, width1, height1);
		JLm5.setBounds(left, up+vs*5, width1, height1);
		JLm4.setBounds(left, up+vs*6, width1, height1);
		JLm3.setBounds(left, up+vs*7, width1, height1);
		JLm2.setBounds(left, up+vs*8, width1, height1);
		JLm1.setBounds(left, up+vs*9, width1, height1);
		
		JTorderid.setBounds(left+hs, up-5, width2, height2);
		JTcustomerid.setBounds(left+hs, up+vs-5, width2, height2);
		JTday.setBounds(left+hs, up+vs*2-5, width2, height2);
		JTstate.setBounds(left+hs, up+vs*3-5, width2, height2);
		JTm6.setBounds(left+hs, up+vs*4-5, width2, height2);
		JTm5.setBounds(left+hs, up+vs*5-5, width2, height2);
		JTm4.setBounds(left+hs, up+vs*6-5, width2, height2);
		JTm3.setBounds(left+hs, up+vs*7-5, width2, height2);
		JTm2.setBounds(left+hs, up+vs*8-5, width2, height2);
		JTm1.setBounds(left+hs, up+vs*9-5, width2, height2);
		
		JBsave.setBounds(70, 500, 100, 40);
		JBback.setBounds(230, 500, 100, 40);
		
		/*背景位置*/
		JLbackground.setBounds(0, 0, width, height);
		
		JTorderid.setText(orderidn);
		JTcustomerid.setText(customeridn);
		JTday.setText(dayn);
		JTstate.setText(staten);
		JTm6.setText(String.valueOf(m6n));
		JTm5.setText(String.valueOf(m5n));
		JTm4.setText(String.valueOf(m4n));
		JTm3.setText(String.valueOf(m3n));
		JTm2.setText(String.valueOf(m2n));
		JTm1.setText(String.valueOf(m1n));

		/*设置按钮透明*/
		JBsave.setContentAreaFilled(false);
		JBback.setContentAreaFilled(false);

		/*不可修改部分*/
		JTorderid.setEditable(false);
		JTcustomerid.setEditable(false);
		JTday.setEditable(false);
		JTstate.setEditable(false);
		
		/*监听*/
		JTm6.addActionListener(this);
		JTm5.addActionListener(this);
		JTm4.addActionListener(this);
		JTm3.addActionListener(this);
		JTm2.addActionListener(this);
		JTm1.addActionListener(this);
		JBsave.addActionListener(this);
		JBback.addActionListener(this);

		JP.add(JLorderid);
		JP.add(JLcustomerid);
		JP.add(JLday);
		JP.add(JLstate);
		JP.add(JLm6);
		JP.add(JLm5);
		JP.add(JLm4);
		JP.add(JLm3);
		JP.add(JLm2);
		JP.add(JLm1);
		JP.add(JTorderid);
		JP.add(JTcustomerid);
		JP.add(JTday);
		JP.add(JTstate);
		JP.add(JTm6);
		JP.add(JTm5);
		JP.add(JTm4);
		JP.add(JTm3);
		JP.add(JTm2);
		JP.add(JTm1);
		JP.add(JBsave);
		JP.add(JBback);
		JP.add(JLbackground);

	}
	
	/*判断输入框是否为空，若为空，设置为0*/
	private void istextnull() {
		if(JTm6.getText().equals("")) {
			JTm6.setText("0");
		}
		if(JTm5.getText().equals("")) {
			JTm5.setText("0");
		}
		if(JTm4.getText().equals("")) {
			JTm4.setText("0");
		}
		if(JTm3.getText().equals("")) {
			JTm3.setText("0");
		}
		if(JTm2.getText().equals("")) {
			JTm2.setText("0");
		}
		if(JTm1.getText().equals("")) {
			JTm1.setText("0");
		}
	}
	
	/*获取新的数据*/
	private void getnewdata() {
		m6n = Integer.parseInt(JTm6.getText());
		m5n = Integer.parseInt(JTm5.getText());
		m4n = Integer.parseInt(JTm4.getText());
		m3n = Integer.parseInt(JTm3.getText());
		m2n = Integer.parseInt(JTm2.getText());
		m1n = Integer.parseInt(JTm1.getText());
	}
	
	/*判断是否全为0，不全为0，则更新数据库*/
	private void isallzero() {
		if(m6n == 0 && m5n == 0 && m4n == 0 && m3n == 0 && m2n == 0 && m1n == 0) {
			JOptionPane.showMessageDialog(null, "【30】全部为0，请返回并删除", "修改错误", JOptionPane.WARNING_MESSAGE);
		}else {
			updatemysql();	/*不全为0，更新数据库*/
		}
	}
	
	/*更新数据库*/
	private void updatemysql() {
		c = Connect.getConnection();
		updatedata = "UPDATE orders SET 6米=" + m6n + ",5米=" + m5n + ",4米=" + m4n + ",3米=" + m3n + ",2米=" + m2n + ",1米=" + m1n + " WHERE 订单编号='" + orderidn + "';";
		
		try {
			s = c.createStatement();
			s.executeUpdate(updatedata);
			s.close();
			Connect.closeConnection(c);
			JOptionPane.showMessageDialog(null, "【31】订单修改成功", "修改成功", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "【32】数据库更新失败", "修改错误", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
