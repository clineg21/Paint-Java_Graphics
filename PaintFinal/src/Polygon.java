import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Polygon extends Primatives
{

	Polygon(ArrayList<Integer> xArray, ArrayList<Integer> yArray, Color c, boolean filled) 
	{
		super(xArray, yArray, c, filled);
	}

	@Override
	public void fill(Graphics g) 
	{
		//we have to store the values in the x and y coordinate arraylists in int[] arrays in order to fill the polygon
		int[] xCoordinates = new int[xArray2.size()];
		int[] yCoordinates = new int[yArray2.size()];
		
		for(int i = 0; i < xCoordinates.length; i++) {
			xCoordinates[i] = xArray2.get(i);
		}
		
		for(int i = 0; i < yCoordinates.length; i++) {
			yCoordinates[i] = yArray2.get(i);
		}
		
		Graphics g2d = (Graphics2D) g;
		g.setColor(pColor);
		g2d.fillPolygon(xCoordinates, yCoordinates, xCoordinates.length);
	}
	
	public void paint(Graphics g) 
	{
		super.setbBox();
		//x holds the index value of the array
		//add one to x for each iteration through the loop
		int x = 0;
				
		//while x is less than the x array, check if x does not equal the last index in the array
		//if x does not equal the last value in the array, get the values at x and x + 1
		//else, if x does equal the last values in the array, get the coordinate at index x and set the last 2 coordinates
		//to the first values in the array to connect the polygon
		//increment x by 1 to go through the arraylist
		while(x <= xArray2.size() - 1) { 
			if(x != xArray2.size() - 1)
			{
				g.drawLine(xArray2.get(x), yArray2.get(x), xArray2.get(x + 1), yArray2.get(x + 1));
			}	
			else
			{
				g.drawLine(xArray2.get(x), yArray2.get(x), xArray2.get(0), yArray2.get(0));
			}
			x++;
		}
	}

}
