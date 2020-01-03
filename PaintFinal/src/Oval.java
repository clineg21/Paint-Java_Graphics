import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Oval extends Primatives
{
	

	Oval(ArrayList<Integer> xArray, ArrayList<Integer> yArray, Color c, boolean filled) 
	{
		super(xArray, yArray, c, filled);
	}
	
	@Override
	public void fill(Graphics g)
	{
		Graphics g2d = (Graphics2D) g;
		g.setColor(pColor);
		g.fillOval(Math.min(xArray2.get(0), xArray2.get(1)), Math.min(yArray2.get(0), yArray2.get(1)), Math.abs(xArray2.get(1) - xArray2.get(0)), Math.abs(yArray2.get(1) - yArray2.get(0)));
	}
	
	//draw the oval
	public void paint(Graphics g)
	{
		Graphics g2d = (Graphics2D) g;
		g2d.setColor(pColor);
		g.drawOval(Math.min(xArray2.get(0), xArray2.get(1)), Math.min(yArray2.get(0), yArray2.get(1)), Math.abs(xArray2.get(1) - xArray2.get(0)), Math.abs(yArray2.get(1) - yArray2.get(0)));
	}
	
}
