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
	 * �����ݿ��в�ѯ������ݱ��浽JTable��
	 */
	Vector rowdata,columnname;	/*rowdata�������ݣ�columnname��������*/
	JTable jt;			/*����������*/
	JScrollPane jsp;	/*��������������*/

	Connection c;
	Statement s;
	ResultSet r;
	String query;	/*��ѯ��sql���*/
	int num;		/*��ѯ���Ŀͻ�����*/
	String idn = "";
	String namen = "";
	String teln = "";
	String addrn = "";
	
	Image imgtitle;		/*���ڱ�����ͼ��*/
	
	public Showcustomer(String id,String name,String tel,String addr) {
		
		num = 0;	/*��ʼ��Ϊ0*/
		
		c = Connect.getConnection();

		columnname = new Vector();
		/*��������*/
		columnname.add("�ͻ����");
		columnname.add("��  ��");
		columnname.add("��  ��");
		columnname.add("ס  ַ");
		
		rowdata = new Vector();	/*rowdata���Դ�Ŷ��У������ݿ��ȡ*/

		query = "SELECT * FROM customer";

		/*��ǻ�ȡ�Ĳ�ѯ�����Ƿ��ǿ��ַ���*/
		boolean bi,bn,bt,ba;
		bi = id.equals("");
		bn = name.equals("");
		bt = tel.equals("");
		ba = addr.equals("");
		
		if(!bi || !bn || !bt || !ba) {	/*ֻҪ��һ�����վ���������ҪWHERE*/
			query = query + " WHERE ";
			if(!bi) {		/*�ͻ���Ų���*/
				query = query + "�ͻ����='" + id + "'";
				if(!bn || !bt || !ba) {		/*������滹��������ѯ��������AND*/
					query = query + " AND ";
				}
			}
			if(!bn) {
				query = query + "���� LIKE '%" + name + "%'";		/*��������ģ����ѯ��ûʲô��*/
				if(!bt || !ba) {
					query = query + " AND ";
				}
			}
			if(!bt) {
				query = query + "�绰='" + tel + "'";
				if(!ba) {
					query = query + " AND ";
				}
			}
			if(!ba) {
				query = query + "סַ LIKE '%" + addr + "%'";		/*סַ����ģ������*/
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
			JOptionPane.showMessageDialog(null, "��12���ͻ���ѯʧ��", "��ѯʧ��", JOptionPane.ERROR_MESSAGE);
		}
		
		Connect.closeConnection(c);
		
		/*��ʼ�����*/
		jt = new JTable(rowdata,columnname);
		
		/*���ñ��ĸ�*/
		jt.setRowHeight(50);
		
		/*���ñ�ͷ*/
		JTableHeader head = jt.getTableHeader();
		head.setFont(Main.f20);	/*����*/		
		
		/*���ñ������*/
		jt.setFont(Main.f20);
				
		/*���ñ�����־���*/
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		jt.getColumn("�ͻ����").setCellRenderer(render);
		jt.getColumn("��  ��").setCellRenderer(render);
		jt.getColumn("��  ��").setCellRenderer(render);
		jt.getColumn("ס  ַ").setCellRenderer(render);
		
		/*ֻ��һ������ʱ����ȡ��Ϣ*/
		if(num == 1) {
			idn = String.valueOf(jt.getValueAt(0, 0));	/*��ȡ�б�ĵ�һ�����������޸Ŀͻ���Ϣ��0 0 ��ʼ��*/
			namen = String.valueOf(jt.getValueAt(0, 1));
			teln = String.valueOf(jt.getValueAt(0, 2));
			addrn = String.valueOf(jt.getValueAt(0, 3));
		}

		/*������ʼ����ʾ�ڽ���*/
		jsp = new JScrollPane(jt,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		imgtitle = new ImageIcon("guan.png").getImage();
		this.setIconImage(imgtitle);
		
		this.add(jsp);
		this.setVisible(true);
		this.setSize(600, 600);
		this.setTitle("�ͻ���Ϣ");
		this.setLocationRelativeTo(null);

		if(num == 0) {
			JOptionPane.showMessageDialog(null, "��13��û���ҵ����ϲ�ѯ�����Ŀͻ���Ϣ", "��ѯ���", JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, "��14����ѯ��" + num + "���ͻ���Ϣ", "��ѯ���", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	/*��ȡ��ѯ�������ݸ�����1������true�����򷵻�false�������ж��Ƿ�ɵ���޸İ�ť*/
	public boolean canmodify() {
		if(num == 1) {
			return true;
		}
		return false;
	}
	
	/*��ȡҪ�޸ĵĿͻ����*/
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
