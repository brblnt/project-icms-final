package brblnt.icms.web.converter.modules.worksheet.dealer;

import brblnt.icms.service.modules.common.converter.creator.CompleteAddressCreator;
import brblnt.icms.service.modules.common.converter.creator.CompleteFinanceCreator;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteDealer;
import brblnt.icms.web.model.modules.worksheet.dealer.request.DealerRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter.
 * Convert Dealer Request to Complete Dealer.
 */
@Component
@RequiredArgsConstructor
public class ConvertDealerRequestToCompleteDealer implements Converter<DealerRequest, CompleteDealer> {

  private final CompleteAddressCreator completeAddressCreator;
  private final CompleteFinanceCreator completeFinanceCreator;

  @Override
  public CompleteDealer convert(@NonNull final DealerRequest source) {
    return new CompleteDealer(
            null,
            source.getDealerName(),
            completeAddressCreator.createById((long) source.getAddress1()),
            completeAddressCreator.createById((long) source.getAddress2()),
            completeAddressCreator.createById((long) source.getAddress3()),
            completeFinanceCreator.createById((long) source.getFinanceID()),
            source.getPhoneNumber(),
            source.getEmailAddress(),
            source.getOther());
  }
}
