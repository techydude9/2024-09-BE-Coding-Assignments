package movie.finalproj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import movie.finalproj.controller.model.ReviewData;
import movie.finalproj.service.ReviewService;

@RestController
@RequestMapping("/movie_finalproj")
@Slf4j
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@PostMapping("/{movieId}/review")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ReviewData createReview(@PathVariable Long movieId, @RequestBody ReviewData reviewData) {
		log.info("For movie with ID={}, creating review {}", movieId, reviewData);
		return reviewService.saveReview(movieId, reviewData);
	} //  end of createReview method -----
		
	@GetMapping("/review")
	public List<ReviewData> retrieveAllReviews(){
		log.info("Retrieving all reviews.");
		return reviewService.retrieveAllReviews();
		
	}  // end of retrieveAllReviews ---
	
}  // end of ReviewController class -----
