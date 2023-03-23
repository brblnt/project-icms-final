package brblnt.icms.service.modules.common.repository.utility.additional;

import java.util.List;
import java.util.Objects;

import brblnt.icms.data.modules.common.model.additional.FinanceJPA;
import brblnt.icms.data.modules.common.repository.additional.FinanceRepository;
import brblnt.icms.service.interfaces.UtilityInterface;
import brblnt.icms.service.modules.common.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Finance Repository Utility.
 */
@Service
@RequiredArgsConstructor
public class FinanceRepositoryUtility implements UtilityInterface<FinanceJPA, NotFoundException> {

  private final FinanceRepository financeRepository;

  @Override
  public List<FinanceJPA> getAll() {
    return financeRepository.findAll().stream().toList();
  }

  @Override
  public FinanceJPA getById(Long id) throws NotFoundException {
    for (FinanceJPA finance : getAll()) {
      if (Objects.equals(finance.getId(), id)) {
        return finance;
      }
    }
    throw new NotFoundException("No finance with this id " + id);
  }

  /**
   * Not required.
   */
  @Override
  public FinanceJPA getById(String id) throws NotFoundException {
    return null;
  }

  @Override
  public void save(FinanceJPA finance) {
    financeRepository.save(finance);
  }

  @Override
  public void delete(Long id) {

  }
}
