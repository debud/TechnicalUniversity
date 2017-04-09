package polinoame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class View extends JFrame {

	private JTextPane output1 = new JTextPane();
	private JTextPane output2 = new JTextPane();
	private JTextPane outputResult = new JTextPane();
	private JTextPane inputCoef = new JTextPane();
	private JTextPane inputPower = new JTextPane();
	private JButton butAddMonomial = new JButton("Add Monomial");
	private JButton butAddition = new JButton("Addition");
	private JButton butSubstraction = new JButton("Subtraction");
	private JButton butMultiplication = new JButton("Multiplication");
	private JButton butDivision = new JButton("Division");
	private JButton butDerivate = new JButton("Derivate");
	private JButton butIntegrate = new JButton("Integrate");
	private JComboBox inputPolynomialNumb = new JComboBox();
	Font font = new Font("Courier", Font.BOLD, 20);
	JPanel panel = new JPanel();
	JPanel content1 = new JPanel();
	JPanel content2 = new JPanel();
	JPanel content3 = new JPanel();
	JPanel content4 = new JPanel();
	boolean inputValidation = false;

	View() {

		this.setPreferredSize(new Dimension(1500, 600));
		inputCoef.setPreferredSize(new Dimension(150, 50));
		inputPower.setPreferredSize(new Dimension(150, 50));
		output1.setPreferredSize(new Dimension(500, 100));
		output2.setPreferredSize(new Dimension(500, 100));
		outputResult.setPreferredSize(new Dimension(1000, 100));
		inputPolynomialNumb.addItem("1");
		inputPolynomialNumb.addItem("2");
		JScrollPane scroll = new JScrollPane(output1);
		getContentPane().add(scroll);

		panel.setBackground(new Color(30, 20, 50));
		inputCoef.setBackground(new Color(50, 80, 80));
		inputPower.setBackground(new Color(50, 80, 80));
		output1.setBackground(new Color(50, 80, 80));
		output2.setBackground(new Color(50, 80, 80));
		outputResult.setBackground(new Color(40, 30, 70));
		output1.setForeground(new Color(255, 255, 255));
		output2.setForeground(new Color(255, 255, 255));
		outputResult.setForeground(new Color(255, 255, 255));
		inputCoef.setForeground(new Color(255, 255, 255));
		inputPower.setForeground(new Color(255, 255, 255));
		inputCoef.setFont(font);
		inputPower.setFont(font);
		output1.setFont(font);
		output2.setFont(font);
		outputResult.setFont(font);

		content1.add(new JLabel("Add Coefficient"));
		content1.add(inputCoef);
		content1.add(new JLabel("Add Power"));
		content1.add(inputPower);
		content1.add(new JLabel("Select which Polynomial to modify"));
		content1.add(inputPolynomialNumb);
		content2.add(butAddMonomial);
		content2.add(butAddition);
		content2.add(butSubstraction);
		content2.add(butMultiplication);
		content2.add(butDivision);
		content2.add(butDerivate);
		content2.add(butIntegrate);
		content3.add(new JLabel("Polynomial 1"));
		content3.add(output1);
		content3.add(new JLabel("Polynomial 2"));
		content3.add(output2);
		content4.add(new JLabel("Result"));
		content4.add(outputResult);
		panel.add(content1);
		panel.add(content2);
		panel.add(content3);
		panel.add(content4);

		outputResult.setEditable(false);
		this.setContentPane(panel);
		this.pack();
		this.setTitle("Integer Polynomial Calculator");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void addMonomialListener(ActionListener l) {
			butAddMonomial.addActionListener(l);
	}

	void addAdditionListener(ActionListener l) {
		butAddition.addActionListener(l);
	}

	void addSubstractionListener(ActionListener l) {
		butSubstraction.addActionListener(l);
	}

	void addDivisionListener(ActionListener l) {
		butDivision.addActionListener(l);
	}

	void addMultiplicationListener(ActionListener l) {
		butMultiplication.addActionListener(l);
	}

	void addIntegrationListener(ActionListener l) {
		butIntegrate.addActionListener(l);
	}

	void addDerivationListener(ActionListener l) {
		butDerivate.addActionListener(l);
	}

	int getCoef() {
		String text = inputCoef.getText();
		try {
			if  (Integer.parseInt(text) > Integer.MAX_VALUE || Integer.parseInt(text) < Integer.MIN_VALUE){
				JOptionPane.showMessageDialog(this, "Input numbers should be in interval [-2147483648, 2147483647] !");
				inputValidation = false;
			} else { 
				BigInteger coefficient = new BigInteger(inputCoef.getText());
				inputValidation = true;
				return coefficient.intValue();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Invalid Input!");
			inputValidation = false;
		}
		return 0;

	}

	int getPower() {
		String text = inputPower.getText();
		try {
				if (Integer.parseInt(text) > Integer.MAX_VALUE || Integer.parseInt(text) < Integer.MIN_VALUE){
					JOptionPane.showMessageDialog(this, "Input numbers should be in interval [-2147483648, 2147483647] !");
					inputValidation = false;
				}else{ 
						BigInteger coefficient = new BigInteger(text);
						inputValidation = true;
						return coefficient.intValue();
				}
			}catch(Exception e)
				{
						JOptionPane.showMessageDialog(this, "Invalid Input!");
						inputValidation = false;
				}
			return 0;
	}

	int getPolynomialNumb() {
		Integer polynomialNumb = Integer.valueOf((String) inputPolynomialNumb.getSelectedItem());
		return polynomialNumb - 1;
	}

	void setOutput1(String value) {
		output1.setText(value);
	}

	void setOutput2(String value) {
		output2.setText(value);
	}

	void setOutputResult(String value) {
		outputResult.setText(value);
	}

	void showError(String errMessage) {
		JOptionPane.showMessageDialog(this, errMessage);
	}

}
