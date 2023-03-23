package brblnt.icms.web.model.modules.common.response;

/**
 * Web layer model for colleague.
 */
public record ColleagueResponse(
        Long id,
        String customCode,
        String name,
        String phoneNumber,
        String emailAddress,
        String position,
        String qualifications,
        String workingArea,
        String other,
        String registrationDate
) {
}
