package brblnt.icms.service.modules.worksheet.converter.creator;

import java.util.ArrayList;
import java.util.List;

import brblnt.icms.data.modules.worksheet.model.ServiceJPA;
import brblnt.icms.service.interfaces.CompleteCreator;
import brblnt.icms.service.modules.worksheet.exceptions.ServiceNotFoundException;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteService;
import brblnt.icms.service.modules.worksheet.repository.utility.ServiceRepositoryUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Create Complete Service.
 * Convert JPA to CompleteObject.
 */
@Service
@RequiredArgsConstructor
public class CompleteServiceCreator implements CompleteCreator<CompleteService> {

  private final ServiceRepositoryUtility serviceRepositoryUtility;

  @Override
  public List<CompleteService> getAll() {
    List<CompleteService> list = new ArrayList<>();
    List<ServiceJPA> services = serviceRepositoryUtility.getAll();
    for (ServiceJPA service : services) {
      list.add(createById(service.getId()));
    }
    return list;
  }

  @Override
  public CompleteService createById(Long serviceID) {
    CompleteService cs = new CompleteService();

    ServiceJPA tempService;
    try {
      tempService = serviceRepositoryUtility.getById(serviceID);
    } catch (ServiceNotFoundException e) {
      throw new RuntimeException(e);
    }
    cs.setId(tempService.getId());
    cs.setCustomCode(tempService.getCustomCode());
    cs.setServiceName(tempService.getServiceName());
    cs.setPriceB(tempService.getPriceB());
    cs.setPriceN(tempService.getPriceN());
    cs.setVat(tempService.getVat());

    return cs;
  }
}
