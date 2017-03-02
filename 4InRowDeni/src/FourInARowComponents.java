import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;

public class FourInARowComponents {

	public Button btnStart;
	public Button btnReset;
	public Label playerTurnLabel;
	public Label winnerLabel;
	public Label titleLabel;
	public Label scoreLabel;
	public TextField textFieldPlayer1;
	public TextField textFieldPlayer2;
	private FourInARowWindow window;
	private int offset;
	private Color player1Color;
	private Color player2Color;
	
	public FourInARowComponents(FourInARowWindow window, Color player1Color, Color player2Color)
	{
		this.window = window;
		this.offset = FourInARowOffset.offset;
		this.player1Color = player1Color;
		this.player2Color = player2Color;
		initComponents();
		addComponents();
	}
	
	public void initComponents()
	{
		initTitleLabel();
		initPlayerTurnLabel();
		initWinnerLabel();
		initScoreLabel();
		initBtnStart();
		initTextFieldPlayer1();
		initTextFieldPlayer2();
		initBtnReset();
	}
	
	public void addComponents()
	{
		window.add(btnStart);
		window.add(btnReset);
		window.add(playerTurnLabel);
		window.add(textFieldPlayer1);
		window.add(textFieldPlayer2);
		window.add(scoreLabel);
		window.add(titleLabel);
	}
	
	public void initBtnStart()
	{
		int x = (int)(offset * 8.2);
		int y = (int)(offset * 2);
		int width = (int)(offset * 1.5);
		int height = (int)(offset / 2);
		
		btnStart = new Button("Start New Game");
		btnStart.setBounds(x, y , width, height);
		btnStart.setFont(new Font("Courier New", 1, (int)((offset / 2) * 0.3)));
		btnStart.addActionListener(window);
	}
	
	public void initBtnReset()
	{
		int x = (int)(offset * 8.2);
		int y = (int)(offset * 2.5);
		int width = (int)(offset * 1.5);
		int height = (int)(offset / 2);
		
		btnReset = new Button("Reset Score");
		btnReset.setBounds(x, y , width, height);
		btnReset.setFont(new Font("Courier New", 1, (int)((offset / 2) * 0.3)));
		btnReset.addActionListener(window);
	}
	
	public void initWinnerLabel()
	{
		winnerLabel = new Label();
		winnerLabel.setBounds((int)(offset * 3.5), offset / 2, 300, offset / 2);
		winnerLabel.setFont(new Font("Courier New", 1, (int)(offset * 0.2)));
		winnerLabel.setForeground(Color.red);
		winnerLabel.setAlignment(Label.CENTER);
	}
	
	public void initTitleLabel()
	{
		titleLabel = new Label("Four in a row");
		titleLabel.setBackground(Color.PINK);
		titleLabel.setForeground(Color.blue);
		titleLabel.setBounds((int)(offset), 0, offset * 7, (int)(offset * 0.6));
		titleLabel.setFont(new Font("Courier New", 2, (int)(offset * 0.7)));
		titleLabel.setAlignment(Label.CENTER);
	}
	
	public void initScoreLabel()
	{
		scoreLabel = new Label();
		scoreLabel.setBackground(Color.PINK);
		scoreLabel.setForeground(Color.black);
		scoreLabel.setBounds(offset, offset * 7, offset * 7, (int)(offset / 3));
		scoreLabel.setFont(new Font("Courier New", 1, (int)(offset * 0.2)));
		scoreLabel.setAlignment(Label.CENTER);
	}
	
	public void initPlayerTurnLabel()
	{
		playerTurnLabel = new Label();
		playerTurnLabel.setBackground(Color.PINK);
		playerTurnLabel.setBounds((int)(offset), (int)(offset * 0.8), (int)(offset * 0.9), (int)(offset * 0.2));
		playerTurnLabel.setFont(new Font("Courier New", 1, (int)(offset * 0.2)));
		playerTurnLabel.setForeground(Color.yellow);
		playerTurnLabel.setText("Player 1:");
	}
	
	public void initTextFieldPlayer1()
	{
		textFieldPlayer1 = new TextField("Player 1");
		textFieldPlayer1.setBounds((int)(offset * 8.2), (int)(offset), (int)(offset * 1.5), (int)(offset / 3));
		textFieldPlayer1.setFont(new Font("Courier New", 1, (int)(offset * 0.2)));
		textFieldPlayer1.setBackground(Color.cyan);
		textFieldPlayer1.setForeground(player1Color);
	}
	
	public void initTextFieldPlayer2()
	{
		textFieldPlayer2 = new TextField("Player 2");
		textFieldPlayer2.setBounds((int)(offset * 8.2), (int)(offset * 1.5), (int)(offset * 1.5), (int)(offset / 3));
		textFieldPlayer2.setFont(new Font("Courier New", 1, (int)(offset * 0.2)));
		textFieldPlayer2.setBackground(Color.cyan);
		textFieldPlayer2.setForeground(player2Color);
	}
	
}
