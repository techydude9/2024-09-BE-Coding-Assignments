package movie.finalproj.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Actor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long actorId;
	
	private String firstName;
	private String lastName;
	
	@Column(name = "list_ranking")
	private String listRank;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "actors")
	private Set<Movie> movies = new HashSet<>();
	
}  // end of Actors class ----
