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
	
	/*标签*/
	private JLabel JLid = new JLabel("编号");
	private JLabel JLname = new JLabel("姓名");
	private JLabel JLtel = new JLabel("电话");
	private JLabel JLaddr = new JLabel("住址");
	private JLabel JLbackground;	/*背景*/

	/*输入框*/
	private JTextField JTid = new JTextField();
	private JTextField JTname = new JTextField();
	private JTextField JTtel = new JTextField();
	private JTextField JTaddr = new JTextField();

	/*按钮*/
	private JButton JBconfirm = new JButton("确认");
	private JButton JBcancel = new JButton("取消");
	private JButton JBmodify = new JButton("修改");

	boolean b;	/*标志修改按钮是否可点击*/
	
	/*查询的时候，默认什么都没输入，查询所有客户，所以初始化为空字符串*/
	String id = "";	
	String name = "";
	String tel = "";
	String addr = "";
	
	private int width = 400;	/*窗口的宽和高*/

	Showcustomer sc;		/*声明表格类Showcustomer对象*/
	
	/*图片*/
	Image imgtitle;
	Image imgbackground;

	public Searchcustomer() {
		
		init();
		
		/*设置窗体标题栏图标*/
		imgtitle = new ImageIcon("guan.png").getImage();
		this.setIconImage(imgtitle);
		
		this.add(JP);
		this.setVisible(true);		/*可视化*/
		this.setTitle("查询客户");	/*标题*/
		this.setResizable(false);	/*大小固定*/
		this.setSize(width, width);	/*大小*/
		this.setLocation(200, 200);	/*位置*/
		
	}
	
	private void init() {
		
		JP.setLayout(null);	/*设置布局为空*/
		
		imgbackground = new ImageIcon("background3.jpg").getImage();
		JLbackground = new JLabel(new ImageIcon(imgbackground));
		
		b = false;
		JBmodify.setEnabled(b);	/*默认不可点击修改按钮*/
		
		/*字体*/
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

		/*位置*/
		int left = 65;
		int up = 30;
		int width1 = 50;
		int height1 = 20;
		int width2 = 200;
		int height2 = 30;
		int hs = 60;	/*水平间距*/
		int vs = 60;	/*垂直间距*/
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

		/*监听器*/
		JTid.addActionListener(this);
		JTname.addActionListener(this);
		JTtel.addActionListener(this);
		JTaddr.addActionListener(this);
		JBconfirm.addActionListener(this);
		JBcancel.addActionListener(this);
		JBmodify.addActionListener(this);

		/*组件添加到容器中*/
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

		/*只要点击按钮，如果上次查询的窗口没关闭的话，关闭掉上次的窗口*/
		if(sc != null) {
			sc.dispose();
		}
		
		JBmodify.setEnabled(false);	/*初始化修改按钮关闭*/
		
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
				JOptionPane.showMessageDialog(null, "【15】请输入要修改的客户", "修改错误", JOptionPane.WARNING_MESSAGE);
			}else {
				new Modifycustomer(id,name,tel,addr);	/*修改*/
			}
		}
		
	}

}
