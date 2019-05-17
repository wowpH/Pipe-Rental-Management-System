package LeasingSystem;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener {
	
	/*���л�*/
	private static final long serialVersionUID = 1L;

	/*����һ��JPanel�������󣬿�����Ӹ������*/
	private JPanel JP = new JPanel();

	/*��ǩ*/
	private JLabel JLuser = new JLabel("�û���");		/*�û���*/
	private JLabel JLpassword = new JLabel("��  ��");	/*����*/
	private JLabel JLstate = new JLabel();			/*״̬*/
	private JLabel JLnew = new JLabel("");			/*��ʾ״̬*/
	private JLabel JLbackground;					/*����ͼƬ*/
	
	/*��ť*/
	private JButton JBlogin = new JButton("��¼");		/*��¼*/
	private JButton JBexit = new JButton("�˳�");		/*�˳�*/
	private JButton JBclearuser = new JButton("��");		/*�û������*/
	private JButton JBclearpassword = new JButton("��");	/*�������*/

	/*�����*/
	private JTextField JTuser = new JTextField();				/*�û���*/
	private JPasswordField JPpassword = new JPasswordField();	/*����*/

	static boolean b = false;	/*�����ж��Ƿ���ʾ��ʾ��Ĭ�ϲ���ʾ*/

	/*ͼƬ*/
	Image imgtitle;		/*������ͼ��*/
	Image imgbackground;/*��¼���汳��ͼƬ*/
	
	/*��ʼ������*/
	public Login() {
		
		/*��ʼ������JP*/
		init();
		
		/*���ô��������ͼ��*/
		imgtitle = new ImageIcon("guan.png").getImage();
		this.setIconImage(imgtitle);
		
		this.add(JP);			/*ע����Σ����뽫Jpanel�����ӵ�������*/
		this.setTitle("��¼");		/*���ô�������*/
		this.setVisible(true);			/*���ڿ��ӻ�*/
		this.setSize(500, 350);				/*���ڴ�С��(width,height)*/
		this.setResizable(false);				/*���ڲ��ܵ�����С*/
		this.setLocationRelativeTo(null);				/*�ô��������ʾ*/
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);/*����رպ�ִ�еĲ��������رմ���*/

	}
	
	/*��ʼ������JP*/
	public void init() {
		
		JP.setLayout(null);	/*���ù̶��������ģʽ����Ҫ���ò��ֹ�����Ϊ��*/
		
		/*����ͼƬ��1.jpg�ǵ�ǰ��Ŀ�ļ����µ�ͼƬ�����ļ��н�ͼƬ���뵽src����Ŀ¼����*/
		imgbackground = new ImageIcon("background1.jpg").getImage();
		JLbackground = new JLabel(new ImageIcon(imgbackground));
		
		/*�������ӵ�JPanel������*/
		JLuser.setBounds(80, 50, 64, 20);			/*�û���*/
		JLpassword.setBounds(80,120,64,20);			/*����*/
		JLstate.setBounds(10, 270, 480, 20);		/*״̬*/
		JLnew.setBounds(110, 160, 290, 20);			/*��ʾĬ����Ϣ*/
		JTuser.setBounds(170,45,250,30);			/*�û��������*/
		JPpassword.setBounds(170,115,250,30);		/*���������*/
		JBclearuser.setBounds(405, 45, 55, 30);		/*�û��������ť*/
		JBclearpassword.setBounds(405, 115, 55, 30);/*���������ť*/
		JBlogin.setBounds(100, 190, 100, 40);		/*��¼��ť*/
		JBexit.setBounds(300, 190, 100, 40);		/*�˳���ť*/
		JLbackground.setBounds(0, 0, 500, 350);		/*����ͼƬ*/
		
		/*����*/
		JLuser.setFont(Main.f20);			/*�û���*/
		JLpassword.setFont(Main.f20);		/*����*/
		JLstate.setFont(Main.f20);			/*״̬*/
		JLnew.setFont(Main.f15);			/*��ʾĬ����Ϣ*/
		JTuser.setFont(Main.f20);			/*�û��������*/
		JPpassword.setFont(Main.f20);		/*���������*/
		JBclearuser.setFont(Main.f20);		/*�û��������ť*/
		JBclearpassword.setFont(Main.f20);	/*���������ť*/
		JBlogin.setFont(Main.f25bi);		/*��¼��ť*/
		JBexit.setFont(Main.f25bi);			/*�˳���ť*/
		
		/*�����ֵ���ɫ*/
		JLstate.setForeground(Color.WHITE);
		JLnew.setForeground(Color.RED);
		JBclearuser.setForeground(Color.WHITE);
		JBclearpassword.setForeground(Color.WHITE);
		
		/*��ʾĬ����Ϣ����һ�ε�¼��ʾ�û���������*/
		if(b == true) {
			JLnew.setText("Ĭ���û�����ph    Ĭ�����룺123456");
		}

		/*��������Ϊ���ɼ�����**/
		JPpassword.setEchoChar('*');			/*����������ʾ**/
		
		/*�¼�����*/
		JTuser.addActionListener(this);			/*�û���*/
		JPpassword.addActionListener(this);		/*����*/
		JBclearuser.addActionListener(this);	/*����û���*/
		JBclearpassword.addActionListener(this);/*�������*/
		JBlogin.addActionListener(this);		/*��¼*/
		JBexit.addActionListener(this);			/*�˳�*/
		
		/*ȥ�������ť�߿�*/
		JBclearuser.setBorderPainted(false);	
		JBclearpassword.setBorderPainted(false);
		/*���������ť͸��*/
		JBclearuser.setContentAreaFilled(false);
		JBclearpassword.setContentAreaFilled(false);
		
		/*�����������ӵ�����JP��*/
		JP.add(JLuser);
		JP.add(JLpassword);
		JP.add(JLstate);
		JP.add(JLnew);
		JP.add(JTuser);
		JP.add(JPpassword);
		JP.add(JBclearuser);
		JP.add(JBclearpassword);
		JP.add(JBlogin);
		JP.add(JBexit);
		JP.add(JLbackground);
	}

	/*�¼�����*/
	@Override
	public void actionPerformed(ActionEvent e) {
		
		/*����û��������*/
		if(e.getSource() == JBclearuser) {
			JTuser.setText("");				
		/*������������*/
		}else if(e.getSource() == JBclearpassword) {
			JPpassword.setText("");
		/*�����¼��ť*/
		}else if(e.getSource() == JBlogin){
			/*������֤�û����������Ƿ�������ȷ���෽��*/
			if(Verify.verify_check(JTuser.getText(),String.valueOf(JPpassword.getPassword()))) {
				dispose();	/*�رյ�ǰ����*/
				new Menu();
			} else {
				JLstate.setText("�û����������������");
			}
		/*����˳���ť*/
		}else if(e.getSource() == JBexit) {
				System.exit(0);		/*�˳�����*/
		}

	}
}
