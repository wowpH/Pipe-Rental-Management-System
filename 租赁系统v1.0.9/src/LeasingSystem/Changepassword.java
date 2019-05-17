package LeasingSystem;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Changepassword extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	/*JPanel����*/
	private JPanel JP = new JPanel();

	/*��ǩ*/
	private JLabel JLold = new JLabel("���������");
	private JLabel JLnew1 = new JLabel("����������");
	private JLabel JLnew2 = new JLabel("ȷ��������");
	private JLabel JLstate = new JLabel();	/*��ʾ�Ƿ��������*/
	private JLabel JLbackground;			/*����ͼƬ*/

	/*�ı���*/
	private JTextField JTold =new JTextField();
	private JPasswordField JPnew1 =new JPasswordField();
	private JPasswordField JPnew2 =new JPasswordField();

	/*��ť*/
	private JButton JBsave = new JButton("����");
	private JButton JBback = new JButton("����");
	
	/*ͼƬ*/
	Image imgtitle;
	Image imgbackground;
	
	/*����*/
	private int width = 500;
	private int height = 350;
	
	public Changepassword() {
		
		init();
		
		/*���ô��������ͼ��*/
		imgtitle = new ImageIcon("guan.png").getImage();
		this.setIconImage(imgtitle);
		
		this.add(JP);		/*��������ӵ�������*/
		this.setVisible(true);	/*���ڿ��ӻ�*/
		this.setTitle("�޸�����");	/*���ô�������*/
		this.setResizable(false);		/*���ڲ��ܵ�����С*/
		this.setSize(width, height);		/*���ڴ�С��(width,height)*/
		this.setLocationRelativeTo(null);	/*���������ʾ*/
				
	}
	
	public void init() {
		
		/*���ò��ֹ�����Ϊ��*/
		JP.setLayout(null);
		
		/*����ͼƬ��1.jpg�ǵ�ǰ��Ŀ�ļ����µ�ͼƬ�����ļ��н�ͼƬ���뵽src����Ŀ¼����*/
		imgbackground = new ImageIcon("background1.jpg").getImage();
		JLbackground = new JLabel(new ImageIcon(imgbackground));
		
		/*�����������*/
		JLold.setFont(Main.f20);
		JLnew1.setFont(Main.f20);
		JLnew2.setFont(Main.f20);
		JLstate.setFont(Main.f20);
		JTold.setFont(Main.f20);
		JPnew1.setFont(Main.f20);
		JPnew2.setFont(Main.f20);
		JBsave.setFont(Main.f25bi);
		JBback.setFont(Main.f25bi);

		/*�������λ�úʹ�С*/
		JLold.setBounds(60, 50, 110, 20);
		JLnew1.setBounds(60, 110, 110, 20);
		JLnew2.setBounds(60, 170, 110, 20);
		JLstate.setBounds(10, 280, 490, 20);
		JTold.setBounds(180, 45, 250, 30);
		JPnew1.setBounds(180, 105, 250, 30);
		JPnew2.setBounds(180, 165, 250, 30);
		JBsave.setBounds(70, 230, 100, 40);
		JBback.setBounds(330, 230, 100, 40);
		JLbackground.setBounds(0, 0, width, height);
		
		/*�������������ط�������Ҫ�õ�����*/
		JPnew1.setEchoChar('*');
		JPnew2.setEchoChar('*');

		/*����¼�������*/
		JTold.addActionListener(this);
		JPnew1.addActionListener(this);
		JPnew2.addActionListener(this);
		JBsave.addActionListener(this);
		JBback.addActionListener(this);

		/*�������ӵ�������*/
		JP.add(JLold);
		JP.add(JLnew1);
		JP.add(JLnew2);
		JP.add(JLstate);
		JP.add(JTold);
		JP.add(JPnew1);
		JP.add(JPnew2);
		JP.add(JBsave);
		JP.add(JBback);
		JP.add(JLbackground);

	}
	
	public void updatemysql() {
		
		/*�жϾ������Ƿ�������ȷ*/
		if(Verify.verify_check("ph", JTold.getText())) {
			
			/*��ȡ����*/
			String newpassword1 = String.valueOf(JPnew1.getPassword());
			String newpassword2 = String.valueOf(JPnew2.getPassword());
			
			/*�ж��������Ƿ�һ�£�������  newpassword != null */
			if(newpassword1.equals("")) {	/*���������Ϊ��*/
				JLstate.setText("������������");
			} else if(newpassword2.equals("")) {	/*ȷ�������Ϊ��*/
				JLstate.setText("��ȷ��������");
			} else if(newpassword1.equals(newpassword2)) {	/*����ȷ�ϳɹ������浽���ݿ�*/
				
				/*�����������*/
				Connection c = Connect.getConnection();
				
				try {
					/*
					 * sql���һ��Ҫע��֮��Ŀո�
					 * ǧ��Ҫע�⣬���к�����м�û�пո�
					 * ���ĩβ���Բ�Ҫ�ֺţ���ϰ�ߴ���
					 */
					String update_password = "UPDATE userlist "
															+ "SET userpassword = "
															+ "'" + newpassword1 + "' "
															+ "WHERE username = 'ph'"; 
					Statement s = c.createStatement();
					s.executeUpdate(update_password);
					s.close();				
										
					JOptionPane.showMessageDialog(null, "��6�������޸ĳɹ�", "�޸ĳɹ�", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "��7�����뱣�浽���ݿ�ʧ��", "�޸�����ʧ��", JOptionPane.ERROR_MESSAGE);
				}
				
				Connect.closeConnection(c);
			/*����ȷ��ʧ�ܣ�������Ĳ�һ��*/
			} else {
				JLstate.setText("���������벻һ��");
			}
		/*�������������*/
		} else {
			JLstate.setText("�����������");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		/*�������*/
		if(e.getSource() == JBback) {
			dispose();
		/*�������*/
		} else if(e.getSource() == JBsave) {
			updatemysql();
		}
	}

}
