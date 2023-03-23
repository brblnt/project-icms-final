package brblnt.icms.service.service.modules.worksheet;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import brblnt.icms.data.modules.worksheet.model.CustomerJPA;
import brblnt.icms.service.interfaces.ServiceInterface;
import brblnt.icms.service.modules.common.converter.creator.CompleteAddressCreator;
import brblnt.icms.service.modules.worksheet.converter.creator.CompleteCustomerCreator;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteCustomer;
import brblnt.icms.service.modules.worksheet.repository.utility.CustomerRepositoryUtility;
import brblnt.icms.service.service.modules.common.CommonFinanceService;
import brblnt.icms.web.model.modules.worksheet.customer.request.CustomerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

/**
 * Customer Service.
 */
@RequiredArgsConstructor
@Service
public class WorksheetCustomerService implements ServiceInterface<CompleteCustomer, CustomerRequest> {

  private final CompleteCustomerCreator completeCustomerCreator;
  private final CompleteAddressCreator completeAddressCreator;
  private final Converter<CompleteCustomer, CustomerJPA> convertCompleteCustomerToCustomerJPA;
  private final Converter<CustomerRequest, CompleteCustomer> convertCustomerRequestToCompleteCustomer;
  private final CustomerRepositoryUtility customerRepositoryUtility;
  private final CommonFinanceService commonFinanceService;


  /**
   * Get all customer from complete creator.
   */
  @Override
  public List<CompleteCustomer> getAll() {
    return completeCustomerCreator.getAll();
  }

  /**
   * Get customer by id from complete creator.
   */
  @Override
  public CompleteCustomer getById(Long id) {
    return completeCustomerCreator.createById(id);
  }

  /**
   * Not required.
   */
  @Override
  public boolean exist(Long id) {
    return false;
  }

  /**
   * Save customer.
   */
  @Override
  public boolean save(CustomerRequest request, CompleteCustomer completeCustomer) {
    try {
      CompleteCustomer save = create(request);
      save.setId(completeCustomer.getId());
      commonFinanceService.saveFinance(request, "customer", save.getFinance().getFinanceID());
      save.setAddress1(completeAddressCreator.createById(completeCustomer.getAddress1().getAddressID()));
      save.setAddress2(completeAddressCreator.createById(completeCustomer.getAddress2().getAddressID()));
      save.setAddress3(completeAddressCreator.createById(completeCustomer.getAddress3().getAddressID()));
      if (completeCustomer.getFinance().getFinanceID() == 0) {
        save.getFinance().setFinanceID(commonFinanceService.getLastSaveId());
      }
      customerRepositoryUtility.save(convertCompleteCustomerToCustomerJPA.convert(save));
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Delete customer by id.
   */
  @Override
  public boolean deleteById(Long id) {
    try {
      customerRepositoryUtility.delete(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Convert customer request to complete customer.
   */
  @Override
  public CompleteCustomer create(CustomerRequest request) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    CompleteCustomer newCustomer = convertCustomerRequestToCompleteCustomer.convert(request);
    newCustomer.setRegistrationDate(
            request.getRegistrationDate() == "na" ? formatter.format(date) : request.getRegistrationDate());
    return newCustomer;
  }

  /**
   * Update address id.
   */
  public boolean updateCustomerAddress(Long id, Long addressID, int position) {
    try {
      CustomerJPA cc = convertCompleteCustomerToCustomerJPA.convert(completeCustomerCreator.createById(id));
      if (position == 1) {
        cc.setAddressID(Math.toIntExact(addressID));
        customerRepositoryUtility.save(cc);
        return true;
      } else if (position == 2) {
        cc.setAddressID2(Math.toIntExact(addressID));
        customerRepositoryUtility.save(cc);
        return true;
      } else if (position == 3) {
        cc.setAddressID3(Math.toIntExact(addressID));
        customerRepositoryUtility.save(cc);
        return true;
      } else {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Set address id to 0.
   */
  public boolean clearAddressId(Long id, int position) {
    try {
      CustomerJPA cc = convertCompleteCustomerToCustomerJPA.convert(completeCustomerCreator.createById(id));
      if (position == 2) {
        cc.setAddressID2(0);
        customerRepositoryUtility.save(cc);
      } else if (position == 3) {
        cc.setAddressID3(0);
        customerRepositoryUtility.save(cc);
      }
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Create empty customer.
   */
  @Override
  public CompleteCustomer createEmpty() {
    return completeCustomerCreator.createEmpty();
  }


}
