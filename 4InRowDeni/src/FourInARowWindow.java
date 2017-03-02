import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FourInARowWindow extends Applet implements MouseListener, ActionListener {

	private static final long serialVersionUID = 1L;
	
	FourInARowComponents components;
	FourInARowController controller;
	FourInARowPlayer[] players = {new FourInARowPlayer(), new FourInARowPlayer()};
	
	int countClick;
	boolean gameOver;
	int offset;

	public void init() {

		this.setLayout(null);
		setSize(800, 640);
		addMouseListener(this);
		FourInARowOffset.setOffset();
		components = new FourInARowComponents(this, players[0].getColor(), players[1].getColor());
		controller = new FourInARowController();
		gameOver = false;
		offset = FourInARowOffset.offset;
	}

	public void paint(Graphics gr) {

		setBackground(Color.PINK);
		gr.drawRect(offset, offset, offset * 7, offset * 6);
		for (int l = 0; l < 6; l++) {
			for (int c = 0; c < 7; c++) {
				gr.setColor(Color.blue);
				gr.fill3DRect(offset + (c * offset), offset + (l * offset), offset, offset, false);
				gr.setColor(Color.cyan);
				gr.fillOval(offset + (c * offset) + 3, offset + (l * offset) + 3, offset - 6, offset - 6);
			}
		}
	}

	public void drawOval(int y, int x, Color c) {
		Graphics gr = this.getGraphics();
		gr.setColor(c);
		gr.fillOval(offset + (x * offset) + 3, offset + (y * offset) + 3, offset - 6, offset - 6);
	}
	
	public void setScore(int winner)
	{
		players[winner - 1].addAPoint();
		components.scoreLabel.setText(players[0].getName() + " ("+ players[0].getPoints() +")" + "-" +
				  "("+ players[1].getPoints() +") " + players[1].getName());
	}

	public void mouseClicked(MouseEvent e) {
		if (!gameOver) { /** am nevoie de variabila gameOver pentru a inceta
							 * jocul daca s-a umplut grila sau daca cineva a
							 * castigat pana la apasarea butonului StartNewGame
							 **/
			int l, c;
			c = controller.getColumnSelected(e.getX());
			if (c != -1) {
				l = controller.getLineAvailable(c);
				if (l != -1) {
					countClick++;
					if(controller.whoseTurn(l, c, countClick) == 1)
					{
						drawOval(l, c, Color.yellow);
						components.playerTurnLabel.setForeground(Color.red);
						components.playerTurnLabel.setText(players[1].getName());
					}else
					{
						drawOval(l, c, Color.red);
						components.playerTurnLabel.setForeground(Color.yellow);
						components.playerTurnLabel.setText(players[0].getName());
					}
					int winner = controller.testWinner();
					System.out.println("winner: " + winner);
					if (winner == 1 || winner == 2) {
						components.winnerLabel.setForeground(players[winner - 1].getColor());
						components.winnerLabel.setText(players[winner - 1].getName() + "  has won");
						setScore(winner);
						add(components.winnerLabel);
						gameOver = true;
					}
					else if(winner == 3)
					{
						components.winnerLabel.setForeground(Color.green);
						components.winnerLabel.setText("Game Over");
						add(components.winnerLabel);
						gameOver = true;
					}
					add(components.playerTurnLabel);
				}
			}
		} 
	}
	
	public void removeWinnerLabel()
	{
		  this.remove(components.winnerLabel);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		components.btnStart.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
				  removeWinnerLabel();
					repaint();
					controller.gridInit();
					gameOver = false;
					players[0].setName(components.textFieldPlayer1.getText());
					players[1].setName(components.textFieldPlayer2.getText());
					components.scoreLabel.setText(players[0].getName() + " ("+ players[0].getPoints() +")" + "-" +
							  "("+ players[1].getPoints() +") " + players[1].getName());
					components.playerTurnLabel.setText(players[countClick % 2].getName());
			  } 
			} );
		
		components.btnReset.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
				  removeWinnerLabel();
					repaint();
					controller.gridInit();
					gameOver = false;
					players[0].setName(components.textFieldPlayer1.getText());
					players[0].resetScore();
					players[1].setName(components.textFieldPlayer2.getText());
					players[1].resetScore();
					components.scoreLabel.setText(players[0].getName() + " ("+ players[0].getPoints() +")" + "-" +
							  "("+ players[1].getPoints() +") " + players[1].getName());
					components.playerTurnLabel.setText(players[countClick % 2].getName());
			  } 
			} );
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

}