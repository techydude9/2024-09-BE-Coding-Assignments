package dog.rescue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import dog.rescue.controller.model.LocationData;
import dog.rescue.entity.Location;

public class RescueServiceTestSupport {

	private static final String LOCATION_TABLE = "location";

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
	
}  // end of RescueServiceTestSupport class -----
