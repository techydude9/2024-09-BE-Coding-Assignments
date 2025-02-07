package dog.rescue.controller.model;

import java.util.HashSet;
import java.util.Set;

import dog.rescue.entity.Breed;
import dog.rescue.entity.Dog;
import dog.rescue.entity.Location;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LocationData {
	private Long locationId;
	private String businessName;
	private String streetAddress;
	private String city;
	private String state;
	private String zip;
	private String phone;
	private Set<DogData> dogs = new HashSet<>();
	
	public LocationData (Location location) {
		this.locationId = location.getLocationId();
		this.businessName = location.getBusinessName();
		this.streetAddress = location.getStreetAddress();
		this.city = location.getCity();
		this.state = location.getState();
		this.zip = location.getZip();
		this.phone = location.getPhone();
		
		for(Dog dog : location.getDogs()) {
			this.dogs.add(new DogData(dog));
		}  // end of for Dog -		
	} // end of LocationData location only method ------

	public LocationData(Long locationId, String businessName, String streetAddress,
			String city, String state, String zip, String phone) {
		this.locationId = locationId;
		this.businessName = businessName;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
		
	} // end of LocationData all parameter method needed for testing ----

	public Location toLocation() {
		Location location = new Location();
		
		location.setLocationId(locationId);
		location.setBusinessName(businessName);
		location.setStreetAddress(streetAddress);
		location.setCity(city);
		location.setState(state);
		location.setZip(zip);
		location.setPhone(phone);
		
		for(DogData dogData : dogs) {
			location.getDogs().add(dogData.toDog());
		}
		
		return location;
	} // end of toLocation method ---
	
	@Data
	@NoArgsConstructor
	public class DogData {
		private Long dogId;
		private String name;
		private int age;
		private String color;
		private Set<BreedData> breeds = new HashSet<>();
		
		public DogData(Dog dog) {
			this.dogId = dog.getDogId();
			this.name = dog.getName();
			this.age = dog.getAge();
			this.color = dog.getColor();
			
			for(Breed breed : dog.getBreeds()) {
				this.breeds.add(new BreedData(breed));
			}
		} // end of DogData method with Dog parm
		
		public Dog toDog() {
			Dog dog = new Dog();
			
			dog.setDogId(dogId);
			dog.setName(name);
			dog.setAge(age);
			dog.setColor(color);
			
			for(BreedData breedData : breeds) {
				dog.getBreeds().add(breedData.toBreed());
			}
			
			return dog;
		} // end of toDog method ----
	} // end of DogData class ----
	
	@Data
	@NoArgsConstructor
	public class BreedData {
		private Long breedId;
		private String name;
		
		public BreedData(Breed breed) {
			this.breedId = breed.getBreedId();
			this.name = breed.getName();
		} // end of BreedData with breed parm method
		
		public Breed toBreed() {
			Breed breed = new Breed();
			
			breed.setBreedId(breedId);
			breed.setName(name);
			
			return breed;
		} // end of toBreed mthod ----
		
	} // end of BreadData class ----
	
}  // end of LocationData class -----
