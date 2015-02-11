package movie.manager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;


public class MovieDriver
{
	/*Variables for the program.*/
	private static final Scanner console = new Scanner(System.in);
	private static Movie[] movieArray;
	private static String[] actorArray;
	public static Scanner scanner;
	public static PrintWriter writer;
	private static String content = null;
	private static String actors = "";
	
	public static void main(String[] args)
	{
		/*Calling the methods into the main method to be run.*/
		intro();
		
		/*Close the console*/
		console.close();
	}
	
	/**Intro UI for the program
	 * @description This basically is the console ui for the program allowing
	 * the user to select 1-3 and then the program will enter into that mode.
	 *  */
	public static void intro()
	{
		/*User input and selections for the user*/
		System.out.println(" ");
		System.out.println("Movies Management System");
		System.out.println("1. Create a movie!");
		System.out.println("2. Print saved Movies!");
		System.out.println("3. Search for Movies!");
		System.out.println("4. Exit.");
		System.out.println(" ");
		inputReader();
		
	}
	/**
	 * Input One (when the user hits 1)
	 * @description When 1 is pressed the program will enter
	 * into the inputOne method and print the movies into a file.
	 */
	public static void inputOne()
	{
		/*Tell the user to pick a selection between 1-4*/
		System.out.println("Please enter how many movies you want to add: ");
		int movieAmount = console.nextInt(); //clears the prompt (to skip enter key)
		console.nextLine();
		
		/*Create a new movie array based upon the amount the user inputs*/
		movieArray = new Movie[movieAmount];
		
		/*Loop over creating movie objects for the X amount of times
		 * the user specifies in the movieAmount Variable*/
		for(int i=0; i<movieAmount; i++)
		{
		
			/*Tell the user to enter the movie name*/
			System.out.println("Please enter the movie name: ");
			String movieName = console.nextLine(); //clears the prompt (to skip enter key)
			
			/*Tell the user to enter the year of the movie*/
			System.out.println("Please enter the year of the movie: ");
			int year = console.nextInt(); //clears the prompt (to skip enter key)
			console.nextLine();
			
			/*Tell the user to enter the movie rating*/
			System.out.println("Please enter the movie rating (R,PG, PG-13): ");
			String mpaaRating = console.nextLine(); //clears the prompt (to skip enter key)
			
			/*Tell the user to enter a movie length*/
			System.out.println("Please enter the movie length in minutes (105): ");
			int length = console.nextInt(); //clears the prompt (to skip enter key)
			console.nextLine();
			
			/*Tell the user to enter a movie director*/
			System.out.println("Please enter the movie director: ");
			String director = console.nextLine(); //clears the prompt (to skip enter key)
			
			/*Tell the user to enter the amount of actors*/
			System.out.println("Please enter the amount of actors: ");
			int actorAmount = console.nextInt(); //clears the prompt (to skip enter key)
			console.nextLine();
			
			/*Create a new actor array based upon the amount the user inputs*/
			actorArray = new String[actorAmount];
			
			
			for(int j=0; j<actorAmount; j++)
			{
				/*Tell the user to pick a selection between 1-4*/
				System.out.println("Please enter the movie actors: ");
				actorArray[j] = console.nextLine(); //clears the prompt (to skip enter key)
				
			}

			/*Loop over creating actor objects for the X amount of times
			 * the user specifies in the actorAmount Variable*/
			for(int k=0; k<actorAmount; k++)
			{
				/*Checks to see if the actorAmount is the last one or not*/
				if(k<actorAmount-1)
				{
					actors += actorArray[k] + " -- ";
				}
				/*If its the last one don't add the dash to it.*/
				else
				{
					actors += actorArray[k];
				}
			}
			
			/*Try to create a movie Object from the values provided by the user*/
			try
			{
				movieArray[i] = new Movie(movieName, year, mpaaRating, length, director, actors);
			}
			
			/*Try to catch a bad varibile passed in by the user causing 
			 *the arrays to crash or the movie object to fail to be created*/
			catch(IllegalStateException | NullPointerException ex)
			{
				System.out.println(ex.getMessage());
				System.out.println("An invald varibile has been inputed, Try again!");
				
				/*Repeat The method again for the correct variables*/
				inputOne();
			}
		}
		
		
		for(int i=0; i<movieArray.length; i++)
		{
			/*Try to create a movie.txt in the project folder */
			try
			{
				/*If the file is already made append the file*/
				if(Files.exists(Paths.get("movies.txt")))
				{
					Files.write(Paths.get("movies.txt"), movieArray[i].printCSV().getBytes(), StandardOpenOption.APPEND);
				}
				
				/*If the movies.txt is not present create the file*/
				else
				{
					Files.write(Paths.get("movies.txt"), movieArray[i].printCSV().getBytes(), StandardOpenOption.CREATE);
				}
			}
			
			/*Try to catch a exception if the files is 
			 *unable to be made or a array error happens*/
			catch (IOException | NullPointerException ex)
			{
				System.out.println("Movies.txt has a problem being created or writen to.");
				ex.printStackTrace();
			}
		}
	}
	
