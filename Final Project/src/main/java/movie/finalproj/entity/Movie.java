package movie.finalproj.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long movieId;
	
	@EqualsAndHashCode.Exclude
	private String title;
	
	@EqualsAndHashCode.Exclude
	private String description;
	
	@EqualsAndHashCode.Exclude
	private int releaseYear;
	
	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Review> reviews = new HashSet<>();
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
		name = "movie_actor",
		joinColumns = @JoinColumn(name = "movie_id"),
		inverseJoinColumns = @JoinColumn(name = "actor_id")
			)
	private Set<Actor> actors = new HashSet<>();

}  // end of Movies class -----
