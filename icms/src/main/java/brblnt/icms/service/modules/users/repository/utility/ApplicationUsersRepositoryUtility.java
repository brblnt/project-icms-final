package brblnt.icms.service.modules.users.repository.utility;

import java.util.List;
import java.util.Objects;

import brblnt.icms.data.modules.users.ApplicationUserRepository;
import brblnt.icms.data.modules.users.model.ApplicationUserJPA;
import brblnt.icms.service.exceptions.UserNotFoundException;
import brblnt.icms.service.interfaces.UtilityInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * UserRepositoryUtility contains database manipulation methods.
 */
@Service
@RequiredArgsConstructor
public class ApplicationUsersRepositoryUtility implements UtilityInterface<ApplicationUserJPA, UserNotFoundException> {

  private final ApplicationUserRepository applicationUserRepository;

  @Override
  public List<ApplicationUserJPA> getAll() {
    return applicationUserRepository.findAll().stream().toList();
  }

  @Override
  public ApplicationUserJPA getById(Long id) throws UserNotFoundException {
    for (ApplicationUserJPA user : getAll()) {
      if (Objects.equals(user.getId(), id)) {
        return user;
      }
    }
    throw new UserNotFoundException("No user with this id " + id);
  }

  /**
   * Not required.
   */
  @Override
  public ApplicationUserJPA getById(String id) throws UserNotFoundException {
    return null;
  }

  /**
   * Get application user by username.
   */
  public ApplicationUserJPA getUserByUsername(String username) throws UserNotFoundException {
    for (ApplicationUserJPA user : getAll()) {
      if (user.getUserName().equals(username)) {
        return user;
      }
    }
    throw new UserNotFoundException("No user with this username " + username);
  }

  /**
   * Get user password validity.
   */
  public boolean passwordExpired(String username) throws UserNotFoundException {
    try {
      return !getUserByUsername(username).isPasswordChanged();
    } catch (UserNotFoundException e) {
      throw new UserNotFoundException("No user with this username " + username);
    }
  }

  @Override
  public void save(ApplicationUserJPA user) {
    applicationUserRepository.save(user);
  }

  @Override
  public void delete(Long id) {
    applicationUserRepository.deleteById(id);
  }

  /**
   * Update user password.
   */
  public void updatePassword(ApplicationUserJPA user, String password) {
    user.setPassword(password);
    user.setPasswordChanged(true);
    save(user);
  }


}
