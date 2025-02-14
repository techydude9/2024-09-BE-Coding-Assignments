package dog.rescue.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dog.rescue.controller.model.LocationData;
import dog.rescue.dao.LocationDao;
import dog.rescue.entity.Location;

@Service
public class RescueService {

	@Autowired
	private LocationDao locationDao;
	
	@Transactional(readOnly = false)
	public LocationData saveLocation(LocationData locationData) {
		Location location = locationData.toLocation();
		Location dbLocation = locationDao.save(location);
		
		return new LocationData(dbLocation);
	}  // end of saveLocation with LocationData parm ----

	@Transactional(readOnly = true)
	public LocationData retrieveLocationById(Long locationId) {
		Location location = findLocationById(locationId);
		return new LocationData(location);
		
	}  // end of retrieveLocationById method ----

	private Location findLocationById(Long locationId) {
		return locationDao.findById(locationId).orElseThrow(
				() -> new NoSuchElementException("Location with ID=" + locationId + " was not found."));
	}  // end of findLocationById method -----

	@Transactional(readOnly = true)
	public List<LocationData> retrieveAllLocations() {
		/* option 1:
	     * Uncomment the code below to retrieve, sort, and convert location data
	     * using a loop.
	     */
	    // List<Location> locationEntities = locationDao.findAll();
	    // List<LocationData> locationDtos = new LinkedList<>();
	    //
	    // locationEntities
	    // .sort((loc1, loc2) -> sortLocationByBusinessName(loc1, loc2));
	    //
	    // for(Location location : locationEntities) {
	    // LocationData locationData = new LocationData(location);
	    // locationDtos.add(locationData);
	    // }
	    //
	    // return locationDtos;

	    // @formatter:off

	    /* option 2:
	     * Uncomment the code below to retrieve, sort, and convert location data
	     * using a stream and lambda expressions.
	     */
//	    return locationDao.findAll()
//	        .stream()
//	        .sorted((loc1, loc2) -> sortLocationByBusinessName(loc1, loc2))
//	        .map(loc -> new LocationData(loc))
//	        .toList();

	    /* option 3:
	     * Uncomment the code below to retrieve, sort, and convert location data
	     * using a stream and a method references.
	     */
	     return locationDao.findAll()
	         .stream()
	         .sorted(this::sortLocationByBusinessName)
	         .map(LocationData::new)
	         .toList();
	    // @formatter:on
	}  // end of retrieveAllLocations method ----

	 /**
	   * This helps implement the Comparator interface in the sort methods, above.
	   * It takes two location objects as parameters.
	   * 
	   * @param loc1 The first location.
	   * @param loc2 The second location.
	   * @return If the business name of the first location is alphabetically
	   *         "greater" than the second, a positive integer is returned. If the
	   *         business name of the first location is alphabetically "less" than
	   *         the second, a negative number is returned. If the two business
	   *         names are the same, zero is returned.
	   */
	  private int sortLocationByBusinessName(Location loc1, Location loc2) {
	    return loc1.getBusinessName().compareTo(loc2.getBusinessName());
	  }  // end of sortLocationByBusinessName method -------

	  @Transactional(readOnly = false)
	  public void deleteLocation(Long locationId) {
		Location location = findLocationById(locationId);
		locationDao.delete(location);
		
	}  // end of deleteLocation method -----

} // end of RescueService class -----
