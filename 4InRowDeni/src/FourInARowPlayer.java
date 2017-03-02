import java.awt.Color;

public class FourInARowPlayer {

	private static int nr = 0;
	private static Color[] colors = {Color.yellow, Color.red};
	
	private String name;
	private Color color;
	private int points; 
	
	public FourInARowPlayer()
	{
		nr++;
		name = "Player " + nr;
		points = 0;
		color = colors[nr - 1];
	}

	public Color getColor() {
		return color;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String newName)
	{
		name = newName;
	}
	
	public void addAPoint()
	{
		points++;
	}
	
	public int getPoints()
	{
		return points;
	}
	
	public void resetScore()
	{
		points = 0;
	}
	
}
