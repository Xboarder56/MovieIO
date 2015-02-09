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
	private static Movie[] movie;
	private static String[] actorArray;
	private static String actors;
	public static Scanner scanner;
	public static PrintWriter writer;
	private static String content = null;
	
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
	
	public static void clearConsole()
	{
		for (int i=0;i<50;i++) 
		{
			System.out.println();
		}
	}
	
	/**
	 * Input One (when the user hits 1)
	 * @description When 1 is pressed the program will enter
	 * into the inputOne method and print out the contacts in the array
	 */
	public static void inputOne()
	{
		/*Tell the user to pick a selection between 1-4*/
		System.out.println("Please enter how many movies you want to add: ");
		int movieAmount = console.nextInt(); //clears the prompt (to skip enter key)
		console.nextLine();
		
		movie = new Movie[movieAmount];
		
		for(int i=0; i<movieAmount; i++)
		{
		
			/*Tell the user to pick a selection between 1-4*/
			System.out.println("Please enter the movie name: ");
			String movieName = console.nextLine(); //clears the prompt (to skip enter key)
			
			/*Tell the user to pick a selection between 1-4*/
			System.out.println("Please enter the year of the movie: ");
			int year = console.nextInt(); //clears the prompt (to skip enter key)
			console.nextLine();
			
			/*Tell the user to pick a selection between 1-4*/
			System.out.println("Please enter the movie rating (R,PG, PG-13): ");
			String mpaaRating = console.nextLine(); //clears the prompt (to skip enter key)
			
			/*Tell the user to pick a selection between 1-4*/
			System.out.println("Please enter the movie length in minutes (105): ");
			int length = console.nextInt(); //clears the prompt (to skip enter key)
			console.nextLine();
			
			/*Tell the user to pick a selection between 1-4*/
			System.out.println("Please enter the movie director: ");
			String director = console.nextLine(); //clears the prompt (to skip enter key)
			
			/*Tell the user to pick a selection between 1-4*/
			System.out.println("Please enter the amount of actors: ");
			int actorAmount = console.nextInt(); //clears the prompt (to skip enter key)
			console.nextLine();
			
			actorArray = new String[actorAmount];
			for(int j=0; j<actorAmount; j++)
			{
				/*Tell the user to pick a selection between 1-4*/
				System.out.println("Please enter the movie actors: ");
				actorArray[j] = console.nextLine(); //clears the prompt (to skip enter key)
				
				String[] actorDash = new String[actorAmount];
				actorDash[j] = " -- ";
				
				for(int k=0; k<actorAmount; k++)
				{
					actors = new String(actorArray[k]+actorDash[k]);
				}
			}
			
			
			try
			{
				movie[i] = new Movie(movieName, year, mpaaRating, length, director, actors);
			}
			catch(IllegalStateException ex)
			{
				System.out.println(ex.getMessage());
				System.out.println("An invald varibile has been inputed, Try again!");
				inputOne();
			}
		}
		
		for(int i=0; i<movie.length; i++)
		{
			//System.out.println(movie[i].toString());
			try
			{
				
				if(Files.exists(Paths.get("movies.txt")))
				{
					Files.write(Paths.get("movies.txt"), movie[i].printCSV().getBytes(), StandardOpenOption.APPEND);
				}
				else
				{
					Files.write(Paths.get("movies.txt"), movie[i].printCSV().getBytes(), StandardOpenOption.CREATE);
				}
			} catch (IOException | NullPointerException ex)
			{
				// TODO Auto-generated catch block
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
			
			scanner = new Scanner(new FileInputStream("movies.txt"));
			
			while(scanner.hasNext())
			{
				String movieLine = scanner.nextLine();
				String[] movieParts = movieLine.split(", ");
				System.out.println(movieParts[0] + " (" + movieParts[1] + 
						", " + movieParts[3]+ ") ");
			}
		 }
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
			
			/*Tell the user to pick a selection between 1-4*/
			System.out.println("Please pick a selection: ");
			int selection = console.nextInt(); 
			console.nextLine(); //clears the prompt (to skip enter key)
			
			/*If user enters 1 enter this section*/
			if(selection == 1)
			{
				/*Tell the user to pick a selection between 1-4*/
				System.out.println("Please enter the year to find in the list: ");
				String yearSearch = console.nextLine(); 
				//console.nextLine(); //clears the prompt (to skip enter key)
				
				while(scanner.hasNextLine())
				{
						
					content = scanner.nextLine();
						
					
					if(content.contains(yearSearch))
					{
						System.out.println("WORKED!");
					}
				}
			}
			
			/*If user enters 2 enter this section*/
			else if(selection == 2)
			{
				/*Tell the user to pick a selection between 1-4*/
				System.out.println("Please enter the name to find in the list: ");
				String ratingSearch = console.nextLine(); //clears the prompt (to skip enter key)
			}
			
			/*Anything else other then 1-4 will go down here*/ 
			else
			{
				/*Tell the user to please enter a number between 1-4 and loop the code*/
				System.out.println("Not a valid selection please enter a number between 1-2");
				inputThree();
			}
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		clearConsole();
		
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
