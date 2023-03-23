package brblnt.icms.service.modules.worksheet.repository.utility;

import java.util.List;
import java.util.Objects;

import brblnt.icms.data.modules.worksheet.model.WorksheetJPA;
import brblnt.icms.data.modules.worksheet.repository.WorksheetRepository;
import brblnt.icms.service.interfaces.UtilityInterface;
import brblnt.icms.service.modules.worksheet.exceptions.WorksheetNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * WorksheetRepositoryUtility contains database manipulation methods.
 */
@Service
@RequiredArgsConstructor
public class WorksheetRepositoryUtility implements UtilityInterface<WorksheetJPA, WorksheetNotFoundException> {

  private final WorksheetRepository worksheetRepository;

  @Override
  public List<WorksheetJPA> getAll() {
    return worksheetRepository.findAll().stream().toList();
  }

  @Override
  public WorksheetJPA getById(Long id) throws WorksheetNotFoundException {
    for (WorksheetJPA worksheet : getAll()) {
      if (Objects.equals(worksheet.getId(), id)) {
        return worksheet;
      }
    }
    throw new WorksheetNotFoundException("No worksheet with this id " + id);
  }

  /**
   * Not required.
   */
  @Override
  public WorksheetJPA getById(String id) throws WorksheetNotFoundException {
    return null;
  }

  @Override
  public void save(WorksheetJPA worksheet) {
    worksheetRepository.save(worksheet);
  }

  @Override
  public void delete(Long id) {
    worksheetRepository.deleteById(id);
  }
}
