package brblnt.icms.service.modules.worksheet.converter.creator;

import java.util.ArrayList;
import java.util.List;

import brblnt.icms.data.modules.worksheet.model.ObjectJPA;
import brblnt.icms.service.interfaces.CompleteCreator;
import brblnt.icms.service.modules.worksheet.exceptions.ObjectNotFoundException;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteObject;
import brblnt.icms.service.modules.worksheet.repository.utility.ObjectRepositoryUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Create Complete Object.
 * Convert JPA to CompleteObject.
 */
@Service
@RequiredArgsConstructor
public class CompleteObjectCreator implements CompleteCreator<CompleteObject> {

  private final ObjectRepositoryUtility objectRepositoryUtility;
  private final CompleteCustomerCreator completeCustomerCreator;


  @Override
  public List<CompleteObject> getAll() {
    List<CompleteObject> list = new ArrayList<>();
    List<ObjectJPA> objects = objectRepositoryUtility.getAll();
    for (ObjectJPA object : objects) {
      list.add(createById(object.getId()));
    }
    return list;
  }

  @Override
  public CompleteObject createById(Long objectID) {
    CompleteObject co = new CompleteObject();

    ObjectJPA tempObject;
    try {
      tempObject = objectRepositoryUtility.getById(objectID);
    } catch (ObjectNotFoundException e) {
      throw new RuntimeException(e);
    }

    co.setId(tempObject.getId());
    co.setCustomer(completeCustomerCreator.createById((long) tempObject.getCustomerID()));
    co.setItemName(tempObject.getItemName());
    co.setItemBrand(tempObject.getItemBrand());
    co.setItemType(tempObject.getItemType());
    co.setItemSerial(tempObject.getItemSerial());
    co.setOther(tempObject.getOther());
    return co;
  }
}
