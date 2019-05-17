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
	
	/*��ǩ*/
	private JLabel JLorderid = new JLabel("�������");
	private JLabel JLcustomerid = new JLabel("�ͻ����");
	private JLabel JLday = new JLabel("  ����");
	private JLabel JLstate = new JLabel("  ״̬");
	private JLabel JLm6 = new JLabel("  6��");
	private JLabel JLm5 = new JLabel("  5��");
	private JLabel JLm4 = new JLabel("  4��");
	private JLabel JLm3 = new JLabel("  3��");
	private JLabel JLm2 = new JLabel("  2��");
	private JLabel JLm1 = new JLabel("  1��");
	private JLabel JLbackground;	/*����*/
	
	/*�����*/
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
	
	/*��ť*/
	private JButton JBsave = new JButton("����"); 
	private JButton JBback = new JButton("ȡ��");

	Image imgtitle,imgbackground;	/*������ͼ�꣬����ͼƬ*/
	
	/*Ҫ�޸ĵ����ݣ����ұ����޸ĺ������*/
	String orderidn,customeridn,dayn,staten;
	int m6n,m5n,m4n,m3n,m2n,m1n;
	
	/*���ڴ�С*/
	int width = 400,height = 600;
	
	/*���ݿ����ȫ�ֱ���*/
	Connection c;
	Statement s;
	String updatedata;

	public Modifyorder(String orderid,String customerid,String day,String state,int m6,int m5,int m4,int m3,int m2,int m1) {
		
		/*��ʼ��ȫ�ֱ���*/
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
		
		/*��ʼ������JP*/
		init();
		
		/*������ͼ��*/
		imgtitle = new ImageIcon("guan.png").getImage();
		this.setIconImage(imgtitle);
		
		/*��ʼ������*/
		this.add(JP);
		this.setVisible(true);
		this.setSize(width, height);
		this.setTitle("�޸Ķ���");
		this.setResizable(false);
		this.setLocation(300, 300);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == JBback) {
			dispose();
		}else if(e.getSource() == JBsave) {
			istextnull();	/*�ж�������Ƿ�Ϊ�գ���Ϊ�գ�����Ϊ0*/
			getnewdata();	/*��ȡ�µ�����*/
			isallzero();	/*�ж��Ƿ�ȫ��Ϊ0����ȫΪ0����������ݿ�*/
		}
		
	}
	
	/*��ʼ��JP*/
	private void init() {	
		
		JP.setLayout(null);
		
		/*����ͼƬ*/
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
		
		/*��ǩ������ɫ*/
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
		/*��ť������ɫ*/
		JBsave.setForeground(Color.WHITE);
		JBback.setForeground(Color.WHITE);
		
		/*λ��*/
		int left = 45;
		int up = 35;
		int width1 = 90;
		int height1 = 20;
		int width2 = 200;
		int height2 = 30;
		int hs = 100;	/*ˮƽ���*/
		int vs = 45;	/*��ֱ���*/
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
		
		/*����λ��*/
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

		/*���ð�ť͸��*/
		JBsave.setContentAreaFilled(false);
		JBback.setContentAreaFilled(false);

		/*�����޸Ĳ���*/
		JTorderid.setEditable(false);
		JTcustomerid.setEditable(false);
		JTday.setEditable(false);
		JTstate.setEditable(false);
		
		/*����*/
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
	
	/*�ж�������Ƿ�Ϊ�գ���Ϊ�գ�����Ϊ0*/
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
	
	/*��ȡ�µ�����*/
	private void getnewdata() {
		m6n = Integer.parseInt(JTm6.getText());
		m5n = Integer.parseInt(JTm5.getText());
		m4n = Integer.parseInt(JTm4.getText());
		m3n = Integer.parseInt(JTm3.getText());
		m2n = Integer.parseInt(JTm2.getText());
		m1n = Integer.parseInt(JTm1.getText());
	}
	
	/*�ж��Ƿ�ȫΪ0����ȫΪ0����������ݿ�*/
	private void isallzero() {
		if(m6n == 0 && m5n == 0 && m4n == 0 && m3n == 0 && m2n == 0 && m1n == 0) {
			JOptionPane.showMessageDialog(null, "��30��ȫ��Ϊ0���뷵�ز�ɾ��", "�޸Ĵ���", JOptionPane.WARNING_MESSAGE);
		}else {
			updatemysql();	/*��ȫΪ0���������ݿ�*/
		}
	}
	
	/*�������ݿ�*/
	private void updatemysql() {
		c = Connect.getConnection();
		updatedata = "UPDATE orders SET 6��=" + m6n + ",5��=" + m5n + ",4��=" + m4n + ",3��=" + m3n + ",2��=" + m2n + ",1��=" + m1n + " WHERE �������='" + orderidn + "';";
		
		try {
			s = c.createStatement();
			s.executeUpdate(updatedata);
			s.close();
			Connect.closeConnection(c);
			JOptionPane.showMessageDialog(null, "��31�������޸ĳɹ�", "�޸ĳɹ�", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "��32�����ݿ����ʧ��", "�޸Ĵ���", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
