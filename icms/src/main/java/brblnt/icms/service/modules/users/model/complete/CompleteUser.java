package brblnt.icms.service.modules.users.model.complete;

import lombok.*;

/**
 * User service layer model.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CompleteUser {

  private Long id;

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
