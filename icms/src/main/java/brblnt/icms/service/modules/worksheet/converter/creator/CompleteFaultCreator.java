package brblnt.icms.service.modules.worksheet.converter.creator;

import java.util.ArrayList;
import java.util.List;

import brblnt.icms.data.modules.worksheet.model.FaultJPA;
import brblnt.icms.service.interfaces.CompleteCreator;
import brblnt.icms.service.modules.worksheet.exceptions.FaultNotFoundException;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteFault;
import brblnt.icms.service.modules.worksheet.repository.utility.FaultRepositoryUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Create Complete Fault.
 * Convert JPA to CompleteObject.
 */
@Service
@RequiredArgsConstructor
public class CompleteFaultCreator implements CompleteCreator<CompleteFault> {

  private final FaultRepositoryUtility faultRepositoryUtility;

  @Override
  public List<CompleteFault> getAll() {
    List<CompleteFault> list = new ArrayList<>();
    List<FaultJPA> faults = faultRepositoryUtility.getAll();
    for (FaultJPA fault : faults) {
      list.add(createById(fault.getId()));
    }
    return list;
  }

  @Override
  public CompleteFault createById(Long faultID) {
    CompleteFault cf = new CompleteFault();

    FaultJPA tempFault;
    try {
      tempFault = faultRepositoryUtility.getById(faultID);
    } catch (FaultNotFoundException e) {
      throw new RuntimeException(e);
    }

    cf.setId(tempFault.getId());
    cf.setFaultName(tempFault.getFaultName());
    cf.setFaultOther(tempFault.getFaultOther());

    return cf;
  }
}
