package brblnt.icms.service.modules.worksheet.repository.utility;

import java.util.List;
import java.util.Objects;

import brblnt.icms.data.modules.worksheet.model.ServiceJPA;
import brblnt.icms.data.modules.worksheet.repository.ServiceRepository;
import brblnt.icms.service.interfaces.UtilityInterface;
import brblnt.icms.service.modules.worksheet.exceptions.ServiceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * ServiceRepositoryUtility contains database manipulation methods.
 */
@Service
@RequiredArgsConstructor
public class ServiceRepositoryUtility implements UtilityInterface<ServiceJPA, ServiceNotFoundException> {

  private final ServiceRepository serviceRepository;

  @Override
  public List<ServiceJPA> getAll() {
    return serviceRepository.findAll().stream().toList();
  }

  @Override
  public ServiceJPA getById(Long id) throws ServiceNotFoundException {
    for (ServiceJPA service : getAll()) {
      if (Objects.equals(service.getId(), id)) {
        return service;
      }
    }
    throw new ServiceNotFoundException("No service with this id " + id);
  }

  /**
   * Not required.
   */
  @Override
  public ServiceJPA getById(String id) throws ServiceNotFoundException {
    return null;
  }

  @Override
  public void save(ServiceJPA service) {
    serviceRepository.save(service);
  }

  @Override
  public void delete(Long id) {
    serviceRepository.deleteById(id);
  }
}
