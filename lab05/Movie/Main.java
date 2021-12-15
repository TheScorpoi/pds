import java.util.ArrayList;
import java.util.List;

/*
 * @author Pedro Sobral, 98491, sobral@ua.pt
 * @author Fábio Martins, 98119, fabio.m@ua.pt
 */

public class Main {
    
    public static void main(String[] args) {
		MovieBuilder.Builder builder = new MovieBuilder.Builder("O ceu azul, e o mar verde", 2077);
		
		List<Place> locations = new ArrayList<Place>();
		locations.add(new Place("California"));
		locations.add(new Place("Japan"));
		
		builder.diretor(new Person("Christopher", "Nolan")).isNetflix(true).isTelevision(false).isIndependent(false);
		builder.writer(new Person("Samuel", "Aleixo"));
		builder.locations(locations);
		
		List<String> languages = new ArrayList<>();
		languages.add("PT");languages.add("ES");languages.add("EN");
		builder.languages(languages);

		builder.series("Trilogia");
		
		List<Person> cast = new ArrayList<>();
		cast.add(new Person("Henrique", "Fonseca"));
		cast.add(new Person("Tiago", "Gonçalves"));
		cast.add(new Person("Xavier", "Sousa"));
		builder.cast(cast);

		List<String> genres = new ArrayList<>();
		genres.add("Comedia");
		genres.add("Drama");
		builder.genres(genres);

		System.out.println(builder.build());
		
		
	}
}
