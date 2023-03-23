package brblnt.icms.web.model.modules.worksheet.dealer.response;

import brblnt.icms.service.modules.common.model.complete.CompleteAddress;
import brblnt.icms.service.modules.common.model.complete.CompleteFinance;

/**
 * Web layer model for dealer.
 */
public record DealerResponse(
        Long id,
        String dealerName,
        CompleteAddress address1,
        CompleteAddress address2,
        CompleteAddress address3,
        CompleteFinance finance,
        String phoneNumber,
        String emailAddress,
        String other) {
}
