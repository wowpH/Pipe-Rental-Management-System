package LeasingSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Verify {
	
	/*�ǹ��캯��������û����������Ƿ�������ȷ������Ϊ��������ȡ���û���������*/
	public static boolean verify_check(String username,String password) {
		
		Connection c = Connect.getConnection();	/*������������Ļ�Ҫ����Ϊstatic���͵ģ���Ϊ����static����*/
				
		/*Init���Ѿ���ʼ�����ˣ��˴�Ĭ�ϱ�һ�����ڣ����Բ��ٲ�ѯ���ݿ��Ƿ����userlist����ȻҲ���Բο�Init������Ĵ�����Ĺ��̣������жϴ���*/
		try {
			Statement s = c.createStatement();
			String query_user = "SELECT * FROM userlist;";
			ResultSet r = s.executeQuery(query_user);	/*���ݿ��в�ѯ��������������������*/
			
			r.next();	/*r��ʼָ����ǵ�0�����ݣ�next����������������ݣ��˴�ֻ��һ������Ա�û����޷�ע�ᣬ����ֻ��һ�ξ����ˣ����ñ���*/
			String n = r.getString("username");		/*������������ݿ�����ı������*/
			String p = r.getString("userpassword");

			if(n.equals(username) && p.equals(password)) {
				return true;	/*�û���������ȷ������true*/
			}
			
			r.close();
			s.close();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "��5����֤�������", "��֤����", JOptionPane.ERROR_MESSAGE);
		}
		
		Connect.closeConnection(c);	/*�ر�����*/
		
		return false;
	}

}