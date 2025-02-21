package movie.finalproj.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import movie.finalproj.controller.model.ReviewData;
import movie.finalproj.dao.ReviewDao;
import movie.finalproj.entity.Movie;
import movie.finalproj.entity.Review;

@Service
public class ReviewService {

	@Autowired
	private ReviewDao reviewDao;
	
	@Autowired
	private MovieService movieService;
	
	@Transactional(readOnly = true)
	public List<ReviewData> retrieveAllReviews(){
		
		// @formatter:off
		return reviewDao.findAll()
			.stream()
			.map(ReviewData::new)
			.toList();
		// @formatter:on
		
	} // end of retrieveAllReviews method -----

	@Transactional(readOnly = false)
	public ReviewData saveReview(Long movieId, ReviewData reviewData) {
		
		Movie movie = movieService.findMovieById(movieId);
		Long reviewId = reviewData.getReviewId();
		Review review = findOrCreateReview(movieId, reviewId);
		
		copyReviewFields(review, reviewData);
		
		review.setMovie(movie);
		movie.getReviews().add(review);
		
		Review dbReview = reviewDao.save(review);
		
		return new ReviewData(dbReview);
	}  // end of saveReview method -----

	private Review findOrCreateReview(Long movieId, Long reviewId) {
		if(Objects.isNull(reviewId)) {
			return new Review();
		}
		
		return findReviewById(movieId, reviewId);
	}  // end of findOrCreateReview method -----

	private Review findReviewById(Long movieId, Long reviewId) {
		Review review = reviewDao.findById(reviewId).orElseThrow(() -> new NoSuchElementException(
				"Review with ID=" + reviewId + " was not found."));
		
		if(review.getMovie().getMovieId() != movieId) {
			throw new IllegalArgumentException("The review with ID=" + reviewId +
					" is not related to the movie with ID=" + movieId + ".");
		}
	
		return review;
	}  // end of findReviewById method ----

	private void copyReviewFields(Review review, ReviewData reviewData) {
		review.setReviewId(reviewData.getReviewId());
		review.setReviewerName(reviewData.getReviewerName());
		review.setReviewText(reviewData.getReviewText());
		review.setStars(reviewData.getStars());
		review.setCreatedTimeStamp(reviewData.getCreatedTimeStamp());
	}  // end of copyReviewFields method ------

}  // end of ReviewService class ------
