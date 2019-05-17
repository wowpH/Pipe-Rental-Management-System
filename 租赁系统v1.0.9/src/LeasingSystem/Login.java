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
	
	/*序列化*/
	private static final long serialVersionUID = 1L;

	/*声明一个JPanel容器对象，可以添加各种组件*/
	private JPanel JP = new JPanel();

	/*标签*/
	private JLabel JLuser = new JLabel("用户名");		/*用户名*/
	private JLabel JLpassword = new JLabel("密  码");	/*密码*/
	private JLabel JLstate = new JLabel();			/*状态*/
	private JLabel JLnew = new JLabel("");			/*提示状态*/
	private JLabel JLbackground;					/*背景图片*/
	
	/*按钮*/
	private JButton JBlogin = new JButton("登录");		/*登录*/
	private JButton JBexit = new JButton("退出");		/*退出*/
	private JButton JBclearuser = new JButton("×");		/*用户名清除*/
	private JButton JBclearpassword = new JButton("×");	/*密码清除*/

	/*输入框*/
	private JTextField JTuser = new JTextField();				/*用户名*/
	private JPasswordField JPpassword = new JPasswordField();	/*密码*/

	static boolean b = false;	/*用于判断是否显示提示，默认不提示*/

	/*图片*/
	Image imgtitle;		/*标题栏图标*/
	Image imgbackground;/*登录界面背景图片*/
	
	/*初始化窗口*/
	public Login() {
		
		/*初始化容器JP*/
		init();
		
		/*设置窗体标题栏图标*/
		imgtitle = new ImageIcon("guan.png").getImage();
		this.setIconImage(imgtitle);
		
		this.add(JP);			/*注意这段，必须将Jpanel组件添加到界面中*/
		this.setTitle("登录");		/*设置窗口名称*/
		this.setVisible(true);			/*窗口可视化*/
		this.setSize(500, 350);				/*窗口大小，(width,height)*/
		this.setResizable(false);				/*窗口不能调整大小*/
		this.setLocationRelativeTo(null);				/*让窗体居中显示*/
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);/*点击关闭后执行的操作，即关闭窗口*/

	}
	
	/*初始化容器JP*/
	public void init() {
		
		JP.setLayout(null);	/*采用固定组件坐标模式，需要设置布局管理器为空*/
		
		/*背景图片，1.jpg是当前项目文件夹下的图片，从文件夹将图片放入到src所在目录即可*/
		imgbackground = new ImageIcon("background1.jpg").getImage();
		JLbackground = new JLabel(new ImageIcon(imgbackground));
		
		/*将组件添加到JPanel容器中*/
		JLuser.setBounds(80, 50, 64, 20);			/*用户名*/
		JLpassword.setBounds(80,120,64,20);			/*密码*/
		JLstate.setBounds(10, 270, 480, 20);		/*状态*/
		JLnew.setBounds(110, 160, 290, 20);			/*提示默认信息*/
		JTuser.setBounds(170,45,250,30);			/*用户名输入框*/
		JPpassword.setBounds(170,115,250,30);		/*密码输入框*/
		JBclearuser.setBounds(405, 45, 55, 30);		/*用户名清除按钮*/
		JBclearpassword.setBounds(405, 115, 55, 30);/*密码清除按钮*/
		JBlogin.setBounds(100, 190, 100, 40);		/*登录按钮*/
		JBexit.setBounds(300, 190, 100, 40);		/*退出按钮*/
		JLbackground.setBounds(0, 0, 500, 350);		/*背景图片*/
		
		/*字体*/
		JLuser.setFont(Main.f20);			/*用户名*/
		JLpassword.setFont(Main.f20);		/*密码*/
		JLstate.setFont(Main.f20);			/*状态*/
		JLnew.setFont(Main.f15);			/*提示默认信息*/
		JTuser.setFont(Main.f20);			/*用户名输入框*/
		JPpassword.setFont(Main.f20);		/*密码输入框*/
		JBclearuser.setFont(Main.f20);		/*用户名清除按钮*/
		JBclearpassword.setFont(Main.f20);	/*密码清除按钮*/
		JBlogin.setFont(Main.f25bi);		/*登录按钮*/
		JBexit.setFont(Main.f25bi);			/*退出按钮*/
		
		/*设置字的颜色*/
		JLstate.setForeground(Color.WHITE);
		JLnew.setForeground(Color.RED);
		JBclearuser.setForeground(Color.WHITE);
		JBclearpassword.setForeground(Color.WHITE);
		
		/*提示默认信息，第一次登录提示用户名和密码*/
		if(b == true) {
			JLnew.setText("默认用户名：ph    默认密码：123456");
		}

		/*密码设置为不可见，即**/
		JPpassword.setEchoChar('*');			/*密码输入显示**/
		
		/*事件监听*/
		JTuser.addActionListener(this);			/*用户名*/
		JPpassword.addActionListener(this);		/*密码*/
		JBclearuser.addActionListener(this);	/*清除用户名*/
		JBclearpassword.addActionListener(this);/*清除密码*/
		JBlogin.addActionListener(this);		/*登录*/
		JBexit.addActionListener(this);			/*退出*/
		
		/*去掉清除按钮边框*/
		JBclearuser.setBorderPainted(false);	
		JBclearpassword.setBorderPainted(false);
		/*设置清除按钮透明*/
		JBclearuser.setContentAreaFilled(false);
		JBclearpassword.setContentAreaFilled(false);
		
		/*将所有组件添加到容器JP中*/
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

	/*事件监听*/
	@Override
	public void actionPerformed(ActionEvent e) {
		
		/*清除用户名输入框*/
		if(e.getSource() == JBclearuser) {
			JTuser.setText("");				
		/*清除密码输入框*/
		}else if(e.getSource() == JBclearpassword) {
			JPpassword.setText("");
		/*点击登录按钮*/
		}else if(e.getSource() == JBlogin){
			/*调用验证用户名和密码是否输入正确的类方法*/
			if(Verify.verify_check(JTuser.getText(),String.valueOf(JPpassword.getPassword()))) {
				dispose();	/*关闭当前窗口*/
				new Menu();
			} else {
				JLstate.setText("用户名或密码输入错误");
			}
		/*点击退出按钮*/
		}else if(e.getSource() == JBexit) {
				System.exit(0);		/*退出程序*/
		}

	}
}
