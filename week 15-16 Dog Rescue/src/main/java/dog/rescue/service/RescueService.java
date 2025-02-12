package dog.rescue.service;

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

} // end of RescueService class -----
