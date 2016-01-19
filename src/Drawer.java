import java.util.ArrayList;

import processing.core.PApplet;


/**
 * 绘图
 */
public class Drawer extends PApplet{
	
	private ArrayList<MPoint> points;
	
	private KMeansMethod kmd;
	
	public void setup()
	{
		size(800, 600);
		
		points = new ArrayList<MPoint>();
		for(int i = 0 ; i < 25 ; i++)
		{
			points.add(new MPoint((float)Math.random() * 200 + 300, (float)Math.random() * 200 + 300));
		}
		for(int i = 25 ; i < 50 ; i++)
		{
			points.add(new MPoint((float)Math.random() * 200 + 200, (float)Math.random() * 200 + 0));
		}
		for(int i = 50 ; i < 75 ; i++)
		{
			points.add(new MPoint((float)Math.random() * 200 + 0, (float)Math.random() * 200 + 200));
		}
		
		kmd = new KMeansMethod();
		kmd.setData(points, 3);
		
		noLoop();
	}
	
	public void draw()
	{
		background(255);
		noStroke();
		
		for(int i = 0 ; i < points.size() ; i++)
		{
			MPoint p = points.get(i);
			
			setColor(p.getType());
			
			ellipse(points.get(i).getX(), points.get(i).getY(), 5, 5);
		}
		
		MPoint[] center = kmd.getCenter();
		
		line(0 , 0, 50, 50);
		
		for(int i = 0 ; i < center.length; i++)
		{
			int color[] = setColor(i);
			MPoint p = center[i];
			
			stroke(color[0], color[1], color[2]);
			line(p.getX() - 10, p.getY(), p.getX() + 10, p.getY());
			line(p.getX(), p.getY() - 10, p.getX(), p.getY() + 10);
		}
	}
	
	public void mouseClicked()
	{
		kmd.update();
		redraw();
	}
	
	public int[] setColor(int type)
	{
		type %= 255;
		int[] color = new int[]{type * 70 %255, type * 150 %255, type * 250 %255};
		
		fill(color[0], color[1], color[2]);
		
		return color;
	}
	
	public static void main(String args[])
	{
		PApplet.runSketch(new String[]{"k means cluster method"}, new Drawer());
	}
}
