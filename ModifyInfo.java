import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ModifyInfo extends JFrame {

	private JPanel contentPane;
	private JButton button;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	
	private String name;
	private String gender;
	private String age;
	private String password;
	private String account;
	private JRadioButton radioButton;
	private JRadioButton radioButton_1;
	private JRadioButton radioButton_2;

	/**
	 * Create the frame.
	 */
	public ModifyInfo() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				MainWin mw = new MainWin();
				mw.setVisible(true);
				mw.setButton(true);
			}
		});
		setBounds(550, 150, 380, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u59D3\u540D");
		label.setBounds(56, 101, 70, 30);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u5E74\u9F84");
		label_1.setBounds(56, 153, 70, 30);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u6027\u522B");
		label_2.setBounds(56, 211, 70, 30);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u5BC6\u7801");
		label_3.setBounds(56, 322, 70, 30);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		label_4.setBounds(45, 362, 70, 30);
		contentPane.add(label_4);
		
		button = new JButton("\u786E\u8BA4\u4FEE\u6539");
		button.addActionListener(new ActionListener() {
			String sql = "update users set 姓名=?, 年龄=?, 性别=?, 密码=? " + " where 账号=" + MainRun.ID ;
			public void actionPerformed(ActionEvent e) {
				if(isTrue()) {
					Connection con = MySQLConnection.getConnection();
					PreparedStatement ps = null;
					
					try {
						Statement stmt = con.createStatement();
						ps = con.prepareStatement(sql);
						ps.setString(1, name);
						ps.setInt(2, Integer.parseInt(age));
						ps.setString(3, gender);
						ps.setString(4, password);
						
						int num = ps.executeUpdate();
					}catch (Exception e1) {
						e1.printStackTrace();
					}
					new ErrorTip("提示", "您的信息已修改成功");
					dispose();
					new MyInfo(name, Integer.parseInt(age), gender, MainRun.ID).setVisible(true);
				}
			}
			public boolean isTrue() {
				name = textField.getText();
				age = textField_1.getText();
				account = textField_3.getText();
				password = passwordField.getText();
				String password_2 = passwordField_1.getText();
				if(radioButton.isSelected())
					gender = "男";
				else if(radioButton_1.isSelected())
					gender = "女";
				else if(radioButton_2.isSelected())
					gender = "保密";
				else {
					new ErrorTip("警告", "请选择性别");
					return false;
				}
				if(name.equals("") || age.equals("") || account.equals("")
						|| password.equals("") || password_2.equals("")) {
					new ErrorTip("警告", "请将信息项填满");
					return false;
				}
				String regex = "[0-9]{0,3}";
				if(!age.matches(regex)) {
					new ErrorTip("错误", "年龄格式不对！");
					return false;
				}
				if(!password.equals(password_2)) {
					new ErrorTip("错误", "两次密码不一致");
					return false;
				}
				return true;
			}
		});
		button.setBounds(116, 425, 119, 43);
		contentPane.add(button);
		
		JLabel label_5 = new JLabel("\u8BF7\u8C28\u614E\u7684\u4FEE\u6539\u4FE1\u606F");
		label_5.setFont(new Font("新宋体", Font.PLAIN, 20));
		label_5.setBounds(106, 10, 172, 43);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("\u8D26\u53F7");
		label_6.setBounds(56, 265, 70, 30);
		contentPane.add(label_6);
		
		textField = new JTextField();
		textField.setBounds(136, 106, 100, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(136, 158, 100, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setText(MainRun.ID);
		textField_3.setEditable(false);
		textField_3.setBounds(136, 270, 100, 21);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(136, 327, 100, 21);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(136, 367, 100, 21);
		contentPane.add(passwordField_1);
		
		radioButton = new JRadioButton("\u7537");
		radioButton.setBounds(130, 215, 56, 23);
		contentPane.add(radioButton);
		
		radioButton_1 = new JRadioButton("\u5973");
		radioButton_1.setBounds(188, 215, 56, 23);
		contentPane.add(radioButton_1);
		
		radioButton_2 = new JRadioButton("\u4FDD\u5BC6");
		radioButton_2.setBounds(241, 215, 77, 23);
		contentPane.add(radioButton_2);
		
		ButtonGroup bGroup = new ButtonGroup();
		bGroup.add(radioButton_2);
		bGroup.add(radioButton_1);
		bGroup.add(radioButton);
		
	}
}
