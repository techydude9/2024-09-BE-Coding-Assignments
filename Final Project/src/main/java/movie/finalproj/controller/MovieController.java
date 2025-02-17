package movie.finalproj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import movie.finalproj.controller.model.MovieData;
import movie.finalproj.service.MovieService;

@RestController
@RequestMapping("/movie_finalproj")
@Slf4j
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@PostMapping("/movie")
	@ResponseStatus(code = HttpStatus.CREATED)
	public MovieData createMovie(@RequestBody MovieData movieData) {
		log.info("Creating Movie {}", movieData);
		return movieService.saveMovie(movieData);
	} // end of createLocation method ----
	

}  // end of MovieController class ------
