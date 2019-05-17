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
	
	/*JPanel容器*/
	private JPanel JP = new JPanel();

	/*标签*/
	private JLabel JLold = new JLabel("输入旧密码");
	private JLabel JLnew1 = new JLabel("设置新密码");
	private JLabel JLnew2 = new JLabel("确认新密码");
	private JLabel JLstate = new JLabel();	/*提示是否输入错误*/
	private JLabel JLbackground;			/*背景图片*/

	/*文本框*/
	private JTextField JTold =new JTextField();
	private JPasswordField JPnew1 =new JPasswordField();
	private JPasswordField JPnew2 =new JPasswordField();

	/*按钮*/
	private JButton JBsave = new JButton("保存");
	private JButton JBback = new JButton("返回");
	
	/*图片*/
	Image imgtitle;
	Image imgbackground;
	
	/*常量*/
	private int width = 500;
	private int height = 350;
	
	public Changepassword() {
		
		init();
		
		/*设置窗体标题栏图标*/
		imgtitle = new ImageIcon("guan.png").getImage();
		this.setIconImage(imgtitle);
		
		this.add(JP);		/*将容器添加到窗口中*/
		this.setVisible(true);	/*窗口可视化*/
		this.setTitle("修改密码");	/*设置窗口名称*/
		this.setResizable(false);		/*窗口不能调整大小*/
		this.setSize(width, height);		/*窗口大小，(width,height)*/
		this.setLocationRelativeTo(null);	/*窗体居中显示*/
				
	}
	
	public void init() {
		
		/*设置布局管理器为空*/
		JP.setLayout(null);
		
		/*背景图片，1.jpg是当前项目文件夹下的图片，从文件夹将图片放入到src所在目录即可*/
		imgbackground = new ImageIcon("background1.jpg").getImage();
		JLbackground = new JLabel(new ImageIcon(imgbackground));
		
		/*设置组件字体*/
		JLold.setFont(Main.f20);
		JLnew1.setFont(Main.f20);
		JLnew2.setFont(Main.f20);
		JLstate.setFont(Main.f20);
		JTold.setFont(Main.f20);
		JPnew1.setFont(Main.f20);
		JPnew2.setFont(Main.f20);
		JBsave.setFont(Main.f25bi);
		JBback.setFont(Main.f25bi);

		/*设置组件位置和大小*/
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
		
		/*设置密码框的隐藏符，里面要用单引号*/
		JPnew1.setEchoChar('*');
		JPnew2.setEchoChar('*');

		/*添加事件监听器*/
		JTold.addActionListener(this);
		JPnew1.addActionListener(this);
		JPnew2.addActionListener(this);
		JBsave.addActionListener(this);
		JBback.addActionListener(this);

		/*将组件添加到容器中*/
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
		
		/*判断旧密码是否输入正确*/
		if(Verify.verify_check("ph", JTold.getText())) {
			
			/*获取密码*/
			String newpassword1 = String.valueOf(JPnew1.getPassword());
			String newpassword2 = String.valueOf(JPnew2.getPassword());
			
			/*判断新密码是否一致，不能用  newpassword != null */
			if(newpassword1.equals("")) {	/*设置密码框为空*/
				JLstate.setText("请输入新密码");
			} else if(newpassword2.equals("")) {	/*确认密码框为空*/
				JLstate.setText("请确认新密码");
			} else if(newpassword1.equals(newpassword2)) {	/*密码确认成功，保存到数据库*/
				
				/*保存密码操作*/
				Connection c = Connect.getConnection();
				
				try {
					/*
					 * sql语句一定要注意之间的空格，
					 * 千万要注意，换行后可能中间没有空格
					 * 语句末尾可以不要分号，我习惯带上
					 */
					String update_password = "UPDATE userlist "
															+ "SET userpassword = "
															+ "'" + newpassword1 + "' "
															+ "WHERE username = 'ph'"; 
					Statement s = c.createStatement();
					s.executeUpdate(update_password);
					s.close();				
										
					JOptionPane.showMessageDialog(null, "【6】密码修改成功", "修改成功", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "【7】密码保存到数据库失败", "修改密码失败", JOptionPane.ERROR_MESSAGE);
				}
				
				Connect.closeConnection(c);
			/*密码确认失败，即输入的不一致*/
			} else {
				JLstate.setText("新密码输入不一致");
			}
		/*就密码输入错误*/
		} else {
			JLstate.setText("密码输入错误");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		/*点击返回*/
		if(e.getSource() == JBback) {
			dispose();
		/*点击保存*/
		} else if(e.getSource() == JBsave) {
			updatemysql();
		}
	}

}
