/*This program calculates the total amount payable on a loan*/

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.math.BigDecimal;

public class LoanRepayment extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	JLabel lblTotalInterest = new JLabel("");
	JLabel lblTotalAmountRepayable = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoanRepayment frame = new LoanRepayment();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void simpleInterest(){
		
  		try{
  			//get values from textfields
  			BigDecimal years = new BigDecimal(textField_2.getText());
  			BigDecimal rate = new BigDecimal(textField_1.getText());
  			BigDecimal principle = new BigDecimal(textField.getText());
  			
  			//calculate simple interest
  			BigDecimal interest = principle.multiply(rate).multiply(years).divide(new BigDecimal(100));
  			
  			lblTotalInterest.setText("Total Interest: $"+ interest.setScale(2, BigDecimal.ROUND_HALF_UP));
  			lblTotalAmountRepayable.setText("Total repayment amount: $" + (principle.add(interest)));
  		}
  		catch(Exception IOException){
  			 JOptionPane.showMessageDialog(null, "Please enter valid values in all fields.");
  		}
	}
	
	public void compoundInterest(){
		int years;
		
  		try{
  			//get values from textfields
  			years = Integer.parseInt(textField_2.getText());
  			BigDecimal rate = new BigDecimal(textField_1.getText());
  			BigDecimal principle = new BigDecimal(textField.getText());
  			
  			BigDecimal interest = rate.multiply(principle).divide(new BigDecimal(100));
  			
  			BigDecimal accumulatedPrinciple = principle;
  			
  			for (int i = 0; i < years; ++i){
  				//get annual interest and recalculate principle
  				interest = rate.multiply(accumulatedPrinciple).divide(new BigDecimal(100));
  				accumulatedPrinciple = accumulatedPrinciple.add(interest);
  			}
  			
  			BigDecimal totalInterest = accumulatedPrinciple.subtract(principle);
  			
  			lblTotalInterest.setText("Total Interest: $"+ (totalInterest.setScale(2, BigDecimal.ROUND_HALF_UP)));
  			lblTotalAmountRepayable.setText("Total repayment amount: $" + accumulatedPrinciple.setScale(2, BigDecimal.ROUND_HALF_UP));
  		}
  		catch(Exception IOException){
  			 JOptionPane.showMessageDialog(null, "Please enter valid values in all fields.");
  		}
	}

	/**
	 * Create the frame.
	 */
	public LoanRepayment() {
		setTitle("Interest Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 304, 291);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblOriginalPrinciple = new JLabel("Borrowed Amount (US$)");
		lblOriginalPrinciple.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOriginalPrinciple.setBounds(10, 8, 138, 14);
		contentPane.add(lblOriginalPrinciple);
		
		textField = new JTextField();
		textField.setBounds(161, 5, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblInterestRate = new JLabel("Interest Rate (%)");
		lblInterestRate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInterestRate.setBounds(10, 33, 138, 14);
		contentPane.add(lblInterestRate);
		
		textField_1 = new JTextField();
		textField_1.setBounds(161, 30, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblTimeyears = new JLabel("Time (Years)");
		lblTimeyears.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTimeyears.setBounds(10, 58, 138, 14);
		contentPane.add(lblTimeyears);
		
		textField_2 = new JTextField();
		textField_2.setBounds(161, 55, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnCalculateSimpleInterest = new JButton("Calculate Simple Interest");
		btnCalculateSimpleInterest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				simpleInterest();
			}
		});
		btnCalculateSimpleInterest.setBounds(10, 86, 216, 23);
		contentPane.add(btnCalculateSimpleInterest);
		
		JButton btnNewButton = new JButton("Calculate Compound Interest");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				compoundInterest();
			}
		});
		btnNewButton.setBounds(10, 116, 216, 23);
		contentPane.add(btnNewButton);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTotalInterest.setText("");
				lblTotalAmountRepayable.setText("");
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
			}
		});
		btnClear.setBounds(10, 225, 70, 23);
		contentPane.add(btnClear);
		
		
		lblTotalInterest.setBounds(10, 146, 210, 14);
		contentPane.add(lblTotalInterest);
		
		lblTotalAmountRepayable.setBounds(10, 165, 216, 14);
		contentPane.add(lblTotalAmountRepayable);
	}
}
