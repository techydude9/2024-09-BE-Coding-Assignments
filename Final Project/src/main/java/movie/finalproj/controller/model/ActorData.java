package movie.finalproj.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import movie.finalproj.entity.Actor;

@Data
@NoArgsConstructor
public class ActorData {
		private Long actorId;
		private String firstName;
		private String lastName;
		private String listRank;
		
		public ActorData(Actor actor) {
			this.actorId = actor.getActorId();
			this.firstName = actor.getFirstName();
			this.lastName = actor.getLastName();
			this.listRank = actor.getListRank();
		}  // end of ActorData with actor parms ----
		
		public Actor toActor() {
			Actor actor = new Actor();
			
			actor.setActorId(actorId);
			actor.setFirstName(firstName);
			actor.setLastName(lastName);
			actor.setListRank(listRank);
			
			return actor;
		}  // end of toActor method -----
}  // end of ActorData class ------
