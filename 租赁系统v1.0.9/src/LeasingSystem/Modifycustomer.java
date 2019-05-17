package LeasingSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Modifycustomer extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JPanel JP = new JPanel();
	
	private JLabel JLid = new JLabel("编号");
	private JLabel JLname = new JLabel("姓名");
	private JLabel JLtel = new JLabel("电话");
	private JLabel JLaddr = new JLabel("地址");
	
	private JTextField JTid = new JTextField();
	private JTextField JTname = new JTextField();
	private JTextField JTtel = new JTextField();
	private JTextField JTaddr = new JTextField();

	private JButton JBconfirm = new JButton("确认");
	private JButton JBcancel = new JButton("取消");

	Connection c;
	Statement s;
	String sql;
	String idn;
	String namen;
	String teln;
	String addrn;

	
	public Modifycustomer(String id,String name,String tel,String addr) {
		
		idn = id;
		namen = name;
		teln = tel;
		addrn = addr;

		init();
		
		this.add(JP);
		this.setVisible(true);
		this.setSize(400, 400);
		this.setResizable(false);
		this.setLocation(300, 300);
		this.setTitle("修改客户信息");

		
		
	}
	
	private void init() {
		
		JP.setLayout(null);
		
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
		JBconfirm.setBounds(70, 280, 100, 40);
		JBcancel.setBounds(230, 280, 100, 40);
		
		/*输入框显示要修改的客户信息*/
		JTid.setText(idn);
		JTname.setText(namen);
		JTtel.setText(teln);
		JTaddr.setText(addrn);
		
		/*id不可更改*/
		JTid.setEditable(false);
		
		/*监听器*/
		JTname.addActionListener(this);
		JTtel.addActionListener(this);
		JTaddr.addActionListener(this);
		JBconfirm.addActionListener(this);
		JBcancel.addActionListener(this);

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

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == JBcancel) {
			dispose();
		}else if(e.getSource() == JBconfirm) {
			updatemysql();
		}
		
	}
	
	private void updatemysql() {
		
		namen = JTname.getText();
		teln = JTtel.getText();
		addrn = JTaddr.getText();
		
		if(namen.equals("") || teln.equals("") || addrn.equals("")) {
			JOptionPane.showMessageDialog(null, "【17】信息不全，无法保存", "修改错误", JOptionPane.WARNING_MESSAGE);
		}else if(teln.length() != 11) {
			JOptionPane.showMessageDialog(null, "【16】电话号码不合法", "添加失败", JOptionPane.ERROR_MESSAGE);
		}else {
			/*修改数据库数据*/
			c = Connect.getConnection();
			sql = "UPDATE customer SET 姓名='" + namen 
					+ "',电话='" + teln 
					+ "',住址='" + addrn + "' "
					+ "WHERE 客户编号='" + idn + "';";

			try {
				s = c.createStatement();
				s.executeUpdate(sql);
				s.close();
				JOptionPane.showMessageDialog(null, "【18】修改客户信息成功", "修改成功", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "【19】更新数据库客户信息失败", "修改失败", JOptionPane.ERROR_MESSAGE);
			}
			Connect.closeConnection(c);
			dispose();
		}
		
	}

}
