package movie.finalproj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import movie.finalproj.controller.model.ActorData;
import movie.finalproj.dao.ActorDao;
import movie.finalproj.entity.Actor;

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

	@Transactional(readOnly = false)
	public ActorData saveActor(ActorData actorData) {
		Actor actor = actorData.toActor();
		Actor dbActor = actorDao.save(actor);
		
		return new ActorData(dbActor);
	}  // end of saveActor method -----
	
} // end of ActorService class -----
