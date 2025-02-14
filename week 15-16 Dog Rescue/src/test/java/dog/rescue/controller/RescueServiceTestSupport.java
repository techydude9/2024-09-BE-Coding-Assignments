package dog.rescue.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import dog.rescue.controller.model.LocationData;
import dog.rescue.entity.Location;

public class RescueServiceTestSupport {

	private static final String DOG_TABLE = "dog";
	private static final String DOG_BREED_TABLE = "dog_breed";
	private static final String BREED_TABLE = "breed";
	private static final String LOCATION_TABLE = "location";

	private static final String INSERT_DOG_1_SQL = """
			INSERT INTO dog 
			(age, color, name, location_id)
			VALUES (4, 'Brown and white', 'Ralphy', 1)
			""";

	private static final String INSERT_DOG_2_SQL = """
			INSERT INTO dog 
			(age, color, name, location_id)
			VALUES (6, 'Gray and black', 'Murdock', 1)
			""";

	private static final String INSERT_BREEDS_1_SQL = """
			INSERT INTO dog_breed 
			(dog_id,breed_id)
			VALUES (1, 3), (1, 13)
			""";

	private static final String INSERT_BREEDS_2_SQL = """
			INSERT INTO dog_breed 
			(dog_id,breed_id)
			VALUES (2, 5), (2, 16)
			""";

	// @formatter:off
	private LocationData insertAddress1 = new LocationData (
			1L,
			"North Hills Dog Rescue Society",
			"52 Pine Street",
			"Abdingdon",
			"Maryland",
			"21009",
			"(410) 459-3200"
			);
	
	private LocationData insertAddress2 = new LocationData (
			2L,
			"Navarre Rescue",
			"42 Valley Farms Street",
			"Navarre",
			"Florida",
			"32556",
			"(850) 204-9485"
			);
	
	private LocationData updateAddress1 = new LocationData(
			1L,
			"Glenlake Dog Rescue Society",
			"8 East Glenlake Drive",
			"Wadsworth",
			"Ohio",
			"83241",
			"(330) 336-2105"
			);
	// @formatter:on
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private RescueController rescueController;
	
	protected LocationData buildInsertLocation(int which) {
		
		return which == 1 ? insertAddress1 : insertAddress2;
	}  // end of buildInsertLocation method -----

	protected int rowsInLocationTable() {
		
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, LOCATION_TABLE);
	}  // end of rowsInLocationTable method -----

	protected LocationData insertLocation(LocationData locationData) {
		Location location = locationData.toLocation();
		LocationData clone = new LocationData(location);		
		
		clone.setLocationId(null);
		return rescueController.createLocation(clone);
		
	}  // end of insertLocation method ----

	protected LocationData retrieveLocation(Long locationId) {
		return rescueController.retrieveLocation(locationId);
				
	}  // end of retrieveLocation method -----
	
	protected List<LocationData> insertTwoLocations() {
		LocationData location1 = insertLocation(buildInsertLocation(1));
		LocationData location2 = insertLocation(buildInsertLocation(2));
		
		return List.of(location1, location2);
	}  // end of insertTwoLocations method ---

	protected List<LocationData> retrieveAllLocations() {
		return rescueController.retrieveAllLocations();
	}  // end of retrieveAllLocations method -----

	protected List<LocationData> sorted(List<LocationData> list) {
		List<LocationData> data = new LinkedList<>(list);
		
		data.sort((loc1, loc2) -> (int)(loc1.getLocationId() - loc2.getLocationId()));
		return data;
	}  // end of sorted method ----
	
	protected LocationData updateLocation(LocationData locationData) {
		return rescueController.updateLocation(locationData.getLocationId(), locationData);
	}  // end of updateLocation method ------

	protected LocationData buildUpdateLocation() {
		return updateAddress1;
	}  // end of buildUpdateLocation method ----
	
	protected void insertDog(int which) {
		String dogSql = which == 1 ? INSERT_DOG_1_SQL : INSERT_DOG_2_SQL;
		String breedSql =
				which == 1 ? INSERT_BREEDS_1_SQL : INSERT_BREEDS_2_SQL;
		
		jdbcTemplate.update(dogSql);
		jdbcTemplate.update(breedSql);
		
	}  // end of insertDog method -----
	
	protected int rowsInBreedTable() {
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, BREED_TABLE);
	} // end of rowsInBreedTable method -----

	protected int rowsInDogBreedTable() {
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, DOG_BREED_TABLE);
	}  // end of rowsInDogBreedTable method -------

	protected int rowsInDogTable() {
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, DOG_TABLE);
	}  // end of rowsInDogTable method ------

	protected void deleteLocation(Long locationId) {
		rescueController.deleteLocation(locationId);
	}  // end of deleteLocation method --------

}  // end of RescueServiceTestSupport class -----
