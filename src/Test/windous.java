package Test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class windous extends JFrame {
	private JTextField jtf1 = new JTextField();
	private JTextField jtf2 = new JTextField();
	private JTextField jtf3 = new JTextField();
	private JTextField jtf4 = new JTextField();
	private JTextField jtf5 = new JTextField();
	private JTextArea jtf6 = new JTextArea(5,19);

	private JButton jbt = new JButton("启动");

	public windous() {
		JPanel p1 = new JPanel(new GridLayout(8, 2));
		p1.add(new JLabel("LRU算法"));
		p1.add(new JLabel(" "));
		p1.add(new JLabel("输入页面个数"));
		p1.add(jtf1);
		p1.add(new JLabel("输入最小物理块数"));
		p1.add(jtf2);
		p1.add(new JLabel("输入页面访问序列"));
		p1.add(jtf3);
		p1.setBorder(new TitledBorder("页面置换"));
		JPanel p3 = new JPanel(new GridLayout(3, 1));
		p3.add(new JLabel("输出缺页次数"));
		p3.add(jtf4);
		p3.add(new JLabel("输出缺页率"));
		p3.add(jtf5);
		p3.add(new JLabel("输出过程"));
		p3.add(jtf6);
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p2.add(jbt);
		add(p1, BorderLayout.NORTH);
		add(p3, BorderLayout.CENTER);
		add(p2, BorderLayout.SOUTH);

		jbt.addActionListener(new ButtonListener());
	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int N = Integer.parseInt(jtf1.getText());
			int M = Integer.parseInt(jtf2.getText());
			String inputString = jtf3.getText();
			LRUtest re = new LRUtest(N, M, inputString);
			re.dispaly();
			jtf6.setLineWrap(true);
			jtf4.setText(String.format("%d", re.Outputcishu()));
			jtf5.setText(String.format("%s", re.Outputlv()));
			jtf6.setText(String.format(re.Output1()+"\r\n"+re.Output2()));
		}
	}

	public static void main(String[] args) {
		windous frame = new windous();
		frame.pack();
		frame.setTitle("页面置换算法");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
