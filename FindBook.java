import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.AbstractListModel;
import javax.swing.ScrollPaneConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FindBook extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton button;
	private JList list;
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	public FindBook() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				MainWin mw = new MainWin();
				mw.setVisible(true);
				if("".equals(MainRun.ID))
					mw.setButton(false);
				else mw.setButton(true);
			}
		});
		button = new JButton("查找");
		setBounds(550, 200, 450, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u60F3\u8981\u67E5\u627E\u7684\u4E66\u7C4D\u7684\u540D\u79F0");
		label.setBounds(87, 10, 318, 34);
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(144, 54, 150, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		

		button.setBounds(165, 94, 97, 23);
		contentPane.add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(67, 134, 318, 160);
		contentPane.add(scrollPane);
		
		list = new JList();
		scrollPane.setViewportView(list);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list.clearSelection();
				String name = textField.getText();
				if("".equals(name)) {
					new ErrorTip("警告", "请输入书籍名称");
					return;
				}
				list.setModel(new AbstractListModel() {
					String[] values = new String[0];
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
							ResultSet rs = 
									stmt.executeQuery("select * from books where book_name='" + name + "'");
							int rowCount = 0;
							if(rs.last()) {
								rowCount = rs.getRow();
								rs.beforeFirst();
							}
							if(rowCount == 0) {
								new ErrorTip("提示", "数据库里没有这本书");
								textField.setText("");
								return;
							}
							values = new String[rowCount];
							int i = 0;
							while(rs.next()) {
								String canOut;
								int can_out = rs.getInt("can_out");
								if(can_out == 1) canOut = "已外借";
								else canOut = "还未外借";
								values[i] = "编号： " + rs.getString("id") +
										"     书名： " + rs.getString("book_name")
										+ "    作者：" + rs.getString("author")
										+ "    是否借出：" + canOut;
								i++;
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				});
			}
		});
	}

}
