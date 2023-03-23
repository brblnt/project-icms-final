package brblnt.icms.service.service.modules.worksheet;

import java.util.List;

import brblnt.icms.data.modules.worksheet.model.FaultJPA;
import brblnt.icms.service.interfaces.ServiceInterface;
import brblnt.icms.service.modules.worksheet.converter.creator.CompleteFaultCreator;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteFault;
import brblnt.icms.service.modules.worksheet.repository.utility.FaultRepositoryUtility;
import brblnt.icms.web.model.modules.worksheet.fault.request.FaultRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * Fault service.
 */
@RequiredArgsConstructor
@Service
public class WorksheetFaultService implements ServiceInterface<CompleteFault, FaultRequest> {

  private final ModelMapper modelMapper;
  private final FaultRepositoryUtility faultRepositoryUtility;
  private final CompleteFaultCreator completeFaultCreator;

  /**
   * Get fault from complete creator.
   */
  @Override
  public List<CompleteFault> getAll() {
    return completeFaultCreator.getAll();
  }

  /**
   * Get fault by id from complete creator.
   */
  @Override
  public CompleteFault getById(Long id) {
    return completeFaultCreator.createById(id);
  }

  /**
   * Not required.
   */
  @Override
  public boolean exist(Long id) {
    return false;
  }

  /**
   * Save fault.
   */
  @Override
  public boolean save(FaultRequest faultRequest, CompleteFault completeFault) {
    try {
      CompleteFault save = create(faultRequest);
      if (completeFault.getId() != 0) {
        save.setId(completeFault.getId());
      }
      faultRepositoryUtility.save(modelMapper.map(save, FaultJPA.class));
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Delete service by id.
   */
  @Override
  public boolean deleteById(Long id) {
    try {
      faultRepositoryUtility.delete(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Map fault request to complete fault.
   */
  @Override
  public CompleteFault create(FaultRequest faultRequest) {
    return modelMapper.map(faultRequest, CompleteFault.class);
  }

  /**
   * Create empty fault.
   */
  @Override
  public CompleteFault createEmpty() {
    return new CompleteFault(
            0L,
            "",
            ""
    );
  }
}
