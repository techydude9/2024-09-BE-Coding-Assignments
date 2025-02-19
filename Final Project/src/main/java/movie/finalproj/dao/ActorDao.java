package movie.finalproj.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import movie.finalproj.entity.Actor;

public interface ActorDao extends JpaRepository<Actor, Long> {

}  // end of ActorDao interface -----
