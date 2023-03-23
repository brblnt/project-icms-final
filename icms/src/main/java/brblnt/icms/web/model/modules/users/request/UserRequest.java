package brblnt.icms.web.model.modules.users.request;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * User web layer representation.
 */
@Builder
@EqualsAndHashCode
@Getter
@ToString
public class UserRequest {

  private String firstName;
  private String lastName;
  private String userName;
  private String emailAddress;
  private String password;
  private String role;
  private boolean passwordChanged;
  private String registrationDate;
  private boolean accountNonExpired;
  private boolean accountNonLocked;
  private boolean accountCredentialsNonExpired;
  private boolean accountEnabled;

}