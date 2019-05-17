package LeasingSystem;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class Menu extends JFrame implements ActionListener {
		
	private static final long serialVersionUID = 1L;

	private JPanel JPbutton = new JPanel();		/*��Ӱ�ť������*/

	private JButton JBaddorder = new JButton("��Ӽ�¼");			/*��Ӽ�¼*/
	private JButton JBsearchorder = new JButton("��ѯ��¼");		/*��ѯ��¼*/
	private JButton JBaddcustomer = new JButton("��ӿͻ�");		/*��ӿͻ�*/
	private JButton JBsearchcustomer = new JButton("��ѯ�ͻ�");	/*��ѯ�ͻ�*/
	private JButton JBchangepassword = new JButton("�޸�����");	/*�޸�����*/
	private JButton JBexit = new JButton("�˳�ϵͳ");				/*�˳�ϵͳ*/
	
	private JLabel JLimg;
	
	/*ͼƬ*/
	Image imgtitle;
	Image imgbackground;
	
	/*����*/
	private int width = 400;	/*���ڿ�*/
	private int height = 600;	/*���ڸ�*/
	private int x = 100;		/*���ں�����*/
	private int y = 100;		/*����������*/
	private int left = 40;		/*�����а�ť�ĺ�����*/
	private int up = 50;		/*�����е�һ����ť��������*/
	private int vs = 70;		/*�����а�ť���ϱ������ϱ��صľ���*/
	private int width2 = 150;	/*��ť�Ŀ�*/
	private int height2 = 50;	/*��ť�ĸ�*/
	
	/*������ť��Ӧ����Ķ���*/
	Addorder ad = null;
	Searchorder so = null;
	Addcustomer ac = null;
	Searchcustomer sc = null;
	Changepassword cp = null;
	
	public Menu() {
		
		init();
		
		/*����ͼ��*/
		imgtitle = new ImageIcon("guan.png").getImage();
		this.setIconImage(imgtitle);
		
		this.add(JPbutton);		/*������ӵ�������*/
		this.setVisible(true);		/*���ӻ�*/
		this.setLocation(x, y);			/*λ��*/
		this.setResizable(false);			/*���ڲ��ܵ�����С*/
		this.setSize(width, height);			/*��С*/
		this.setTitle("����ϵͳV1.0.9");					/*��������*/
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	/*�����ţ��رճ���*/
		
	}
	
	public void init() {
		
		/*���������Ĳ��ֹ�����*/
		JPbutton.setLayout(null);
		
		/*��������͹��߿򣬲��Ǻ�����,�ɲ�Ҫ*/
		JPbutton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		
		/*����ͼƬ*/
		imgbackground = new ImageIcon("background2.jpg").getImage();
		JLimg = new JLabel(new ImageIcon(imgbackground));
		
		/*�������������*/
		JBaddorder.setFont(Main.f20);
		JBsearchorder.setFont(Main.f20);
		JBaddcustomer.setFont(Main.f20);
		JBsearchcustomer.setFont(Main.f20);
		JBchangepassword.setFont(Main.f20);
		JBexit.setFont(Main.f20);
		
		/*���ð�ť�ֵ���ɫ*/
		JBaddorder.setForeground(Color.WHITE);
		JBsearchorder.setForeground(Color.WHITE);
		JBaddcustomer.setForeground(Color.WHITE);
		JBsearchcustomer.setForeground(Color.WHITE);
		JBchangepassword.setForeground(Color.WHITE);
		JBexit.setForeground(Color.WHITE);

		/*���������λ��*/
		JBaddorder.setBounds(left, up, width2, height2);
		JBsearchorder.setBounds(left, up+vs, width2, height2);
		JBaddcustomer.setBounds(left, up+vs*2, width2, height2);
		JBsearchcustomer.setBounds(left, up+vs*3, width2, height2);
		JBchangepassword.setBounds(left, up+vs*4, width2, height2);
		JBexit.setBounds(left, up+vs*5, width2, height2);
		JLimg.setBounds(0, 0, width, height);
		
		/*����ť����͸��*/
		JBaddorder.setContentAreaFilled(false);
		JBsearchorder.setContentAreaFilled(false);
		JBaddcustomer.setContentAreaFilled(false);
		JBsearchcustomer.setContentAreaFilled(false);
		JBchangepassword.setContentAreaFilled(false);
		JBexit.setContentAreaFilled(false);
		
		/*����¼�������*/
		JBaddorder.addActionListener(this);
		JBsearchorder.addActionListener(this);
		JBaddcustomer.addActionListener(this);
		JBsearchcustomer.addActionListener(this);
		JBchangepassword.addActionListener(this);
		JBexit.addActionListener(this);

		/*����ť�����ӵ���Ӧ��������*/
		JPbutton.add(JBaddorder);
		JPbutton.add(JBsearchorder);
		JPbutton.add(JBaddcustomer);
		JPbutton.add(JBsearchcustomer);
		JPbutton.add(JBchangepassword);
		JPbutton.add(JBexit);
		JPbutton.add(JLimg);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		closeoldwindows();	/*�رվɵĴ���*/
		
		if(e.getSource() == JBaddorder) {	/*��Ӽ�¼��*/
			ad = new Addorder();
		} else if(e.getSource() == JBsearchorder) {	/*��ѯ��¼*/
			so = new Searchorder();
		} else if(e.getSource() == JBaddcustomer) {	/*��ӿͻ�*/
			ac = new Addcustomer();
		} else if(e.getSource() == JBsearchcustomer) {	/*��ѯ�ͻ�*/
			sc = new Searchcustomer();
		} else if(e.getSource() == JBchangepassword) {	/*�޸�����*/
			cp = new Changepassword();
		} else if(e.getSource() == JBexit) {	/*�˳�*/
			System.exit(0);		
		}
		
	}
	
	/*�رվɵĴ���*/
	private void closeoldwindows() {
		if(ad != null) {	/*��һ����Ӽ�¼�Ĵ���û�ر�*/
			ad.dispose();
		}
		if(so != null) {	/*��ѯ����û�ر�*/
			so.dispose();
		}
		if(ac != null) {	/*��ӿͻ�û�ر�*/
			ac.dispose();
		}
		if(sc != null) {	/*��ѯ�ͻ�û�ر�*/
			sc.dispose();
		}
		if(cp != null) {	/*�޸�����û�ر�*/
			cp.dispose();
		}
	}

}
