package dog.rescue.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import dog.rescue.entity.Location;

public interface LocationDao extends JpaRepository<Location, Long> {

}  // end of LocationDao interface ------
