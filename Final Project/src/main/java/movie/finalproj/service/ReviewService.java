package movie.finalproj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import movie.finalproj.controller.model.ReviewData;
import movie.finalproj.dao.ReviewDao;

@Service
public class ReviewService {

	@Autowired
	private ReviewDao reviewDao;
	
	public List<ReviewData> retrieveAllReviews(){
		
		// @formatter:off
		return reviewDao.findAll()
			.stream()
			.map(ReviewData::new)
			.toList();
		// @formatter:on
		
	} // end of retrieveAllReviews method -----

}  // end of ReviewService class ------
