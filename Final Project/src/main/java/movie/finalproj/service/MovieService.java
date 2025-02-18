package movie.finalproj.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import movie.finalproj.controller.model.MovieData;
import movie.finalproj.dao.MovieDao;
import movie.finalproj.entity.Movie;

@Service
public class MovieService {

	@Autowired
	private MovieDao movieDao;

	@Transactional(readOnly = false)
	public MovieData saveMovie(MovieData movieData) {
		Movie movie = movieData.toMovie();
		Movie dbMovie = movieDao.save(movie);
		
		return new MovieData(dbMovie);		
	
	} // end of saveMovie method

	@Transactional(readOnly = true)
	public MovieData retrieveMovieById(Long movieId) {
		Movie movie = findMovieById(movieId);
		return new MovieData(movie);
	}  // end of retrieveMovieById method -------

	private Movie findMovieById(Long movieId) {
		return movieDao.findById(movieId).orElseThrow(
				() -> new NoSuchElementException("Movie with ID=" + movieId + " was not found."));
				
	}  // end of findMovieById method -----

	@Transactional(readOnly = true)
	public List<MovieData> retrieveAllMovies() {
		
		// @formatter:off
		return movieDao.findAll()
			.stream()
			.map(MovieData::new)
			.toList();
		// @formatter:on
	}  // end of retrieveAllMovies method -----
	
	
}  // end of MovieService class ------
