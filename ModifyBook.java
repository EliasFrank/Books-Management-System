import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JRadioButton;

public class ModifyBook extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton button;
	private JTextArea textArea;
	private String name;
	private String time;
	private String author;
	private String introduce;
	private String ID;
	private int canOut = 1;
	private JLabel label_5;
	private JTextField textField_3;
	private JLabel label_6;
	private JRadioButton radioButton;
	private JRadioButton radioButton_1;
	private ButtonGroup bGroup;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifyBook frame = new ModifyBook();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ModifyBook() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				new AdminMainWin().setVisible(true);
			}
		});
		setBounds(550, 150, 453, 531);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u8BF7\u5728\u4E0B\u65B9\u586B\u5199\u4E66\u7C4D\u4FE1\u606F");
		label.setFont(new Font("新宋体", Font.PLAIN, 20));
		label.setBounds(116, 32, 261, 30);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u4E66\u540D");
		label_1.setBounds(94, 106, 58, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u4F5C\u8005");
		label_2.setBounds(94, 148, 58, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u51FA\u7248\u65F6\u95F4");
		label_3.setBounds(81, 207, 58, 15);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("\u4E66\u7C4D\u7B80\u4ECB(200\u5B57\u4EE5\u5185\uFF0C \u53EF\u4EE5\u4E0D\u586B)");
		label_4.setBounds(126, 232, 200, 30);
		contentPane.add(label_4);
		
		textArea = new JTextArea();
		textArea.setBounds(66, 280, 300, 150);
		contentPane.add(textArea);
		
		button = new JButton("\u786E\u8BA4\u4FEE\u6539");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql;
				if("".equals(introduce))
					sql = "update books set book_name=?, author=?, "
						+ "book_time=?,  can_out=?" + " where id=" + ID;
				else sql = "update books set book_name=?, author=?, "
						+ "book_time=?, introduce=?,  can_out=?" + " where id=" + ID;
				Connection con = MySQLConnection.getConnection();
				PreparedStatement ps = null;
				if(isTrue()) {
					try {
						ps = con.prepareStatement(sql);
						ps.setString(1, name);
						ps.setString(2, author);
						ps.setString(3, time);
						if(introduce.equals("")) {
							ps.setInt(4, canOut);
						}
						else {
							ps.setString(4, introduce);
							ps.setInt(5, canOut);
						}
						int num = ps.executeUpdate();
						if(num == 0) {
							new ErrorTip("错误", "书籍的编号错误");
							textField_3.setText("");
							return;
						}
						if(num != 0) {
							new ErrorTip("恭喜", "书籍信息修改成功");
							textField.setText("");
							textField_1.setText("");
							textField_2.setText("");
							textField_3.setText("");
							textArea.setText("");
						}
					}catch (Exception e1) {
						e1.printStackTrace();
					}
					
				}
			}
			private boolean isTrue() {
				name = textField.getText();
				author = textField_1.getText();
				time = textField_2.getText();
				introduce = textArea.getText();
				ID = textField_3.getText();
				if(radioButton.isSelected())
					canOut = 0;
				else if(radioButton_1.isSelected())
					canOut = 1;
				else {
					new ErrorTip("提示", "请选择书籍状态");
					return false;
				}
				if(introduce.length() > 200) {
					new ErrorTip("提示", "书籍简介超过200行");
					return false;
				}
				if("".equals(name) || "".equals(author) || 
						"".equals(time) || "".equals(ID)) {
					new ErrorTip("提示", "请输入所有信息");
					return false;
				}	
				return true;
			}
		});
		button.setBounds(151, 454, 100, 30);
		contentPane.add(button);
		
		textField = new JTextField();
		textField.setBounds(195, 103, 100, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(195, 145, 100, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(195, 204, 100, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		label_5 = new JLabel("\u7F16\u53F7");
		label_5.setBounds(94, 72, 58, 15);
		contentPane.add(label_5);
		
		textField_3 = new JTextField();
		textField_3.setBounds(195, 72, 100, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		label_6 = new JLabel("\u662F\u5426\u501F\u51FA");
		label_6.setBounds(81, 182, 58, 15);
		contentPane.add(label_6);
		
		radioButton = new JRadioButton("\u662F");
		radioButton.setBounds(195, 175, 49, 23);
		contentPane.add(radioButton);
		
		radioButton_1 = new JRadioButton("\u5426");
		radioButton_1.setBounds(252, 175, 49, 23);
		contentPane.add(radioButton_1);
		
		bGroup = new ButtonGroup();
		bGroup.add(radioButton);
		bGroup.add(radioButton_1);
		
	}
}
