package pet.park.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.park.controller.model.ContributorData;
import pet.park.controller.model.PetParkData;
import pet.park.dao.AmenityDao;
import pet.park.dao.ContributorDao;
import pet.park.dao.PetParkDao;
import pet.park.entity.Amenity;
import pet.park.entity.Contributor;
import pet.park.entity.PetPark;

@Service
public class ParkService {

	@Autowired
	private ContributorDao contributorDao;

	@Autowired
	private AmenityDao amenityDao;

	@Autowired
	private PetParkDao petParkDao;

	@Transactional(readOnly = false)
	public ContributorData saveContributor(ContributorData contributorData) {
		Long contributorId = contributorData.getContributorId();
		Contributor contributor = findOrCreateContributor(contributorId, contributorData.getContributorEmail());

		setFieldsInContributor(contributor, contributorData);
		return new ContributorData(contributorDao.save(contributor));

	} // end of saveContributor method ----

	private void setFieldsInContributor(Contributor contributor, ContributorData contributorData) {
		contributor.setContributorEmail(contributorData.getContributorEmail());
		contributor.setContributorName(contributorData.getContributorName());

	} // end of setFieldsInContributor method ------

	private Contributor findOrCreateContributor(Long contributorId, String contributorEmail) {
		Contributor contributor;

		if (Objects.isNull(contributorId)) {
			Optional<Contributor> opContrib = contributorDao.findByContributorEmail(contributorEmail);

			if (opContrib.isPresent()) {
				throw new DuplicateKeyException("Contributor with email " + contributorEmail + " already exists.");

			}
			contributor = new Contributor();
		} else {
			contributor = findContributorById(contributorId);
		}

		return contributor;
	} // end of findOrCreateContributor method -----

	private Contributor findContributorById(Long contributorId) {
		return contributorDao.findById(contributorId).orElseThrow(
				() -> new NoSuchElementException("Contributor with ID=" + contributorId + " was not found."));
	} // end of findContributorById method ----

	@Transactional(readOnly = true)
	public List<ContributorData> retrieveAllContributors() {
		// one option for coding this retrieval (traditional) is:
		// List<Contributor> contributors = contributorDao.findAll();
		// List<ContributorData> response = new LinkedList<>();
		//
		// for(Contributor contributor : contributors) {
		// response.add(new ContributorData(contributor));
		// }
		// return response;

		// another option for coding this retrieval (stream) is:
		// @formatter:off
		return contributorDao.findAll()
			.stream()
			.map(ContributorData::new)
			.toList();
		// @formatter:on

	} // end of retrieveAllContributors method

	@Transactional(readOnly = true)
	public ContributorData retrieveContributorById(Long contributorId) {
		Contributor contributor = findContributorById(contributorId);
		return new ContributorData(contributor);
	} // end of retrieveCOntributorById method ------

	@Transactional(readOnly = false)
	public void deleteContributorById(Long contributorId) {
		Contributor contributor = findContributorById(contributorId);
		contributorDao.delete(contributor);

	} // end of deleteContributorById method ------

	@Transactional(readOnly = false)
	public PetParkData savePetPark(Long contributorId, PetParkData petParkData) {
		Contributor contributor = findContributorById(contributorId);

		Set<Amenity> amenities = amenityDao.findAllByAmenityIn(petParkData.getAmenities());

		PetPark petPark = findOrCreatePetPark(petParkData.getPetParkId());
		setPetParkFields(petPark, petParkData);
		
		petPark.setContributor(contributor);
		contributor.getPetParks().add(petPark);
		
		for(Amenity amenity : amenities) {
			amenity.getPetParks().add(petPark);
			petPark.getAmenities().add(amenity);
		}
		
		PetPark dbPetPark = petParkDao.save(petPark);
		
		return new PetParkData(dbPetPark);
	} // end of savePetPark method ---

	private void setPetParkFields(PetPark petPark, PetParkData petParkData) {
		petPark.setCountry(petParkData.getCountry());
		petPark.setDirections(petParkData.getDirections());
		petPark.setGeoLocation(petParkData.getGeoLocation());
		petPark.setParkName(petParkData.getParkName());
		petPark.setPetParkId(petParkData.getPetParkId());
		petPark.setStateOrProvince(petParkData.getStateOrProvince());
		
		
		
	} // end of setPetParkFields method ------

	private PetPark findOrCreatePetPark(Long petParkId) {
		PetPark petPark;

		if (Objects.isNull(petParkId)) {
			petPark = new PetPark();
		} else {
			petPark = findPetParkById(petParkId);
		} // end of if-else

		return petPark;

	} // end of findOrCreatePetPark method ---

	private PetPark findPetParkById(Long petParkId) {
		return petParkDao.findById(petParkId)
				.orElseThrow(() -> new NoSuchElementException(
					"Pet park with ID=" + petParkId + " does not exist."));

	} // end of findPetParkById method ----

	@Transactional(readOnly = true)
	public PetParkData retrievePetParkById(Long contributorId, Long parkId) {
		findContributorById(contributorId);
		PetPark petPark = findPetParkById(parkId);
		
		if(petPark.getContributor().getContributorId() != contributorId) {
			throw new IllegalStateException("Pet park with ID=" + parkId +
				" is not owned by contributor with ID=" + contributorId);
		} 
		
		return new PetParkData(petPark);
		
	} // end of retrievePetParkById method -----
	

} // end of ParkService class -------
