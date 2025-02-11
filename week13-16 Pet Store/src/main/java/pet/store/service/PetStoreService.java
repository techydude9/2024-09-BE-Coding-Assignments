package pet.store.service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreEmployee;
import pet.store.dao.EmployeeDao;
import pet.store.dao.PetStoreDao;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

@Service
public class PetStoreService {
	
	@Autowired
	private PetStoreDao petStoreDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Transactional(readOnly = false)
	public PetStoreData savePetStore(PetStoreData petStoreData) {
		Long petStoreId = petStoreData.getPetStoreId();
		PetStore petStore = findOrCreatePetStore(petStoreId);
		
		copyPetStoreFields(petStore, petStoreData);
		return new PetStoreData(petStoreDao.save(petStore));
	} // end of savePetStore method

	private void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
		petStore.setPetStoreId(petStoreData.getPetStoreId());
		petStore.setPetStoreName(petStoreData.getPetStoreName());
		petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
		petStore.setPetStoreCity(petStoreData.getPetStoreCity());
		petStore.setPetStoreState(petStoreData.getPetStoreState());
		petStore.setPetStoreZip(petStoreData.getPetStoreZip());
		petStore.setPetStorePhone(petStoreData.getPetStorePhone());
		
	}  // end of copyPetStoreFields method ---

	private PetStore findOrCreatePetStore(Long petStoreId) {
		
		if(Objects.isNull(petStoreId)) {
			return new PetStore();
		} else {
			return findPetStoreById(petStoreId);
		} // end of if/else
		
	}  // end of findOrCreatePetStore method -----

	private PetStore findPetStoreById(Long petStoreId) {
		return petStoreDao.findById(petStoreId)
				.orElseThrow(() -> new NoSuchElementException(
					"Pet store with ID=" + petStoreId + " was not found."));
	}  // end of findPetStoreById method

	// Code below is for PetStoreEmployee ------
	@Transactional(readOnly = false)
	public PetStoreEmployee saveEmployee(Long petStoreId, PetStoreEmployee petStoreEmployee) {
		
		PetStore petStore = findPetStoreById(petStoreId);
		Long employeeId = petStoreEmployee.getEmployeeId();
		Employee employee = findOrCreateEmployee(petStoreId, employeeId);
		
		copyEmployeeFields(employee, petStoreEmployee);
		
		employee.setPetStore(petStore);
		petStore.getEmployees().add(employee);
		
		Employee dbEmployee = employeeDao.save(employee);
		
		return new PetStoreEmployee(dbEmployee);
		
	}  // end of saveEmployee method ------

	private void copyEmployeeFields(Employee employee, PetStoreEmployee petStoreEmployee) {
		employee.setEmployeeFirstName(petStoreEmployee.getEmployeeFirstName());
		employee.setEmployeeId(petStoreEmployee.getEmployeeId());
		employee.setEmployeeJobTitle(petStoreEmployee.getEmployeeJobTitle());
		employee.setEmployeeLastName(petStoreEmployee.getEmployeeLastName());
		employee.setEmployeePhone(petStoreEmployee.getEmployeePhone());
		
	} // end of copyEmployeeFields method ---
	
	private Employee findOrCreateEmployee(Long petStoreId, Long employeeId) {
		if(Objects.isNull(employeeId)) {
			return new Employee();
		}
		
		return findEmployeeById(petStoreId, employeeId);
		
	} // end of findOrCreateEmployee method -----
	
	private Employee findEmployeeById(Long petStoreId, Long employeeId) {
		Employee employee = employeeDao.findById(employeeId).orElseThrow(() -> new NoSuchElementException(
				"Employee with ID=" + employeeId + " was not found")); 
		
		if(employee.getPetStore().getPetStoreId() != petStoreId) {
			throw new IllegalArgumentException("The employee with ID=" + employeeId +
				" is not employeed by the pet store with ID=" + petStoreId +".");
		}
		
		return employee;
	}  // end of findEmployeeById method ---
	
}  // end of PetStoreService class ------
