package dog.rescue.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dog.rescue.controller.model.LocationData;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/dog_rescue")
@Slf4j
public class RescueController {
	
	@PostMapping("/location")
	@ResponseStatus(code = HttpStatus.CREATED)
	public LocationData createLocation(LocationData locationData) {
		log.info("Creating location {}", locationData);
		return rescueService.saveLocation(locationData);
	} // end of createLocation method ----
}  // end of RescueController class -----------
