package LeasingSystem;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Init {

	Connection c;
	DatabaseMetaData dbm;	/*DatabaseMetaData类获取数据库的信息*/
	Statement s = null;	/*SQL容器，注意，注意，要初始化为null，否则调用close()时可能出错*/
	ResultSet r = null;	/*结果集，尽量带上，避免出错*/
	String create_table;	/*创建表的sql语句*/
	String insert_user1;	/*插入数据的sql语句*/
	String create_customer;	/*创建customer表sql语句*/
	String create_orders;	/*创建orders表的sql语句*/
	
	public Init() {
		
		c = Connect.getConnection();
		
		try {
			
			/*
			 * 判断数据库中是否存在userlist表，用来保存用户名和密码的
			 */
			dbm = c.getMetaData();
			r = dbm.getTables(null, null, "userlist", null);	/*在数据库中查找是否存在userlist表*/
			/*返回false，说明表中无数据，即无用户，需要创建表并创建默认用户名和密码*/
			if(r.next() == false) {
				/*默认用户名:ph   默认密码:123456*/
				create_table = "CREATE TABLE userlist (username CHAR(20),"
											+ "userpassword CHAR(20));";
				s = c.createStatement();
				s.executeUpdate(create_table);
				/*插入默认的用户*/
				insert_user1 = "INSERT INTO userlist VALUES('ph','123456');";
				s.executeUpdate(insert_user1);
				Login.b = true;	/*新用户，改为提示，不然别人怎么知道怎么登陆，又不能注册*/
			}
			
			/*
			 * 创建客户信息表customer,保存客户信息
			 */
			r = dbm.getTables(null, null, "customer", null);
			if(r.next() == false) {
				create_customer = "CREATE TABLE customer ( "
																+ "客户编号 CHAR(6) PRIMARY KEY, "
																+ "姓名 CHAR(20), "
																+ "电话 CHAR(11), "
																+ "住址 CHAR(30));";
				s = c.createStatement();
				s.executeUpdate(create_customer);
			}
			
			/*
			 * 创建订单记录表orders，不能用order，这个是数据库关键字
			 */
			r = dbm.getTables(null, null, "orders", null);
			if(r.next() == false) {
				create_orders = "CREATE TABLE orders ( 订单编号 CHAR(11) PRIMARY KEY, "
															+ "客户编号 CHAR(6), "
															+ "日期 DATE, "
															+ "状态 CHAR(10) CHECK(状态 IN('出库','入库')), "
															+ "6米 SMALLINT, "
															+ "5米 SMALLINT, "
															+ "4米 SMALLINT, "
															+ "3米 SMALLINT, "
															+ "2米 SMALLINT, "
															+ "1米 SMALLINT, "
															+ "FOREIGN KEY(客户编号) REFERENCES customer(客户编号)"
															+ ");";
				s = c.createStatement();
				s.executeUpdate(create_orders);
			}

			/*关闭之后不能调用，尽量放在最后，也可以用一次关闭一次，多声明几个不同的*/
			r.close();
			if(s != null) {	/*为null的话，不能调用方法*/
				s.close();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "【4】初始化数据库失败", "初始化失败", JOptionPane.ERROR_MESSAGE);
		}
		
		Connect.closeConnection(c);
		
	}
	
}
