package com.mageddo.stringcontatenator;

import static com.mageddo.stringcontatenator.AppMethods.generateJavaCode;

import java.awt.*;

import javax.swing.*;

public class App extends JFrame {
	private static final long serialVersionUID = 7259014485143657400L;
	protected static final Object VALIDATION_MESSAGE = "Type some text before";
	private final JTextArea txtSql = new JTextArea();
	private final JTextArea txtJavaCode = new JTextArea();
	private final JTextField txtVariableName = new JTextField("str");
	private final JButton bGenerateJavaQuery = new JButton("generate java");
	private final JButton bGenerateStr = new JButton("generate string");
	private final JButton bClear = new JButton("clear java string");
	private final JButton bClearAll = new JButton("clear all inputs");

	public App() {
		setLayout(new BorderLayout());

		txtJavaCode.setTabSize(2);
		txtSql.setTabSize(2);
		final JPanel panelCodigos = new JPanel(new GridLayout(2, 1));
		panelCodigos.add(new JScrollPane(this.txtSql));
		panelCodigos.add(new JScrollPane(this.txtJavaCode));

		final JPanel panelButtons = new JPanel(new GridLayout(2, 3));
		panelButtons.add(new JLabel("variable:"));
		panelButtons.add(this.txtVariableName);
		panelButtons.add(this.bClear);
		panelButtons.add(this.bClearAll);
		panelButtons.add(this.bGenerateStr);
		panelButtons.add(this.bGenerateJavaQuery);

		add(panelCodigos, BorderLayout.CENTER);
		add(panelButtons, BorderLayout.SOUTH);

		setSize(600, 400);
		setTitle("Java String Concatenator 1.0");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

		setUpActions();
	}

	private void setUpActions() {
		/*
		 * Limpa o texto da caixa do código java
		 */
		this.bClear.addActionListener(e -> {
			App.this.txtJavaCode.setText("");
		});

		/**
		 * Limpa o texto de todas as caixas
		 */
		this.bClearAll.addActionListener(e -> {
			App.this.txtJavaCode.setText("");
			App.this.txtSql.setText("");
		});

		this.bGenerateJavaQuery.addActionListener((e) -> {
			final String sql = App.this.txtSql.getText();
			if(isValidInput(sql)){
				App.this.txtJavaCode.setText(generateJavaCode(sql, txtVariableName.getText()));
			}
		});

		this.bGenerateStr.addActionListener(e -> {
			final String javaStr = App.this.txtJavaCode.getText();
			if(isValidInput(javaStr)){
				App.this.txtSql.setText(AppMethods.generateTextFromStringBuilder(javaStr));
			}
		});
	}

	private boolean isValidInput(final String text) {
		if ((text == null) || ("".equals(text.trim()))) {
			JOptionPane.showMessageDialog(App.this, VALIDATION_MESSAGE);
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		new App();
	}

}
