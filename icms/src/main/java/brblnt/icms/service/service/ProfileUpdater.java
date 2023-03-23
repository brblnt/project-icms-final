package brblnt.icms.service.service;

import brblnt.icms.config.security.PasswordGenerator;
import brblnt.icms.service.modules.users.converter.ConvertCompleteUserToUserJPA;
import brblnt.icms.service.modules.users.model.complete.CompleteUser;
import brblnt.icms.service.modules.users.repository.utility.ApplicationUsersRepositoryUtility;
import brblnt.icms.web.model.modules.users.request.ProfileUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Profile Update Service. Update profile data by user.
 */
@RequiredArgsConstructor
@Service
public class ProfileUpdater {

  private final PasswordGenerator passwordGenerator;
  private final ApplicationUsersRepositoryUtility applicationUsersRepositoryUtility;
  private final ConvertCompleteUserToUserJPA convertCompleteUserToUserJPA;


  /**
   * Update user data.
   */
  public boolean saveUserWithNewData(CompleteUser user, ProfileUpdateRequest profileUpdateRequest) {
    boolean success = false;

    if (!profileUpdateRequest.getPassword().equals("")) {
      if (profileUpdateRequest.getPassword().equals(profileUpdateRequest.getRePassword())) {
        user.setPassword(passwordGenerator.encode(profileUpdateRequest.getPassword()));
        user.setFirstName(profileUpdateRequest.getFirstName());
        user.setLastName(profileUpdateRequest.getLastName());
        user.setEmailAddress(profileUpdateRequest.getEmailAddress());
        success = true;
        applicationUsersRepositoryUtility.save(convertCompleteUserToUserJPA.convert(user));
      }
    } else {
      user.setFirstName(profileUpdateRequest.getFirstName());
      user.setLastName(profileUpdateRequest.getLastName());
      user.setEmailAddress(profileUpdateRequest.getEmailAddress());
      success = true;
      applicationUsersRepositoryUtility.save(convertCompleteUserToUserJPA.convert(user));
    }

    return success;
  }


}
