package brblnt.icms.web.model.modules.worksheet.customer.response;

import brblnt.icms.service.modules.common.model.complete.CompleteAddress;
import brblnt.icms.service.modules.common.model.complete.CompleteFinance;

/**
 * Web layer model for customer.
 */
public record CustomerResponse(
        Long id,
        String customerName,
        CompleteAddress addressID1,
        CompleteAddress addressID2,
        CompleteAddress addressID3,
        CompleteFinance financeID,
        String phoneNumber,
        String emailAddress,
        String other,
        String registrationDate) {
}
