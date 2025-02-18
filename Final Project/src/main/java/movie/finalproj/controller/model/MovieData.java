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
	
	public MovieData (Movie movie) {
		this.movieId = movie.getMovieId();
		this.title = movie.getTitle();
		this.description = movie.getDescription();
		this.releaseYear = movie.getReleaseYear();
		
		for(Review review : movie.getReviews()) {
			this.reviews.add(new ReviewData(review));
		} // end of for - Review ---
				
				
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
	
	@Data
	@NoArgsConstructor
	public class ReviewData {
		private Long reviewId;
		private String reviewerName;
		private String reviewText;
		private String stars;
		private LocalDateTime createdTimeStamp;
		
		public ReviewData(Review review) {
			this.reviewId = review.getReviewId();
			this.reviewerName = review.getReviewerName();
			this.reviewText = review.getReview();
			this.stars = review.getStars();
			this.createdTimeStamp = review.getCreatedTimeStamp();
		} // end of ReviewData review method ----
		
		public Review toReview() {
			Review review = new Review();
			
			review.setReviewId(reviewId);
			review.setReviewerName(reviewerName);
			review.setReview(reviewText);
			review.setStars(stars);
			review.setCreatedTimeStamp(createdTimeStamp);
			
			return review;
		} // end of toReview method ------
	} // end of ReviewData class ----
	
	@Data
	@NoArgsConstructor
	public class ActorData {
		private Long actorId;
		private String firstName;
		private String lastName;
		private String listRank;
		
		public ActorData(Actor actor) {
			this.actorId = actor.getActorId();
			this.firstName = actor.getFirstName();
			this.lastName = actor.getLastName();
			this.listRank = actor.getListRank();
		}  // end of ActorData with actor parms ----
		
		public Actor toActor() {
			Actor actor = new Actor();
			
			actor.setActorId(actorId);
			actor.setFirstName(firstName);
			actor.setLastName(lastName);
			actor.setListRank(listRank);
			
			return actor;
		}  // end of toActor method -----
	}  // end of ActorData class -----
}  // end of MovieData Class ----- 
