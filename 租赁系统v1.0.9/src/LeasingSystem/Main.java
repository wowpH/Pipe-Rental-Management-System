package LeasingSystem;

import java.awt.Font;

public class Main {

	/*全局变量*/
	public static Font f15 = new Font("楷体",Font.BOLD,15);
	public static Font f20 = new Font("楷体",Font.BOLD,20);
	public static Font f25b = new Font("楷体",Font.BOLD,25);
	public static Font f25bi = new Font("楷体",Font.BOLD|Font.ITALIC,25);	/*粗斜体*/
	
	public static void main(String[] args) {
		new Init();		/*初始化数据库，具体见Init类*/
		new Login();	/*登录界面*/
	}

}
