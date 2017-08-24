package gradebook;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

// The main menu of the grade book.
public class MainMenu extends Menu implements ActionListener
{
	private JButton createNewClassButton;
	private JButton loadStudentsFromFileButton; 
	private JButton addNewStudentsButton; 
	private JButton sortStudentsButton; 
	private JButton viewAndDeleteStudentsButton;
	private JButton backUpStudentsToFileButton;
	private JButton exitButton;
	private JTextField className, numberOfStudents;
	private ArrayList<Menu> menuCollection = new ArrayList<Menu>();
	private ArrayList<Student> studentCollection = new ArrayList<Student>();
	
	// Setting up the GUI, creating all the menu and adding them to a list with a reference to the menu
	// to the list as well as a list of students.
	// Action listeners for all the button are added as well.
	public MainMenu()
	{
		super("Gradebook");
		guiSetUp();
		menuCollection.add(this);
		menuCollection.add(new NewClassMenu(menuCollection));
		menuCollection.add(new NewStudentMenu(menuCollection, studentCollection));
		menuCollection.add(new LoadStudentsMenu(menuCollection, studentCollection));
		menuCollection.add(new SortStudentsMenu(menuCollection, studentCollection));
		menuCollection.add(new ViewAndDeleteStudentsMenu(menuCollection, studentCollection));
		
		createNewClassButton.addActionListener(this);
		loadStudentsFromFileButton.addActionListener(this); 
		addNewStudentsButton.addActionListener(this); 
		sortStudentsButton.addActionListener(this); 
		viewAndDeleteStudentsButton.addActionListener(this);
		backUpStudentsToFileButton.addActionListener(this);
	}
	// Private subroutine called by the constructor that will set up the GUI and make it
	// visible to the user.
	private void guiSetUp()
	{	
	    super.setLayout(new BorderLayout());
	    // Setting up the top border panel.
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Use the Buttons Below to Manage Students");
	    label.setFont(new Font("SansSerif", Font.BOLD, 18));
		panel.add(label);
		super.add(panel, BorderLayout.NORTH);
		// Setting up the center border panel.
		JPanel panel2 = new JPanel();
		JLabel label2 = new JLabel("Class Name:");
		panel2.add(label2);
		className = new JTextField(9);
		className.setEditable(false);
		panel2.add(className);
		JLabel label3 = new JLabel("Number of students:");
		panel2.add(label3);
	    numberOfStudents = new JTextField(9);
		numberOfStudents.setEditable(false);
		numberOfStudents.setText("0");
		panel2.add(numberOfStudents);
		super.add(panel2, BorderLayout.CENTER);
		// Setting up the bottom border panel that uses a grid layout.
		JPanel panel3 = new JPanel(new GridLayout(2,4,2,2));
		// Establishing the buttons.
		createNewClassButton = new JButton("0. Create a New Class");
		panel3.add(createNewClassButton);
		loadStudentsFromFileButton = new JButton("1. Load Students From File");
		panel3.add(loadStudentsFromFileButton);
		addNewStudentsButton = new JButton("2. Add New Students");
		panel3.add(addNewStudentsButton);
		sortStudentsButton = new JButton("4. Sort Students");
		panel3.add(sortStudentsButton);
		viewAndDeleteStudentsButton = new JButton("5. View/Edit/Delete Students");
		panel3.add(viewAndDeleteStudentsButton);
		backUpStudentsToFileButton = new JButton("6. Back Up Students To File");
		backUpStudentsToFileButton.setAction(new BackUpStudentsCommand(menuCollection, studentCollection).getBackUp());
		panel3.add(backUpStudentsToFileButton); 
		exitButton = new JButton("7. Exit");
		exitButton.setAction(new ExitCommand().getExit());
		panel3.add(exitButton);
		// Setting panel3 to the bottom border position.
		super.add(panel3, BorderLayout.SOUTH);
		super.setVisible(true);
	}
	public void setClassName(String name)
	{
		className.setText(name);
	}
	public String getClassName()
	{
		return className.getText();
	}
	public void setNumberOfStudents(int number)
	{
		numberOfStudents.setText(String.valueOf(number));
	}
	public int getNumberOfStudnets()
	{
		return Integer.parseInt(numberOfStudents.getText());
	}
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == createNewClassButton)
		{
			Iterator<Menu> elements = menuCollection.iterator();
			while(elements.hasNext())
			{
				Menu object = elements.next();
				if(object instanceof NewClassMenu)
				{
					object.setVisible(true);
					object.setLocation(this.getLocation().x, this.getLocation().y);
					this.setVisible(false);
				}
			}
		}
		if(e.getSource() == loadStudentsFromFileButton)
		{
			Iterator<Menu> elements = menuCollection.iterator();
			while(elements.hasNext())
			{
				Menu object = elements.next();
				if(object instanceof LoadStudentsMenu)
				{
					object.setVisible(true);
					object.setLocation(this.getLocation().x, this.getLocation().y);
					this.setVisible(false);
				}
			}
		}
		if(e.getSource() == addNewStudentsButton)
		{
			Iterator<Menu> elements = menuCollection.iterator();
			while(elements.hasNext())
			{
				Menu object = elements.next();
				if(object instanceof NewStudentMenu)
				{
					object.setVisible(true);
					object.setLocation(this.getLocation().x, this.getLocation().y);
					this.setVisible(false);
				}
			}
		}
		if(e.getSource() == sortStudentsButton)
		{
			Iterator<Menu> elements = menuCollection.iterator();
			while(elements.hasNext())
			{
				Menu object = elements.next();
				if(object instanceof SortStudentsMenu)
				{
					object.setVisible(true);
					object.setLocation(this.getLocation().x, this.getLocation().y);
					this.setVisible(false);
				}
			}
		}
		if(e.getSource() == viewAndDeleteStudentsButton)
		{
			Iterator<Menu> elements = menuCollection.iterator();
			while(elements.hasNext())
			{
				Menu object = elements.next();
				if(object instanceof ViewAndDeleteStudentsMenu)
				{
					object.setVisible(true);
					object.setLocation(this.getLocation().x, this.getLocation().y);
					this.setVisible(false);
				}
			}
		}
	}
}
