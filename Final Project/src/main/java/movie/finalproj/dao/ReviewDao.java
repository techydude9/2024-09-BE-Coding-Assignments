package movie.finalproj.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import movie.finalproj.entity.Review;

public interface ReviewDao extends JpaRepository<Review, Long> {

}  // end of ReviewDao interface
