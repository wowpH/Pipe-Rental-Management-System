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

	private JPanel JPbutton = new JPanel();		/*添加按钮的容器*/

	private JButton JBaddorder = new JButton("添加记录");			/*添加记录*/
	private JButton JBsearchorder = new JButton("查询记录");		/*查询记录*/
	private JButton JBaddcustomer = new JButton("添加客户");		/*添加客户*/
	private JButton JBsearchcustomer = new JButton("查询客户");	/*查询客户*/
	private JButton JBchangepassword = new JButton("修改密码");	/*修改密码*/
	private JButton JBexit = new JButton("退出系统");				/*退出系统*/
	
	private JLabel JLimg;
	
	/*图片*/
	Image imgtitle;
	Image imgbackground;
	
	/*常量*/
	private int width = 400;	/*窗口宽*/
	private int height = 600;	/*窗口高*/
	private int x = 100;		/*窗口横坐标*/
	private int y = 100;		/*窗口竖坐标*/
	private int left = 40;		/*容器中按钮的横坐标*/
	private int up = 50;		/*容器中第一个按钮的竖坐标*/
	private int vs = 70;		/*容器中按钮的上边沿与上边沿的距离*/
	private int width2 = 150;	/*按钮的宽*/
	private int height2 = 50;	/*按钮的高*/
	
	/*各个按钮对应的类的对象*/
	Addorder ad = null;
	Searchorder so = null;
	Addcustomer ac = null;
	Searchcustomer sc = null;
	Changepassword cp = null;
	
	public Menu() {
		
		init();
		
		/*标题图标*/
		imgtitle = new ImageIcon("guan.png").getImage();
		this.setIconImage(imgtitle);
		
		this.add(JPbutton);		/*容器添加到窗口中*/
		this.setVisible(true);		/*可视化*/
		this.setLocation(x, y);			/*位置*/
		this.setResizable(false);			/*窗口不能调整大小*/
		this.setSize(width, height);			/*大小*/
		this.setTitle("租赁系统V1.0.9");					/*窗口名称*/
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	/*点击叉号，关闭程序*/
		
	}
	
	public void init() {
		
		/*设置容器的布局管理器*/
		JPbutton.setLayout(null);
		
		/*容器采用凸起边框，不是很明显,可不要*/
		JPbutton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		
		/*背景图片*/
		imgbackground = new ImageIcon("background2.jpg").getImage();
		JLimg = new JLabel(new ImageIcon(imgbackground));
		
		/*设置组件的字体*/
		JBaddorder.setFont(Main.f20);
		JBsearchorder.setFont(Main.f20);
		JBaddcustomer.setFont(Main.f20);
		JBsearchcustomer.setFont(Main.f20);
		JBchangepassword.setFont(Main.f20);
		JBexit.setFont(Main.f20);
		
		/*设置按钮字的颜色*/
		JBaddorder.setForeground(Color.WHITE);
		JBsearchorder.setForeground(Color.WHITE);
		JBaddcustomer.setForeground(Color.WHITE);
		JBsearchcustomer.setForeground(Color.WHITE);
		JBchangepassword.setForeground(Color.WHITE);
		JBexit.setForeground(Color.WHITE);

		/*设置组件的位置*/
		JBaddorder.setBounds(left, up, width2, height2);
		JBsearchorder.setBounds(left, up+vs, width2, height2);
		JBaddcustomer.setBounds(left, up+vs*2, width2, height2);
		JBsearchcustomer.setBounds(left, up+vs*3, width2, height2);
		JBchangepassword.setBounds(left, up+vs*4, width2, height2);
		JBexit.setBounds(left, up+vs*5, width2, height2);
		JLimg.setBounds(0, 0, width, height);
		
		/*将按钮设置透明*/
		JBaddorder.setContentAreaFilled(false);
		JBsearchorder.setContentAreaFilled(false);
		JBaddcustomer.setContentAreaFilled(false);
		JBsearchcustomer.setContentAreaFilled(false);
		JBchangepassword.setContentAreaFilled(false);
		JBexit.setContentAreaFilled(false);
		
		/*添加事件监听器*/
		JBaddorder.addActionListener(this);
		JBsearchorder.addActionListener(this);
		JBaddcustomer.addActionListener(this);
		JBsearchcustomer.addActionListener(this);
		JBchangepassword.addActionListener(this);
		JBexit.addActionListener(this);

		/*将按钮组件添加到相应的容器中*/
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

		closeoldwindows();	/*关闭旧的窗口*/
		
		if(e.getSource() == JBaddorder) {	/*添加记录，*/
			ad = new Addorder();
		} else if(e.getSource() == JBsearchorder) {	/*查询记录*/
			so = new Searchorder();
		} else if(e.getSource() == JBaddcustomer) {	/*添加客户*/
			ac = new Addcustomer();
		} else if(e.getSource() == JBsearchcustomer) {	/*查询客户*/
			sc = new Searchcustomer();
		} else if(e.getSource() == JBchangepassword) {	/*修改密码*/
			cp = new Changepassword();
		} else if(e.getSource() == JBexit) {	/*退出*/
			System.exit(0);		
		}
		
	}
	
	/*关闭旧的窗口*/
	private void closeoldwindows() {
		if(ad != null) {	/*上一个添加记录的窗口没关闭*/
			ad.dispose();
		}
		if(so != null) {	/*查询窗口没关闭*/
			so.dispose();
		}
		if(ac != null) {	/*添加客户没关闭*/
			ac.dispose();
		}
		if(sc != null) {	/*查询客户没关闭*/
			sc.dispose();
		}
		if(cp != null) {	/*修改密码没关闭*/
			cp.dispose();
		}
	}

}
