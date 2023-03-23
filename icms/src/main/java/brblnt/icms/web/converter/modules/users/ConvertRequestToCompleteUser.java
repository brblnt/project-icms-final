package brblnt.icms.web.converter.modules.users;

import brblnt.icms.service.modules.users.model.complete.CompleteUser;
import brblnt.icms.web.model.modules.users.request.UserRequest;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


/**
 * Converter User Request To Complete User.
 */
@Component
public class ConvertRequestToCompleteUser implements Converter<UserRequest, CompleteUser> {

  @Override
  public CompleteUser convert(@NonNull final UserRequest source) {
    return new CompleteUser(null,
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
