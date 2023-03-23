package brblnt.icms.service.modules.worksheet.repository.utility;

import java.util.List;
import java.util.Objects;

import brblnt.icms.data.modules.worksheet.model.ObjectJPA;
import brblnt.icms.data.modules.worksheet.repository.ObjectRepository;
import brblnt.icms.service.interfaces.UtilityInterface;
import brblnt.icms.service.modules.worksheet.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * ObjectRepositoryUtility contains database manipulation methods.
 */
@Service
@RequiredArgsConstructor
public class ObjectRepositoryUtility implements UtilityInterface<ObjectJPA, ObjectNotFoundException> {

  private final ObjectRepository objectRepository;

  @Override
  public List<ObjectJPA> getAll() {
    return objectRepository.findAll().stream().toList();
  }

  @Override
  public ObjectJPA getById(Long id) throws ObjectNotFoundException {
    for (ObjectJPA object : getAll()) {
      if (Objects.equals(object.getId(), id)) {
        return object;
      }
    }
    throw new ObjectNotFoundException("No object with this id " + id);
  }

  /**
   * Not required.
   */
  @Override
  public ObjectJPA getById(String id) throws ObjectNotFoundException {
    return null;
  }

  @Override
  public void save(ObjectJPA object) {
    objectRepository.save(object);
  }

  @Override
  public void delete(Long id) {
    objectRepository.deleteById(id);
  }
}
