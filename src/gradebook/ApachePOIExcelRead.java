package gradebook;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class ApachePOIExcelRead {

    private String file;
    private Student student;
    
    public ApachePOIExcelRead(String className)
    {
    	file = "." + File.separator + "Class Files" + File.separator + className + ".xlsx";
    }
    public void read(ArrayList<Student> studentCollection)
    {
        try 
        {
            FileInputStream excelFile = new FileInputStream(new File(file));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            // Getting the first row to remove it from the retrieved sheet as 
            // the first row has only header columns.
            Row currentRow = iterator.next();
            datatypeSheet.removeRow(currentRow);
            // Resetting the iterator.
            iterator = datatypeSheet.iterator();
            while (iterator.hasNext()) 
            {
            	currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                student = new Student();
                Cell currentCell = cellIterator.next();
                student.setID(Integer.parseInt(currentCell.getStringCellValue()));
                currentCell = cellIterator.next();
                student.setName(currentCell.getStringCellValue());
                currentCell = cellIterator.next();
                student.setClassLevel(currentCell.getStringCellValue());
                currentCell = cellIterator.next();
                student.setGender(currentCell.getStringCellValue().charAt(0));
                currentCell = cellIterator.next();
                student.setAge(currentCell.getStringCellValue());
                
                for(int i = 0; i < 10; i++)
                {
                	currentCell = cellIterator.next();
                	student.setScore(i, (int)currentCell.getNumericCellValue());
                }
                studentCollection.add(student);
            }
            workbook.close();
        } 
        catch (FileNotFoundException e) 
        {
        	JOptionPane.showMessageDialog(null, "File not found.");
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

    }
}
