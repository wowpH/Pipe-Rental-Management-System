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

public class Showcustomer extends JFrame {

	private static final long serialVersionUID = 1L;
	
	/*
	 * 将数据库中查询后的数据保存到JTable中
	 */
	Vector rowdata,columnname;	/*rowdata保存数据，columnname保存列名*/
	JTable jt;			/*声明表格对象*/
	JScrollPane jsp;	/*带滚动条的容器*/

	Connection c;
	Statement s;
	ResultSet r;
	String query;	/*查询的sql语句*/
	int num;		/*查询到的客户人数*/
	String idn = "";
	String namen = "";
	String teln = "";
	String addrn = "";
	
	Image imgtitle;		/*窗口标题栏图标*/
	
	public Showcustomer(String id,String name,String tel,String addr) {
		
		num = 0;	/*初始化为0*/
		
		c = Connect.getConnection();

		columnname = new Vector();
		/*设置列名*/
		columnname.add("客户编号");
		columnname.add("姓  名");
		columnname.add("电  话");
		columnname.add("住  址");
		
		rowdata = new Vector();	/*rowdata可以存放多行，从数据库读取*/

		query = "SELECT * FROM customer";

		/*标记获取的查询条件是否是空字符串*/
		boolean bi,bn,bt,ba;
		bi = id.equals("");
		bn = name.equals("");
		bt = tel.equals("");
		ba = addr.equals("");
		
		if(!bi || !bn || !bt || !ba) {	/*只要有一个不空就有条件需要WHERE*/
			query = query + " WHERE ";
			if(!bi) {		/*客户编号不空*/
				query = query + "客户编号='" + id + "'";
				if(!bn || !bt || !ba) {		/*如果后面还有其他查询条件，加AND*/
					query = query + " AND ";
				}
			}
			if(!bn) {
				query = query + "姓名 LIKE '%" + name + "%'";		/*姓名采用模糊查询，没什么用*/
				if(!bt || !ba) {
					query = query + " AND ";
				}
			}
			if(!bt) {
				query = query + "电话='" + tel + "'";
				if(!ba) {
					query = query + " AND ";
				}
			}
			if(!ba) {
				query = query + "住址 LIKE '%" + addr + "%'";		/*住址采用模糊查找*/
			}

		}
		query = query + ";";

		try {
			s = c.createStatement();
			r = s.executeQuery(query);
			
			while(r.next()) {
				Vector hang = new Vector();
				hang.add(r.getString(1));
				hang.add(r.getString(2));
				hang.add(r.getString(3));
				hang.add(r.getString(4));
				rowdata.add(hang);
				num++;
			}
			
			r.close();
			s.close();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "【12】客户查询失败", "查询失败", JOptionPane.ERROR_MESSAGE);
		}
		
		Connect.closeConnection(c);
		
		/*初始化表格*/
		jt = new JTable(rowdata,columnname);
		
		/*设置表格的高*/
		jt.setRowHeight(50);
		
		/*设置表头*/
		JTableHeader head = jt.getTableHeader();
		head.setFont(Main.f20);	/*字体*/		
		
		/*设置表格字体*/
		jt.setFont(Main.f20);
				
		/*设置表格文字居中*/
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		jt.getColumn("客户编号").setCellRenderer(render);
		jt.getColumn("姓  名").setCellRenderer(render);
		jt.getColumn("电  话").setCellRenderer(render);
		jt.getColumn("住  址").setCellRenderer(render);
		
		/*只有一组数据时，获取信息*/
		if(num == 1) {
			idn = String.valueOf(jt.getValueAt(0, 0));	/*获取列表的第一组数据用于修改客户信息，0 0 开始的*/
			namen = String.valueOf(jt.getValueAt(0, 1));
			teln = String.valueOf(jt.getValueAt(0, 2));
			addrn = String.valueOf(jt.getValueAt(0, 3));
		}

		/*滚动条始终显示在界面*/
		jsp = new JScrollPane(jt,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		imgtitle = new ImageIcon("guan.png").getImage();
		this.setIconImage(imgtitle);
		
		this.add(jsp);
		this.setVisible(true);
		this.setSize(600, 600);
		this.setTitle("客户信息");
		this.setLocationRelativeTo(null);

		if(num == 0) {
			JOptionPane.showMessageDialog(null, "【13】没有找到符合查询条件的客户信息", "查询结果", JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, "【14】查询到" + num + "条客户信息", "查询结果", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/*获取查询到数数据个数，1条返回true，否则返回false，用于判断是否可点击修改按钮*/
	public boolean canmodify() {
		if(num == 1) {
			return true;
		}
		return false;
	}
	
	/*获取要修改的客户编号*/
	public String getid() {
		return idn;
	}
	public String getname() {
		return namen;
	}
	public String gettel() {
		return teln;
	}
	public String getaddr() {
		return addrn;
	}
}
