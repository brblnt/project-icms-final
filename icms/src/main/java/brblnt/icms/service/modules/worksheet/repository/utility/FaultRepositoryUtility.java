package brblnt.icms.service.modules.worksheet.repository.utility;

import java.util.List;
import java.util.Objects;

import brblnt.icms.data.modules.worksheet.model.FaultJPA;
import brblnt.icms.data.modules.worksheet.repository.FaultRepository;
import brblnt.icms.service.interfaces.UtilityInterface;
import brblnt.icms.service.modules.worksheet.exceptions.FaultNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * FaultRepositoryUtility contains database manipulation methods.
 */
@Service
@RequiredArgsConstructor
public class FaultRepositoryUtility implements UtilityInterface<FaultJPA, FaultNotFoundException> {

  private final FaultRepository faultRepository;

  @Override
  public List<FaultJPA> getAll() {
    return faultRepository.findAll().stream().toList();
  }

  @Override
  public FaultJPA getById(Long id) throws FaultNotFoundException {
    for (FaultJPA fault : getAll()) {
      if (Objects.equals(fault.getId(), id)) {
        return fault;
      }
    }
    throw new FaultNotFoundException("No fault with this id " + id);
  }

  /**
   * Not required.
   */
  @Override
  public FaultJPA getById(String id) throws FaultNotFoundException {
    return null;
  }

  @Override
  public void save(FaultJPA fault) {
    faultRepository.save(fault);
  }

  @Override
  public void delete(Long id) {
    faultRepository.deleteById(id);
  }
}
