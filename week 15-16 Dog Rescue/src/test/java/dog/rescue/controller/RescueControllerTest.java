package dog.rescue.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import dog.rescue.DogRescueApplication;
import dog.rescue.controller.model.LocationData;

@SpringBootTest(webEnvironment = WebEnvironment.NONE,
		classes = DogRescueApplication.class)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"})
@SqlConfig(encoding = "utf-8")
class RescueControllerTest  extends RescueServiceTestSupport {

	@Test
	void testInsertLocation() {
		// Given: A location request
		LocationData request = buildInsertLocation(1);
		LocationData expected = buildInsertLocation(1);
		
		// When: the location is added to the location table
		LocationData actual = insertLocation(request);
		
		
		// Then: the location is returned is what is expected
		assertThat(actual).isEqualTo(expected);
		
		// And: there is  one row in the location table.
		assertThat(rowsInLocationTable()).isOne();
		
	} // end of testInsertLocation method ----

	@Test
	void testRetrieveLocation() {
		// Given: a location
		LocationData location = insertLocation(buildInsertLocation(1));
		LocationData expected = buildInsertLocation(1);
		
		// When: the location is retrieved by location ID
		LocationData actual = retrieveLocation(location.getLocationId());
		
		// Then: the actual location is equal to the expected location
		assertThat(actual).isEqualTo(expected);
	}  // end of testRetrieveLocation method ----

	@Test
	void testRetrieveAllLocations() {
		// Given: two locations
		List<LocationData> expected = insertTwoLocations();
		
		// When: all locations are retrieved
		List<LocationData> actual = retrieveAllLocations();
		// Then: the retrieved locations are the same as expected.
		assertThat(sorted(actual)).isEqualTo(sorted(expected));
		
	}  // end of testRetrieveAllLocations method -----

	@Test
	void testUpdateLocation() {
		// Given: a location and an update request
		insertLocation(buildInsertLocation(1));
		LocationData expected = buildUpdateLocation();
		
		// When: the location is updated
		LocationData actual = updateLocation(expected);
		
		// Then: the location is returned as expected
		assertThat(actual).isEqualTo(expected);
		
		// And: there is one row in the location table
		assertThat(rowsInLocationTable()).isOne();
		
	} // end of testUpdateLocation method ---------

	@Test
	void testDeleteLocationWithDogs() {
		// Given: a location and two dogs
		LocationData location = insertLocation(buildInsertLocation(1));
		Long locationId = location.getLocationId();
		
		insertDog(1);
		insertDog(2);
		
		assertThat(rowsInLocationTable()).isOne();
		assertThat(rowsInDogTable()).isEqualTo(2);
		assertThat(rowsInDogBreedTable()).isEqualTo(4);
		int breedRows = rowsInBreedTable();
		
		// When: the location is deleted
		deleteLocation(locationId);
		
		// Then: there are no location, dog, or dog_breed rows
		assertThat(rowsInLocationTable()).isZero();
		assertThat(rowsInDogTable()).isZero();
		assertThat(rowsInDogBreedTable()).isZero();
		
		// And: the number of breed rows has not changed.
		assertThat(rowsInBreedTable()).isEqualTo(breedRows);
		
	}  // end of testDeleteLocationWithDogs method ------

}  // end of RescueControllerTest class -----------
