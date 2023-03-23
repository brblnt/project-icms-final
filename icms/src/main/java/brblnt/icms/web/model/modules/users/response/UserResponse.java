package brblnt.icms.web.model.modules.users.response;

/**
 * User web layer representation.
 */
public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String userName,
        String emailAddress,
        String password,
        String role,
        boolean passwordChanged,
        String registrationDate,
        boolean accountNonExpired,
        boolean accountNonLocked,
        boolean accountCredentialsNonExpired,
        boolean accountEnabled) {
}