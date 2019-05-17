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

	/*��������������*/
	private JScrollPane jsp;
	
	Vector rowdata,columnname;	/*rowdata�������ݣ�columnname��������*/
	JTable jt;	/*������*/
	Vector row;	/*���ÿһ�е�����*/
	
	Connection c;
	Statement s;
	ResultSet r;
	int num;		/*��ѯ���Ķ�����*/
	String query;	/*����sql��䣬���ڲ�ѯ����*/
	Image imgtitle;	/*������ͼƬ*/
	/*	������ţ��ͻ���ţ����ڣ�״̬��
	 * 	����û�����ݣ����ط������ͻ��������Ҫ��ʼ��Ϊ���ַ���
	 */
	String orderidn = "";
	String customeridn = "";
	String dayn = "";
	String staten = "";
	/*6�ף�5�ף�4�ף�3�ף�2�ף�1�׸ֹܵ���������ʼ��Ϊ0*/
	int m6n = 0;
	int m5n = 0;
	int m4n = 0;
	int m3n = 0;
	int m2n = 0;
	int m1n = 0;
	
	public Showorder(String orderid,String customerid,String day,String state) {
		
		num = 0;	/*��ʼ���鵽0��*/
		
		columnname = new Vector();
		/*��������*/
		columnname.add("�������");
		columnname.add("�ͻ����");
		columnname.add("����");
		columnname.add("״̬");
		columnname.add("6��");
		columnname.add("5��");
		columnname.add("4��");
		columnname.add("3��");
		columnname.add("2��");
		columnname.add("1��");
		
		rowdata = new Vector();		/*rowdata���Դ�Ŷ��У������ݿ��ȡ*/
		
		/*
		 * ������ѯ���
		 */
		query = "SELECT * FROM orders";	/*��ѯ������*/
		
		boolean bo,bc,bd,bs;
		bo = orderid.equals("");
		bc = customerid.equals("");
		bd = day.equals("");
		bs = state.equals("");
		
		if(!bo || !bc || !bd || !bs) {	/*ֻҪ��һ�����գ���˵��������*/
			query = query + " WHERE ";
			if(!bo) {	/*������Ų���*/
				query = query + "�������='" + orderid + "'";
				if(!bc || !bd || !bs) {	/*������滹�������������������*/
					query = query + " AND ";
				}
			}
			if(!bc) {	/*�ͻ���Ų���*/
				query = query + "�ͻ����='" + customerid + "'";
				if(!bd || !bs) {
					query = query + " AND ";
				}
			}
			if(!bd) {	/*���ڲ���*/
				query = query + "���� LIKE '" + day + "%'";
				if(!bs) {
					query = query + " AND ";
				}
			}
			if(!bs) {
				query = query + "״̬='" + state + "'";
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
			JOptionPane.showMessageDialog(null, "��26��������ʾ�쳣", "��ѯʧ��", JOptionPane.ERROR_MESSAGE);
		}
		Connect.closeConnection(c);
		
		init();	/*��ʼ��jsp����*/
		
		/*ͼ��*/
		imgtitle = new ImageIcon("guan.png").getImage();
		this.setIconImage(imgtitle);
		
		/*���ֻ��ѯ��һ�����ݣ�Ҫ���ظ��޸Ľ��棬�����޸�*/
		if(num == 1) {
			orderidn = String.valueOf(jt.getValueAt(0, 0));	/*0,0��ʼ��*/
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
		
		/*����*/		
		this.add(jsp);
		this.setVisible(true);
		this.setSize(1000, 600);
		this.setTitle("������Ϣ");
		this.setLocation(700, 300);
		
		/*��ѯ���*/
		if(num == 0) {
			JOptionPane.showMessageDialog(null, "��27��û���ҵ����ϲ�ѯ�����Ķ�����Ϣ", "��ѯ���", JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, "��28����ѯ��" + num + "��������Ϣ", "��ѯ���", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private void init() {
		
		jt = new JTable(rowdata,columnname);	/*��ʼ����������*/
		
		jt.setRowHeight(50);	/*�����и�*/
		
		/*���ñ�ͷ*/
		JTableHeader head = jt.getTableHeader();
		head.setFont(Main.f20);	/*����*/		
		
		jt.setFont(Main.f20);	/*�������*/
		
		/*���ò����п�*/
		jt.getColumnModel().getColumn(0).setPreferredWidth(110);
		jt.getColumnModel().getColumn(2).setPreferredWidth(100);

		/*������־���*/
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		jt.getColumn("�������").setCellRenderer(render);
		jt.getColumn("�ͻ����").setCellRenderer(render);
		jt.getColumn("����").setCellRenderer(render);
		jt.getColumn("״̬").setCellRenderer(render);
		jt.getColumn("6��").setCellRenderer(render);
		jt.getColumn("5��").setCellRenderer(render);
		jt.getColumn("4��").setCellRenderer(render);
		jt.getColumn("3��").setCellRenderer(render);
		jt.getColumn("2��").setCellRenderer(render);
		jt.getColumn("1��").setCellRenderer(render);
		
		/*������ʼ����ʾ�ڽ���*/
		jsp = new JScrollPane(jt,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
	}
	
	/*��ȡ��ѯ�������ݸ�����1������true�����򷵻�false�������ж��Ƿ�ɵ���޸İ�ť*/
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
