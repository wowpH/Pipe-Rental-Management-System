package LeasingSystem;

import java.awt.Dimension;
import java.awt.Image;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class Showorder extends JFrame {

	private static final long serialVersionUID = 1L;

	/*带滚动条的容器*/
	private JScrollPane jsp;
	
	Vector rowdata,columnname;	/*rowdata保存数据，columnname保存列名*/
	JTable jt;	/*表格对象*/
	Vector row;	/*存放每一行的数据*/
	
	Connection c;
	Statement s;
	ResultSet r;
	int num;		/*查询到的订单数*/
	String query;	/*保存sql语句，用于查询订单*/
	Image imgtitle;	/*标题栏图片*/
	/*	订单编号，客户编号，日期，状态，
	 * 	可能没有数据，返回方法及就会出错，所以要初始化为空字符串
	 */
	String orderidn = "";
	String customeridn = "";
	String dayn = "";
	String staten = "";
	/*6米，5米，4米，3米，2米，1米钢管的数量，初始化为0*/
	int m6n = 0;
	int m5n = 0;
	int m4n = 0;
	int m3n = 0;
	int m2n = 0;
	int m1n = 0;
	
	public Showorder(String orderid,String customerid,String day,String state) {
		
		num = 0;	/*初始化查到0人*/
		
		columnname = new Vector();
		/*设置列名*/
		columnname.add("订单编号");
		columnname.add("客户编号");
		columnname.add("日期");
		columnname.add("状态");
		columnname.add("6米");
		columnname.add("5米");
		columnname.add("4米");
		columnname.add("3米");
		columnname.add("2米");
		columnname.add("1米");
		
		rowdata = new Vector();		/*rowdata可以存放多行，从数据库读取*/
		
		/*
		 * 产生查询语句
		 */
		query = "SELECT * FROM orders";	/*查询整个表*/
		
		boolean bo,bc,bd,bs;
		bo = orderid.equals("");
		bc = customerid.equals("");
		bd = day.equals("");
		bs = state.equals("");
		
		if(!bo || !bc || !bd || !bs) {	/*只要有一个不空，就说明有条件*/
			query = query + " WHERE ";
			if(!bo) {	/*订单编号不空*/
				query = query + "订单编号='" + orderid + "'";
				if(!bc || !bd || !bs) {	/*如果后面还有其他条件，继续添加*/
					query = query + " AND ";
				}
			}
			if(!bc) {	/*客户编号不空*/
				query = query + "客户编号='" + customerid + "'";
				if(!bd || !bs) {
					query = query + " AND ";
				}
			}
			if(!bd) {	/*日期不空*/
				query = query + "日期 LIKE '" + day + "%'";
				if(!bs) {
					query = query + " AND ";
				}
			}
			if(!bs) {
				query = query + "状态='" + state + "'";
			}
		}
		query = query + ";";
		
		c = Connect.getConnection();
		try {
			s = c.createStatement();
			r = s.executeQuery(query);
			while(r.next()) {
				row = new Vector();
				row.add(r.getString(1));
				row.add(r.getString(2));
				row.add(r.getString(3));
				row.add(r.getString(4));
				row.add(r.getString(5));
				row.add(r.getString(6));
				row.add(r.getString(7));
				row.add(r.getString(8));
				row.add(r.getString(9));
				row.add(r.getString(10));
				rowdata.add(row);
				num++;
			}
			r.close();
			s.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "【26】数据显示异常", "查询失败", JOptionPane.ERROR_MESSAGE);
		}
		Connect.closeConnection(c);
		
		init();	/*初始化jsp容器*/
		
		/*图标*/
		imgtitle = new ImageIcon("guan.png").getImage();
		this.setIconImage(imgtitle);
		
		/*如果只查询到一个数据，要返回给修改界面，方便修改*/
		if(num == 1) {
			orderidn = String.valueOf(jt.getValueAt(0, 0));	/*0,0开始的*/
			customeridn = String.valueOf(jt.getValueAt(0, 1));
			dayn = String.valueOf(jt.getValueAt(0, 2));
			staten = String.valueOf(jt.getValueAt(0, 3));
			m6n = Integer.parseInt(String.valueOf(jt.getValueAt(0, 4)));
			m5n = Integer.parseInt(String.valueOf(jt.getValueAt(0, 5)));
			m4n = Integer.parseInt(String.valueOf(jt.getValueAt(0, 6)));
			m3n = Integer.parseInt(String.valueOf(jt.getValueAt(0, 7)));
			m2n = Integer.parseInt(String.valueOf(jt.getValueAt(0, 8)));
			m1n = Integer.parseInt(String.valueOf(jt.getValueAt(0, 9)));
		}
		
		/*窗口*/		
		this.add(jsp);
		this.setVisible(true);
		this.setSize(1000, 600);
		this.setTitle("订单信息");
		this.setLocation(700, 300);
		
		/*查询结果*/
		if(num == 0) {
			JOptionPane.showMessageDialog(null, "【27】没有找到符合查询条件的订单信息", "查询结果", JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, "【28】查询到" + num + "条订单信息", "查询结果", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private void init() {
		
		jt = new JTable(rowdata,columnname);	/*初始化表格的内容*/
		
		jt.setRowHeight(50);	/*表格的行高*/
		
		/*设置表头*/
		JTableHeader head = jt.getTableHeader();
		head.setFont(Main.f20);	/*字体*/		
		
		jt.setFont(Main.f20);	/*表格字体*/
		
		/*设置部分列宽*/
		jt.getColumnModel().getColumn(0).setPreferredWidth(110);
		jt.getColumnModel().getColumn(2).setPreferredWidth(100);

		/*表格文字居中*/
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		jt.getColumn("订单编号").setCellRenderer(render);
		jt.getColumn("客户编号").setCellRenderer(render);
		jt.getColumn("日期").setCellRenderer(render);
		jt.getColumn("状态").setCellRenderer(render);
		jt.getColumn("6米").setCellRenderer(render);
		jt.getColumn("5米").setCellRenderer(render);
		jt.getColumn("4米").setCellRenderer(render);
		jt.getColumn("3米").setCellRenderer(render);
		jt.getColumn("2米").setCellRenderer(render);
		jt.getColumn("1米").setCellRenderer(render);
		
		/*滚动条始终显示在界面*/
		jsp = new JScrollPane(jt,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
	}
	
	/*获取查询到数数据个数，1条返回true，否则返回false，用于判断是否可点击修改按钮*/
	public boolean canmodify() {
		if(num == 1) {
			return true;
		}
		return false;
	}

	public String getorderid() {
		return orderidn;
	}
	
	public String getcustomerid() {
		return customeridn;
	}
	
	public String getday() {
		return dayn;
	}
	
	public String getstate() {
		return staten;
	}
	
	public int getm6() {
		return m6n;
	}
	
	public int getm5() {
		return m5n;
	}
	
	public int getm4() {
		return m4n;
	}
	
	public int getm3() {
		return m3n;
	}
	
	public int getm2() {
		return m2n;
	}
	
	public int getm1() {
		return m1n;
	}
	
}
