import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextArea;

public class MainWin extends JFrame {

	private JPanel contentPane;
	private final JLayeredPane layeredPane_2 = new JLayeredPane();
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton button_4;
	private JLabel lblNewLabel_1;
	private JTextArea txtrufo;
	private JButton button_5;
	
	/**
	 * Create the frame.
	 */
	public MainWin() {
		setResizable(false);
		setTitle("\u4E3B\u9875\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 150, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 266, 1, 1);
		contentPane.add(layeredPane);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBounds(0, 0, 800, 500);
		contentPane.add(layeredPane_1);
		layeredPane_1.setLayer(layeredPane_2, 1);
		layeredPane_2.setBounds(10, 10, 800, 500);
		layeredPane_1.add(layeredPane_2);
		layeredPane_2.setLayout(null);
		
		JLabel label = new JLabel("\u8BA9\u9605\u8BFB\u6210\u4E3A\u4E00\u751F\u7684\u4E60\u60EF");
		label.setFont(new Font("方正粗黑宋简体", Font.PLAIN, 20));
		label.setForeground(Color.BLUE);
		label.setBounds(281, 7, 217, 40);
		layeredPane_2.add(label);
		
		button = new JButton("\u767B\u5F55");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LogWin();
				dispose();
			}
		});
		button.setBounds(23, 19, 97, 23);
		layeredPane_2.add(button);
		
		button_1 = new JButton("\u6CE8\u518C");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RegisterWin("注册");
				dispose();
			}
		});
		button_1.setBounds(676, 19, 97, 23);
		layeredPane_2.add(button_1);
		
		button_2 = new JButton("\u5F52\u8FD8\u56FE\u4E66");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if("".equals(MainRun.ID)) {
					new ErrorTip("提示", "亲，要先登录才能继续操作哦！");
				}
				dispose();
				new ReturnBook().setVisible(true);
			}
		});
		button_2.setBounds(120, 52, 97, 23);
		layeredPane_2.add(button_2);
		
		button_3 = new JButton("\u501F\u9605\u56FE\u4E66");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new BorrowBooks().setVisible(true);
			}
		});
		button_3.setBounds(337, 52, 97, 23);
		layeredPane_2.add(button_3);
		
		button_4 = new JButton("\u67E5\u627E\u56FE\u4E66");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new FindBook().setVisible(true);
			}
		});
		button_4.setBounds(581, 52, 97, 23);
		layeredPane_2.add(button_4);
		
		JLabel label_1 = new JLabel("\u6BCF\u65E5\u63A8\u8350");
		label_1.setFont(new Font("楷体", Font.PLAIN, 20));
		label_1.setForeground(Color.BLACK);
		label_1.setBounds(120, 93, 87, 30);
		layeredPane_2.add(label_1);
		
		lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("H:\\test\\\u4E16\u754C\u672A\u89E3\u4E4B\u8C1C.jpg"));
		lblNewLabel_1.setBounds(62, 133, 200, 300);
		layeredPane_2.add(lblNewLabel_1);
		
		txtrufo = new JTextArea();
		txtrufo.setEditable(false);
		txtrufo.setFont(new Font("等线", Font.PLAIN, 20));
		txtrufo.setLineWrap(true);
		txtrufo.setText("\u672C\u4E66\u7F16\u5F55\u4E86\u5730\u7403\u4E0A\u79D1\u5B66\u5C1A\u4E0D\u80FD\u89E3\u91CA\u7684\u5404\u79CD\u672A\u89E3\u4E4B\u8C1C\uFF1A\r\n1.\u5E7D\u7075\u822C\u9891\u7E41\u5149\u987E\u5730\u7403\u7684\u4E0D\u660E\u98DE\u884C\u7269UFO\u4E0E\u5149\u602A\u9646\u79BB\u7684\u5916\u661F\u4EBA\uFF0C\u662F\u6765\u5730\u7403\u8003\u5BDF\uFF0C\u8FD8\u662F\u5728\u76D1\u89C6\u5730\u7403\u4EBA\uFF1F\r\n2.\u590D\u6D3B\u8282\u5C9B\u7684\u5DE8\u77F3\u662F\u5916\u661F\u4EBA\u7684\u6770\u4F5C\u5417\uFF1F\r\n3.\u739B\u96C5\u4EBA\u4E3A\u4F55\u5728\u4E1B\u6797\u4E2D\u5EFA\u9020\u81EA\u5DF1\u7684\u5BB6\u56ED\uFF0C\u4E3A\u4EC0\u4E48\u5728\u521B\u9020\u4E86\u9AD8\u5EA6\u53D1\u8FBE\u7684\u6587\u660E\u4E4B\u540E\uFF0C\u53C8\u7A81\u7136\u5728\u4E00\u591C\u4E4B\u95F4\u5C06\u5B83\u629B\u5F03\u5462\uFF1F\r\n4.\u4E16\u754C\u4E0A\u4E3A\u4F55\u4F1A\u6709\u90A3\u4E48\u591A\u201C\u6B7B\u4EA1\u4E09\u89D2\u533A\u201D\uFF0C\u662F\u5730\u78C1\u4F5C\u7528\uFF0C\u8FD8\u662F\u53E6\u6709\u7384\u673A\uFF1F\r\n......");
		txtrufo.setBounds(276, 151, 431, 273);
		layeredPane_2.add(txtrufo);
		
		button_5 = new JButton("\u6211\u7684\u4FE1\u606F");
		button_5.setBounds(676, 19, 97, 23);
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "select * from users where 账号 =" + MainRun.ID;
				Connection con = MySQLConnection.getConnection();
				try {
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					if(rs.next()) {
						new MyInfo(rs.getString("姓名"),rs.getInt("年龄"), 
								rs.getString("性别"),rs.getString("账号")).setVisible(true);
						dispose();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		layeredPane_2.add(button_5);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("H:\\test\\\u4E66\u56DB\u53F6\u8349.jpg"));
		lblNewLabel.setBounds(0, 0, 800, 500);
		contentPane.add(lblNewLabel);
	}
	public void setButton(boolean flag) {
		if(flag) {
			button.setVisible(false);
			button_1.setVisible(false);
			button_5.setVisible(true);
		}
		else {
			button.setVisible(true);
			button_1.setVisible(true);
			button_5.setVisible(false);
		}
	}
}
