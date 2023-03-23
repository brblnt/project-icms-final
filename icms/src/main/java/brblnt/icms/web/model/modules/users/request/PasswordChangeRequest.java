package brblnt.icms.web.model.modules.users.request;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * PasswordChange request.
 */
@Builder
@EqualsAndHashCode
@Getter
@ToString
public class PasswordChangeRequest {
  private String password;
  private String passwordRe;
}
