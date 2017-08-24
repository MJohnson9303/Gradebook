package gradebook;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

// This class is used to sort the student collection. Each time a sort selection is chosen,
// the table in ViewAndDeleteStudentsMenu is updated.
public class SortStudentsMenu extends Menu implements ActionListener
{
	private JButton sortButton, backToMainMenuButton;
	private ArrayList<Menu> menuCollection;
	private ArrayList<Student> studentCollection;
	private JRadioButton byIdButton, byNameButton;
	
	public SortStudentsMenu(ArrayList<Menu> menuCollection, ArrayList<Student> studentCollection)
	{
		super("Gradebook");
		guiSetUp();
		this.menuCollection = menuCollection;
		this.studentCollection = studentCollection;
		sortButton.addActionListener(this);
		backToMainMenuButton.addActionListener(this);
		byIdButton.addActionListener(this);
		byNameButton.addActionListener(this);
	}
	private void guiSetUp()
	{
		super.setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Sort Students");
	    label.setFont(new Font("SansSerif", Font.BOLD, 18));
	    panel.add(label);
		super.add(panel, BorderLayout.NORTH);
		
		JPanel panel2 = new JPanel(new GridLayout(1,2,0,0));
        // To force radio buttons below the label and center them
		panel2.setBorder(BorderFactory.createEmptyBorder( 
								0, //top 
								255, //left 
								100, //bottom 
								255)); //right 
		// Create the radio buttons.
		byIdButton = new JRadioButton("By ID", true);
		panel2.add(byIdButton);
		byNameButton = new JRadioButton("By Name", false);
		panel2.add(byNameButton);
		super.add(panel2, BorderLayout.CENTER);
		JPanel panel3 = new JPanel(new GridLayout(1,2,4,2));
		
        // To create space between the labels and textfields and the buttons
		// Left and right are set to 180 to center the Create and Cancel buttons
		panel3.setBorder(BorderFactory.createEmptyBorder( 
								0, //top 
								240, //left 
							   10, //bottom 
								240)); //right 
		sortButton = new JButton("Sort");
		panel3.add(sortButton);
		backToMainMenuButton = new JButton("Back to Main Menu");
		panel3.add(backToMainMenuButton);
		
		super.add(panel3, BorderLayout.SOUTH);
	}
	public void actionPerformed(ActionEvent e) 
	{
		// If the ID radio button is selected, then the Name radio button is unselected
		if(e.getSource() == byIdButton)
		{
			if(byIdButton.isSelected() == true)
			{
				byNameButton.setSelected(false);
				this.validate();
			}
		}
		// If the Name radio button is selected, then the ID radio button is unselected
		if(e.getSource() == byNameButton)
		{
			if(byNameButton.isSelected() == true)
			{
				byIdButton.setSelected(false);
				this.validate();
			}
		}
		if(e.getSource() == sortButton)
		{
			// Checking if a radio sorting button is selected. Give an error message if otherwise.
			if(byIdButton.isSelected() != false || byNameButton.isSelected() != false)
			{
				if(byIdButton.isSelected() == true)
				{
					Collections.sort(studentCollection, idComparator);
					Iterator<Menu> elements = menuCollection.iterator();
					while(elements.hasNext())
					{
						Menu object = elements.next();
						if(object instanceof ViewAndDeleteStudentsMenu)
						{
							ViewAndDeleteStudentsMenu menu = (ViewAndDeleteStudentsMenu)object;
							menu.resetTable();
						}
					}
				}
				if(byNameButton.isSelected() == true)
				{
					Collections.sort(studentCollection, nameComparator);
					Iterator<Menu> elements = menuCollection.iterator();
					while(elements.hasNext())
					{
						Menu object = elements.next();
						if(object instanceof ViewAndDeleteStudentsMenu)
						{
							ViewAndDeleteStudentsMenu menu = (ViewAndDeleteStudentsMenu)object;
							menu.resetTable();
						}
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please select a sorting option.");
			}
		}
		if(e.getSource() == backToMainMenuButton)
		{
			Iterator<Menu> elements = menuCollection.iterator();
			while(elements.hasNext())
			{
				Menu object = elements.next();
				if(object instanceof MainMenu)
				{
					// Simulating redirection by hiding this frame and revealing the main menu.
					object.setVisible(true);
					object.setLocation(this.getLocation().x, this.getLocation().y);
					this.setVisible(false);
				}
			}
		}
	}
	// Private comparator for ID comparison in a descending manner.
	private Comparator<Student> idComparator = new Comparator<Student>() 
	{               
	    public int compare(Student st1, Student st2) 
	    {             

	      return (st1.getID() < st2.getID() ? -1 :                     

	              (st2.getID() == st1.getID() ? 0 : 1));           
	    }   
	};
	// Private comparator for name comparison in a descending manner.
	private Comparator<Student> nameComparator = new Comparator<Student>() 
	{               
	    public int compare(Student st1, Student st2) 
	    {             

	      return (int)(st1.getName().compareTo(st2.getName()));                               
	    }   
	};
}
