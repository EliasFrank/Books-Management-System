import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BookInfo extends JFrame {

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
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookInfo frame = new BookInfo();
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
	public BookInfo() {
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
		label_3.setBounds(81, 198, 58, 15);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("\u4E66\u7C4D\u7B80\u4ECB(200\u5B57\u4EE5\u5185)");
		label_4.setBounds(126, 232, 200, 30);
		contentPane.add(label_4);
		
		textArea = new JTextArea();
		textArea.setBounds(66, 280, 300, 150);
		contentPane.add(textArea);
		
		button = new JButton("\u786E\u8BA4\u6DFB\u52A0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "insert into books values(default,?,?,?,?,1,0)";
				
				Connection con = MySQLConnection.getConnection();
				PreparedStatement ps = null;
				if(isTrue()) {
					try {
						ps = con.prepareStatement(sql);
						ps.setString(1, name);
						ps.setString(2, author);
						ps.setString(3, time);
						ps.setString(4, introduce);
						
						int num = ps.executeUpdate();
						
						if(num != 0) {
							new ErrorTip("恭喜", "书籍添加成功");
							textField.setText("");
							textField_1.setText("");
							textField_2.setText("");
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
				if(introduce.length() > 200) {
					new ErrorTip("提示", "书籍简介超过200行");
					return false;
				}
				if("".equals(name) || "".equals(author) || "".equals(time) || "".equals(introduce)) {
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
		textField_2.setBounds(195, 195, 100, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
	}
}
