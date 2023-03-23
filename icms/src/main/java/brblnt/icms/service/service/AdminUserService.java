package brblnt.icms.service.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import brblnt.icms.config.security.PasswordGenerator;
import brblnt.icms.data.modules.users.model.ApplicationUserJPA;
import brblnt.icms.service.exceptions.UserNotFoundException;
import brblnt.icms.service.interfaces.ServiceInterface;
import brblnt.icms.service.modules.users.converter.creator.CompleteUserCreator;
import brblnt.icms.service.modules.users.model.complete.CompleteUser;
import brblnt.icms.service.modules.users.repository.utility.ApplicationUsersRepositoryUtility;
import brblnt.icms.web.model.modules.users.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 * Administrator User Service.
 * Contains service method for user management.
 */
@Service
@RequiredArgsConstructor
public class AdminUserService implements ServiceInterface<CompleteUser, UserRequest> {


  private final Converter<UserRequest, CompleteUser> userRequestCompleteUserConverter;
  private final Converter<CompleteUser, ApplicationUserJPA> convertCompleteUserToUserJPA;
  private final PasswordGenerator passwordGenerator;
  private final CompleteUserCreator completeUserCreator;
  private final ApplicationUsersRepositoryUtility applicationUsersRepositoryUtility;


  @Override
  public List<CompleteUser> getAll() {
    return completeUserCreator.getAll();
  }

  @Override
  public CompleteUser getById(Long id) {
    return completeUserCreator.createById(id);
  }

  @Override
  public boolean exist(Long id) {
    try {
      ApplicationUserJPA user = applicationUsersRepositoryUtility.getById(id);
      return true;
    } catch (UserNotFoundException e) {
      return false;
    }
  }

  /**
   * Get username is available.
   */
  public boolean exist(String username) {
    for (CompleteUser completeUser : getAll()) {
      if (completeUser.getUserName().equals(username)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean save(UserRequest request, CompleteUser completeUser) {
    try {
      CompleteUser newUser = userRequestCompleteUserConverter.convert(request);
      if (newUser.getRole() == null) {
        newUser.setRole("SIMPLE_USER");
      }
      newUser.setId(completeUser.getId());
      newUser.setPassword(completeUser.getPassword());
      newUser.setRegistrationDate(completeUser.getRegistrationDate());
      applicationUsersRepositoryUtility.save(convertCompleteUserToUserJPA.convert(newUser));
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public boolean deleteById(Long id) {
    try {
      applicationUsersRepositoryUtility.delete(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public CompleteUser create(UserRequest request) {
    CompleteUser user = userRequestCompleteUserConverter.convert(request);
    String[] password = passwordGenerator.generatePassword();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    user.setRegistrationDate(formatter.format(date));
    user.setPassword(password[1]);
    if (!isNameOrEmailEmpty(request)) {
      applicationUsersRepositoryUtility.save(convertCompleteUserToUserJPA.convert(user));
    }
    user.setUserName(user.getUserName() + ";" + password[0]);
    return user;
  }

  @Override
  public CompleteUser createEmpty() {
    return null;
  }


  /**
   * Check user request field is empty or not.
   */
  public boolean isNameOrEmailEmpty(UserRequest request) {
    CompleteUser user = userRequestCompleteUserConverter.convert(request);
    if (!user.getUserName().equals("") && !user.getEmailAddress().equals("")) {
      return false;
    } else {
      return true;
    }
  }

}
