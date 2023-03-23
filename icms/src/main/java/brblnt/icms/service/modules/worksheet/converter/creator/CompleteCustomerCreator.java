package brblnt.icms.service.modules.worksheet.converter.creator;

import java.util.ArrayList;
import java.util.List;

import brblnt.icms.data.modules.worksheet.model.CustomerJPA;
import brblnt.icms.service.interfaces.CompleteCreator;
import brblnt.icms.service.modules.common.converter.creator.CompleteAddressCreator;
import brblnt.icms.service.modules.common.converter.creator.CompleteFinanceCreator;
import brblnt.icms.service.modules.worksheet.exceptions.CustomerNotFoundException;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteCustomer;
import brblnt.icms.service.modules.worksheet.repository.utility.CustomerRepositoryUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Create Complete Customer.
 * Convert JPA to CompleteObject.
 */
@Service
@RequiredArgsConstructor
public class CompleteCustomerCreator implements CompleteCreator<CompleteCustomer> {

  private final CompleteAddressCreator completeAddressCreator;
  private final CompleteFinanceCreator completeFinanceCreator;
  private final CustomerRepositoryUtility customerRepositoryUtility;

  @Override
  public List<CompleteCustomer> getAll() {
    List<CompleteCustomer> list = new ArrayList<>();
    List<CustomerJPA> customers = customerRepositoryUtility.getAll();
    for (CustomerJPA customer : customers) {
      list.add(createById(customer.getId()));
    }
    return list;
  }

  @Override
  public CompleteCustomer createById(Long customerID) {
    CompleteCustomer cc = new CompleteCustomer();

    if (customerID == 0) {
      return createEmpty();
    }

    CustomerJPA tempCustomer;
    try {
      tempCustomer = customerRepositoryUtility.getById(customerID);
    } catch (CustomerNotFoundException e) {
      throw new RuntimeException(e);
    }

    cc.setId(tempCustomer.getId());
    cc.setCustomerName(tempCustomer.getCustomerName());

    cc.setAddress1(completeAddressCreator.createById((long) tempCustomer.getAddressID()));
    cc.setAddress2(completeAddressCreator.createById((long) tempCustomer.getAddressID2()));
    cc.setAddress3(completeAddressCreator.createById((long) tempCustomer.getAddressID3()));

    cc.setFinance(completeFinanceCreator.createById((long) tempCustomer.getFinanceID()));

    cc.setPhoneNumber(tempCustomer.getPhoneNumber());
    cc.setEmailAddress(tempCustomer.getEmailAddress());
    cc.setOther(tempCustomer.getOther());
    cc.setRegistrationDate(tempCustomer.getRegistrationDate());

    return cc;
  }

  /**
   * Create empty customer.
   */
  public CompleteCustomer createEmpty() {
    return new CompleteCustomer(
            0L,
            "",
            completeAddressCreator.createById(0L),
            completeAddressCreator.createById(0L),
            completeAddressCreator.createById(0L),
            completeFinanceCreator.createById(0L),
            "",
            "",
            "",
            "");
  }
}
