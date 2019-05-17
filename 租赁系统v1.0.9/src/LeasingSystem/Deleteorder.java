package LeasingSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Deleteorder {
	
	String query = "SELECT * FROM orders";	/*��ѯ���*/
	int num = 0;	/*ɾ�˶�������¼*/

	public Deleteorder(String orderid,String customerid,String day,String state) {
		buildsql(orderid,customerid,day,state);	/*����sql���*/
		updatemysql();	/*�������ݿ�*/
	}
	
	/*����sql���*/
	private void buildsql(String orderid,String customerid,String day,String state) {
		boolean bo,bc,bd,bs;
		bo = orderid.equals("");
		bc = customerid.equals("");
		bd = day.equals("");
		bs = state.equals("");
		if(!bo || !bc || !bd || !bs) {	/*ֻҪ��һ�����վ���������ҪWHERE*/
			query = query + " WHERE ";
			if(!bo) {
				query = query + "�������='" + orderid + "'";
				if(!bc || !bd || !bs) {
					query = query + " AND ";
				}
			}
			if(!bc) {
				query = query + "�ͻ����='" + customerid + "'";
				if(!bd || !bs) {
					query = query + " AND ";
				}
			}
			if(!bd) {
				query = query + "���� LIKE '%" + day + "%'";
				if(!bs) {
					query = query + " AND ";
				}
			}
			if(!bs) {
				query = query + "״̬='" + state + "';";
			}
		}
		query = query + ";";
	}
	
	/*�������ݿ�*/
	private void updatemysql() {
		Connection c = Connect.getConnection();
		try {
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				String delete = "DELETE FROM orders WHERE �������='";
				delete = delete + r.getString(1) + "';";
				s = c.createStatement();	/*��һ�䲻����*/
				s.executeUpdate(delete);
				num++;
			}
			r.close();
			s.close();
			if(num == 0) {
				JOptionPane.showMessageDialog(null, "��35����ѡ��Ҫɾ���ļ�¼", "ɾ��ʧ��", JOptionPane.WARNING_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "��36���ɹ�ɾ��" + num + "����¼", "ɾ���ɹ�", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "��37�����ݿ�ɾ��ʧ��", "ɾ��ʧ��", JOptionPane.ERROR_MESSAGE);
		}
		Connect.closeConnection(c);
	}
}
