package brblnt.icms.web.converter.modules.users;

import brblnt.icms.service.modules.users.model.complete.CompleteUser;
import brblnt.icms.web.model.modules.users.response.UserResponse;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Convert Complete User to User Response.
 */
@Component
public class ConvertCompleteUserToResponse implements Converter<CompleteUser, UserResponse> {

  @Override
  public UserResponse convert(@NonNull final CompleteUser source) {
    return new UserResponse(
            source.getId(),
            source.getFirstName(),
            source.getLastName(),
            source.getUserName(),
            source.getEmailAddress(),
            source.getPassword(),
            source.getRole(),
            source.isPasswordChanged(),
            source.getRegistrationDate(),
            source.isAccountNonExpired(),
            source.isAccountNonLocked(),
            source.isAccountCredentialsNonExpired(),
            source.isAccountEnabled());
  }

}
