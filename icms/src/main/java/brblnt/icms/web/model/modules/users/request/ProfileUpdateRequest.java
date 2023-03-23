package brblnt.icms.web.model.modules.users.request;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Profile update request.
 */
@Builder
@EqualsAndHashCode
@Getter
@ToString
public class ProfileUpdateRequest {

  private String firstName;
  private String lastName;
  private String emailAddress;
  private String password;
  private String rePassword;
}
