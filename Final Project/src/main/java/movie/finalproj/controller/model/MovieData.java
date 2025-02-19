package movie.finalproj.controller.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import movie.finalproj.entity.Actor;
import movie.finalproj.entity.Movie;
import movie.finalproj.entity.Review;

@Data
@NoArgsConstructor
public class MovieData {
	private Long movieId;
	private String title;
	private String description;
	private Integer releaseYear;
	private Set<ReviewData> reviews = new HashSet<>();
	private Set<ActorData> actors = new HashSet<>();
	
	public MovieData (Movie movie) {
		this.movieId = movie.getMovieId();
		this.title = movie.getTitle();
		this.description = movie.getDescription();
		this.releaseYear = movie.getReleaseYear();
		
		for(Review review : movie.getReviews()) {
			this.reviews.add(new ReviewData(review));
		} // end of for - Review ---
		
		for(Actor actor : movie.getActors()) {
			this.actors.add(new ActorData(actor));
		} // end of for - Actors
		
	} // end of MovieData movie method ----
	
	public MovieData(Long movieId, String title, String description, Integer releaseYear) {
		this.movieId = movieId;
		this.title = title;
		this.description = description;
		this.releaseYear =releaseYear;
		
	} // end of MovieData method with all args ------
	
	public Movie toMovie() {
		Movie movie = new Movie();
		
		movie.setMovieId(movieId);
		movie.setTitle(title);
		movie.setDescription(description);
		movie.setReleaseYear(releaseYear);
		
		for(ReviewData reviewData : reviews) {
			movie.getReviews().add(reviewData.toReview());
		}  // end of for reviewData
		
		return movie;
	} // end of toMovie method -----
	
}  // end of MovieData Class ----- 
