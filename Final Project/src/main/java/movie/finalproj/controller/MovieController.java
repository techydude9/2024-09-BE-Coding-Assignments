package movie.finalproj.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	/*
	 * This section is for movie table mappings
	 */
	
	@PostMapping("/movie")
	@ResponseStatus(code = HttpStatus.CREATED)
	public MovieData createMovie(@RequestBody MovieData movieData) {
		log.info("Creating Movie {}", movieData);
		return movieService.saveMovie(movieData);
	} // end of createLocation method ----
	
	@PutMapping("/movie/{movieId}")
	public MovieData updateMovie(@PathVariable Long movieId, @RequestBody MovieData movieData) {
		movieData.setMovieId(movieId);
		log.info("Updating movie with ID={}", movieId);
		return movieService.saveMovie(movieData);
	}  // end of updateMovie
	
	@GetMapping("/movie/{movieId}")
	public MovieData retrieveMovie(@PathVariable Long movieId) {
		log.info("Retrieving movie with ID={}", movieId);
		return movieService.retrieveMovieById(movieId);
	}  // end of retrieveMovie method -------
	
	@GetMapping("/movie")
	public List<MovieData> retrieveAllMovies() {
		log.info("Retrieving all movies.");
		return movieService.retrieveAllMovies();
	}
	
	@DeleteMapping("/movie/{movieId}")
	public Map<String, String> deleteMovie(@PathVariable Long movieId) {
		log.info("Deleting movie with ID=" + movieId);
		movieService.deleteMovie(movieId);
		return Map.of("message", "Movie with ID=" + movieId + " was successfully deleted.");
	}  // end of deleteMovie method ----

}  // end of MovieController class ------
