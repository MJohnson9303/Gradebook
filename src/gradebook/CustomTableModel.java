package gradebook;

import javax.swing.table.DefaultTableModel;

// Custom table model used for a JTable to prevent any cell from being altered by
// the user.
public class CustomTableModel extends DefaultTableModel
{
	public CustomTableModel(Object[][] data, Object[] columns)
	{
		super(data, columns);
	}
    public boolean isCellEditable(int row, int column) 
	{
       return false;
    }
}
