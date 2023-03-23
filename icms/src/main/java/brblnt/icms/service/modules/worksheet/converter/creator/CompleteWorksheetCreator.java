package brblnt.icms.service.modules.worksheet.converter.creator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import brblnt.icms.data.modules.worksheet.model.FaultJPA;
import brblnt.icms.data.modules.worksheet.model.ServiceJPA;
import brblnt.icms.data.modules.worksheet.model.WorksheetJPA;
import brblnt.icms.service.interfaces.CompleteCreator;
import brblnt.icms.service.modules.worksheet.exceptions.FaultNotFoundException;
import brblnt.icms.service.modules.worksheet.exceptions.ServiceNotFoundException;
import brblnt.icms.service.modules.worksheet.exceptions.WorksheetNotFoundException;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteFault;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteProduct;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteService;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteWorksheet;
import brblnt.icms.service.modules.worksheet.repository.utility.FaultRepositoryUtility;
import brblnt.icms.service.modules.worksheet.repository.utility.ServiceRepositoryUtility;
import brblnt.icms.service.modules.worksheet.repository.utility.WorksheetRepositoryUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * CompleteWorksheetCreator create service layer model for Worksheet.
 */
@Service
@RequiredArgsConstructor
public class CompleteWorksheetCreator implements CompleteCreator<CompleteWorksheet> {

  private final WorksheetRepositoryUtility worksheetRepositoryUtility;
  private final CompleteCustomerCreator completeCustomerCreator;
  private final CompleteObjectCreator completeObjectCreator;
  private final CompleteProductCreator completeProductCreator;
  private final CompleteFaultCreator completeFaultCreator;
  private final CompleteServiceCreator completeServiceCreator;


  @Override
  public List<CompleteWorksheet> getAll() {
    List<CompleteWorksheet> list = new ArrayList<>();
    List<WorksheetJPA> worksheets = worksheetRepositoryUtility.getAll();
    for (WorksheetJPA worksheet : worksheets) {
      list.add(createById(worksheet.getId()));
    }
    return list;
  }

  @Override
  public CompleteWorksheet createById(Long worksheetID) {
    CompleteWorksheet cw = new CompleteWorksheet();

    WorksheetJPA tempWorksheet;
    try {
      tempWorksheet = worksheetRepositoryUtility.getById(worksheetID);
    } catch (WorksheetNotFoundException e) {
      throw new RuntimeException(e);
    }

    cw.setId(tempWorksheet.getId());
    cw.setState(tempWorksheet.getState());
    cw.setEngineerCode(tempWorksheet.getEngineerCode());
    cw.setCustomer(completeCustomerCreator.createById((long) tempWorksheet.getCustomerID()));
    cw.setObject(completeObjectCreator.createById((long) tempWorksheet.getObjectID()));

    ArrayList<CompleteFault> faults = new ArrayList<>();
    String[] faultsArray = tempWorksheet.getFaultsID().split(";");


    if (!Objects.equals(tempWorksheet.getFaultsID(), "")) {
      for (String i : faultsArray) {
        try {
          faults.add(completeFaultCreator.createById((long) Integer.parseInt(i)));
        } catch (Exception e) {
          System.out.print("");
        }
      }
    }

    cw.setFaults(faults);
    ArrayList<CompleteService> services = new ArrayList<>();
    String[] servicesArray = tempWorksheet.getServicesID().split(";");


    if (!Objects.equals(tempWorksheet.getServicesID(), "")) {
      for (String i : servicesArray) {
        try {
          services.add(completeServiceCreator.createById((long) Integer.parseInt(i)));
        } catch (Exception e) {
          System.out.print("");
        }
      }
    }

    cw.setServices(services);
    ArrayList<CompleteProduct> completeProducts = new ArrayList<>();
    String[] productArray = tempWorksheet.getProductsID().split(";");


    if (!Objects.equals(tempWorksheet.getProductsID(), "")) {
      for (String i : productArray) {
        try {
          completeProducts.add(completeProductCreator.createById((long) Integer.parseInt(i)));
        } catch (Exception e) {
          System.out.print("");
        }
      }
    }

    cw.setProducts(completeProducts);
    cw.setCreateDate(tempWorksheet.getCreateDate());
    cw.setFinishDate(tempWorksheet.getFinishDate());
    cw.setOther(tempWorksheet.getOther());


    return cw;
  }

  /**
   * Get Complete Faults on Worksheet by fault id.
   */
  public ArrayList<CompleteFault> getAllFaultOnWorksheet(ArrayList<Integer> faultIDs) {
    ArrayList<CompleteFault> faults = new ArrayList<>();
    for (int i : faultIDs) {
      faults.add(completeFaultCreator.createById((long) i));
    }
    return faults;
  }

  /**
   * Get Complete Services on Worksheet by service id.
   */
  public ArrayList<CompleteService> getAllServiceOnWorksheet(ArrayList<Integer> serviceIDs) {
    ArrayList<CompleteService> services = new ArrayList<>();
    for (int i : serviceIDs) {
      services.add(completeServiceCreator.createById((long) i));
    }
    return services;
  }

  /**
   * Get Complete Products on Worksheet by product id.
   */
  public ArrayList<CompleteProduct> getAllProductOnWorksheet(ArrayList<Integer> productIDs) {
    ArrayList<CompleteProduct> products = new ArrayList<>();
    for (int i : productIDs) {
      products.add(completeProductCreator.createById((long) i));
    }
    return products;
  }

}
