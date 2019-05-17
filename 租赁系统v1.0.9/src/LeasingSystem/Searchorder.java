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
	
	/*标签*/
	private JLabel JLorderid = new JLabel("订单编号");
	private JLabel JLcustomerid = new JLabel("客户编号");
	private JLabel JLday = new JLabel("日期");
	private JLabel JLstate = new JLabel("状态");
	private JLabel JLbackground;	/*背景*/
	
	/*输入框*/
	private JTextField JTorderid = new JTextField();
	private JTextField JTcustomerid = new JTextField();
	private JTextField JTday = new JTextField();
	String[] scr = {"","出库","入库"};
	@SuppressWarnings({ "rawtypes", "unchecked" })
	JComboBox JCstate = new JComboBox(scr);
	
	/*按钮*/
	private JButton JBconfirm = new JButton("确认");
	private JButton JBcancel = new JButton("取消");
	private JButton JBmodify = new JButton("修改");
	private JButton JBdelete = new JButton("删除");
	
	boolean b;	/*标志修改按钮是否可点击，默认不可点击*/

	/*窗口宽和高*/
	int width = 500;
	int height = 400;
	
	Showorder so;	/*声明显示订单表格的类的对象*/
	Modifyorder mo;	/*修改订单的类的对象*/
	
	Image imgtitle,imgbackground;	/*窗口标题栏图标和背景图片*/
	
	/*	订单编号，客户编号，日期，状态，
	 * 	可能没有数据，所以要初始化为空字符串
	 */
	String orderid = "";
	String customerid = "";
	String day = "";
	String state = "";
	/*6米，5米，4米，3米，2米，1米钢管的数量，初始化为0*/
	int m6 = 0;
	int m5 = 0;
	int m4 = 0;
	int m3 = 0;
	int m2 = 0;
	int m1 = 0;

	/*构造方法*/
	public Searchorder() {
		
		init();	/*初始化JP*/
		
		imgtitle = new ImageIcon("guan.png").getImage();
		this.setIconImage(imgtitle);
		
		/*设置窗口*/
		this.add(JP);
		this.setVisible(true);
		this.setTitle("查询订单");
		this.setResizable(false);
		this.setLocation(200, 200);
		this.setSize(width, height);
		
	}
	
	/*初始化JP*/
	private void init() {
		
		JP.setLayout(null);	/*方便将组件自由放置*/
		
		imgbackground = new ImageIcon("background4.jpg").getImage();
		JLbackground = new JLabel(new ImageIcon(imgbackground));
		
		/*标签字体*/
		JLorderid.setFont(Main.f20);
		JLcustomerid.setFont(Main.f20);
		JLday.setFont(Main.f20);
		JLstate.setFont(Main.f20);
		/*输入框字体*/
		JTorderid.setFont(Main.f20);
		JTcustomerid.setFont(Main.f20);
		JTday.setFont(Main.f20);
		JCstate.setFont(Main.f20);
		/*按钮字体*/
		JBconfirm.setFont(Main.f20);
		JBcancel.setFont(Main.f20);
		JBmodify.setFont(Main.f20);
		JBdelete.setFont(Main.f20);

		/*
		 * 组件位置
		 */
		int left = 95;
		int up = 30;
		int width1 = 90;
		int height1 = 20;
		int width2 = 200;
		int height2 = 30;
		int hs = 100;
		int vs = 60;
		/*标签位置*/
		JLorderid.setBounds(left, up, width1, height1);
		JLcustomerid.setBounds(left, up+vs, width1, height1);
		JLday.setBounds(left, up+vs*2, width1, height1);
		JLstate.setBounds(left, up+vs*3, width1, height1);
		JLbackground.setBounds(0, 0, width, height);
		/*输入框位置*/
		JTorderid.setBounds(left+hs, up-5, width2, height2);
		JTcustomerid.setBounds(left+hs, up+vs-5, width2, height2);
		JTday.setBounds(left+hs, up+vs*2-5, width2, height2);
		JCstate.setBounds(left+hs, up+vs*3-5, width2, height2);
		/*按钮位置*/
		JBconfirm.setBounds(35, 290, 100, 40);
		JBcancel.setBounds(145, 290, 100, 40);
		JBmodify.setBounds(255, 290, 100, 40);
		JBdelete.setBounds(365, 290, 100, 40);
		
		/*监听器*/
		JTorderid.addActionListener(this);
		JTcustomerid.addActionListener(this);
		JTday.addActionListener(this);
		JCstate.addActionListener(this);
		JBconfirm.addActionListener(this);
		JBcancel.addActionListener(this);
		JBmodify.addActionListener(this);
		JBdelete.addActionListener(this);

		/*初始化修改按钮不可用*/
		b = false;
		JBmodify.setEnabled(b);
		/*删除按钮不可用*/
		JBdelete.setEnabled(b);
		
		/*将标签添加到JP中*/
		JP.add(JLorderid);
		JP.add(JLcustomerid);
		JP.add(JLday);
		JP.add(JLstate);
		/*将输入框添加到JP中*/
		JP.add(JTorderid);
		JP.add(JTcustomerid);
		JP.add(JTday);
		JP.add(JCstate);
		/*将按钮添加到JP中*/
		JP.add(JBconfirm);
		JP.add(JBcancel);
		JP.add(JBmodify);
		JP.add(JBdelete);
		/*将图片标签添加到JP中，这个要放在最后，放在前面，界面显示错误*/
		JP.add(JLbackground);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		/*如果上个窗口没关闭，先关闭上个窗口*/
		if(so != null) {
			so.dispose();
		}
		/*如果修改窗口未关闭*/
		if(mo != null) {
			mo.dispose();
		}
		
		JBdelete.setEnabled(false);	/*删除按钮不可用*/
		
		if(e.getSource() == JBcancel) {
			dispose();
		}else if(e.getSource() == JBconfirm) {
			if(isdaylegal()) {	/*日期合法才能查询*/
				so = new Showorder(JTorderid.getText(),JTcustomerid.getText(),JTday.getText(),String.valueOf(JCstate.getSelectedItem()));
				initmodify();	
				JBdelete.setEnabled(true);	/*删除按钮可用*/
			}else {
				JOptionPane.showMessageDialog(null, "【33】日期输入不合法，正确的格式有：\n    按年查询： 2018\n    按月查询： 2018-09\n    按日查询： 2018-09-01", "查询错误", JOptionPane.ERROR_MESSAGE);
			}
		}else if(e.getSource() == JBmodify) {
			islegalmodify();	/*是否合法，能否修改*/
			JBmodify.setEnabled(false);		/*不可用*/
		}else if(e.getSource() == JBdelete) {
			int op = JOptionPane.showConfirmDialog(null, "【34】删除查询到的列表记录？", "删除警告", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(op ==JOptionPane.OK_OPTION) {
				new Deleteorder(JTorderid.getText(),JTcustomerid.getText(),JTday.getText(),String.valueOf(JCstate.getSelectedItem()));
			}
		}
		
	}
	
	/*初始化数据，为修改做准备*/
	private void initmodify(){
		/*获取是否能修改数据，条件见Showorder类的canmodify方法*/
		b = so.canmodify();
		if(b) {	/*可以修改的话，获取要修改的数据，作为Modifyorder方法的形参*/
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
	
	/*判断是否合法修改*/
	private void islegalmodify() {
		if(orderid.equals("")) {
			JOptionPane.showMessageDialog(null, "【29】请输入要修改的订单", "修改错误", JOptionPane.WARNING_MESSAGE);
		}else {
			mo = new Modifyorder(orderid,customerid,day,state,m6,m5,m4,m3,m2,m1);	/*可以修改*/
		}
	}
	
	/*日期输入是否合法*/
	private boolean isdaylegal() {
		int len = JTday.getText().length();
		if(len == 0 || len == 4 || len == 7 || len == 10) {
			return true;
		}
		return false;
	}

}
