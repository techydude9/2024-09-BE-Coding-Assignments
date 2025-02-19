package movie.finalproj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import movie.finalproj.controller.model.ActorData;
import movie.finalproj.dao.ActorDao;

@Service
public class ActorService {

	@Autowired
	private ActorDao actorDao;
	
	@Transactional(readOnly = true)
	public List<ActorData> retrieveAllActors() {
		
		// @formatter:off
		return actorDao.findAll()
			.stream()
			.map(ActorData::new)
			.toList();
		// @formatter:on
	}  // end of retrieveAllActors method -----
	
} // end of ActorService class -----
