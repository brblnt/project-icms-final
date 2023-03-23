package brblnt.icms.web.converter.modules.worksheet.customer;

import brblnt.icms.service.modules.common.converter.creator.CompleteAddressCreator;
import brblnt.icms.service.modules.common.converter.creator.CompleteFinanceCreator;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteCustomer;
import brblnt.icms.web.model.modules.worksheet.customer.request.CustomerRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter.
 * Convert Customer Request to CompleteCustomer.
 */
@Component
@RequiredArgsConstructor
public class ConvertCustomerRequestToCompleteCustomer implements Converter<CustomerRequest, CompleteCustomer> {

  private final CompleteAddressCreator completeAddressCreator;
  private final CompleteFinanceCreator completeFinanceCreator;

  @Override
  public CompleteCustomer convert(@NonNull final CustomerRequest source) {
    return new CompleteCustomer(
            null,
            source.getCustomerName(),
            completeAddressCreator.createById((long) Integer.parseInt(source.getAddress1())),
            completeAddressCreator.createById((long) Integer.parseInt(source.getAddress2())),
            completeAddressCreator.createById((long) Integer.parseInt(source.getAddress3())),
            completeFinanceCreator.createById((long) Integer.parseInt(source.getFinanceID())),
            source.getPhoneNumber(),
            source.getEmailAddress(),
            source.getOther(),
            source.getRegistrationDate()
    );
  }
}
