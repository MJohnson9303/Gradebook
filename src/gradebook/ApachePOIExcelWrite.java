package gradebook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class ApachePOIExcelWrite 
{
    private String file;
    private String fileName;
    private Object[] columnNames = {"ID", "Name", "Level", "Gender", "Age", "Lab 1", "Lab 2", "Lab 3", "Lab 4", "Lab 5", "Lab 6", "Lab 7", "Lab 8", "Lab 9", "Lab 10"};
    
    public ApachePOIExcelWrite(String fileName)
    {
    	this.fileName = fileName;
    	file = "." + File.separator + "Class Files" + File.separator + fileName + ".xlsx";
    }
    public void write(ArrayList<Student> studentCollection) 
    {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(fileName);

        int rowNum = 0;
        
        Row row = sheet.createRow(rowNum++);
        int colNum = 0;
        for (Object field : columnNames) 
        {
            Cell cell = row.createCell(colNum++);
            if (field instanceof String) {
                cell.setCellValue((String) field);
            } else if (field instanceof Integer) {
                cell.setCellValue((Integer) field);
            }
        }
        Cell cell;
        Iterator<Student> elements = studentCollection.iterator();
        while(elements.hasNext())
        {
        	Student student = elements.next();
        	row = sheet.createRow(rowNum++);
        	cell = row.createCell(0);
            cell.setCellValue(String.valueOf(student.getID()));
            cell = row.createCell(1);
            cell.setCellValue(student.getName());
            cell = row.createCell(2);
            cell.setCellValue(student.getClassLevel());
            cell = row.createCell(3);
            cell.setCellValue(""+student.getGender());;
            cell = row.createCell(4);
            cell.setCellValue(student.getAge());
            colNum = 5;
            for (int k = 0; k < 10; ++k)
			{
            	cell = row.createCell(colNum);
				cell.setCellValue(student.getScore(k));
				colNum++;
			}
        }
        try 
        {
            FileOutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            workbook.close();
        }
        catch (FileNotFoundException e) 
        {
        	JOptionPane.showMessageDialog(null, "File not found. Please make sure you do not have the file current open.");
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
