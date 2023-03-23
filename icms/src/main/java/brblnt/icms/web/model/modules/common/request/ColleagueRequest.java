package brblnt.icms.web.model.modules.common.request;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Web layer model for colleague.
 */
@Builder
@EqualsAndHashCode
@Getter
@ToString
public class ColleagueRequest {

  private String customCode;

  private String name;

  private String phoneNumber;

  private String emailAddress;

  private String position;

  private String qualifications;

  private String workingArea;

  private String other;

  private String registrationDate;
}
