import java.util.ArrayList;
import java.awt.*;

public class Line extends Primatives{

	
	Line(ArrayList<Integer> xArray, ArrayList<Integer> yArray, Color c, boolean filled)
	{
		super(xArray, yArray, c, filled);
	}
	
	//draw the line
	public void paint(Graphics g)
	{
		Graphics g2d = (Graphics2D) g;
		g2d.setColor(pColor);
		g.drawLine(xArray2.get(0), yArray2.get(0), xArray2.get(1), yArray2.get(1));
	}
}
