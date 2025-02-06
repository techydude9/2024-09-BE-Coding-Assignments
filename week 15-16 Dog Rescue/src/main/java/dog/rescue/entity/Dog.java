package dog.rescue.entity;

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
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Dog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dogId;
	
	@EqualsAndHashCode.Exclude
	private String name;
	
	@EqualsAndHashCode.Exclude
	private int age;
	
	@EqualsAndHashCode.Exclude
	private String color;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "location_id", nullable = false)
	private Location location;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
		name = "dog_breed",
		joinColumns = @JoinColumn(name = "dog_id"),
		inverseJoinColumns = @JoinColumn(name = "breed_id")
			)
	private Set<Breed> breeds = new HashSet<>();
	
}  // end of Dog class -----
