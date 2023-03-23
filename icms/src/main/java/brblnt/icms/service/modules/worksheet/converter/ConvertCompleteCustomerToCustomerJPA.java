package brblnt.icms.service.modules.worksheet.converter;

import brblnt.icms.data.modules.worksheet.model.CustomerJPA;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteCustomer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter.
 * Convert CompleteCustomer tor CustomerJPA.
 */
@Component
public class ConvertCompleteCustomerToCustomerJPA implements Converter<CompleteCustomer, CustomerJPA> {

  @Override
  public CustomerJPA convert(CompleteCustomer source) {
    return new CustomerJPA(
            source.getId(),
            source.getCustomerName(),
            Math.toIntExact(source.getAddress1().getAddressID()),
            Math.toIntExact(source.getAddress2().getAddressID()),
            Math.toIntExact(source.getAddress3().getAddressID()),
            source.getFinance().getFinanceID(),
            source.getPhoneNumber(),
            source.getEmailAddress(),
            source.getOther(),
            source.getRegistrationDate()
    );
  }
}
