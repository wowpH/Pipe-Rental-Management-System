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
	
	/*容器*/
	private JPanel JP = new JPanel();
	
	/*标签*/
	private JLabel JLid = new JLabel("编号");
	private JLabel JLname = new JLabel("姓名");
	private JLabel JLtel = new JLabel("电话");
	private JLabel JLaddr = new JLabel("住址");
	private JLabel JLbackground;	/*背景*/

	/*按钮*/
	private JButton JBsave = new JButton("保存");
	private JButton JBback = new JButton("返回");

	/*输入框*/
	private JTextField JTid = new JTextField("");
	private JTextField JTname = new JTextField();
	private JTextField JTtel = new JTextField();
	private JTextField JTaddr = new JTextField();

	String id;		/*保存生成的客户编号*/
	private int width = 400;	/*窗口宽高*/
	
	/*图片*/
	Image imgtitle;
	Image imgbackground;
	
	public Addcustomer() {
		
		generateid();	/*生成客户编号*/
		
		init();
		
		/*设置窗体标题栏图标*/
		imgtitle = new ImageIcon("guan.png").getImage();
		this.setIconImage(imgtitle);
		
		this.add(JP);
		this.setTitle("添加客户");	/*设置窗口名称*/
		this.setResizable(false);	/*窗口不能调整大小*/
		this.setSize(width, width);	/*窗口大小，(width,height)*/
		this.setLocation(200, 200);	/*窗口位置*/
		this.setVisible(true);		/*窗口可视化*/
		
	}
	
	/*
	 * 生成客户编号，日期当天的订单数加1，假设一天不会超过999个订单，例如：20180927001
	 */
	private void generateid() {
		
		Connection c = Connect.getConnection();
		String count_num = "SELECT COUNT(*) FROM customer;";	/*统计表中有多少个客户*/
		try {
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery(count_num);
			r.next();
			String id_form = r.getString("COUNT(*)");	/*获取统计结果*/
			int id_int = Integer.parseInt(id_form) + 1;	/*将字符串行的结果转换为int型，并加1*/
			id = String.valueOf(id_int);		/*将生成的int型客户编号转换为字符串型*/
			JTid.setText(id);				/*将生成的编号显示在输入框中*/
			JTid.setEnabled(false);		/*将输入框设置为不可编辑，避免客户编号混乱*/
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "【8】生成客户编号失败", "添加客户失败", JOptionPane.ERROR_MESSAGE);
		}
		Connect.closeConnection(c);
		
	}
	
	/*初始化容器*/
	public void init() {
		
		JP.setLayout(null);
		
		imgbackground = new ImageIcon("background3.jpg").getImage();
		JLbackground = new JLabel(new ImageIcon(imgbackground));
		
		/*字体*/
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

		/*组件位置*/
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

		/*事件监听*/
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
	
	/*保存到数据库*/
	private void save() {
		
		/*不能添加空用户*/
		if(JTname.getText().equals("") || JTtel.getText().equals("") || JTaddr.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "【9】不能为空", "添加失败", JOptionPane.WARNING_MESSAGE);
		}else if(JTtel.getText().length() != 11) {
			JOptionPane.showMessageDialog(null, "【39】电话号码不合法", "添加失败", JOptionPane.ERROR_MESSAGE);
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
				JOptionPane.showMessageDialog(null, "【10】客户添加成功", "添加成功", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "【11】数据库更新失败", "添加失败", JOptionPane.ERROR_MESSAGE);
			}
			
			Connect.closeConnection(c);	/*这个要放在后面*/
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
