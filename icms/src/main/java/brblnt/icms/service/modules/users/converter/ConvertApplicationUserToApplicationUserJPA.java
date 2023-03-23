package brblnt.icms.service.modules.users.converter;

import static brblnt.icms.config.security.ApplicationUserRole.*;

import brblnt.icms.config.auth.ApplicationUser;
import brblnt.icms.data.modules.users.model.ApplicationUserJPA;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter.
 * Convert ApplicationUserJpa to ApplicationUser.
 */
@Component
public class ConvertApplicationUserToApplicationUserJPA implements Converter<ApplicationUserJPA, ApplicationUser> {

  @Override
  public ApplicationUser convert(ApplicationUserJPA source) {
    return new ApplicationUser(
            source.getUserName(),
            source.getPassword(),
            source.getRole().equals(ADMIN.toString()) ?
                    ADMIN.getGrantedAuthorities() :
                    (source.getRole().equals(SUPER_USER.toString()) ? SUPER_USER.getGrantedAuthorities() :
                            SIMPLE_USER.getGrantedAuthorities()),
            source.isAccountNonExpired(),
            source.isAccountNonLocked(),
            source.isAccountCredentialsNonExpired(),
            source.isAccountEnabled());
  }

}
