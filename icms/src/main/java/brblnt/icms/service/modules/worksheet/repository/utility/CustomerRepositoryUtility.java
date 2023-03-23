package brblnt.icms.service.modules.worksheet.repository.utility;

import java.util.List;
import java.util.Objects;

import brblnt.icms.data.modules.worksheet.model.CustomerJPA;
import brblnt.icms.data.modules.worksheet.repository.CustomerRepository;
import brblnt.icms.service.interfaces.UtilityInterface;
import brblnt.icms.service.modules.worksheet.exceptions.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * CustomerRepositoryUtility contains database manipulation methods.
 */
@Service
@RequiredArgsConstructor
public class CustomerRepositoryUtility implements UtilityInterface<CustomerJPA, CustomerNotFoundException> {

  private final CustomerRepository customerRepository;

  @Override
  public List<CustomerJPA> getAll() {
    return customerRepository.findAll().stream().toList();
  }

  @Override
  public CustomerJPA getById(Long id) throws CustomerNotFoundException {
    for (CustomerJPA customer : getAll()) {
      if (Objects.equals(customer.getId(), id)) {
        return customer;
      }
    }
    throw new CustomerNotFoundException("No customer with this id " + id);
  }

  /**
   * Not required.
   */
  @Override
  public CustomerJPA getById(String id) throws CustomerNotFoundException {
    return null;
  }

  @Override
  public void save(CustomerJPA customer) {
    customerRepository.save(customer);
  }

  @Override
  public void delete(Long id) {
    customerRepository.deleteById(id);
  }
}