	/**
	 * Input Two (when the user hits 2)
	 * @description When 2 is pressed the program will enter
	 * into the inputTwo method and calculate the data based
	 * upon the array that is preprogrammed into the program
	 * it will print out, total contacts, personal contact#, 
	 * business contact#, average contact age
	 */
	public static void inputTwo()
	{
		 try 
		 {
			/*Read the file content into a new scanner object*/
			scanner = new Scanner(new FileInputStream("movies.txt"));
			
			/*Loop over the content until there is no lines left*/
			while(scanner.hasNext())
			{
				/*Store each line of the content in movieLine*/
				String movieLine = scanner.nextLine();
				
				/*Split the line into parts for the data to be anylazed based upon ", " */
				String[] movieParts = movieLine.split(", ");
				
				/*Print the movie's out line by line with just the movie title, length,year*/
				System.out.println(movieParts[0] + " (" + movieParts[1] + 
						", " + movieParts[3]+ "minutes ) ");
			}
		 }
		 
			/*Try to catch a exception if the files is 
			 *unable to be opened or a scanner error happens*/
		 catch(IOException | NullPointerException ex)
		 {
			 	System.out.println("No File found!");
				ex.printStackTrace();
				inputReader();
		 }
		
	}
	
	/**
	 * Input 3 (when the user hits 3)
	 * @description When 3 is pressed the program will enter
	 * into the inputFour method and tell the user goodbye
	 * then the system will exit the java machine.
	 */
	public static void inputThree()
	{	 
		 try 
		{
			
			scanner = new Scanner(new FileInputStream("movies.txt"));
			
			System.out.println("Search By: ");
			System.out.println("1. Year: ");
			System.out.println("2. Rating: ");
			
			/*Tell the user to pick a selection between 1-2*/
			System.out.println("Please pick a selection: ");
			int selection = console.nextInt(); 
			console.nextLine(); //clears the prompt (to skip enter key)
			
			/*If user enters 1 enter this section*/
			if(selection == 1)
			{
				/*Tell the user to enter a Year to find*/
				System.out.println("Please enter the year to find in the list: ");
				String yearSearch = console.nextLine();
				System.out.println();
				
				/*Loop until the scanner has run out of lines*/
				while(scanner.hasNextLine())
				{
					/*read the content line by line*/
					content = scanner.nextLine();
					
					/*Split the movie lines based upon the split Point ", "*/
					String[] movieParts = content.split(", ");
					
					/*If the movie equals the year, print the movie*/
					if(movieParts[1].equalsIgnoreCase(yearSearch))
					{
						System.out.println(movieParts[0] + " (" + movieParts[1] + 
								", " + movieParts[3]+ " minutes ) ");
					}
				}
			}
			
			/*If user enters 2 enter this section*/
			else if(selection == 2)
			{
				/*Tell the user to enter a Rating to find*/
				System.out.println("Please enter the Rating to find in the list: ");
				String ratingSearch = console.nextLine(); 
				System.out.println();
				
				/*Loop until the scanner has run out of lines*/
				while(scanner.hasNextLine())
				{
					/*read the content line by line*/
					content = scanner.nextLine();
					
					/*Split the movie lines based upon the split Point ", "*/
					String[] movieParts = content.split(", ");
					
					/*If the movie equals the rating, print the movie*/
					if(movieParts[2].equalsIgnoreCase(ratingSearch))
					{
						System.out.println(movieParts[0] + " (" + movieParts[1] + 
								", " + movieParts[3]+ " minutes ) ");
					}
				}
			}
		} 
		 
		 /*Try to catch a exception if the files is 
		 *unable to be opened or a scanner error happens*/
		 catch(IOException | NullPointerException ex)
		 {
			System.out.println("Invalid search or File opening error!");
			ex.printStackTrace();
		 }
		 
		 /*Finally close the scanner and writer.*/
		 finally
		 {
			 if(writer !=null)
			 {
				 writer.close();
			 }
			 
			 if(scanner !=null)
			 {
				 scanner.close();
			 }
		 }
		
		 
	}
	
	
	/**
	 * Input 4 (when the user hits 4)
	 * @description When 4 is pressed the program will enter
	 * into the inputFour method and tell the user goodbye
	 * then the system will exit the java machine.
	 */
	public static void inputFour()
	{
		/*Goodbye to the user and thank them*/
		System.out.println("Thanks for the movies! :)");
		
		/*Close the java machine*/
		System.exit(0);
	}

	/**
	 * This will read what the user inputs under the UI
	 * @description: This will read the users input from the UI then
	 * there selected decision will be placed into a variable between 1-3
	 * and compared to the programmed selections 1. Display contacts, 2. Contact info
	 * 3. Exit
	 */
	public static void inputReader()
	{
		/*Tell the user to pick a selection between 1-4*/
		System.out.println("Please enter a selection: ");
		int selection = console.nextInt(); //clears the prompt (to skip enter key)
		console.nextLine();

		/*If user enters 1 enter this section*/
		if(selection == 1)
		{
			/*Go to 1 then loop the code after*/
			inputOne();
			intro();
			inputReader();
		}
		
		/*If user enters 2 enter this section*/
		else if(selection == 2)
		{
			/*Go to 2 then loop the code after*/
			inputTwo();
			intro();
			inputReader();
		}
		
		/*If user enters 3 enter this section*/
		else if(selection == 3)
		{
			/*Go to 3 then loop the code after*/
			inputThree();
			intro();
			inputReader();
		}
		
		/*If user enters 3 enter this section*/
		else if(selection == 4)
		{
			/*Go to 4 then loop the code after*/
			inputFour();
		}
		
		/*Anything else other then 1-4 will go down here*/ 
		else
		{
			/*Tell the user to please enter a number between 1-4 and loop the code*/
			System.out.println("Not a valid selection please enter a number between 1-3");
			inputReader();
		}
	}

}
