package brblnt.icms.web.model.modules.worksheet.fault.response;

/**
 * Web layer model for fault.
 */
public record FaultResponse(
        Long id,
        String faultName,
        String faultOther) {
}
