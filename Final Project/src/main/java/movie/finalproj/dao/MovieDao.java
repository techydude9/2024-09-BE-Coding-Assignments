package movie.finalproj.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import movie.finalproj.entity.Movie;

public interface MovieDao extends JpaRepository<Movie, Long> {

}
