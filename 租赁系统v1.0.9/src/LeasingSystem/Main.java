package LeasingSystem;

import java.awt.Font;

public class Main {

	/*ȫ�ֱ���*/
	public static Font f15 = new Font("����",Font.BOLD,15);
	public static Font f20 = new Font("����",Font.BOLD,20);
	public static Font f25b = new Font("����",Font.BOLD,25);
	public static Font f25bi = new Font("����",Font.BOLD|Font.ITALIC,25);	/*��б��*/
	
	public static void main(String[] args) {
		new Init();		/*��ʼ�����ݿ⣬�����Init��*/
		new Login();	/*��¼����*/
	}

}
