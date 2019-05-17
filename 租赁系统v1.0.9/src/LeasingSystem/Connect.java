/*
 * 1�����ӵ����ݿ�ph��ph�����Լ����������ݿ�����������Ĭ�ϵ�mysql
 * 2
 */

package LeasingSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Connect {
	
	/*�ǹ��캯�������������෽�����ã�����ֵ��Connection�Ķ���*/
	public static Connection getConnection() {
		
		Connection c = null;
		/*ph�����Լ����������ݿ⣬Ҳ������Ĭ�ϵ�mysql*/
		//String url = "jdbc:mysql://localhost:3306/ph";	/*��ʼ���������������������Ǹ�����*/	
		//String url = "jdbc:mysql://localhost:3306/ph"
		//		+ "?useUnicode=true&characterEncoding=utf-8&useSSL=false";	/*�ٲ��У��������Ǹ�*/
		String url = "jdbc:mysql://localhost:3306/ph"
				+ "?useUnicode=true&characterEncoding=utf-8&useSSL=false"
				+ "&serverTimezone=GMT";
		String user = "root";			/*mysqlĬ�ϵ��û�����������Ļ���һ��������ˡ���*/
		String password = "mysqlph120";	/*���ݿ�����룬���ذ�װmysqlʱ���õ����룬һ��Ҫ��ס*/

		try {
			//Class.forName("com.mysql.jdbc.Driver");		/*���еĻ��������Ǹ�*/
			Class.forName("com.mysql.cj.jdbc.Driver");		/*mysql�Ĺ̶����*/
			c = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "��1�����ݿ�����ʧ��", "���Ӵ���", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "��2�����ݿ�����ʧ��", "���Ӵ���", JOptionPane.ERROR_MESSAGE);
		}

		return c;
	}
	
	/*�ر�����*/
	public static void closeConnection(Connection c) {
		if(c != null) {
			try {
				c.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "[3]���ݿ�ر�����ʧ��", "���Ӵ���", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
