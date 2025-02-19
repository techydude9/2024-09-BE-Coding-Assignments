package movie.finalproj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import movie.finalproj.controller.model.ActorData;
import movie.finalproj.service.ActorService;

@RestController
@RequestMapping("/movie_finalproj")
@Slf4j
public class ActorController {
	
	@Autowired
	private ActorService actorService;
	
	/*
	 * This section is for actor table mappings
	 */
	
	@PostMapping("/actor")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ActorData createActor(@RequestBody ActorData actorData) {
		log.info("Creating actor {}", actorData);
		return actorService.saveActor(actorData);
	} //  end of createActor method -----
	
	
	@GetMapping("/actor")
	public List<ActorData> retrieveAllActors() {
		log.info("Retrieving all actors.");
		return actorService.retrieveAllActors();
	}  // end of retrieveAllActors method
	
}  // end of ActorController class ------
