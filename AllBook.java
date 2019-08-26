import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.sql.Statement;

import javax.swing.JMenu;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLayeredPane;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.UIManager;
import javax.swing.AbstractListModel;
import javax.swing.event.ListSelectionListener;


import javax.swing.event.ListSelectionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AllBook extends JFrame {

	private JPanel contentPane;
	private JLabel label;
	private JList list;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	private JList list_2;
	private String[] values = null;
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AllBook() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				new AdminMainWin().setVisible(true);
			}
		});
		setResizable(false);
		setBounds(500, 200, 626, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label = new JLabel("\u6240\u6709\u56FE\u4E66");
		label.setBounds(234, 10, 128, 46);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("楷体", Font.PLAIN, 20));
		contentPane.add(label);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(59, 66, 497, 280);
		contentPane.add(scrollPane);
		
		list_2 = new JList();
		
		list_2.setFont(new Font("楷体", Font.PLAIN, 20));
		list_2.setModel(new AbstractListModel() {
			private static final long serialVersionUID = 1L;
			{
				addList();
			}
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(list_2);
	}
	public void addList() {
		try {
			Connection con = MySQLConnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from books");
			int rowCount = 0;
			if(rs.last()) {
				rowCount = rs.getRow();
				rs.beforeFirst();
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
}
