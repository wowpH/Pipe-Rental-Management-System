package LeasingSystem;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Searchorder extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JPanel JP = new JPanel();
	
	/*��ǩ*/
	private JLabel JLorderid = new JLabel("�������");
	private JLabel JLcustomerid = new JLabel("�ͻ����");
	private JLabel JLday = new JLabel("����");
	private JLabel JLstate = new JLabel("״̬");
	private JLabel JLbackground;	/*����*/
	
	/*�����*/
	private JTextField JTorderid = new JTextField();
	private JTextField JTcustomerid = new JTextField();
	private JTextField JTday = new JTextField();
	String[] scr = {"","����","���"};
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox JCstate = new JComboBox(scr);
	
	/*��ť*/
	private JButton JBconfirm = new JButton("ȷ��");
	private JButton JBcancel = new JButton("ȡ��");
	private JButton JBmodify = new JButton("�޸�");
	private JButton JBdelete = new JButton("ɾ��");
	
	boolean b;	/*��־�޸İ�ť�Ƿ�ɵ����Ĭ�ϲ��ɵ��*/

	/*���ڿ�͸�*/
	int width = 500;
	int height = 400;
	
	Showorder so;	/*������ʾ����������Ķ���*/
	Modifyorder mo;	/*�޸Ķ�������Ķ���*/
	
	Image imgtitle,imgbackground;	/*���ڱ�����ͼ��ͱ���ͼƬ*/
	
	/*	������ţ��ͻ���ţ����ڣ�״̬��
	 * 	����û�����ݣ�����Ҫ��ʼ��Ϊ���ַ���
	 */
	String orderid = "";
	String customerid = "";
	String day = "";
	String state = "";
	/*6�ף�5�ף�4�ף�3�ף�2�ף�1�׸ֹܵ���������ʼ��Ϊ0*/
	int m6 = 0;
	int m5 = 0;
	int m4 = 0;
	int m3 = 0;
	int m2 = 0;
	int m1 = 0;

	/*���췽��*/
	public Searchorder() {
		
		init();	/*��ʼ��JP*/
		
		imgtitle = new ImageIcon("guan.png").getImage();
		this.setIconImage(imgtitle);
		
		/*���ô���*/
		this.add(JP);
		this.setVisible(true);
		this.setTitle("��ѯ����");
		this.setResizable(false);
		this.setLocation(200, 200);
		this.setSize(width, height);
		
	}
	
	/*��ʼ��JP*/
	private void init() {
		
		JP.setLayout(null);	/*���㽫������ɷ���*/
		
		imgbackground = new ImageIcon("background4.jpg").getImage();
		JLbackground = new JLabel(new ImageIcon(imgbackground));
		
		/*��ǩ����*/
		JLorderid.setFont(Main.f20);
		JLcustomerid.setFont(Main.f20);
		JLday.setFont(Main.f20);
		JLstate.setFont(Main.f20);
		/*���������*/
		JTorderid.setFont(Main.f20);
		JTcustomerid.setFont(Main.f20);
		JTday.setFont(Main.f20);
		JCstate.setFont(Main.f20);
		/*��ť����*/
		JBconfirm.setFont(Main.f20);
		JBcancel.setFont(Main.f20);
		JBmodify.setFont(Main.f20);
		JBdelete.setFont(Main.f20);

		/*
		 * ���λ��
		 */
		int left = 95;
		int up = 30;
		int width1 = 90;
		int height1 = 20;
		int width2 = 200;
		int height2 = 30;
		int hs = 100;
		int vs = 60;
		/*��ǩλ��*/
		JLorderid.setBounds(left, up, width1, height1);
		JLcustomerid.setBounds(left, up+vs, width1, height1);
		JLday.setBounds(left, up+vs*2, width1, height1);
		JLstate.setBounds(left, up+vs*3, width1, height1);
		JLbackground.setBounds(0, 0, width, height);
		/*�����λ��*/
		JTorderid.setBounds(left+hs, up-5, width2, height2);
		JTcustomerid.setBounds(left+hs, up+vs-5, width2, height2);
		JTday.setBounds(left+hs, up+vs*2-5, width2, height2);
		JCstate.setBounds(left+hs, up+vs*3-5, width2, height2);
		/*��ťλ��*/
		JBconfirm.setBounds(35, 290, 100, 40);
		JBcancel.setBounds(145, 290, 100, 40);
		JBmodify.setBounds(255, 290, 100, 40);
		JBdelete.setBounds(365, 290, 100, 40);
		
		/*������*/
		JTorderid.addActionListener(this);
		JTcustomerid.addActionListener(this);
		JTday.addActionListener(this);
		JCstate.addActionListener(this);
		JBconfirm.addActionListener(this);
		JBcancel.addActionListener(this);
		JBmodify.addActionListener(this);
		JBdelete.addActionListener(this);

		/*��ʼ���޸İ�ť������*/
		b = false;
		JBmodify.setEnabled(b);
		/*ɾ����ť������*/
		JBdelete.setEnabled(b);
		
		/*����ǩ��ӵ�JP��*/
		JP.add(JLorderid);
		JP.add(JLcustomerid);
		JP.add(JLday);
		JP.add(JLstate);
		/*���������ӵ�JP��*/
		JP.add(JTorderid);
		JP.add(JTcustomerid);
		JP.add(JTday);
		JP.add(JCstate);
		/*����ť��ӵ�JP��*/
		JP.add(JBconfirm);
		JP.add(JBcancel);
		JP.add(JBmodify);
		JP.add(JBdelete);
		/*��ͼƬ��ǩ��ӵ�JP�У����Ҫ������󣬷���ǰ�棬������ʾ����*/
		JP.add(JLbackground);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		/*����ϸ�����û�رգ��ȹر��ϸ�����*/
		if(so != null) {
			so.dispose();
		}
		/*����޸Ĵ���δ�ر�*/
		if(mo != null) {
			mo.dispose();
		}
		
		JBdelete.setEnabled(false);	/*ɾ����ť������*/
		
		if(e.getSource() == JBcancel) {
			dispose();
		}else if(e.getSource() == JBconfirm) {
			if(isdaylegal()) {	/*���ںϷ����ܲ�ѯ*/
				so = new Showorder(JTorderid.getText(),JTcustomerid.getText(),JTday.getText(),String.valueOf(JCstate.getSelectedItem()));
				initmodify();	
				JBdelete.setEnabled(true);	/*ɾ����ť����*/
			}else {
				JOptionPane.showMessageDialog(null, "��33���������벻�Ϸ�����ȷ�ĸ�ʽ�У�\n    �����ѯ�� 2018\n    ���²�ѯ�� 2018-09\n    ���ղ�ѯ�� 2018-09-01", "��ѯ����", JOptionPane.ERROR_MESSAGE);
			}
		}else if(e.getSource() == JBmodify) {
			islegalmodify();	/*�Ƿ�Ϸ����ܷ��޸�*/
			JBmodify.setEnabled(false);		/*������*/
		}else if(e.getSource() == JBdelete) {
			int op = JOptionPane.showConfirmDialog(null, "��34��ɾ����ѯ�����б��¼��", "ɾ������", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(op ==JOptionPane.OK_OPTION) {
				new Deleteorder(JTorderid.getText(),JTcustomerid.getText(),JTday.getText(),String.valueOf(JCstate.getSelectedItem()));
			}
		}
		
	}
	
	/*��ʼ�����ݣ�Ϊ�޸���׼��*/
	private void initmodify(){
		/*��ȡ�Ƿ����޸����ݣ�������Showorder���canmodify����*/
		b = so.canmodify();
		if(b) {	/*�����޸ĵĻ�����ȡҪ�޸ĵ����ݣ���ΪModifyorder�������β�*/
			orderid = so.getorderid();
			customerid = so.getcustomerid();
			day = so.getday();
			state = so.getstate();
			m6 = so.getm6();
			m5 = so.getm5();
			m4 = so.getm4();
			m3 = so.getm3();
			m2 = so.getm2();
			m1 = so.getm1();
			JBmodify.setEnabled(b);
		}
	}
	
	/*�ж��Ƿ�Ϸ��޸�*/
	private void islegalmodify() {
		if(orderid.equals("")) {
			JOptionPane.showMessageDialog(null, "��29��������Ҫ�޸ĵĶ���", "�޸Ĵ���", JOptionPane.WARNING_MESSAGE);
		}else {
			mo = new Modifyorder(orderid,customerid,day,state,m6,m5,m4,m3,m2,m1);	/*�����޸�*/
		}
	}
	
	/*���������Ƿ�Ϸ�*/
	private boolean isdaylegal() {
		int len = JTday.getText().length();
		if(len == 0 || len == 4 || len == 7 || len == 10) {
			return true;
		}
		return false;
	}

}
