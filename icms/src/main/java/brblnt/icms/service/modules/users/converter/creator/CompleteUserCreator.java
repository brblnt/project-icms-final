package brblnt.icms.service.modules.users.converter.creator;

import java.util.ArrayList;
import java.util.List;

import brblnt.icms.data.modules.users.model.ApplicationUserJPA;
import brblnt.icms.service.exceptions.UserNotFoundException;
import brblnt.icms.service.interfaces.CompleteCreator;
import brblnt.icms.service.modules.users.model.complete.CompleteUser;
import brblnt.icms.service.modules.users.repository.utility.ApplicationUsersRepositoryUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * CompleteUserCreator.
 * Creator service layer model for User.
 */
@Service
@RequiredArgsConstructor
public class CompleteUserCreator implements CompleteCreator<CompleteUser> {

  private final ApplicationUsersRepositoryUtility applicationUsersRepositoryUtility;

  @Override
  public List<CompleteUser> getAll() {
    List<CompleteUser> list = new ArrayList<>();
    List<ApplicationUserJPA> users = applicationUsersRepositoryUtility.getAll();
    for (ApplicationUserJPA user : users) {
      list.add(createById(user.getId()));
    }
    return list;
  }

  @Override
  public CompleteUser createById(Long userID) {
    CompleteUser cu = new CompleteUser();

    ApplicationUserJPA tempUser;
    try {
      tempUser = applicationUsersRepositoryUtility.getById(userID);
    } catch (UserNotFoundException e) {
      throw new RuntimeException(e);
    }

    cu.setId(tempUser.getId());
    cu.setFirstName(tempUser.getFirstName());
    cu.setLastName(tempUser.getLastName());
    cu.setUserName(tempUser.getUserName());
    cu.setEmailAddress(tempUser.getEmailAddress());
    cu.setPassword(tempUser.getPassword());
    cu.setRole(tempUser.getRole());
    cu.setPasswordChanged(tempUser.isPasswordChanged());
    cu.setRegistrationDate(tempUser.getRegistrationDate());
    cu.setAccountEnabled(tempUser.isAccountEnabled());
    cu.setAccountCredentialsNonExpired(tempUser.isAccountCredentialsNonExpired());
    cu.setAccountNonLocked(tempUser.isAccountNonLocked());
    cu.setAccountNonExpired(tempUser.isAccountNonExpired());

    return cu;
  }
}
