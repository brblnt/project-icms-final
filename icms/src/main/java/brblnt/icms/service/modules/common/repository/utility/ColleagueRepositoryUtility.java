package brblnt.icms.service.modules.common.repository.utility;

import java.util.List;
import java.util.Objects;

import brblnt.icms.data.modules.common.model.ColleagueJPA;
import brblnt.icms.data.modules.common.repository.ColleagueRepository;
import brblnt.icms.service.interfaces.UtilityInterface;
import brblnt.icms.service.modules.common.exceptions.ColleagueNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Colleague Repository Utility.
 */
@Service
@RequiredArgsConstructor
  public class ColleagueRepositoryUtility implements UtilityInterface<ColleagueJPA, ColleagueNotFoundException> {

  private final ColleagueRepository colleagueRepository;

  @Override
  public List<ColleagueJPA> getAll() {
    return colleagueRepository.findAll().stream().toList();
  }

  @Override
  public ColleagueJPA getById(Long id) throws ColleagueNotFoundException {
    for (ColleagueJPA colleague : getAll()) {
      if (Objects.equals(colleague.getId(), id)) {
        return colleague;
      }
    }
    throw new ColleagueNotFoundException("No colleague with this id " + id);
  }

  /**
   * Not required.
   */
  @Override
  public ColleagueJPA getById(String id) throws ColleagueNotFoundException {
    return null;
  }

  @Override
  public void save(ColleagueJPA colleague) {
    colleagueRepository.save(colleague);
  }

  @Override
  public void delete(Long id) {
    colleagueRepository.deleteById(id);
  }

}
