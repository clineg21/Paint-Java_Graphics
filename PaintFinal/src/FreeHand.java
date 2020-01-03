import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class FreeHand extends Primatives {

	FreeHand(ArrayList<Integer> xArray, ArrayList<Integer> yArray, Color c, boolean filled) {
		super(xArray, yArray, c, filled);
	}

	//draw the freehand
	public void paint(Graphics g) 
	{
		
		int[] xCoordinates = new int[xArray2.size()];
		int[] yCoordinates = new int[yArray2.size()];
		
		for(int i = 0; i < xCoordinates.length; i++) {
			xCoordinates[i] = xArray2.get(i);
		}
		
		for(int i = 0; i < yCoordinates.length; i++) {
			yCoordinates[i] = yArray2.get(i);
		}
		
		Graphics g2d = (Graphics2D) g;
		g2d.setColor(pColor);
		g.drawPolyline(xCoordinates, yCoordinates, xCoordinates.length);
		
		
	}
	
	
	
}
