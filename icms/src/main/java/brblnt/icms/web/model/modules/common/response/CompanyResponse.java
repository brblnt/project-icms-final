package brblnt.icms.web.model.modules.common.response;

import brblnt.icms.service.modules.common.model.complete.CompleteAddress;
import brblnt.icms.service.modules.common.model.complete.CompleteFinance;

/**
 * Web layer model for company.
 */
public record CompanyResponse(
        Long i,
        String companyName,
        CompleteAddress address1,
        CompleteAddress address2,
        CompleteAddress address3,
        CompleteFinance finance,
        String phoneNumber,
        String emailAddress,
        String other,
        String modificationDate
) {
}
