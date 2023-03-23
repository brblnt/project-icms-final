package brblnt.icms.config.auth;

import static brblnt.icms.config.security.ApplicationUserRole.*;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;


/**
 * Development repository for test first logins.
 */
@Repository("development")
@RequiredArgsConstructor
public class TestApplicationUserDaoService implements ApplicationUserDao {

  private final PasswordEncoder passwordEncoder;


  @Override
  public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
    return getApplicationUsers()
            .stream()
            .filter(applicationUser -> username.equals(applicationUser.getUsername()))
            .findFirst();
  }

  private List<ApplicationUser> getApplicationUsers() {
    List<ApplicationUser> applicationUsers = Lists.newArrayList(
            new ApplicationUser(
                    "admin",
                    passwordEncoder.encode("admin"),
                    ADMIN.getGrantedAuthorities(),
                    true,
                    true,
                    true,
                    true
            ),
            new ApplicationUser(
                    "superuser",
                    passwordEncoder.encode("superuser"),
                    SUPER_USER.getGrantedAuthorities(),
                    true,
                    true,
                    true,
                    true
            ),
            new ApplicationUser(
                    "simpleuser",
                    passwordEncoder.encode("simpleuser"),
                    SIMPLE_USER.getGrantedAuthorities(),
                    true,
                    true,
                    true,
                    true
            )
    );
    return applicationUsers;
  }

}
