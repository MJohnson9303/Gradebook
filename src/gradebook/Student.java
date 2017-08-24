package gradebook;

// Model class for student information.
public class Student
{
	private int ID;
	private String name;
	private String age;
	private char gender; 
	private String classLevel;
	private int[] scores = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; 
	
	public void setID(int ID)
	{
		this.ID = ID;
	}
	public int getID()
	{
		return ID;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return name;
	}
	public void setAge(String age)
	{
		this.age = age;
	}
	public String getAge()
	{
		return age;
	}
	public void setGender(char gender)
	{
		this.gender = gender;
	}
	public char getGender()
	{
		return gender;
	}
	public void setClassLevel(String classLevel)
	{
		this.classLevel = classLevel;
	}
	public String getClassLevel()
	{
		return classLevel;
	}
	public void setScore(int labNumber, int score)
	{
		scores[labNumber] = score;
	}
	public int[] getScores()
	{
		return scores;
	}
	public int getScore(int labNumber)
	{
		return scores[labNumber];
	}
}
