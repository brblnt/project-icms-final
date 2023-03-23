package brblnt.icms.web.model.modules.worksheet.services.response;

/**
 * Web layer model for service.
 */
public record ServiceResponse(
        Long id,
        String serviceName,
        String customID,
        double priceN,
        double priceB,
        int vat) {
}
