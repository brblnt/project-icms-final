package brblnt.icms.service.modules.common.model.complete;

import lombok.*;

/**
 * Complete Colleague service layer model.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CompleteColleague {

  private Long id;

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
