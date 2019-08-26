

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

class LogWin {
	private JFrame logFrame = new JFrame("��¼");
	private ImageIcon background = null;
	private JButton button = null;
	private JButton logButton = null;
	private JButton regButton = null;
	private JTextField accountField = null;
	private JPasswordField passwordField = null;
	private JRadioButton adButton =null;
	private JRadioButton userButton = null;	
	private ButtonGroup bGroup = null;
	LogWin(){
		init(logFrame);
		add(logFrame);
		myEvent();
		logFrame.setVisible(true);
	}
	private void myEvent() {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new LeapForg("����¼������Щ�����޷�ʹ��");
			}
		});
		regButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new RegisterWin("ע��");
				logFrame.dispose();
			}
		});
		logButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String account = accountField.getText();
				String password = passwordField.getText();
				if(MainRun.SuperID.equals(account) && MainRun.SuperPass.equals(password)) {
					new SuperAdmin().setVisible(true);
					logFrame.dispose();
					return;
				}
				String sql = "";
				if(!(adButton.isSelected() || userButton.isSelected())) 
						new ErrorTip("��ʾ", "��ѡ���½���");
				else{
					Connection con = MySQLConnection.getConnection();
					if(adButton.isSelected())
						sql = "select * from admin where �˺�=? and ����=?";
					else if(userButton.isSelected())
						sql = "select * from users where �˺�=? and ����=?";
					try {
						PreparedStatement ps = con.prepareStatement(sql);
						ps.setString(1, account);
						ps.setString(2, password);
						ResultSet rs = ps.executeQuery();
						if(rs.next()) {
							int flag = rs.getInt("����Ա");
							if(flag == 1) {
								int can = rs.getInt("��Ч");
								if(can == 0) {
									new ErrorTip("��ʾ", "����˺Ż�������У����Եȡ�");
									return;
								}
								else {	
									MainRun.isAdmin = true;
									AdminMainWin amw = new AdminMainWin();
									amw.setVisible(true);
								}
							}
							else {
								MainRun.isAdmin = false;
								MainWin mw = new MainWin();
								mw.setButton(true);
								mw.setVisible(true);
							}
							MainRun.ID = rs.getString("�˺�");
							logFrame.dispose();
							rs.close();
							ps.close();
						}
						else new ErrorTip("����", "�˺Ż����������");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}
	private void init(JFrame jf) {
		jf.setBounds(600,200,300,250);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setResizable(false);
		jf.getContentPane().setLayout(null);
	}
	private void add(JFrame jf) {
		Container container = jf.getContentPane();//��ȡ��������
		
		background = new ImageIcon("H:\\test\\����.png");
		JLabel picLabel = new JLabel(background);
		JLabel welcomLabel = new JLabel("\u6B22\u8FCE\u6765\u5230\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF");
		JLabel accountLabel = new JLabel("�˺ţ�");
		JLabel passwordLabel = new JLabel("���룺");
		logButton = new JButton("��¼");
		regButton = new JButton("ע��");
		accountField = new JTextField(10);
		passwordField = new JPasswordField(10);
		adButton = new JRadioButton("����Ա");
		userButton = new JRadioButton("�û�");	
		bGroup = new ButtonGroup();
		bGroup.add(adButton);
		bGroup.add(userButton);
		
		picLabel.setBounds(0, 0, 105, 75);
		welcomLabel.setBounds(100, 30, 157, 20);
		accountLabel.setBounds(30, 80, 50, 20);
		passwordLabel.setBounds(30, 110, 50, 20);
		logButton.setBounds(25, 170, 80, 30);
		regButton.setBounds(112, 170, 80, 30);
		accountField.setBounds(80, 80, 150, 20);
		passwordField.setBounds(80, 110, 150, 20);
		adButton.setBounds(70, 130, 70, 30);
		userButton.setBounds(150, 130, 70, 30);
		
		container.add(picLabel);
		container.add(logButton);
		container.add(regButton);
		container.add(accountField);
		container.add(passwordField);
		container.add(accountLabel);
		container.add(passwordLabel);
		container.add(welcomLabel);
		container.add(adButton);
		container.add(userButton);
		
		button = new JButton("\u8DF3\u8FC7");
		button.setBounds(202, 170, 72, 30);
		logFrame.getContentPane().add(button);
	}
}
