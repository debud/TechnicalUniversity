import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.math.BigInteger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class View extends JFrame{
	/*input JText*/
	private JTextPane noCashiers			= new JTextPane();
	private JTextPane maxArrivial			= new JTextPane();
	private JTextPane minArrivial			= new JTextPane();
	private JTextPane maxService			= new JTextPane();
	private JTextPane minService			= new JTextPane();
	private JTextPane simulationTime		= new JTextPane();
	private JTextPane intervalLeft  		= new JTextPane();
	private JTextPane intervalRight	        = new JTextPane();
	/*output JText */
	static private JTextArea avgData		= new JTextArea();
	static private JTextArea outputEvents	= new JTextArea();
	JPanel panel				 		    = new JPanel();
	JPanel content1 						= new JPanel();
	JPanel content2 						= new JPanel();
	JPanel content3 						= new JPanel();
	JPanel content4 						= new JPanel();
	JPanel contentOutput 					= new JPanel();
	private JButton btnStartSimulation	    = new JButton("Start Simulation");
	private JButton cashier1 				= new JButton("Cashier #1");
	private JButton cashier2			    = new JButton("Cashier #2");
	private JButton cashier3 				= new JButton("Cashier #3");
	private JButton cashier4 				= new JButton("Cashier #4");
	private JButton cashier5 				= new JButton("Cashier #5");
	private JTextArea[] queue				= new JTextArea[5];
	private JScrollPane[] Qscroll		    = new JScrollPane[5];
	Font font 								= new Font("Courier", Font.BOLD, 20);
	Color white							    = new Color(255, 255, 255);
	Color red 								= new Color(250, 250, 250);
	Color blackish 							= new Color(50, 0, 0);
	
	View(){
		cashier1.setBackground(new Color(250, 0, 0));
		cashier2.setBackground(new Color(250, 0, 0));
		cashier3.setBackground(new Color(250, 0, 0));
		cashier4.setBackground(new Color(250, 0, 0));
		cashier5.setBackground(new Color(250, 0, 0));
		panel.setBackground(new Color(30, 20, 50));
	
		noCashiers.setBackground(new Color(50, 80, 180));
		maxArrivial.setBackground(new Color(50, 80, 180));
		minArrivial.setBackground(new Color(50, 80, 180));
		maxService.setBackground(new Color(50, 80, 180));
		minService.setBackground(new Color(50, 80, 180));
		simulationTime.setBackground(new Color(50, 80, 180));
		intervalLeft.setBackground(new Color(50, 80, 180));
		intervalRight.setBackground(new Color(50, 80, 180));
		avgData.setBackground(new Color(150, 150, 250));
		outputEvents.setBackground(new Color(150, 150, 250));
		
		cashier1.setForeground(white);
		cashier2.setForeground(white);
		cashier3.setForeground(white);
		cashier4.setForeground(white);
		cashier5.setForeground(white);
		noCashiers.setForeground(white);
		maxArrivial.setForeground(white);
		minArrivial.setForeground(white);
		maxService.setForeground(white);
		minService.setForeground(white);
		simulationTime.setForeground(white);
		intervalLeft.setForeground(white);
		intervalRight.setForeground(white);
		avgData.setForeground(blackish);
		outputEvents.setForeground(blackish);
		
		cashier1.setFont(font);
		cashier2.setFont(font);
		cashier3.setFont(font);
		cashier4.setFont(font);
		cashier5.setFont(font);
		noCashiers.setFont(font);
		maxArrivial.setFont(font);
		minArrivial.setFont(font);
		maxService.setFont(font);
		minService.setFont(font);
		simulationTime.setFont(font);
		intervalLeft.setFont(font);
		intervalRight.setFont(font);
		avgData.setFont(font);
		outputEvents.setFont(font);

		Dimension inputDim = new Dimension(50, 30);
		Dimension outputDim1 = new Dimension(950, 400);
		Dimension outputDim2 = new Dimension(500, 300);
		Dimension cashierDim = new Dimension(300, 100);
		Dimension queueDim = new Dimension(300, 300);
		JScrollPane scroll = new JScrollPane(outputEvents);
		scroll.setPreferredSize(outputDim1);
		scroll.setSize( 100, 100 );
		this.setPreferredSize(new Dimension(1600, 1000));
		noCashiers.setFont(font);
		noCashiers.setPreferredSize(inputDim);
		simulationTime.setPreferredSize(inputDim);
		minArrivial.setPreferredSize(inputDim);
		maxArrivial.setPreferredSize(inputDim);
		minService.setPreferredSize(inputDim);
		maxService.setPreferredSize(inputDim);
		intervalLeft.setPreferredSize(inputDim);
		intervalRight.setPreferredSize(inputDim);
		avgData.setPreferredSize(outputDim2);		
		cashier1.setPreferredSize(cashierDim);
		cashier2.setPreferredSize(cashierDim);
		cashier3.setPreferredSize(cashierDim);
		cashier4.setPreferredSize(cashierDim);
		cashier5.setPreferredSize(cashierDim);
		content1.add(new JLabel("Number of cashiers:"));
		content1.add(noCashiers);
		content1.add(new JLabel("Simulation Time:"));
		content1.add(simulationTime);
		content1.add(new JLabel("Interval of arriving time between customers:  minimum = "));
		content1.add(minArrivial);
		content1.add(new JLabel("maximum = "));
		content1.add(maxArrivial);
		content1.add(new JLabel("Specify an interval to track the averages: left endpoint ="));
		content1.add(intervalLeft);
		content1.add(new JLabel("right endpoint ="));
		content1.add(intervalRight);
		content2.add(new JLabel("Minimum and maximum service time possible of a client: minimum = "));
		content2.add(minService);
		content2.add(new JLabel("maximum = "));
		content2.add(maxService);
		content2.add(btnStartSimulation);
		content3.add(cashier1);
		content3.add(cashier2);
		content3.add(cashier3);
		content3.add(cashier4);
		content3.add(cashier5);
		for (int i = 0; i < 5; i++)
		{
			queue[i] = new JTextArea();
			queue[i].setForeground(red);
			queue[i].setBackground(new Color(40, 30, 70));
			Qscroll[i] = new JScrollPane(queue[i]);
			Qscroll[i].setPreferredSize(queueDim);
			Qscroll[i].setSize( 100, 100 );	
		}
		for (int i = 0; i < 5; i++)
			content4.add(Qscroll[i]);
		avgData.setText("average things");
		contentOutput.add(avgData);
		contentOutput.add(scroll);
		panel.add(content1);
		panel.add(content2);
		panel.add(content3);
		panel.add(content4);
		panel.add(contentOutput);
		this.setContentPane(panel);
		this.pack();
		this.setTitle("Queues Simulation");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void addSimulationListener(ActionListener l) {
		btnStartSimulation.addActionListener(l);
	}
	
	int getNoCashiers(){
		BigInteger value = new BigInteger(noCashiers.getText());
		if (value.intValue() <= 0)
			putError("negative/null input forbidden");
		if (value.intValue() > 5)
			putError("Maximum number of cashiers available is 5");
		return value.intValue();
	}
	
	int getSimulationTime(){
		BigInteger value = new BigInteger(simulationTime.getText());
		if (value.intValue() <= 0)
			putError("negative/null input forbidden");
		return value.intValue();
	}
	
	int getMaxArrivial(){
		BigInteger value = new BigInteger(maxArrivial.getText());
		if (value.intValue() <= 0)
			putError("negative/null input forbidden");
		return value.intValue();
	}
	
	int getMinArrivial(){
		BigInteger value = new BigInteger(minArrivial.getText());
		if (value.intValue() <= 0)
			putError("negative/null input forbidden");
		return value.intValue();
	}
	
	int getMaxService(){
		BigInteger value = new BigInteger(maxService.getText());
		if (value.intValue() <= 0)
			putError("negative/null input forbidden");
		return value.intValue();
	}
	
	int getMinService(){
		BigInteger value = new BigInteger(minService.getText());
		if (value.intValue() <= 0)
			putError("negative/null input forbidden");
		return value.intValue();
	}
	
	void setAvgData(String value) {
		avgData.setText(value);
	}
	
	void setOutputEvents(String value) {
		outputEvents.setText(value);
	}
	
	void setQueueOutput(int index, String value) {
		queue[index].setText(value);
	}
	
	int getLeftEndpointOfInterval(){
		BigInteger value = new BigInteger(intervalLeft.getText());
		if (value.intValue() <= 0)
			putError("negative/null input forbidden");
		return value.intValue();
	}
	
	int getRightEndpointOfInterval(){
		BigInteger value = new BigInteger(intervalRight.getText());
		if (value.intValue() <= 0)
			putError("negative/null input forbidden");
		return value.intValue();
	}
	
	public boolean inputValidation = true;
	void	putError(String value){
		JOptionPane.showMessageDialog(this, value);
		inputValidation = false;
	}
}