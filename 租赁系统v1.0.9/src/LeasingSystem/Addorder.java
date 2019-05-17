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
	
	/*标签*/
	private JLabel JLorderid = new JLabel("订单编号");
	private JLabel JLcustomerid = new JLabel("客户编号");
	private JLabel JLday = new JLabel("  日期");
	private JLabel JLstate = new JLabel("  状态");
	private JLabel JLm6 = new JLabel("  6米");
	private JLabel JLm5 = new JLabel("  5米");
	private JLabel JLm4 = new JLabel("  4米");
	private JLabel JLm3 = new JLabel("  3米");
	private JLabel JLm2 = new JLabel("  2米");
	private JLabel JLm1 = new JLabel("  1米");
	private JLabel JLbackground;	/*背景*/
	
	/*按钮*/
	private JButton JBsave = new JButton("保存");
	private JButton JBback = new JButton("返回");

	/*输入框*/
	private JTextField JTorderid = new JTextField();
	private JTextField JTcustomerid = new JTextField();
	private JTextField JTday = new JTextField();
	/*下拉列表选择框*/
	String[] cr = {"出库","入库"};
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
	String day;		/*日期*/
	String orderid = "";	/*存放订单编号*/
	
	/*窗口宽和高*/
	int width = 400;
	int height = 600;
	
	/*图片*/
	Image imgtitle;
	Image imgbackground;

	public Addorder() {
		
		builddate();	/*生成日期*/
		
		buildorder();	/*生成订单号*/

		init();

		/*标题图标*/
		imgtitle = new ImageIcon("guan.png").getImage();
		this.setIconImage(imgtitle);
		
		/*窗口设置*/
		this.add(JP);
		this.setVisible(true);		/*窗口可视化*/
		this.setTitle("添加记录");	/*设置窗口名称*/
		this.setResizable(false);	/*窗口不能调整大小*/
		this.setLocation(200, 200);		/*窗口位置*/
		this.setSize(width, height);	/*窗口大小，(width,height)*/

	}
	
	/*生成日期*/
	private void builddate() {
		
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");	/*大小写有区别*/
		day = d.format(new Date());
		JTday.setText(day);	/*将日期写入日期输入框中*/
		
	}
	
	/*生成订单号，订单号有11位，前8位是日期，后3位是当天订单数加1*/
	private void buildorder() {
		
		/*去掉-*/
		String[] days = day.split("-");
		int len = days.length;
		for(int i = 0; i < len; i++) {
			orderid = orderid + days[i].toString();
		}
		
		/*
		 * 获取当天订单数
		 */
		c = Connect.getConnection();
		String num_s = null;	/*存放统计的数量，字符串型的，这里要初始化*/
		String sql_num = "SELECT COUNT(*) FROM orders WHERE 日期 = '" + day + "';";
		try {
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery(sql_num);
			r.next();
			num_s = r.getString("COUNT(*)");
			r.close();
			s.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "【20】添加订单失败", "添加失败", JOptionPane.ERROR_MESSAGE);
		}
		
		Connect.closeConnection(c);
		
		int num_i = Integer.parseInt(num_s) + 1;	/*订单数加1*/
		String num_new = String.valueOf(num_i);
		/*不足3位，前面加0*/
		for(int i = 3; i > num_new.length(); i--) {
			orderid = orderid + "0";
		}
		orderid = orderid + num_new;
		JTorderid.setText(orderid);
		
	}

	private void init() {
		
		JP.setLayout(null);
		
		/*背景图片*/
		imgbackground = new ImageIcon("background2.jpg").getImage();
		JLbackground = new JLabel(new ImageIcon(imgbackground));
		
		/*设置标签字体*/
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
		
		/*输入框字体*/
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

		/*按钮字体*/
		JBsave.setFont(Main.f25bi);
		JBback.setFont(Main.f25bi);
		
		/*标签文字颜色*/
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
		/*按钮文字颜色*/
		JBsave.setForeground(Color.WHITE);
		JBback.setForeground(Color.WHITE);
		
		/*设置按钮透明*/
		JBsave.setContentAreaFilled(false);
		JBback.setContentAreaFilled(false);
		
		/*订单编号和日期不可编辑*/
		JTorderid.setEditable(false);
		JTday.setEditable(false);
		
		/*组件位置*/
		int left = 50;
		int up = 50;
		int width1 = 90;
		int height1 = 20;
		int width2 = 200;
		int height2 = 30;
		int hs = 10;
		int vs = 40;
		/*设置标签在容器中的位置*/
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
		/*设置输入框在容器中的位置*/
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
		/*按钮位置*/
		JBsave.setBounds(70, 500, 100, 40);
		JBback.setBounds(230, 500, 100, 40);
		/*图片位置*/
		JLbackground.setBounds(0, 0, width, height);

		/*添加事件监听器*/
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
		
		/*将标签添加到容器中*/
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
		/*将输入框添加到容器中*/
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
		/*将按钮添加到容器中*/
		JP.add(JBsave);
		JP.add(JBback);
		/*图片添加到容器中*/
		JP.add(JLbackground);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == JBback) {
			dispose();
		}else if(e.getSource() == JBsave) {
			if(JTcustomerid.getText().equals("")) {	/*如果未填写客户*/
				JOptionPane.showMessageDialog(null, "【38】请先填写客户编号", "添加错误", JOptionPane.WARNING_MESSAGE);
			}else {
				iscustomerexist();	/*判断客户是否存在*/				
			}
		}
		
	}
	
	/*判断客户是否存在，若存在保存到数据库*/
	private void iscustomerexist() {
		
		c = Connect.getConnection();
		String customer = JTcustomerid.getText();	/*获取输入框的客户编号*/
		String sql_cus = "SELECT 客户编号 FROM customer WHERE 客户编号 = '" + customer +"';";
		
		try {
			s = c.createStatement();
			r = s.executeQuery(sql_cus);
			/*存在*/
			if(r.next()) {
				r.close();
				s.close();
				Connect.closeConnection(c);
				isinputlegal();	/*判断输入是否合法*/
			/*不存在，返回添加客户*/
			}else {
				r.close();
				s.close();
				Connect.closeConnection(c);
				JOptionPane.showMessageDialog(null, "【21】客户不存在，请先添加客户", "添加错误", JOptionPane.WARNING_MESSAGE);
				dispose();
				new Addcustomer();	/*客户不存在，关闭订单窗口，弹出添加客户界面*/
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "【22】添加订单失败", "添加失败", JOptionPane.ERROR_MESSAGE);
			dispose();
		}
		
	}
	
	/*判断输入是否合法*/
	private void isinputlegal() {
		
		/*判断输入框里面是否有空的，有的话将输入框修改为0*/
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
		/*如果输入框全是0，弹框提示，无法添加*/
		if (JTm6.getText().equals("0") && 
			JTm5.getText().equals("0") && 
			JTm4.getText().equals("0") && 
			JTm3.getText().equals("0") && 
			JTm2.getText().equals("0") && 
			JTm1.getText().equals("0")) {
			JOptionPane.showMessageDialog(null, "【23】订单数据不能全为0", "添加警告", JOptionPane.WARNING_MESSAGE);
		/*合法*/
		}else {
			updatemysql();	/*保存到数据库*/
		}
		
	}
	
	/*保存到数据库*/
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
			JOptionPane.showMessageDialog(null, "【24】订单添加成功", "添加成功", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "【25】添加订单失败", "添加失败", JOptionPane.ERROR_MESSAGE);
		}

		Connect.closeConnection(c);
		dispose();
	}

}
