package brblnt.icms.web.model.modules.worksheet.object.response;

import brblnt.icms.service.modules.worksheet.model.complete.CompleteCustomer;

/**
 * Web layer model for object.
 */
public record ObjectResponse(
        Long id,
        CompleteCustomer customer,
        String itemName,
        String itemBrand,
        String itemType,
        String itemSerial,
        String other
) {
}
