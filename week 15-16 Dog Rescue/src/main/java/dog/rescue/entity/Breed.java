package dog.rescue.entity;

import java.util.HashSet;
import java.util.Set;

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
public class Breed {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long breedId;
	
	private String name;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "breeds")
	private Set<Dog> dogs = new HashSet<>();
	
}  // end of Breed class ----
