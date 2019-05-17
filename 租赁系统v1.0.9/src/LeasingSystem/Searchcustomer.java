package LeasingSystem;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Searchcustomer extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JPanel JP = new JPanel();
	
	/*��ǩ*/
	private JLabel JLid = new JLabel("���");
	private JLabel JLname = new JLabel("����");
	private JLabel JLtel = new JLabel("�绰");
	private JLabel JLaddr = new JLabel("סַ");
	private JLabel JLbackground;	/*����*/

	/*�����*/
	private JTextField JTid = new JTextField();
	private JTextField JTname = new JTextField();
	private JTextField JTtel = new JTextField();
	private JTextField JTaddr = new JTextField();

	/*��ť*/
	private JButton JBconfirm = new JButton("ȷ��");
	private JButton JBcancel = new JButton("ȡ��");
	private JButton JBmodify = new JButton("�޸�");

	boolean b;	/*��־�޸İ�ť�Ƿ�ɵ��*/
	
	/*��ѯ��ʱ��Ĭ��ʲô��û���룬��ѯ���пͻ������Գ�ʼ��Ϊ���ַ���*/
	String id = "";	
	String name = "";
	String tel = "";
	String addr = "";
	
	private int width = 400;	/*���ڵĿ�͸�*/

	Showcustomer sc;		/*���������Showcustomer����*/
	
	/*ͼƬ*/
	Image imgtitle;
	Image imgbackground;

	public Searchcustomer() {
		
		init();
		
		/*���ô��������ͼ��*/
		imgtitle = new ImageIcon("guan.png").getImage();
		this.setIconImage(imgtitle);
		
		this.add(JP);
		this.setVisible(true);		/*���ӻ�*/
		this.setTitle("��ѯ�ͻ�");	/*����*/
		this.setResizable(false);	/*��С�̶�*/
		this.setSize(width, width);	/*��С*/
		this.setLocation(200, 200);	/*λ��*/
		
	}
	
	private void init() {
		
		JP.setLayout(null);	/*���ò���Ϊ��*/
		
		imgbackground = new ImageIcon("background3.jpg").getImage();
		JLbackground = new JLabel(new ImageIcon(imgbackground));
		
		b = false;
		JBmodify.setEnabled(b);	/*Ĭ�ϲ��ɵ���޸İ�ť*/
		
		/*����*/
		JLid.setFont(Main.f20);
		JLname.setFont(Main.f20);
		JLtel.setFont(Main.f20);
		JLaddr.setFont(Main.f20);
		JTid.setFont(Main.f20);
		JTname.setFont(Main.f20);
		JTtel.setFont(Main.f20);
		JTaddr.setFont(Main.f20);
		JBconfirm.setFont(Main.f25bi);
		JBcancel.setFont(Main.f25bi);
		JBmodify.setFont(Main.f25bi);

		/*λ��*/
		int left = 65;
		int up = 30;
		int width1 = 50;
		int height1 = 20;
		int width2 = 200;
		int height2 = 30;
		int hs = 60;	/*ˮƽ���*/
		int vs = 60;	/*��ֱ���*/
		JLid.setBounds(left, up, width1, height1);
		JLname.setBounds(left, up+vs, width1, height1);
		JLtel.setBounds(left, up+vs*2, width1, height1);
		JLaddr.setBounds(left, up+vs*3, width1, height1);
		JTid.setBounds(left+hs, up-5, width2, height2);
		JTname.setBounds(left+hs, up+vs-5, width2, height2);
		JTtel.setBounds(left+hs, up+vs*2-5, width2, height2);
		JTaddr.setBounds(left+hs, up+vs*3-5, width2, height2);
		JBconfirm.setBounds(40, 280, 100, 40);
		JBcancel.setBounds(150, 280, 100, 40);
		JBmodify.setBounds(260, 280, 100, 40);
		JLbackground.setBounds(0, 0, width, width);

		/*������*/
		JTid.addActionListener(this);
		JTname.addActionListener(this);
		JTtel.addActionListener(this);
		JTaddr.addActionListener(this);
		JBconfirm.addActionListener(this);
		JBcancel.addActionListener(this);
		JBmodify.addActionListener(this);

		/*�����ӵ�������*/
		JP.add(JLid);
		JP.add(JLname);
		JP.add(JLtel);
		JP.add(JLaddr);
		JP.add(JTid);
		JP.add(JTname);
		JP.add(JTtel);
		JP.add(JTaddr);
		JP.add(JBconfirm);
		JP.add(JBcancel);
		JP.add(JBmodify);
		JP.add(JLbackground);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		/*ֻҪ�����ť������ϴβ�ѯ�Ĵ���û�رյĻ����رյ��ϴεĴ���*/
		if(sc != null) {
			sc.dispose();
		}
		
		JBmodify.setEnabled(false);	/*��ʼ���޸İ�ť�ر�*/
		
		if(e.getSource() == JBcancel) {
			dispose();
		}else if(e.getSource() == JBconfirm) {
			sc = new Showcustomer(JTid.getText(),JTname.getText(),JTtel.getText(),JTaddr.getText());
			b = sc.canmodify();
			if(b) {
				id = sc.getid();
				name = sc.getname();
				tel = sc.gettel();
				addr = sc.getaddr();
				JBmodify.setEnabled(b);
			}
		}else if(e.getSource() == JBmodify) {
			if(id.equals("")) {
				JOptionPane.showMessageDialog(null, "��15��������Ҫ�޸ĵĿͻ�", "�޸Ĵ���", JOptionPane.WARNING_MESSAGE);
			}else {
				new Modifycustomer(id,name,tel,addr);	/*�޸�*/
			}
		}
		
	}

}
