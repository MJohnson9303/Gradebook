package gradebook;

import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.Point;
import javax.swing.JFrame;

// Parent class for all menus in the Gradebook project.
public class Menu
{
	private JFrame frame;
	private Container contentPane;
	private int sizeWidth = 818;
	private int sizeLength = 230;
	private boolean isResizable = false;
	private boolean isVisible = true;
	
	public Menu(String name)
	{
		frame = new JFrame(name);
	    frame.setSize(sizeWidth,sizeLength);
	    frame.setLocation(0, 0);
	    frame.setResizable(isResizable);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    contentPane = frame.getContentPane();
	}
	public void setResizibleStatus(boolean isResizable)
	{
		this.isResizable = isResizable;
	}
	public boolean getResizableStatus()
	{
		return isResizable;
	}
	public void setVisible(boolean isVisible)
	{
		this.isVisible = isVisible;
		frame.setVisible(isVisible);
	}
	public boolean getVisible()
	{
		return isVisible;
	}
	public void setLocation(int x, int y)
	{
		frame.setLocation(x, y);
	}
	public Point getLocation()
	{
		return frame.getLocation();
	}
	public void setLayout(LayoutManager layoutManager)
	{
		frame.setLayout(layoutManager);
	}
	public void add(Component component, String contraint)
	{
		contentPane.add(component, contraint);
	}
	public void remove(Component component)
	{
		contentPane.remove(component);
	}
	public void validate()
	{
		contentPane.validate();
	}
}
