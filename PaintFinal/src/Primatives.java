import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public abstract class Primatives
{
		//stores the x coordinate points for the primative
		protected ArrayList<Integer> xArray2;
		//stores the y coordinate points for the primative
		protected ArrayList<Integer> yArray2;
		//stores the color for the primative
		protected Color pColor;
		//set bounding box variables
		int xtop, ytop, width, height;
		//set fill state
		protected boolean filled;
		
	Primatives(ArrayList<Integer> xArray, ArrayList<Integer> yArray, Color c, boolean filled)
	{
		xArray2 = new ArrayList<Integer>();
		yArray2 = new ArrayList<Integer>();
		pColor = c;
		this.filled = filled;
		
		//loop through the array list with the points passed in from PaintMain class
		//add the value in each index to xArray2 and yArray2
		for(int i = 0; i < xArray.size(); i++)
		{
			xArray2.add(xArray.get(i));
			yArray2.add(yArray.get(i));
		}
	}
	
	//calculate the bounding box for the primitives
	public void setbBox()
	{	
		xtop = xArray2.get(0);
		ytop = yArray2.get(0);
		int maxX = xArray2.get(0), maxY = yArray2.get(0);
		
		//find the greatest and smallest y coordinates for the selected primitive
		for(int yIndex = 0; yIndex < yArray2.size(); yIndex++) 
		{
			if(yArray2.get(yIndex) < ytop)
			{
				ytop = yArray2.get(yIndex);
			}
			
			if(yArray2.get(yIndex) > maxY) 
			{
				maxY = yArray2.get(yIndex);
			}
		}
		
		//find the greatest and smallest x coordinates for the selected primitive
		for(int xIndex = 0; xIndex < xArray2.size(); xIndex++)
		{
			if(xArray2.get(xIndex) < xtop)
			{
				xtop = xArray2.get(xIndex);
			}
			
			if(xArray2.get(xIndex) > maxX)
			{
				maxX = xArray2.get(xIndex);
			}
		}
		
		width = maxX - xtop;
		height = maxY - ytop;

	}
	
	// test if the user clicks inside a bounding box
	// and return true for 'inside', false otherwise
	public boolean insideBox(int x, int y)
	{
		if((x > xtop) && (x < xtop + width) && (y > ytop) && (y < ytop + height))
		{
			return true;
		}
		return false;
	}
	
	//method to draw the bounding box
	public void drawBBox(Graphics g)
	{
		Graphics g2d = (Graphics2D) g;
		g2d.setColor(Color.red);
		g2d.drawRect(xtop, ytop, width, height);
	}
	
	//set the color for the primitive
	public void setPColor(Color c)
	{
		pColor = c;
	}
	
	//sets the filled state of the primitive
	public void setFillPrimative(boolean f)
	{
		filled = f;
	}
	
	//checks if the primitive is filled when this method is called
	public boolean isFilled()
	{
		return filled;
	}
	
	//this method is overridden in each of the primitive classes
	//needed to be overridden since we have different types of primitives such as lines and rectangles
	public void fill(Graphics g)
	{
		
	}
	
	//this method uses abstract to force a call to the paint method
	public abstract void paint(Graphics g);


}
