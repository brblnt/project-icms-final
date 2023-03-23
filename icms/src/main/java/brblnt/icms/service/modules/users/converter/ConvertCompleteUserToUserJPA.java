package brblnt.icms.service.modules.users.converter;

import brblnt.icms.data.modules.users.model.ApplicationUserJPA;
import brblnt.icms.service.modules.users.model.complete.CompleteUser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter.
 * Convert CompleteUser to ApplicationUserJPA.
 */
@Component
public class ConvertCompleteUserToUserJPA implements Converter<CompleteUser, ApplicationUserJPA> {

  @Override
  public ApplicationUserJPA convert(CompleteUser source) {
    return new ApplicationUserJPA(
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
