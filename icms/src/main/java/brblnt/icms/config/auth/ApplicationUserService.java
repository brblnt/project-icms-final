package brblnt.icms.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * ApplicationUserService implement @{UserDetailsService}.
 * Set up the repository for users.
 */
@Service
public class ApplicationUserService implements UserDetailsService {

  private final String DEVELOPMENT_REPOSITORY = "development";
  private final String MAIN_REPOSITORY = "main";
  private final ApplicationUserDao applicationUserDao;

  @Autowired
  public ApplicationUserService(@Qualifier(MAIN_REPOSITORY) ApplicationUserDao applicationUserDao) {
    this.applicationUserDao = applicationUserDao;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return applicationUserDao
            .selectApplicationUserByUsername(username)
            .orElseThrow(() ->
                    new UsernameNotFoundException(String.format("No user with this username: %s", username))
            );
  }
}