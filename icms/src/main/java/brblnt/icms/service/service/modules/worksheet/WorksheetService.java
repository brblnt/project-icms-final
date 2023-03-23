package brblnt.icms.service.service.modules.worksheet;

import java.util.List;
import java.util.Objects;

import brblnt.icms.data.modules.worksheet.model.WorksheetJPA;
import brblnt.icms.service.interfaces.ServiceInterface;
import brblnt.icms.service.modules.worksheet.converter.creator.CompleteWorksheetCreator;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteFault;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteProduct;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteService;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteWorksheet;
import brblnt.icms.service.modules.worksheet.repository.utility.WorksheetRepositoryUtility;
import brblnt.icms.web.model.modules.worksheet.worksheet.request.WorksheetCreateRequest;
import brblnt.icms.web.model.modules.worksheet.worksheet.request.WorksheetRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

/**
 * Worksheet service.
 */
@RequiredArgsConstructor
@Service
public class WorksheetService implements ServiceInterface<CompleteWorksheet, WorksheetRequest> {

  private final WorksheetRepositoryUtility worksheetRepositoryUtility;
  private final CompleteWorksheetCreator completeWorksheetCreator;
  private final Converter<WorksheetRequest, CompleteWorksheet> worksheetRequestCompleteWorksheetConverter;
  private final Converter<WorksheetCreateRequest, CompleteWorksheet> worksheetCreateRequestCompleteWorksheetConverter;
  private final Converter<CompleteWorksheet, WorksheetJPA> completeWorksheetWorksheetJPAConverter;

  /**
   * Get worksheets from complete creator.
   */
  @Override
  public List<CompleteWorksheet> getAll() {
    return completeWorksheetCreator.getAll();
  }

  /**
   * Get worksheet by id from complete creator.
   */
  @Override
  public CompleteWorksheet getById(Long id) {
    return completeWorksheetCreator.createById(id);
  }

  /**
   * Not required.
   */
  @Override
  public boolean exist(Long id) {
    return false;
  }

  /**
   * Save Worksheet.
   */
  @Override
  public boolean save(WorksheetRequest worksheetRequest, CompleteWorksheet o) {
    try {
      WorksheetJPA actual = completeWorksheetWorksheetJPAConverter.convert(o);

      actual.setOther(worksheetRequest.getOther());
      actual.setFinishDate(worksheetRequest.getFinishDate());
      actual.setEngineerCode(worksheetRequest.getEngineerCode());
      actual.setState(worksheetRequest.getState());
      actual.setFaultsID(worksheetRequest.getFaults());
      actual.setServicesID(worksheetRequest.getServices());
      actual.setProductsID(worksheetRequest.getProducts());

      worksheetRepositoryUtility.save(actual);
      return true;
    } catch (Exception e) {
      return false;
    }

  }

  /**
   * Delete worksheet.
   */
  @Override
  public boolean deleteById(Long id) {
    try {
      worksheetRepositoryUtility.delete(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Map worksheet request to complete worksheet.
   */
  @Override
  public CompleteWorksheet create(WorksheetRequest worksheetRequest) {
    return worksheetRequestCompleteWorksheetConverter.convert(worksheetRequest);
  }

  /**
   * Not required.
   */
  @Override
  public CompleteWorksheet createEmpty() {
    return null;
  }

  /**
   * Create new worksheet.
   */
  public CompleteWorksheet createNew(WorksheetCreateRequest worksheetCreateRequest) {
    CompleteWorksheet save = worksheetCreateRequestCompleteWorksheetConverter.convert(worksheetCreateRequest);
    try {
      worksheetRepositoryUtility.save(completeWorksheetWorksheetJPAConverter.convert(save));
    } catch (Exception e) {
      System.out.print("");
    }
    return save;
  }

  /**
   * Update worksheet if object has new owner.
   */
  public void updateCustomerObjectId(Long objectID, int customerID) {
    for (CompleteWorksheet actual : getAll()) {
      if (Objects.equals(actual.getObject().getId(), objectID)) {
        WorksheetJPA save = completeWorksheetWorksheetJPAConverter.convert(actual);
        save.setCustomerID(customerID);
        try {
          worksheetRepositoryUtility.save(save);
        } catch (Exception e) {
          System.out.print("");
        }
      }
    }
  }

}
