package movie.manager;

public class Movie
{
	private String movieName;
	private int year;
	private String mpaaRating;
	private int length;
	private String director;
	private String actors;
	
	public Movie(String movieName, int year, String mpaaRating, int length,
			String director, String actors)
	{
		super();
		this.movieName = movieName;
		this.year = year;
		this.mpaaRating = mpaaRating;
		this.length = length;
		this.director = director;
		this.actors = actors;
	}

	public String getMovieName()
	{
		return movieName;
	}

	public void setMovieName(String movieName)
	{
		this.movieName = movieName;
	}

	public int getYear()
	{
		return year;
	}

	public void setYear(int year)
	{
		this.year = year;
	}

	public String getMpaaRating()
	{
		return mpaaRating;
	}

	public void setMpaaRating(String mpaaRating)
	{
		this.mpaaRating = mpaaRating;
	}

	public int getLength()
	{
		return length;
	}

	public void setLength(int length)
	{
		this.length = length;
	}

	public String getDirector()
	{
		return director;
	}

	public void setDirector(String director)
	{
		this.director = director;
	}

	public String getActors()
	{
		return actors;
	}

	public void setActors(String actors)
	{
		this.actors = actors;
	}

	
	public String printCSV()
	{
		return movieName + ", " + year + ", " + mpaaRating + ", " + length
				+ ", " + director + ", " + actors + "\n";
	}
	public Movie readCSV(String csvText)
	{
		String[] parts = csvText.split(", ");
		String movieName = parts[0];
		int year = Integer.parseInt(parts[1]); 
		String mpaaRating = parts[2];
		int length = Integer.parseInt(parts[3]); 
		String director = parts[4];
		String actors = parts[5];
		
		Movie newMovie = new Movie(movieName, year, mpaaRating, length, director, actors);
		
		return newMovie;
		
	}
}
