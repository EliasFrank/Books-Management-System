import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminMainWin extends JFrame {

	private JPanel contentPane;
	private final JLabel lblNewLabel = new JLabel("New label");
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMainWin frame = new AdminMainWin();
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
	public AdminMainWin() {
		setTitle("\u7BA1\u7406\u5458\u7A97\u53E3");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 150, 660, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		button = new JButton("\u6DFB\u52A0\u56FE\u4E66");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new BookInfo().setVisible(true);
			}
		});
		button.setBounds(38, 364, 115, 30);
		contentPane.add(button);
		
		button_1 = new JButton("\u5220\u9664\u56FE\u4E66");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new DeleteBook().setVisible(true);
			}
		});
		button_1.setBounds(185, 364, 115, 30);
		contentPane.add(button_1);
		
		button_2 = new JButton("\u4FEE\u6539\u56FE\u4E66\u4FE1\u606F");
		button_2.setBounds(337, 364, 115, 30);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ModifyBook().setVisible(true);
			}
		});
		contentPane.add(button_2);
		
		JLabel label = new JLabel("\u4EB2\u7231\u7684\u7BA1\u7406\u5458\uFF0C\u6B22\u8FCE\u60A8\u7684\u5230\u6765\uFF0C\u60A8\u8F9B\u82E6\u4E86");
		label.setBounds(149, 10, 411, 30);
		label.setFont(new Font("ÐÂËÎÌå", Font.PLAIN, 20));
		contentPane.add(label);
		lblNewLabel.setBounds(10, 50, 626, 293);
		lblNewLabel.setIcon(new ImageIcon("H:\\test\\\u4E00\u672C\u4E66.jpg"));
		contentPane.add(lblNewLabel);
		
		button_3 = new JButton("\u67E5\u770B\u6240\u6709\u56FE\u4E66");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AllBook().setVisible(true);
			}
		});
		button_3.setBounds(484, 366, 115, 27);
		contentPane.add(button_3);
	}
}
