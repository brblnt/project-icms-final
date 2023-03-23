package brblnt.icms.web.model.modules.worksheet.product.response;

import brblnt.icms.service.modules.worksheet.model.complete.CompleteDealer;

/**
 * Web layer model for product.
 */
public record ProductResponse(
        Long id,
        String productName,
        String customCode,
        double priceN,
        double priceB,
        int vat,
        double priceSellN,
        double priceSellB,
        int vatSell,
        CompleteDealer dealer,
        String other) {
}
