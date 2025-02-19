package movie.finalproj.controller.model;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import movie.finalproj.entity.Review;

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
			this.reviewText = review.getReviewText();
			this.stars = review.getStars();
			this.createdTimeStamp = review.getCreatedTimeStamp();
		} // end of ReviewData review method ----
		
		public Review toReview() {
			Review review = new Review();
			
			review.setReviewId(reviewId);
			review.setMovie(null);
			review.setReviewerName(reviewerName);
			review.setReviewText(reviewText);;
			review.setStars(stars);
			review.setCreatedTimeStamp(createdTimeStamp);
			
			return review;
		} // end of toReview method ------
	
}  // end of ReviewData class ------
