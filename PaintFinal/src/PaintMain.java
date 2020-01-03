import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class PaintMain extends Frame
{
	private static final long serialVersionUID = 1L;
	private MenuBar menuBar;
	private Menu choicesMenu, editMenu;
	// the current drawing color
	private Color curColor = Color.black;
	private MenuItem colorItem, exitItem, deleteItem, fillItem;
	private CheckboxMenuItem lineItem, rectItem, ovalItem, polyItem, freeHandItem, selectItem;
	private ArrayList<Integer> xArray;
	private ArrayList<Integer> yArray;
	private ArrayList<Integer> xControlPoints;
	private ArrayList<Integer> yControlPoints;
	private ArrayList<Primatives> primatives = new ArrayList<Primatives>();
	int xControlPoint, yControlPoint;
	private Primatives selected;
	private Primatives line, rect, oval, polygon, freehand;
	//set the default filled state for the primatives to false
	boolean filledState = false;
	//collects the x and y coordinates when the user clicks the screen
	int x, y;
	
	//freehand variables
	int xinit, yinit, xcur, ycur; 
	
	//Constructor
	PaintMain()
	{
		//stores the x coordinates of the primative points
		xArray = new ArrayList<Integer>();
		//stores the y coordinates of the primative points
		yArray = new ArrayList<Integer>();
		//stores the x coordinates of the control points for the primative
		xControlPoints = new ArrayList<Integer>();
		//stores the y coordinates of the control points for the primative
		yControlPoints = new ArrayList<Integer>();
		
		//Enables the closing of the window.
		addWindowListener(new MyFinishWindow());
		createMenu();
		
		//captures the users mouse dragged events and stores every coordinate they drag to in the x and y coordinate array lists		
		addMouseMotionListener(
				new MouseAdapter()
				{
					public void mouseDragged(MouseEvent e)
					{
						x = e.getX();
						y = e.getY();
						if(freeHandItem.getState() == true)
						{
								xArray.add(x);
								yArray.add(y);
								xControlPoints.add(x);
								yControlPoints.add(y);	
								repaint();	
						}
					}
					
				});
		
		addMouseListener(
				new MouseAdapter()
				{
					
					//capture the users mousePressed events
					public void mousePressed(MouseEvent evt)
					{
						if(freeHandItem.getState() == true)
						{
							//set the initial x and y coordinate points when the user presses the mouse
							xinit = evt.getX();
							yinit = evt.getY();
							xArray.add(xinit);
							yArray.add(yinit);
						}
					}
					
					//capture the users mouseReleased events
					public void mouseReleased(MouseEvent evt)
					{
						if(freeHandItem.getState() == true)
						{
							//when the user releases the mouse, create an instance of the freehand
							freehand = new FreeHand(xArray, yArray, curColor, filledState);
							//add the freehand to the primatives arraylist
							primatives.add(freehand);
							xArray.clear();
							yArray.clear();
							xControlPoints.clear();
							yControlPoints.clear();
						}
						repaint();
					}
					
					public void mouseClicked(MouseEvent e)
					{		
						x = e.getX();
						y = e.getY();
						
						if(lineItem.getState() == true)
						{
							xArray.add(x);
							yArray.add(y);
							xControlPoints.add(x);
							yControlPoints.add(y);
							//if the size of the x and y arrays equals 2, we have two points to 
							if(xArray.size() == 2 && yArray.size() == 2)
							{
								//create a new line
								line = new Line(xArray, yArray, curColor, filledState);
								//add the line to the arraylist
								primatives.add(line);
								xArray.clear();
								yArray.clear();
								xControlPoints.clear();
								yControlPoints.clear();
							}
						
						}
						else if(rectItem.getState() == true)
						{
							xArray.add(x);
							yArray.add(y);
							xControlPoints.add(x);
							yControlPoints.add(y);
							//if the size of the x and y arrays equals 2, we have two points to 
							if(xArray.size() == 2 && yArray.size() == 2)
							{
								//create a new rectangle
								rect = new Rectangle(xArray, yArray, curColor, filledState);
								//add the rectangle to the primatives arraylist
								primatives.add(rect);
								xArray.clear();
								yArray.clear();
								xControlPoints.clear();
								yControlPoints.clear();
							}
						}
						else if(ovalItem.getState() == true)
						{
							xArray.add(x);
							yArray.add(y);
							xControlPoints.add(x);
							yControlPoints.add(y);
							//if the size of the x and y arrays equals 2, we have two points to 
							if(xArray.size() == 2 && yArray.size() == 2)
							{
								//create a new oval
								oval = new Oval(xArray, yArray, curColor, filledState);
								//add the oval to the primitives arraylist
								primatives.add(oval);
								xArray.clear();
								yArray.clear();
								xControlPoints.clear();
								yControlPoints.clear();
							}
						}
						else if(polyItem.getState() == true) 
						{
							xArray.add(x);
							yArray.add(y);
							xControlPoints.add(x);
							yControlPoints.add(y);
							if((xArray.size() > 1 && yArray.size() > 1) && (x >= xArray.get(0) - 10 && x <= xArray.get(0) + 10 && y >= yArray.get(0) - 10 && y <= yArray.get(0) + 10)) 
							{
								//create new polygon
								polygon = new Polygon(xArray, yArray, curColor, filledState);
								//add polygon to primitives
								primatives.add(polygon);
								xArray.clear();
								yArray.clear();
								xControlPoints.clear();
								yControlPoints.clear();
							}
						}
						else if(freeHandItem.getState() == true) {
							//call mouseDragged
							mouseDragged(e);
						}
						
						//if the state of the select item is true, loop through the primatives array list
						//and set the bounding box for each one
						else if(selectItem.getState() == true)
						{
							
							for(int i = 0; i < primatives.size(); i++)
							{
								//set the bounding box for the primative
								primatives.get(i).setbBox();
								//if the bounding box is clicked into, set selected to the primative at index i in the arraylist
								if(primatives.get(i).insideBox(x, y) == true)
								{
									selected = primatives.get(i);
								}
							}
						}
						//if the state of the select item is false, set selected to null
						if(selectItem.getState() == false)
						{
							selected = null;
						}
						
						repaint();
					}
				}
		);
		
		
	}
	
	
		
	
	private void createMenu()
	{
		//build the menu
		menuBar = new MenuBar();
		//main menu bar
		setMenuBar(menuBar);
		
		//create choices option for main menu
		choicesMenu = new Menu("Choices");
		//add choices option to main menu
		menuBar.add(choicesMenu);
		
		//allow user to draw a line
		lineItem = new CheckboxMenuItem("Line");
		//add the lineItem to the choices menu
		choicesMenu.add(lineItem);
		
		//allow the user to draw a rectangle
		rectItem = new CheckboxMenuItem("Rectangle");
		choicesMenu.add(rectItem);
		
		//allow the user to draw a oval
		ovalItem = new CheckboxMenuItem("Oval");
		choicesMenu.add(ovalItem);
		
		//allow the user to draw a polygon
		polyItem = new CheckboxMenuItem("Polygon");
		choicesMenu.add(polyItem);
		
		//allow the user to draw freehand
		freeHandItem = new CheckboxMenuItem("Free Hand");
		choicesMenu.add(freeHandItem);
		
		editMenu = new Menu("Edit");
		menuBar.add(editMenu);
		
		//allow the user to select a primitive
		selectItem = new CheckboxMenuItem("Select Shape");
		editMenu.add(selectItem);
		
		//allow the user to delete a primitive
		deleteItem = new MenuItem("Delete Shape");
		editMenu.add(deleteItem);
		deleteItem.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent evt)
					{
						if(selectItem.getState() == true)
						{
							primatives.remove(selected);
							selected = null;
							
						}
						repaint();
					}
				}
				
				);
		
		fillItem = new MenuItem("Fill Shape");
		editMenu.add(fillItem);
		fillItem.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent evt)
					{	
						//make sure the user is not attempting to fill a line or a freehand primitive
						//filling will only occur if the primitive selected is not a line or freehand
						if(selected != line && selected != freehand)
						{
							//if the selectItem state is true and the primitive's filled state is set to false,
							//set filled to true and fill the primitive
							if(selectItem.getState() == true && selected.isFilled() == false)
							{
								selected.setFillPrimative(true);
							}
							else
							{
								selected.setFillPrimative(false);
							}
						}
						
						repaint();
					}
				}
				);
		
		
		// a menu item through which we allow changing the color
		colorItem = new MenuItem("Color");
		choicesMenu.add(colorItem);
		colorItem.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent evt)
					{
						Color c = JColorChooser.showDialog(null,"Select Drawing Color", curColor);
						if(selectItem.getState() == true && selected != null)
						{
							 selected.setPColor(c);
						}
						else
						{
							curColor = c;
						}
						
						repaint();
					}
				}
				);
		
		
		//exit the program
		exitItem = new MenuItem("Exit");
		choicesMenu.add(exitItem);
		//event handler for the exit option
		exitItem.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent evt)
					{
						System.exit(0);
					}
				}
				);
	}
	

	
	//display the primitives and control points
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		
		//display the control points
		for(int i = 0; i < xControlPoints.size(); i++)
		{
			g2d.fillOval(xControlPoints.get(i), yControlPoints.get(i), 10, 10);
		}
		
		//draw the primitives
		for(int i = 0; i < primatives.size(); i++)
		{
			//if the filled state is set to true, call the filled method of the primitive when drawing it, otherwise call the paint method in the primitive's class
			if(primatives.get(i).isFilled() == true)
			{
				primatives.get(i).fill(g2d);
			}
			else
			{
				primatives.get(i).paint(g2d);
			}
			
		}
		
		//draw bounding boxes if the state of select is true
		for(int i = 0; i < primatives.size(); i++)
		{
			if(selected != null)
			{
				selected.drawBBox(g2d);
			}
		}	
	}

	public static void main(String[] argv)
	{
		//Generate the window.
		PaintMain f = new PaintMain();
		
		//Define a title for the window.
		f.setTitle("Basis for Java 2D programs");
		//Definition of the window size in pixels
		f.setSize(800, 600);
		//Show the window on the screen.
		f.setVisible(true);
	}
}

