package brblnt.icms.service.service.modules.worksheet;

import java.util.List;

import brblnt.icms.data.modules.worksheet.model.ServiceJPA;
import brblnt.icms.service.interfaces.ServiceInterface;
import brblnt.icms.service.modules.worksheet.converter.creator.CompleteServiceCreator;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteService;
import brblnt.icms.service.modules.worksheet.repository.utility.ServiceRepositoryUtility;
import brblnt.icms.web.model.modules.worksheet.services.request.ServiceRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * Services service.
 */
@RequiredArgsConstructor
@Service
public class WorksheetServicesService implements ServiceInterface<CompleteService, ServiceRequest> {

  private final ModelMapper modelMapper;
  private final ServiceRepositoryUtility serviceRepositoryUtility;
  private final CompleteServiceCreator completeServiceCreator;

  /**
   * Get services from complete creator.
   */
  @Override
  public List<CompleteService> getAll() {
    return completeServiceCreator.getAll();
  }

  /**
   * Get service by id from complete creator.
   */
  @Override
  public CompleteService getById(Long id) {
    return completeServiceCreator.createById(id);
  }

  /**
   * Not required.
   */
  @Override
  public boolean exist(Long id) {
    return false;
  }

  /**
   * Check service custom code is unique.
   */
  public boolean exist(String id, String actualOwn) {
    if (actualOwn.toLowerCase().equals(id.toLowerCase())) {
      return false;
    }
    for (CompleteService actual : getAll()) {
      if (actual.getCustomCode().toLowerCase().equals(id.toLowerCase())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Save service.
   */
  @Override
  public boolean save(ServiceRequest serviceRequest, CompleteService completeService) {
    try {
      CompleteService save = create(serviceRequest);
      if (completeService.getId() != 0) {
        save.setId(completeService.getId());
      }
      serviceRepositoryUtility.save(modelMapper.map(save, ServiceJPA.class));
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Delete service.
   */
  @Override
  public boolean deleteById(Long id) {
    try {
      serviceRepositoryUtility.delete(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Map service request to complete service.
   */
  @Override
  public CompleteService create(ServiceRequest serviceRequest) {
    return modelMapper.map(serviceRequest, CompleteService.class);
  }

  /**
   * Create empty service.
   */
  @Override
  public CompleteService createEmpty() {
    return new CompleteService(
            0L,
            "",
            "",
            0,
            0,
            0
    );
  }
}
