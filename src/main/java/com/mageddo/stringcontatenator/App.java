package com.mageddo.stringcontatenator;

import static com.mageddo.stringcontatenator.AppMethods.generateJavaCode;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class App extends JFrame {
	private static final long serialVersionUID = 7259014485143657400L;
	protected static final Object VALIDATION_MESSAGE = "Type some text before";
	private JTextArea txtSql = new JTextArea();
	private JTextArea txtJavaCode = new JTextArea();
	private JButton bGenerateJavaQuery = new JButton("concatenate!");
	private JButton bClear = new JButton("clear result");
	private JButton bClearAll = new JButton("clear all inputs");

	public App() {
		setLayout(new BorderLayout());

		txtJavaCode.setTabSize(2);
		txtSql.setTabSize(2);
		JPanel panelCodigos = new JPanel(new GridLayout(2, 1));
		panelCodigos.add(new JScrollPane(this.txtSql));
		panelCodigos.add(new JScrollPane(this.txtJavaCode));

		JPanel panelButtons = new JPanel(new GridLayout(1, 3));
		panelButtons.add(this.bClear);
		panelButtons.add(this.bClearAll);
		panelButtons.add(this.bGenerateJavaQuery);

		add(panelCodigos, BorderLayout.CENTER);
		add(panelButtons, BorderLayout.SOUTH);

		this.bGenerateJavaQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = App.this.txtSql.getText();
				if ((sql == null) || ("".equals(sql.trim()))) {
					JOptionPane.showMessageDialog(null, VALIDATION_MESSAGE);
					return;
				}
				App.this.txtJavaCode.setText(generateJavaCode(sql));
			}
		});
		this.bClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				App.this.txtJavaCode.setText("");
			}
		});
		this.bClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				App.this.txtJavaCode.setText("");
				App.this.txtSql.setText("");
			}
		});
		setSize(600, 400);
		setTitle("Java String Concatenator 1.0");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}



	public static void main(String[] args) {
		new App();
	}

}
