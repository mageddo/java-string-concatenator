package com.mageddo.stringcontatenator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;

public class App extends JFrame {
	private static final long serialVersionUID = 7259014485143657400L;
	protected static final Object VALIDATION_MESSAGE = "Type some text before";
	private final String lineSeparator = System.getProperty("line.separator");
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
				App.this.txtJavaCode.setText(App.this.generateJavaCode(sql));
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

	private String generateSqlCode(String sql) {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("java");
		try {
			Object result = engine.eval(sql + "sql.toString();");
			System.out.println(result);
		} catch (ScriptException e1) {
			e1.printStackTrace();
		}
		return "oi :/";
	}

	private String generateJavaCode(String inputStr) {
		StringBuffer strBuilder = new StringBuffer();
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(
					new ByteArrayInputStream(inputStr.getBytes())));

			strBuilder.append("StringBuilder str = new StringBuilder();");
			strBuilder.append(this.lineSeparator);
			String line;
			while ((line = bf.readLine()) != null) {
				
				// scaping aspas
				line = line.replaceAll("\"", "\\\\\"");
				
				strBuilder.append("str.append(\"");
				strBuilder.append(line);
				strBuilder.append(" \\n");
				strBuilder.append("\");");
				strBuilder.append(this.lineSeparator);
			}
		} catch (IOException localIOException) {
		}
		return strBuilder.toString();
	}

	public static void main(String[] args) {
		new App();
	}

}
