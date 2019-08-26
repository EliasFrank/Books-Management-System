import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;

public class MyInfo extends JFrame {

	private JPanel contentPane;
	private JLabel label_2;
	private JLabel label_1;
	private JLabel label_3;
	private JLabel label_4;


	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	public MyInfo(String name, int age, String gender, String account) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				MainWin mw = new MainWin();
				mw.setVisible(true);
				mw.setButton(true);
			}
		});
		setBounds(550, 200, 396, 551);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u6211\u7684\u4FE1\u606F");
		label.setBounds(160, 10, 97, 35);
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		contentPane.add(label);
		
		label_1 = new JLabel("姓名：" +name);
		label_1.setBounds(72, 72, 117, 28);
		contentPane.add(label_1);
		
		label_2 = new JLabel("年龄：" + age);
		label_2.setBounds(72, 110, 117, 28);
		contentPane.add(label_2);
		
		label_3 = new JLabel("性别：" +gender);
		label_3.setBounds(225, 72, 97, 28);
		contentPane.add(label_3);
		
		label_4 = new JLabel("账号：" + account);
		label_4.setBounds(225, 110, 178, 28);
		contentPane.add(label_4);
		
		JButton button = new JButton("\u4FEE\u6539\u4E2A\u4EBA\u4FE1\u606F");
		button.setBounds(132, 181, 125, 35);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ModifyInfo().setVisible(true);
				dispose();
			}
		});
		contentPane.add(button);
		
		JLabel label_5 = new JLabel("\u5DF2\u501F\u56FE\u4E66");
		label_5.setBounds(150, 236, 107, 28);
		label_5.setFont(new Font("宋体", Font.PLAIN, 20));
		contentPane.add(label_5);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 295, 294, 197);
		contentPane.add(scrollPane);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values;
			{
				addList();
			}
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
			public void addList() {
				try {
					Connection con = MySQLConnection.getConnection();
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select * from books where who=" + MainRun.ID);
					int rowCount = 0;
					if(rs.last()) {
						rowCount = rs.getRow();
						rs.beforeFirst();
					}
					System.out.println(rowCount);
					values = new String[rowCount];
					int i = 0;
					while(rs.next()) {
						values[i] = "编号： " + rs.getString("id") +
								"     书名： " + rs.getString("book_name")
								+ "    作者：" + rs.getString("author");
						i++;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(list);
	}
}
