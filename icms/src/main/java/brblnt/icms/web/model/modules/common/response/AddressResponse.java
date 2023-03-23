package brblnt.icms.web.model.modules.common.response;

/**
 * Web layer model for address.
 */
public record AddressResponse(
        Long id,
        String postalCode,
        String cityName,
        String address,
        String other) {
}
