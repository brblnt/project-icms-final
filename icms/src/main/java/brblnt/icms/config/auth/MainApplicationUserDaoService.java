package brblnt.icms.config.auth;

import java.util.List;
import java.util.Optional;

import brblnt.icms.data.modules.users.ApplicationUserRepository;
import brblnt.icms.data.modules.users.model.ApplicationUserJPA;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;


/**
 * Main repository used in @{ApplicationUserService}.
 */
@Repository("main")
@RequiredArgsConstructor
public class MainApplicationUserDaoService implements ApplicationUserDao {

  private final PasswordEncoder passwordEncoder;
  private final ApplicationUserRepository repository;
  private final Converter<ApplicationUserJPA, ApplicationUser> convertApplicationUserJPAToApplicationUser;

  @Override
  public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
    return getApplicationUsers()
            .stream()
            .filter(applicationUser -> username.equals(applicationUser.getUsername()))
            .findFirst();
  }

  private List<ApplicationUser> getApplicationUsers() {
    return Lists.newArrayList(
            repository.findAll().stream().map(convertApplicationUserJPAToApplicationUser::convert).toList()
    );
  }

}
  

