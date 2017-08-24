package gradebook;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

// This class creates a "class" for students in the collection.
// Essentially this is to be used for giving a name for the file to be created
// if student information is backed up in a Excel sheet.
public class NewClassMenu extends Menu implements ActionListener
{
	private JTextField inputClassName;
	private JButton createButton, cancelButton;
	private ArrayList<Menu> menuCollection;
	
	public NewClassMenu(ArrayList<Menu> menuCollection)
	{
		super("Gradebook");
		guiSetUp();
		this.menuCollection = menuCollection;
		createButton.addActionListener(this);
		cancelButton.addActionListener(this);
	}
	private void guiSetUp()
	{
		super.setLayout(new BorderLayout());
		JPanel lablePanel = new JPanel();
		JLabel label = new JLabel("Create a New Class");
	    label.setFont(new Font("SansSerif", Font.BOLD, 18));
	    lablePanel.add(label);
		super.add(lablePanel, BorderLayout.NORTH);
		JPanel textfieldPanel = new JPanel();
		textfieldPanel.setBorder(BorderFactory.createEmptyBorder( 
								30, // top 
								0, // left 
								0, // bottom 
								0)); // right 
	    JLabel label3 = new JLabel("Class Name:");
		textfieldPanel.add(label3);
		inputClassName = new JTextField(12);
		textfieldPanel.add(inputClassName);
		super.add(textfieldPanel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel(new GridLayout(1,2,8,8));
			
		// To create space between the labels and textfields and the buttons
		// Left and right are set to 250 to center the Create and Cancel buttons
		buttonPanel.setBorder(BorderFactory.createEmptyBorder( 
								0, // top 
								250, // left 
								40, // bottom 
								250)); // right 
		createButton = new JButton("Create");
		buttonPanel.add(createButton);
		cancelButton = new JButton("Cancel");
		buttonPanel.add(cancelButton);
		super.add(buttonPanel, BorderLayout.SOUTH);
	}
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == createButton)
		{
			// Checking if input was provided for the class name. Give error message otherwise.
			if(inputClassName.getText().length() > 0)
			{
				Iterator<Menu> elements = menuCollection.iterator();
				while(elements.hasNext())
				{
					Menu object = elements.next();
					if(object instanceof MainMenu)
					{
						// Setting class name text in main menu.
						((MainMenu) object).setClassName(inputClassName.getText());
						// Resetting the class name text field.
						inputClassName.setText(null);
						// Simulating redirection by hiding this frame and revealing the main menu.
						object.setVisible(true);
						object.setLocation(this.getLocation().x, this.getLocation().y);
						this.setVisible(false);
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please provide a name.");
			}
		}
		if(e.getSource() == cancelButton)
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
}
