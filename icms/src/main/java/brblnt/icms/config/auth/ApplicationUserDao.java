package brblnt.icms.config.auth;

import java.util.Optional;


/**
 * Application User Dao representation.
 */
public interface ApplicationUserDao {
  Optional<ApplicationUser> selectApplicationUserByUsername(String username);
}
