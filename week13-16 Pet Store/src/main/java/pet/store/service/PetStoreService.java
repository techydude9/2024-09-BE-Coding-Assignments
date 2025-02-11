package pet.store.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.store.controller.model.PetStoreCustomer;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreEmployee;
import pet.store.dao.CustomerDao;
import pet.store.dao.EmployeeDao;
import pet.store.dao.PetStoreDao;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

@Service
public class PetStoreService {
	
	@Autowired
	private PetStoreDao petStoreDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private CustomerDao customerDao;
	
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
	
	// end of methods for petStore Employee
	
	// Beginning of methods for petStore Customer
	@Transactional(readOnly = false)
	public PetStoreCustomer saveCustomer(Long petStoreId, PetStoreCustomer petStoreCustomer) {
		
		PetStore petStore = findPetStoreById(petStoreId);
		Long customerId = petStoreCustomer.getCustomerId();
		Customer customer = findOrCreateCustomer(petStoreId, customerId);
		
		copyCustomerFields(customer, petStoreCustomer);
		
		customer.getPetStores().add(petStore);
		petStore.getCustomers().add(customer);
		
		Customer dbCustomer = customerDao.save(customer);
		
		return new PetStoreCustomer(dbCustomer);
		
	}  // end of saveCustomer method ------

	private void copyCustomerFields(Customer customer, PetStoreCustomer petStoreCustomer) {
		customer.setCustomerEmail(petStoreCustomer.getCustomerEmail());
		customer.setCustomerFirstName(petStoreCustomer.getCustomerFirstName());
		customer.setCustomerId(petStoreCustomer.getCustomerId());
		customer.setCustomerLastName(petStoreCustomer.getCustomerLastName());
		
	} // end of copyCustomerFields method ---
	
	private Customer findOrCreateCustomer(Long petStoreId, Long customerId) {
		if(Objects.isNull(customerId)) {
			return new Customer();
		}
		
		return findCustomerById(petStoreId, customerId);
		
	} // end of findOrCreateCustomer method -----
	
	private Customer findCustomerById(Long petStoreId, Long customerId) {
		Customer customer = customerDao.findById(customerId).orElseThrow(() -> new NoSuchElementException(
				"Customer with ID=" + customerId + " was not found")); 
		
		boolean found = false;
		
		for(PetStore petStore : customer.getPetStores()) {
			if(petStore.getPetStoreId() == petStoreId) {
				found = true;
				break;
			}  // end if
		}  // end for
		
		if(!found) {
			throw new IllegalArgumentException("The customer with ID=" + customerId +
				" is not a member of the pet store with ID=" + petStoreId +".");
		}
		
		return customer;
	}  // end of findCustomerById method ---
	
	// end of methods for petStore Customer
	
	// Beginning of Get Mappings for Pet Store -----
	@Transactional(readOnly = true)
	public List<PetStoreData> retrieveAllPetStores() {
		List<PetStore> petStores = petStoreDao.findAll();
		
		List<PetStoreData> result = new LinkedList<>();
		
		for(PetStore petStore : petStores) {
			PetStoreData psd = new PetStoreData(petStore);
			
			psd.getCustomers().clear();
			psd.getEmployees().clear();
			
			result.add(psd);
		} // end of for
		
		return result;
				
	}  // end of retrieveAllPetStores method
	
	@Transactional(readOnly = true)
	public PetStoreData retrievePetStoreById(Long petStoreId) {
		return new PetStoreData(findPetStoreById(petStoreId));
		
	}  // end of retrievePetStoreById method
	// end of all Get Mappings for Pet Store ------
	
	@Transactional(readOnly = false)
	public void deletePetStoreById(Long petStoreId) {
		PetStore petStore = findPetStoreById(petStoreId);
		petStoreDao.delete(petStore);
	}  // end of delete Pet Store method ---- 
}  // end of PetStoreService class ------
