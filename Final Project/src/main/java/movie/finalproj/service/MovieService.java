package movie.finalproj.service;

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
	
	
}  // end of MovieService class ------
