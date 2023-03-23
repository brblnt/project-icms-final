package brblnt.icms.service.service.modules.worksheet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import brblnt.icms.data.modules.worksheet.model.ObjectJPA;
import brblnt.icms.service.interfaces.ServiceInterface;
import brblnt.icms.service.modules.worksheet.converter.creator.CompleteObjectCreator;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteCustomer;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteObject;
import brblnt.icms.service.modules.worksheet.repository.utility.ObjectRepositoryUtility;
import brblnt.icms.web.model.modules.worksheet.object.request.ObjectRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * Object service.
 */
@RequiredArgsConstructor
@Service
public class WorksheetObjectService implements ServiceInterface<CompleteObject, ObjectRequest> {

  private final ModelMapper modelMapper;
  private final ObjectRepositoryUtility objectRepositoryUtility;
  private final CompleteObjectCreator completeObjectCreator;
  private final WorksheetCustomerService worksheetCustomerService;
  private final WorksheetService worksheetService;

  /**
   * Get all object from complete creator.
   */
  @Override
  public List<CompleteObject> getAll() {
    return completeObjectCreator.getAll();
  }

  /**
   * Get object by id from complete creator.
   */
  @Override
  public CompleteObject getById(Long id) {
    return completeObjectCreator.createById(id);
  }

  /**
   * Not required.
   */
  @Override
  public boolean exist(Long id) {
    return false;
  }

  /**
   * Save object.
   */
  @Override
  public boolean save(ObjectRequest objectRequest, CompleteObject completeObject) {
    try {
      CompleteObject save = create(objectRequest);
      if (completeObject.getId() != 0) {
        save.setId(completeObject.getId());
        worksheetService.updateCustomerObjectId(completeObject.getId(), objectRequest.getCustomerID());
      }
      objectRepositoryUtility.save(modelMapper.map(save, ObjectJPA.class));
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Delete object by id.
   */
  @Override
  public boolean deleteById(Long id) {
    try {
      objectRepositoryUtility.delete(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Map object request to complete object.
   */
  @Override
  public CompleteObject create(ObjectRequest objectRequest) {
    return modelMapper.map(objectRequest, CompleteObject.class);
  }

  /**
   * Create empty object.
   */
  @Override
  public CompleteObject createEmpty() {
    return new CompleteObject(
            0L,
            worksheetCustomerService.createEmpty(),
            "",
            "",
            "",
            "",
            ""
    );
  }

  /**
   * Remove object owner from customer list.
   */
  public List<CompleteCustomer> getCustomerListWithoutOwner(Long actualObjectCustomerID) {
    List<CompleteCustomer> tempList = worksheetCustomerService.getAll();
    tempList.removeIf(actual -> Objects.equals(actual.getId(), actualObjectCustomerID));
    return tempList;
  }

  /**
   * Get object that customer own.
   */
  public List<CompleteObject> getCustomerItems(Long actualObjectsCustomerID) {
    List<CompleteObject> tempList = new ArrayList<>();
    for (CompleteObject temp : getAll()) {
      if (Objects.equals(temp.getCustomer().getId(), actualObjectsCustomerID)) {
        tempList.add(temp);
      }
    }
    return tempList;
  }

  /**
   * Clear object id in customers if object removed.
   */
  public void clearUsage(Long id) {
    for (CompleteObject actual : getAll()) {
      if (Objects.equals(actual.getCustomer().getId(), id)) {
        ObjectJPA save = modelMapper.map(actual, ObjectJPA.class);
        save.setCustomerID(0);
        objectRepositoryUtility.save(save);
      }
    }
  }
}
