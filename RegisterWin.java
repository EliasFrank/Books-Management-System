

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.sql.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RegisterWin extends JFrame{
	private JButton regButton;
	private JTextField nameField;
	private JTextField ageField;
	private JRadioButton radioButton;
	private JRadioButton radioButton_1;
	private JRadioButton radioButton_2;
	private JTextField accountField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JRadioButton adButton;
	private JRadioButton userButton;
	private ButtonGroup bGroup;
	private ButtonGroup bGroup2;
	RegisterWin(String name){
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				new LogWin();
				dispose();
			}
		});
		this.setTitle(name);
		this.setBounds(600,200,300,450);
		this.setResizable(false);
		this.addSome();
		this.setVisible(true);
	}
	private void addSome() {
		Container container = this.getContentPane();
		JPanel jPanel = new JPanel();JPanel panelTop=new JPanel();
       

		ImageIcon backgroundIcon = new ImageIcon("h:\\test\\桥2.jpg");
		JLabel backgroundLabel = new JLabel(backgroundIcon);
		regButton = new JButton("确认注册");
		regButton.addActionListener(new ActionListener() {
			
			String name = null;
			String age = null;
			String account = null;
			String password_1 = null;
			String password_2 = null;
			String sex = null;
			int isCom = 0;
			String sql;
			public void actionPerformed(ActionEvent e) {
				if(isFull()) {
					User tempUser = null;
					try {
						tempUser = new User(name, Integer.parseInt(age), 
								sex, account, password_1, isCom);
						boolean success = insertMySQL(tempUser);
						if(success && isCom == 0) {
							new ErrorTip("恭喜", "恭喜你注册成功，请尽情享受阅读之旅吧！");
							MainRun.ID = account;
							MainRun.isAdmin = false;
							MainWin mw = new MainWin();
							mw.setButton(true);
							mw.setVisible(true);
							
						}
						else if(success && isCom == 1) {
							new ErrorTip("提示", "请等待超级管理员审核！");
							dispose();
							new LogWin();
						}
					} catch (NumberFormatException e1) {
						new ErrorTip("错误", "年龄含有非法字符");
					} catch (Exception e1) {
						new ErrorTip("错误", e1.getMessage());
						//System.out.println("这里有问题");
						//e1.printStackTrace();
					}
				}
			}
			private boolean insertMySQL(User user) {
				if(isCom == 0)
					sql = "insert into users values(?,?,?,?,?,0,0)";
				if(isCom == 1)
					sql = "insert into admin values(?,?,?,?,?,1,0,0)";
				
				Connection con = MySQLConnection.getConnection();
				PreparedStatement ps = null;
				
				try {
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select * from users where 账号=" + account);
					if(rs.next()) {
						new ErrorTip("提示", "你好，该账号已被使用，请输入新的账号");
						accountField.setText("");
						return false;
					}
					ps = con.prepareStatement(sql);
					ps.setString(1, user.getName());
					ps.setInt(2, user.getAge());
					ps.setString(3, user.getGender());
					ps.setString(4, user.getAccount());
					ps.setString(5, user.getPassword());
					
					int num = ps.executeUpdate();
					if(num == 0)
						return false;
					else return true;
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					if(ps == null) {
						try {
							ps.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
				return false;
			}
			private boolean isFull() {
				name = nameField.getText();
				age = ageField.getText();
				account = accountField.getText();
				password_1 = passwordField.getText();
				password_2 = passwordField_1.getText();
				if("1972341610".equals(account)) {
					new ErrorTip("提示", "该账号已被使用，请重新输入");
					accountField.setText("");
					return false;
				}
				if(radioButton.isSelected())
					sex = "男";
				else if(radioButton_1.isSelected())
					sex = "女";
				else if(radioButton_2.isSelected())
					sex = "保密";
				else {
					new ErrorTip("警告", "请选择性别");
					return false;
				}
				if(adButton.isSelected())
					isCom = 1;
				else if(userButton.isSelected())
					isCom = 0;
				else {
					new ErrorTip("警告", "请选择要注册账号的身份！");
					return false;
				}
				if(name.equals("") || age.equals("") || account.equals("")
						|| password_1.equals("") || password_2.equals("")) {
					new ErrorTip("警告", "请将信息项填满");
					return false;
				}
				if(!password_1.equals(password_2)) {
					new ErrorTip("错误", "两次密码不一致");
					return false;
				}
				return true;
			}
		});
		regButton.setBounds(90, 330, 100, 30);
		JLabel nameLabel = new JLabel("姓名：");
		nameLabel.setBounds(30, 40, 50, 30);
		JLabel ageLabel = new JLabel("年龄：");
		ageLabel.setBounds(30, 80, 50, 30);
		JLabel sexLabel = new JLabel("性别：");
		sexLabel.setBounds(30, 120, 50, 30);
		JLabel accountLabel = new JLabel("账号：");
		accountLabel.setBounds(30, 160, 50, 30);
		JLabel passwordLabel_1 = new JLabel("密码：");
		passwordLabel_1.setBounds(30, 200, 50, 30);
		JLabel passwordLabel_2 = new JLabel("确认密码：");
		passwordLabel_2.setBounds(10, 240, 70, 30);
		
		
		nameField = new JTextField();
		nameField.setBounds(90, 40, 150, 30);
		ageField = new JTextField();
		ageField.setBounds(90, 80, 150, 30);
		accountField = new JTextField();
		accountField.setBounds(90, 160, 150, 30);
		passwordField = new JPasswordField();
		passwordField.setBounds(90, 200, 150, 30);
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(90, 240, 150, 30);
		
		
		adButton = new JRadioButton("管理员");
		adButton.setBounds(60, 280, 80, 20);
		userButton = new JRadioButton("用户");	
		userButton.setBounds(160, 280, 80, 20);
		bGroup = new ButtonGroup();
		bGroup.add(adButton);
		bGroup.add(userButton);
		
		nameLabel.setForeground(Color.white);
		ageLabel.setForeground(Color.white);
		sexLabel.setForeground(Color.white);
		accountLabel.setForeground(Color.white);
		passwordLabel_1.setForeground(Color.white);
		passwordLabel_2.setForeground(Color.white);
		backgroundLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
		
        this.getLayeredPane().add(backgroundLabel,new Integer(Integer.MIN_VALUE));
        panelTop=(JPanel)this.getContentPane();
        
        //panel和panelTop设置透明
        panelTop.setOpaque(false);
        jPanel.setOpaque(false);
		getContentPane().setLayout(null);
        
		container.add(regButton);
		container.add(nameLabel);
		container.add(ageLabel);
		container.add(sexLabel);
		container.add(accountLabel);
		container.add(passwordLabel_1);
		container.add(passwordLabel_2);
		container.add(nameField);
		container.add(ageField);
		container.add(accountField);
		container.add(passwordField);
		container.add(passwordField_1);
		container.add(adButton);
		container.add(userButton);
		
		radioButton = new JRadioButton("\u7537");
		radioButton.setBounds(90, 124, 42, 23);
		getContentPane().add(radioButton);
		
		radioButton_1 = new JRadioButton("\u5973");
		radioButton_1.setBounds(137, 124, 42, 23);
		getContentPane().add(radioButton_1);
		
		radioButton_2 = new JRadioButton("\u4FDD\u5BC6");
		radioButton_2.setBounds(181, 124, 59, 23);
		getContentPane().add(radioButton_2);
		
		bGroup2 = new ButtonGroup();
		bGroup2.add(radioButton);
		bGroup2.add(radioButton_1);
		bGroup2.add(radioButton_2);
	}
}
