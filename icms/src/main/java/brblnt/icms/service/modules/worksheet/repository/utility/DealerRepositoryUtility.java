package brblnt.icms.service.modules.worksheet.repository.utility;

import java.util.List;
import java.util.Objects;

import brblnt.icms.data.modules.common.model.additional.DealerJPA;
import brblnt.icms.data.modules.worksheet.repository.DealerRepository;
import brblnt.icms.service.interfaces.UtilityInterface;
import brblnt.icms.service.modules.worksheet.exceptions.DealerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * DealerRepositoryUtility contains database manipulation methods.
 */
@Service
@RequiredArgsConstructor
public class DealerRepositoryUtility implements UtilityInterface<DealerJPA, DealerNotFoundException> {

  private final DealerRepository dealerRepository;

  @Override
  public List<DealerJPA> getAll() {
    return dealerRepository.findAll().stream().toList();
  }

  @Override
  public DealerJPA getById(Long id) throws DealerNotFoundException {
    for (DealerJPA dealer : getAll()) {
      if (Objects.equals(dealer.getId(), id)) {
        return dealer;
      }
    }
    throw new DealerNotFoundException("No dealer with this id " + id);
  }

  /**
   * Not required.
   */
  @Override
  public DealerJPA getById(String id) throws DealerNotFoundException {
    return null;
  }

  @Override
  public void save(DealerJPA dealer) {
    dealerRepository.save(dealer);
  }

  @Override
  public void delete(Long id) {
    dealerRepository.deleteById(id);
  }
}
