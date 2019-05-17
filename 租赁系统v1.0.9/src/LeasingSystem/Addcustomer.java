package LeasingSystem;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Addcustomer extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	/*����*/
	private JPanel JP = new JPanel();
	
	/*��ǩ*/
	private JLabel JLid = new JLabel("���");
	private JLabel JLname = new JLabel("����");
	private JLabel JLtel = new JLabel("�绰");
	private JLabel JLaddr = new JLabel("סַ");
	private JLabel JLbackground;	/*����*/

	/*��ť*/
	private JButton JBsave = new JButton("����");
	private JButton JBback = new JButton("����");

	/*�����*/
	private JTextField JTid = new JTextField("");
	private JTextField JTname = new JTextField();
	private JTextField JTtel = new JTextField();
	private JTextField JTaddr = new JTextField();

	String id;		/*�������ɵĿͻ����*/
	private int width = 400;	/*���ڿ��*/
	
	/*ͼƬ*/
	Image imgtitle;
	Image imgbackground;
	
	public Addcustomer() {
		
		generateid();	/*���ɿͻ����*/
		
		init();
		
		/*���ô��������ͼ��*/
		imgtitle = new ImageIcon("guan.png").getImage();
		this.setIconImage(imgtitle);
		
		this.add(JP);
		this.setTitle("��ӿͻ�");	/*���ô�������*/
		this.setResizable(false);	/*���ڲ��ܵ�����С*/
		this.setSize(width, width);	/*���ڴ�С��(width,height)*/
		this.setLocation(200, 200);	/*����λ��*/
		this.setVisible(true);		/*���ڿ��ӻ�*/
		
	}
	
	/*
	 * ���ɿͻ���ţ����ڵ���Ķ�������1������һ�첻�ᳬ��999�����������磺20180927001
	 */
	private void generateid() {
		
		Connection c = Connect.getConnection();
		String count_num = "SELECT COUNT(*) FROM customer;";	/*ͳ�Ʊ����ж��ٸ��ͻ�*/
		try {
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery(count_num);
			r.next();
			String id_form = r.getString("COUNT(*)");	/*��ȡͳ�ƽ��*/
			int id_int = Integer.parseInt(id_form) + 1;	/*���ַ����еĽ��ת��Ϊint�ͣ�����1*/
			id = String.valueOf(id_int);		/*�����ɵ�int�Ϳͻ����ת��Ϊ�ַ�����*/
			JTid.setText(id);				/*�����ɵı����ʾ���������*/
			JTid.setEnabled(false);		/*�����������Ϊ���ɱ༭������ͻ���Ż���*/
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "��8�����ɿͻ����ʧ��", "��ӿͻ�ʧ��", JOptionPane.ERROR_MESSAGE);
		}
		Connect.closeConnection(c);
		
	}
	
	/*��ʼ������*/
	public void init() {
		
		JP.setLayout(null);
		
		imgbackground = new ImageIcon("background3.jpg").getImage();
		JLbackground = new JLabel(new ImageIcon(imgbackground));
		
		/*����*/
		JLid.setFont(Main.f20);
		JLname.setFont(Main.f20);
		JLtel.setFont(Main.f20);
		JLaddr.setFont(Main.f20);
		JTid.setFont(Main.f20);
		JTname.setFont(Main.f20);
		JTtel.setFont(Main.f20);
		JTaddr.setFont(Main.f20);
		JBsave.setFont(Main.f25bi);
		JBback.setFont(Main.f25bi);

		/*���λ��*/
		int left = 65;
		int up = 30;
		int width1 = 50;
		int height1 = 20;
		int width2 = 200;
		int height2 = 30;
		int hs = 10;
		int vs = 60;
		JLid.setBounds(left, up, width1, height1);
		JLname.setBounds(left, up+vs, width1, height1);
		JLtel.setBounds(left, up+vs*2, width1, height1);
		JLaddr.setBounds(left, up+vs*3, width1, height1);
		JTid.setBounds(left+width1+hs, up-5, width2, height2);
		JTname.setBounds(left+width1+hs, up+vs-5, width2, height2);
		JTtel.setBounds(left+width1+hs, up+vs*2-5, width2, height2);
		JTaddr.setBounds(left+width1+hs, up+vs*3-5, width2, height2);
		JBsave.setBounds(80, 280, 100, 40);
		JBback.setBounds(220, 280, 100, 40);
		JLbackground.setBounds(0, 0, width, width);

		/*�¼�����*/
		JTname.addActionListener(this);
		JTtel.addActionListener(this);
		JTaddr.addActionListener(this);
		JBsave.addActionListener(this);
		JBback.addActionListener(this);

		JP.add(JLid);
		JP.add(JLname);
		JP.add(JLtel);
		JP.add(JLaddr);
		JP.add(JTid);
		JP.add(JTname);
		JP.add(JTtel);
		JP.add(JTaddr);
		JP.add(JBsave);
		JP.add(JBback);
		JP.add(JLbackground);

	}
	
	/*���浽���ݿ�*/
	private void save() {
		
		/*������ӿ��û�*/
		if(JTname.getText().equals("") || JTtel.getText().equals("") || JTaddr.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "��9������Ϊ��", "���ʧ��", JOptionPane.WARNING_MESSAGE);
		}else if(JTtel.getText().length() != 11) {
			JOptionPane.showMessageDialog(null, "��39���绰���벻�Ϸ�", "���ʧ��", JOptionPane.ERROR_MESSAGE);
		} else {
			
			Connection c = Connect.getConnection();
			String sql_add = "INSERT INTO customer VALUES('" 
														+ id + "','" 
														+ JTname.getText() + "','" 
														+ JTtel.getText() + "','" 
														+ JTaddr.getText() + "');";
			
			try {
				Statement s = c.prepareStatement(sql_add);
				s.executeUpdate(sql_add);
				s.close();
				JOptionPane.showMessageDialog(null, "��10���ͻ���ӳɹ�", "��ӳɹ�", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "��11�����ݿ����ʧ��", "���ʧ��", JOptionPane.ERROR_MESSAGE);
			}
			
			Connect.closeConnection(c);	/*���Ҫ���ں���*/
		}	
		dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == JBback) {
			dispose();
		}else if(e.getSource() == JBsave) {
			save();
		}
	}

}
