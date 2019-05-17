package LeasingSystem;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Init {

	Connection c;
	DatabaseMetaData dbm;	/*DatabaseMetaData���ȡ���ݿ����Ϣ*/
	Statement s = null;	/*SQL������ע�⣬ע�⣬Ҫ��ʼ��Ϊnull���������close()ʱ���ܳ���*/
	ResultSet r = null;	/*��������������ϣ��������*/
	String create_table;	/*�������sql���*/
	String insert_user1;	/*�������ݵ�sql���*/
	String create_customer;	/*����customer��sql���*/
	String create_orders;	/*����orders���sql���*/
	
	public Init() {
		
		c = Connect.getConnection();
		
		try {
			
			/*
			 * �ж����ݿ����Ƿ����userlist�����������û����������
			 */
			dbm = c.getMetaData();
			r = dbm.getTables(null, null, "userlist", null);	/*�����ݿ��в����Ƿ����userlist��*/
			/*����false��˵�����������ݣ������û�����Ҫ����������Ĭ���û���������*/
			if(r.next() == false) {
				/*Ĭ���û���:ph   Ĭ������:123456*/
				create_table = "CREATE TABLE userlist (username CHAR(20),"
											+ "userpassword CHAR(20));";
				s = c.createStatement();
				s.executeUpdate(create_table);
				/*����Ĭ�ϵ��û�*/
				insert_user1 = "INSERT INTO userlist VALUES('ph','123456');";
				s.executeUpdate(insert_user1);
				Login.b = true;	/*���û�����Ϊ��ʾ����Ȼ������ô֪����ô��½���ֲ���ע��*/
			}
			
			/*
			 * �����ͻ���Ϣ��customer,����ͻ���Ϣ
			 */
			r = dbm.getTables(null, null, "customer", null);
			if(r.next() == false) {
				create_customer = "CREATE TABLE customer ( "
																+ "�ͻ���� CHAR(6) PRIMARY KEY, "
																+ "���� CHAR(20), "
																+ "�绰 CHAR(11), "
																+ "סַ CHAR(30));";
				s = c.createStatement();
				s.executeUpdate(create_customer);
			}
			
			/*
			 * ����������¼��orders��������order����������ݿ�ؼ���
			 */
			r = dbm.getTables(null, null, "orders", null);
			if(r.next() == false) {
				create_orders = "CREATE TABLE orders ( ������� CHAR(11) PRIMARY KEY, "
															+ "�ͻ���� CHAR(6), "
															+ "���� DATE, "
															+ "״̬ CHAR(10) CHECK(״̬ IN('����','���')), "
															+ "6�� SMALLINT, "
															+ "5�� SMALLINT, "
															+ "4�� SMALLINT, "
															+ "3�� SMALLINT, "
															+ "2�� SMALLINT, "
															+ "1�� SMALLINT, "
															+ "FOREIGN KEY(�ͻ����) REFERENCES customer(�ͻ����)"
															+ ");";
				s = c.createStatement();
				s.executeUpdate(create_orders);
			}

			/*�ر�֮���ܵ��ã������������Ҳ������һ�ιر�һ�Σ�������������ͬ��*/
			r.close();
			if(s != null) {	/*Ϊnull�Ļ������ܵ��÷���*/
				s.close();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "��4����ʼ�����ݿ�ʧ��", "��ʼ��ʧ��", JOptionPane.ERROR_MESSAGE);
		}
		
		Connect.closeConnection(c);
		
	}
	
}
