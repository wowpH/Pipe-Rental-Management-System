package LeasingSystem;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Addorder extends JFrame implements ActionListener {

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
	
	/*��ť*/
	private JButton JBsave = new JButton("����");
	private JButton JBback = new JButton("����");

	/*�����*/
	private JTextField JTorderid = new JTextField();
	private JTextField JTcustomerid = new JTextField();
	private JTextField JTday = new JTextField();
	/*�����б�ѡ���*/
	String[] cr = {"����","���"};
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox JCstate = new JComboBox(cr);
	
	private JTextField JTm6 = new JTextField();
	private JTextField JTm5 = new JTextField();
	private JTextField JTm4 = new JTextField();
	private JTextField JTm3 = new JTextField();
	private JTextField JTm2 = new JTextField();
	private JTextField JTm1 = new JTextField();

	Connection c;
	Statement s;
	ResultSet r;
	String day;		/*����*/
	String orderid = "";	/*��Ŷ������*/
	
	/*���ڿ�͸�*/
	int width = 400;
	int height = 600;
	
	/*ͼƬ*/
	Image imgtitle;
	Image imgbackground;

	public Addorder() {
		
		builddate();	/*��������*/
		
		buildorder();	/*���ɶ�����*/

		init();

		/*����ͼ��*/
		imgtitle = new ImageIcon("guan.png").getImage();
		this.setIconImage(imgtitle);
		
		/*��������*/
		this.add(JP);
		this.setVisible(true);		/*���ڿ��ӻ�*/
		this.setTitle("��Ӽ�¼");	/*���ô�������*/
		this.setResizable(false);	/*���ڲ��ܵ�����С*/
		this.setLocation(200, 200);		/*����λ��*/
		this.setSize(width, height);	/*���ڴ�С��(width,height)*/

	}
	
	/*��������*/
	private void builddate() {
		
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");	/*��Сд������*/
		day = d.format(new Date());
		JTday.setText(day);	/*������д�������������*/
		
	}
	
	/*���ɶ����ţ���������11λ��ǰ8λ�����ڣ���3λ�ǵ��충������1*/
	private void buildorder() {
		
		/*ȥ��-*/
		String[] days = day.split("-");
		int len = days.length;
		for(int i = 0; i < len; i++) {
			orderid = orderid + days[i].toString();
		}
		
		/*
		 * ��ȡ���충����
		 */
		c = Connect.getConnection();
		String num_s = null;	/*���ͳ�Ƶ��������ַ����͵ģ�����Ҫ��ʼ��*/
		String sql_num = "SELECT COUNT(*) FROM orders WHERE ���� = '" + day + "';";
		try {
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery(sql_num);
			r.next();
			num_s = r.getString("COUNT(*)");
			r.close();
			s.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "��20����Ӷ���ʧ��", "���ʧ��", JOptionPane.ERROR_MESSAGE);
		}
		
		Connect.closeConnection(c);
		
		int num_i = Integer.parseInt(num_s) + 1;	/*��������1*/
		String num_new = String.valueOf(num_i);
		/*����3λ��ǰ���0*/
		for(int i = 3; i > num_new.length(); i--) {
			orderid = orderid + "0";
		}
		orderid = orderid + num_new;
		JTorderid.setText(orderid);
		
	}

	private void init() {
		
		JP.setLayout(null);
		
		/*����ͼƬ*/
		imgbackground = new ImageIcon("background2.jpg").getImage();
		JLbackground = new JLabel(new ImageIcon(imgbackground));
		
		/*���ñ�ǩ����*/
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
		
		/*���������*/
		JTorderid.setFont(Main.f20);
		JTcustomerid.setFont(Main.f20);
		JTday.setFont(Main.f20);
		JCstate.setFont(Main.f20);
		JTm6.setFont(Main.f20);
		JTm5.setFont(Main.f20);
		JTm4.setFont(Main.f20);
		JTm3.setFont(Main.f20);
		JTm2.setFont(Main.f20);
		JTm1.setFont(Main.f20);

		/*��ť����*/
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
		
		/*���ð�ť͸��*/
		JBsave.setContentAreaFilled(false);
		JBback.setContentAreaFilled(false);
		
		/*������ź����ڲ��ɱ༭*/
		JTorderid.setEditable(false);
		JTday.setEditable(false);
		
		/*���λ��*/
		int left = 50;
		int up = 50;
		int width1 = 90;
		int height1 = 20;
		int width2 = 200;
		int height2 = 30;
		int hs = 10;
		int vs = 40;
		/*���ñ�ǩ�������е�λ��*/
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
		/*����������������е�λ��*/
		JTorderid.setBounds(left+width1+hs, up-5, width2, height2);
		JTcustomerid.setBounds(left+width1+hs, up+vs-5, width2, height2);
		JTday.setBounds(left+width1+hs, up+vs*2-5, width2, height2);
		JCstate.setBounds(left+width1+hs, up+vs*3-5, width2, height2);
		JTm6.setBounds(left+width1+hs, up+vs*4-5, width2, height2);
		JTm5.setBounds(left+width1+hs, up+vs*5-5, width2, height2);
		JTm4.setBounds(left+width1+hs, up+vs*6-5, width2, height2);
		JTm3.setBounds(left+width1+hs, up+vs*7-5, width2, height2);
		JTm2.setBounds(left+width1+hs, up+vs*8-5, width2, height2);
		JTm1.setBounds(left+width1+hs, up+vs*9-5, width2, height2);
		/*��ťλ��*/
		JBsave.setBounds(70, 500, 100, 40);
		JBback.setBounds(230, 500, 100, 40);
		/*ͼƬλ��*/
		JLbackground.setBounds(0, 0, width, height);

		/*����¼�������*/
		JTcustomerid.addActionListener(this);
		JCstate.addActionListener(this);
		JTm6.addActionListener(this);
		JTm5.addActionListener(this);
		JTm4.addActionListener(this);
		JTm3.addActionListener(this);
		JTm2.addActionListener(this);
		JTm1.addActionListener(this);
		JBsave.addActionListener(this);
		JBback.addActionListener(this);
		
		/*����ǩ��ӵ�������*/
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
		/*���������ӵ�������*/
		JP.add(JTorderid);
		JP.add(JTcustomerid);
		JP.add(JTday);
		JP.add(JCstate);
		JP.add(JTm6);
		JP.add(JTm5);
		JP.add(JTm4);
		JP.add(JTm3);
		JP.add(JTm2);
		JP.add(JTm1);
		/*����ť��ӵ�������*/
		JP.add(JBsave);
		JP.add(JBback);
		/*ͼƬ��ӵ�������*/
		JP.add(JLbackground);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == JBback) {
			dispose();
		}else if(e.getSource() == JBsave) {
			if(JTcustomerid.getText().equals("")) {	/*���δ��д�ͻ�*/
				JOptionPane.showMessageDialog(null, "��38��������д�ͻ����", "��Ӵ���", JOptionPane.WARNING_MESSAGE);
			}else {
				iscustomerexist();	/*�жϿͻ��Ƿ����*/				
			}
		}
		
	}
	
	/*�жϿͻ��Ƿ���ڣ������ڱ��浽���ݿ�*/
	private void iscustomerexist() {
		
		c = Connect.getConnection();
		String customer = JTcustomerid.getText();	/*��ȡ�����Ŀͻ����*/
		String sql_cus = "SELECT �ͻ���� FROM customer WHERE �ͻ���� = '" + customer +"';";
		
		try {
			s = c.createStatement();
			r = s.executeQuery(sql_cus);
			/*����*/
			if(r.next()) {
				r.close();
				s.close();
				Connect.closeConnection(c);
				isinputlegal();	/*�ж������Ƿ�Ϸ�*/
			/*�����ڣ�������ӿͻ�*/
			}else {
				r.close();
				s.close();
				Connect.closeConnection(c);
				JOptionPane.showMessageDialog(null, "��21���ͻ������ڣ�������ӿͻ�", "��Ӵ���", JOptionPane.WARNING_MESSAGE);
				dispose();
				new Addcustomer();	/*�ͻ������ڣ��رն������ڣ�������ӿͻ�����*/
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "��22����Ӷ���ʧ��", "���ʧ��", JOptionPane.ERROR_MESSAGE);
			dispose();
		}
		
	}
	
	/*�ж������Ƿ�Ϸ�*/
	private void isinputlegal() {
		
		/*�ж�����������Ƿ��пյģ��еĻ���������޸�Ϊ0*/
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
		/*��������ȫ��0��������ʾ���޷����*/
		if (JTm6.getText().equals("0") && 
			JTm5.getText().equals("0") && 
			JTm4.getText().equals("0") && 
			JTm3.getText().equals("0") && 
			JTm2.getText().equals("0") && 
			JTm1.getText().equals("0")) {
			JOptionPane.showMessageDialog(null, "��23���������ݲ���ȫΪ0", "��Ӿ���", JOptionPane.WARNING_MESSAGE);
		/*�Ϸ�*/
		}else {
			updatemysql();	/*���浽���ݿ�*/
		}
		
	}
	
	/*���浽���ݿ�*/
	private void updatemysql() {
		
		c = Connect.getConnection();
		String sql_add = "INSERT INTO orders VALUES('" 
				+ orderid + "','" 
				+ JTcustomerid.getText() + "','" 
				+ day + "','" 
				+ JCstate.getSelectedItem() + "',"
				+ Integer.parseInt(JTm6.getText()) + ","
				+ Integer.parseInt(JTm5.getText()) + ","
				+ Integer.parseInt(JTm4.getText()) + ","
				+ Integer.parseInt(JTm3.getText()) + ","
				+ Integer.parseInt(JTm2.getText()) + ","
				+ Integer.parseInt(JTm1.getText()) + ");";
		try {
			s = c.createStatement();
			s.executeUpdate(sql_add);
			s.close();
			JOptionPane.showMessageDialog(null, "��24��������ӳɹ�", "��ӳɹ�", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "��25����Ӷ���ʧ��", "���ʧ��", JOptionPane.ERROR_MESSAGE);
		}

		Connect.closeConnection(c);
		dispose();
	}

}
