package brblnt.icms.service.service.modules.common;

import java.util.List;

import brblnt.icms.data.modules.common.model.ColleagueJPA;
import brblnt.icms.service.interfaces.ServiceInterface;
import brblnt.icms.service.modules.common.converter.creator.CompleteColleagueCreator;
import brblnt.icms.service.modules.common.model.complete.CompleteColleague;
import brblnt.icms.service.modules.common.repository.utility.ColleagueRepositoryUtility;
import brblnt.icms.web.model.modules.common.request.ColleagueRequest;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 * Colleague Service.
 */
@Service
@RequiredArgsConstructor
public class CommonColleagueService implements ServiceInterface<CompleteColleague, ColleagueRequest> {

  private final ModelMapper modelMapper;
  private final ColleagueRepositoryUtility colleagueRepositoryUtility;
  private final CompleteColleagueCreator completeColleagueCreator;

  /**
   * Get all Colleague from complete creator.
   */
  @Override
  public List<CompleteColleague> getAll() {
    return completeColleagueCreator.getAll();
  }

  /**
   * Get colleague by id from complete creator.
   */
  @Override
  public CompleteColleague getById(Long id) {
    return completeColleagueCreator.createById(id);
  }


  /**
   * Get colleague by custom code.
   */
  public CompleteColleague getById(String id) {
    for (CompleteColleague actual : getAll()) {
      if (actual.getCustomCode().toLowerCase().equals(id.toLowerCase())) {
        return actual;
      }
    }
    return createEmpty();
  }

  /**
   * Not required.
   */
  @Override
  public boolean exist(Long id) {
    return false;
  }

  /**
   * Check custom code is unique.
   */
  public boolean exist(String id, String actualOwn) {
    if (actualOwn.toLowerCase().equals(id.toLowerCase())) {
      return false;
    }
    for (CompleteColleague actual : getAll()) {
      if (actual.getCustomCode().toLowerCase().equals(id.toLowerCase())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Save colleague data.
   */
  @Override
  public boolean save(ColleagueRequest colleagueRequest, CompleteColleague completeColleague) {
    try {
      CompleteColleague save = create(colleagueRequest);
      if (completeColleague.getId() != 0) {
        save.setId(completeColleague.getId());
      }
      colleagueRepositoryUtility.save(modelMapper.map(save, ColleagueJPA.class));
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Delete colleague by id.
   */
  @Override
  public boolean deleteById(Long id) {
    try {
      colleagueRepositoryUtility.delete(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Map Colleague request to complete colleague.
   */
  @Override
  public CompleteColleague create(ColleagueRequest colleagueRequest) {
    return modelMapper.map(colleagueRequest, CompleteColleague.class);
  }

  /**
   * Create empty colleague.
   */
  @Override
  public CompleteColleague createEmpty() {
    return completeColleagueCreator.createEmpty();
  }
}
