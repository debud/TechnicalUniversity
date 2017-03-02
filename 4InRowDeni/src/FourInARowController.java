
public class FourInARowController {

	private int[][] grid = new int[6][7];
	private int offset;

	public FourInARowController()
	{
		gridInit();
		offset = FourInARowOffset.offset;

	}
	
	public void gridInit() {
		for (int l = 0; l < 6; l++) {
			for (int c = 0; c < 7; c++)
				grid[l][c] = 0;
		}
	}
	
	public void printGrid() {
		for (int l = 0; l < 6; l++){
			for (int c = 0; c < 7; c++) 
				System.out.print(grid[l][c] + " ");
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}
	
	public int testWinner() {
		boolean isFull = true;
		for (int l = 0; l < 6; l++){
			for (int c = 0; c < 7; c++) 
			{
				if(grid[l][c] != 0)
				{
					System.out.println("Test:" + l + " " + c);

					if (c + 3 < 7 && l + 3 < 6)
						if(grid[l][c] == grid[l + 1][c + 1] && grid[l][c] == grid[l + 2][c + 2] && grid[l][c] == grid[l + 3][c + 3])
							return grid[l][c];
					if (c + 3 < 7)
						if(grid[l][c] == grid[l][c + 1] && grid[l][c] == grid[l][c + 2] && grid[l][c] == grid[l][c + 3])
							return grid[l][c];
					if (l + 3 < 6)
						if(grid[l][c] == grid[l + 1][c] && grid[l][c] == grid[l + 2][c] && grid[l][c] == grid[l + 3][c])
							return grid[l][c];
					if (l - 3 > -1 && c + 3 < 7)
						if(grid[l][c] == grid[l - 1][c + 1] && grid[l][c] == grid[l - 2][c + 2] && grid[l][c] == grid[l - 3][c + 3])
							return grid[l][c];
				}
				if(grid[l][c] == 0)
					isFull = false;
			}
		}
		if(isFull == true)
			return 3;
		return 0;
	}
	
	public int getLineAvailable(int c) {
		System.out.println("column " + c);
		for (int l = 5; l >= 0; l--) {
			if (grid[l][c] == 0)
				return l;
		}
		return -1;
	}

	public int getColumnSelected(int x) {
		for (int c = 0; c < 7; c++)
			if (x > (c + 1) * offset && x < (c + 2) * offset)
				return c;
		return -1;
	}
	
	public int whoseTurn(int l, int c, int countClick) {
		if (countClick % 2 == 0) {
			grid[l][c] = 2;
			return 2;
		} else {
			grid[l][c] = 1;
			return 1;
		}
	}

}
