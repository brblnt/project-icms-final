package brblnt.icms.web.model.modules.admin;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Database update request.
 */
@Builder
@EqualsAndHashCode
@Getter
@ToString
public class AdminRequest {

  private String url;
  private String userName;
  private String password;
}
