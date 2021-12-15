import java.util.List;

/*
 * @author Pedro Sobral, 98491, sobral@ua.pt
 * @author Fábio Martins, 98119, fabio.m@ua.pt
 */

//Builder created using the second method od creating builders (in the slides) 

public class MovieBuilder {

    private final String title;
	private final int year;
	private final Person director;
	private final Person writer;
	private final String series;
	private final List<Person> cast;
	private final List<Place> locations;
	private final List<String> languages;
	private final List<String> genres;
	private final boolean isTelevision;
	private final boolean isNetflix;
	private final boolean isIndependent;
	
    public static class Builder {
        
		private final String title;
		private final int year;
        private Person director;
        
		private Person writer = null;
		private String series = null;
		private List<Person> cast = null;
		private List<Place> locations = null;
		private List<String> languages = null;
		private List<String> genres = null;
		private boolean isTelevision = false;
		private boolean isNetflix = false;
		private boolean isIndependent = false;
		
		public Builder(String title, int year) {
			this.title = title;
			this.year = year;
		}
		
		public Builder diretor(Person director) {
			this.director = director;
			return this;
		}
		public Builder writer(Person writer) {
			this.writer = writer;
			return this;
		}
		public Builder series(String series) {
			this.series = series;
			return this;
		}
		public Builder cast(List<Person> cast) {
			this.cast = cast;
			return this;
		}
		public Builder locations(List<Place> locations) {
			this.locations = locations;
			return this;
		}
		public Builder languages(List<String> languages) {
			this.languages = languages;
			return this;
		}
		public Builder genres(List<String> genres) {
			this.genres = genres;
			return this;
		}
		public Builder isTelevision(boolean isTelevision) {
			this.isTelevision = isTelevision;
			return this;
		}
		public Builder isNetflix(boolean isNetflix) {
			this.isNetflix = isNetflix;
			return this;
		}
		public Builder isIndependent(boolean isIndependent) {
			this.isIndependent = isIndependent;
			return this;
		}
		
		public MovieBuilder build() {
			return new MovieBuilder(this);
		}
	}
	
	private MovieBuilder(Builder builder) {
		title = builder.title;
		year = builder.year;
		director = builder.director;
		writer = builder.writer;
		series = builder.series;
		cast = builder.cast;
		locations = builder.locations;
		languages = builder.languages;
		genres = builder.genres;
		isTelevision = builder.isTelevision;
		isNetflix = builder.isNetflix;
		isIndependent = builder.isIndependent;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("TITLE: " + title + "\nYEAR: " + year + "\nDIRECTOR: " + director + "\nWRITER: " + writer + "\n");
		sb.append("SERIES: " + series + "\nCAST: " + cast + "\nLOCATIONS : " + locations + "\nLANGUAGES: " + languages + "\n");
		sb.append("GENRES: " + genres + "\nIS ON TELEVISION? " + isTelevision + "\nIS ON NETFLIX? " + isNetflix + "\n");
		sb.append("IS INDEPENDENT? " + isIndependent);

		return sb.toString();
	}
    
}
