/*
 * 1、连接到数据库ph，ph是我自己创建的数据库名，可以用默认的mysql
 * 2
 */

package LeasingSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Connect {
	
	/*非构造函数，用于其他类方法调用，返回值是Connection的对象*/
	public static Connection getConnection() {
		
		Connection c = null;
		/*ph是我自己创建的数据库，也可以用默认的mysql*/
		//String url = "jdbc:mysql://localhost:3306/ph";	/*开始用这个，如果报错用下面那个试试*/	
		//String url = "jdbc:mysql://localhost:3306/ph"
		//		+ "?useUnicode=true&characterEncoding=utf-8&useSSL=false";	/*再不行，用下面那个*/
		String url = "jdbc:mysql://localhost:3306/ph"
				+ "?useUnicode=true&characterEncoding=utf-8&useSSL=false"
				+ "&serverTimezone=GMT";
		String user = "root";			/*mysql默认的用户名，不清楚的话就一定是这个了。。*/
		String password = "mysqlph120";	/*数据库的密码，下载安装mysql时设置的密码，一定要记住*/

		try {
			//Class.forName("com.mysql.jdbc.Driver");		/*不行的话用下面那个*/
			Class.forName("com.mysql.cj.jdbc.Driver");		/*mysql的固定语句*/
			c = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "【1】数据库连接失败", "连接错误", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "【2】数据库连接失败", "连接错误", JOptionPane.ERROR_MESSAGE);
		}

		return c;
	}
	
	/*关闭连接*/
	public static void closeConnection(Connection c) {
		if(c != null) {
			try {
				c.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "[3]数据库关闭连接失败", "连接错误", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
