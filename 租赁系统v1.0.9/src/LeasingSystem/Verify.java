package LeasingSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Verify {
	
	/*非构造函数，检查用户名和密码是否输入正确，参数为从输入框获取的用户名和密码*/
	public static boolean verify_check(String username,String password) {
		
		Connection c = Connect.getConnection();	/*声明放在外面的话要定义为static类型的，因为这是static方法*/
				
		/*Init类已经初始化表了，此处默认表一定存在，所以不再查询数据库是否存在userlist表，当然也可以参考Init类里面的创建表的过程，增加判断代码*/
		try {
			Statement s = c.createStatement();
			String query_user = "SELECT * FROM userlist;";
			ResultSet r = s.executeQuery(query_user);	/*数据库中查询到的满足条件的行数据*/
			
			r.next();	/*r初始指向的是第0条数据，next方法便利后面的数据，此处只有一个管理员用户，无法注册，所以只有一次就行了，不用遍历*/
			String n = r.getString("username");		/*参数是你的数据库里面的表的列名*/
			String p = r.getString("userpassword");

			if(n.equals(username) && p.equals(password)) {
				return true;	/*用户名密码正确，返回true*/
			}
			
			r.close();
			s.close();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "【5】验证密码错误", "验证错误", JOptionPane.ERROR_MESSAGE);
		}
		
		Connect.closeConnection(c);	/*关闭连接*/
		
		return false;
	}

}