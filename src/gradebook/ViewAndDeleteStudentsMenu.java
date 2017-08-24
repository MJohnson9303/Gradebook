package gradebook;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

// This class is for the viewing and managing of students in a class.
// For example students can be deleted from the collection and lab scores can be altered
// by clicking on them and giving integer values.
public class ViewAndDeleteStudentsMenu extends Menu implements ActionListener, MouseListener
{
	private JButton deleteButton, backToMainMenuButton;
	private JScrollPane scrollPane;
	private JTable table;
	private Object[] columnNames = {"ID", "Name", "Level", "Gender", "Age", "Lab 1", "Lab 2", "Lab 3", "Lab 4", "Lab 5", "Lab 6", "Lab 7", "Lab 8", "Lab 9", "Lab 10"};
	private ArrayList<Menu> menuCollection;
	private ArrayList<Student> studentCollection;
	private String[][] studentArray;
	
	public ViewAndDeleteStudentsMenu(ArrayList<Menu> menuCollection, ArrayList<Student> studentCollection)
	{
		super("Gradebook");
        guiSetUp();
        this.menuCollection = menuCollection;
		this.studentCollection = studentCollection;
		deleteButton.addActionListener(this);
		backToMainMenuButton.addActionListener(this);
	}
	private void guiSetUp()
	{
		super.setLayout(new BorderLayout());
		JPanel labelPanel = new JPanel();
		JLabel label = new JLabel("Student List");
	    label.setFont(new Font("SansSerif", Font.BOLD, 14));
		labelPanel.add(label);
		super.add(labelPanel, BorderLayout.NORTH);
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(665, 125));
		super.add(scrollPane, BorderLayout.CENTER);
		table = new JTable(new Object[0][15], columnNames);
		JScrollPane tmp = new JScrollPane(table);
		
		scrollPane.setViewport(tmp.getViewport()); 
		
		// Not allowing the columns to resize themselves
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		// Prevent user from moving columns around.
	    table.getTableHeader().setReorderingAllowed(false); 
	    
		// Setting the width of individual columns in JTable 
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
	    table.getColumnModel().getColumn(1).setPreferredWidth(120); 
		table.getColumnModel().getColumn(2).setPreferredWidth(80); 
		table.getColumnModel().getColumn(3).setPreferredWidth(50); 
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		table.getColumnModel().getColumn(6).setPreferredWidth(50);
	    table.getColumnModel().getColumn(7).setPreferredWidth(50); 
		table.getColumnModel().getColumn(8).setPreferredWidth(50); 
		table.getColumnModel().getColumn(9).setPreferredWidth(50); 
		table.getColumnModel().getColumn(10).setPreferredWidth(50); 
		table.getColumnModel().getColumn(11).setPreferredWidth(50);
	    table.getColumnModel().getColumn(12).setPreferredWidth(50); 
		table.getColumnModel().getColumn(13).setPreferredWidth(50); 
		table.getColumnModel().getColumn(14).setPreferredWidth(50); 

		JPanel buttonPanel = new JPanel(new GridLayout(1,2,8,8));
        // To create space between the labels and textfields and the buttons
		// Left and right are set to 200 to center the Create and Cancel buttons
		buttonPanel.setBorder(BorderFactory.createEmptyBorder( 
								0, // top 
								200, // left 
							    0, // bottom 
								200)); // right 
		deleteButton = new JButton("Delete Selected");
		buttonPanel.add(deleteButton);
		backToMainMenuButton = new JButton("Back to Main Menu");
		buttonPanel.add(backToMainMenuButton);	
		super.add(buttonPanel, BorderLayout.SOUTH);
	}
	// This method resets the entire JTable displayed in the ViewAndDeleteStudentsMenu
	public void resetTable()
	{
		studentArray = new String[studentCollection.size()][15];
		for(int i = 0; i < studentCollection.size(); i++) 
		{
			Student student = studentCollection.get(i);
			studentArray[i][0] = ""+student.getID();
			studentArray[i][1] = student.getName();
			studentArray[i][2] = student.getClassLevel();
			studentArray[i][3] = ""+student.getGender();
			studentArray[i][4] = student.getAge();
			for (int k = 0; k < 10; ++k)
			{
				studentArray[i][k+5] = ""+student.getScore(k);
			}
		}
		// Removing the scroll pane containing the table.
		super.remove(scrollPane);
		// Recreating the scroll pane and table and inner scroll pane
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(665, 125));
		super.add(scrollPane, BorderLayout.CENTER);
		table = new JTable(studentArray, columnNames);
		JScrollPane tmp = new JScrollPane(table);
		
		scrollPane.setViewport(tmp.getViewport()); 
		
		// Not allowing the columns to resize themselves
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// Prevent user from moving columns around.
	    table.getTableHeader().setReorderingAllowed(false); 
		CustomTableModel customModel = new CustomTableModel(studentArray, columnNames);
		table.setModel(customModel);
		table.addMouseListener(this);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Setting the width of individual columns in JTable 
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
	    table.getColumnModel().getColumn(1).setPreferredWidth(120); 
		table.getColumnModel().getColumn(2).setPreferredWidth(80); 
		table.getColumnModel().getColumn(3).setPreferredWidth(50); 
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		table.getColumnModel().getColumn(6).setPreferredWidth(50);
	    table.getColumnModel().getColumn(7).setPreferredWidth(50); 
		table.getColumnModel().getColumn(8).setPreferredWidth(50); 
		table.getColumnModel().getColumn(9).setPreferredWidth(50); 
		table.getColumnModel().getColumn(10).setPreferredWidth(50); 
		table.getColumnModel().getColumn(11).setPreferredWidth(50);
	    table.getColumnModel().getColumn(12).setPreferredWidth(50); 
		table.getColumnModel().getColumn(13).setPreferredWidth(50); 
		table.getColumnModel().getColumn(14).setPreferredWidth(50);
	}
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == deleteButton)
		{
			// Checking if the user selected on a row to be deleted.
			// If not, give an error message.
			int selectedRow = table.getSelectedRow();
			if(selectedRow >= 0)
			{
				studentCollection.remove(selectedRow);
				resetTable();
				this.validate();
				Iterator<Menu> elements = menuCollection.iterator();
				while(elements.hasNext())
				{
					Menu object = elements.next();
					if(object instanceof MainMenu)
					{
						MainMenu mainMenu = (MainMenu) object;
						mainMenu.setNumberOfStudents(studentCollection.size());
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please select a row.");
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
	public void mouseClicked(MouseEvent arg0) 
	{
		int selectedRow = table.getSelectedRow();
		int selectedColumn = table.getSelectedColumn();
		if(selectedColumn < 5)
		{
			JOptionPane.showMessageDialog(null, "Please select only the lab columns."); 
		}
		else
		{
			try
			{
				int inputValue = Integer.parseInt(JOptionPane.showInputDialog("Please give a score.")); 
			    Student student = studentCollection.get(selectedRow);
			    // Subtract 5 from the selected column to align to the lab score array
			    // of the student
			    student.setScore(selectedColumn - 5, inputValue);
			    resetTable();
			    validate();
			}
			// To catch null and string inputs 
			catch(NumberFormatException nfe)
			{
				if(nfe.getMessage().contains("null"))
				{
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please give only integer input."); 
				}
			}
		}
	}
	public void mouseEntered(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
	}
	public void mouseExited(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
	}
	public void mousePressed(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
	}
	public void mouseReleased(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
	}
}
