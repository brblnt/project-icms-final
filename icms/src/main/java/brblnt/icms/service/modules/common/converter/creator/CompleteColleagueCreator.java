package brblnt.icms.service.modules.common.converter.creator;

import java.util.ArrayList;
import java.util.List;

import brblnt.icms.data.modules.common.model.ColleagueJPA;
import brblnt.icms.data.modules.common.model.CompanyJPA;
import brblnt.icms.service.interfaces.CompleteCreator;
import brblnt.icms.service.modules.common.exceptions.ColleagueNotFoundException;
import brblnt.icms.service.modules.common.model.complete.CompleteColleague;
import brblnt.icms.service.modules.common.model.complete.CompleteCompany;
import brblnt.icms.service.modules.common.repository.utility.ColleagueRepositoryUtility;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * Complete Colleague Creator.
 * Colleague Service Layer creator.
 */
@Service
@RequiredArgsConstructor
public class CompleteColleagueCreator implements CompleteCreator<CompleteColleague> {

  private final ModelMapper modelMapper;
  private final ColleagueRepositoryUtility colleagueRepositoryUtility;

  @Override
  public List<CompleteColleague> getAll() {
    List<CompleteColleague> list = new ArrayList<>();
    List<ColleagueJPA> colleagues = colleagueRepositoryUtility.getAll();
    for (ColleagueJPA colleague : colleagues) {
      list.add(createById(colleague.getId()));
    }
    return list;
  }

  @Override
  public CompleteColleague createById(Long id) {
    try {
      if (id == 0) {
        return createEmpty();
      }
      return modelMapper.map(colleagueRepositoryUtility.getById(id), CompleteColleague.class);
    } catch (ColleagueNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * new Complete Colleague.
   */
  public CompleteColleague createEmpty() {
    return new CompleteColleague(
            0L,
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    );
  }

}
