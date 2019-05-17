package LeasingSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Deleteorder {
	
	String query = "SELECT * FROM orders";	/*查询语句*/
	int num = 0;	/*删了多少条记录*/

	public Deleteorder(String orderid,String customerid,String day,String state) {
		buildsql(orderid,customerid,day,state);	/*生成sql语句*/
		updatemysql();	/*更新数据库*/
	}
	
	/*生成sql语句*/
	private void buildsql(String orderid,String customerid,String day,String state) {
		boolean bo,bc,bd,bs;
		bo = orderid.equals("");
		bc = customerid.equals("");
		bd = day.equals("");
		bs = state.equals("");
		if(!bo || !bc || !bd || !bs) {	/*只要有一个不空就有条件需要WHERE*/
			query = query + " WHERE ";
			if(!bo) {
				query = query + "订单编号='" + orderid + "'";
				if(!bc || !bd || !bs) {
					query = query + " AND ";
				}
			}
			if(!bc) {
				query = query + "客户编号='" + customerid + "'";
				if(!bd || !bs) {
					query = query + " AND ";
				}
			}
			if(!bd) {
				query = query + "日期 LIKE '%" + day + "%'";
				if(!bs) {
					query = query + " AND ";
				}
			}
			if(!bs) {
				query = query + "状态='" + state + "';";
			}
		}
		query = query + ";";
	}
	
	/*更新数据库*/
	private void updatemysql() {
		Connection c = Connect.getConnection();
		try {
			Statement s = c.createStatement();
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				String delete = "DELETE FROM orders WHERE 订单编号='";
				delete = delete + r.getString(1) + "';";
				s = c.createStatement();	/*这一句不能少*/
				s.executeUpdate(delete);
				num++;
			}
			r.close();
			s.close();
			if(num == 0) {
				JOptionPane.showMessageDialog(null, "【35】请选择要删除的记录", "删除失败", JOptionPane.WARNING_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "【36】成功删除" + num + "条记录", "删除成功", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "【37】数据库删除失败", "删除失败", JOptionPane.ERROR_MESSAGE);
		}
		Connect.closeConnection(c);
	}
}
