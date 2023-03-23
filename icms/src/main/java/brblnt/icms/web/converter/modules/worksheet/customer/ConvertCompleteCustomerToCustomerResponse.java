package brblnt.icms.web.converter.modules.worksheet.customer;

import brblnt.icms.service.modules.worksheet.model.complete.CompleteCustomer;
import brblnt.icms.web.model.modules.worksheet.customer.response.CustomerResponse;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter.
 * Convert Complete Customer to Customer Response.
 */
@Component
public class ConvertCompleteCustomerToCustomerResponse implements Converter<CompleteCustomer, CustomerResponse> {

  @Override
  public CustomerResponse convert(@NonNull final CompleteCustomer source) {
    return new CustomerResponse(
            source.getId(),
            source.getCustomerName(),
            source.getAddress1(),
            source.getAddress2(),
            source.getAddress3(),
            source.getFinance(),
            source.getPhoneNumber(),
            source.getEmailAddress(),
            source.getOther(),
            source.getRegistrationDate());
  }
}
