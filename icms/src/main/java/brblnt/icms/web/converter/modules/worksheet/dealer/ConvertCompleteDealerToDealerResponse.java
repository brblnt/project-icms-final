package brblnt.icms.web.converter.modules.worksheet.dealer;

import brblnt.icms.service.modules.worksheet.model.complete.CompleteDealer;
import brblnt.icms.web.model.modules.worksheet.dealer.response.DealerResponse;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter.
 * Convert Complete Dealer to Dealer Response.
 */
@Component
public class ConvertCompleteDealerToDealerResponse  implements Converter<CompleteDealer, DealerResponse> {
  @Override
  public DealerResponse convert(@NonNull final CompleteDealer source) {
    return new DealerResponse(
            source.getId(),
            source.getDealerName(),
            source.getAddress1(),
            source.getAddress2(),
            source.getAddress3(),
            source.getFinance(),
            source.getPhoneNumber(),
            source.getEmailAddress(),
            source.getOther()
    );
  }
}
