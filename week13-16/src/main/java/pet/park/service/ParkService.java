package pet.park.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.park.controller.model.ContributorData;
import pet.park.dao.ContributorDao;
import pet.park.entity.Contributor;

@Service
public class ParkService {
	
	@Autowired
	private ContributorDao contributorDao;

	@Transactional(readOnly = false)
	public ContributorData saveContributor(ContributorData contributorData) {
		Long contributorId = contributorData.getContributorId();
		Contributor contributor = findOrCreateContributor(contributorId, 
				contributorData.getContributorEmail());
		
		setFieldsInContributor(contributor, contributorData);
		return new ContributorData(contributorDao.save(contributor));
			
	} // end of saveContributor method ----

	private void setFieldsInContributor(Contributor contributor, 
			ContributorData contributorData) {
		contributor.setContributorEmail(contributorData.getContributorEmail());
		contributor.setContributorName(contributorData.getContributorName());
		
	} // end of setFieldsInContributor method ------

	private Contributor findOrCreateContributor(Long contributorId, 
			String contributorEmail) {
		Contributor contributor;
		
		if(Objects.isNull(contributorId)) {
			Optional<Contributor> opContrib = 
					contributorDao.findByContributorEmail(contributorEmail);
			
			if(opContrib.isPresent()) {
				throw new DuplicateKeyException(
					"Contributor with email " + contributorEmail + " already exists.");
				
			}
			contributor = new Contributor();
		} else {
			contributor = findContributorById(contributorId);
		}
		
		return contributor;
	} // end of findOrCreateContributor method -----

	private Contributor findContributorById(Long contributorId) {
		return contributorDao.findById(contributorId)
				.orElseThrow(() -> new NoSuchElementException(
						"Contributor with ID=" + contributorId + " was not found."));
	} // end of findContributorById method ----

	@Transactional(readOnly = true)
	public List<ContributorData> retrieveAllContributors() {
	// one option for coding this retrieval (traditional) is:
	//	List<Contributor> contributors = contributorDao.findAll();
	//	List<ContributorData> response = new LinkedList<>();
	//	
	//	for(Contributor contributor : contributors) {
	//		response.add(new ContributorData(contributor));
	//	}
	//	return response;
	
	// another option for coding this retrieval (stream) is:
		// @formatter:off
		return contributorDao.findAll()
			.stream()
			.map(ContributorData::new)
			.toList();
		// @formatter:on
		
	}  // end of retrieveAllContributors method

	public ContributorData retrieveContributorById(Long contributorId) {
		Contributor contributor = findContributorById(contributorId);
		return new ContributorData(contributor);
	}  // end of retrieveCOntributorById method ------

} // end of ParkService class -------
