import java.util.List;

public class BadMovie {

    // This is the initial code that we rewrite with the builder pattern

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

    public BadMovie(final String movieTitle, final int movieYear, final Person movieDirector, final Person movieWriter,
            final String movieSeries, final List<Person> movieCast, final List<Place> movieLocations,
            final List<String> movieLanguages, final List<String> movieGenres, final boolean television,
            final boolean netflix, final boolean independent) {
        this.title = movieTitle;
        this.year = movieYear;
        this.director = movieDirector;
        this.writer = movieWriter;
        this.series = movieSeries;
        this.cast = movieCast;
        this.locations = movieLocations;
        this.languages = movieLanguages;
        this.genres = movieGenres;
        this.isTelevision = television;
        this.isNetflix = netflix;
        this.isIndependent = independent;
    }
}
