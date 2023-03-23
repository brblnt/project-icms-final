package brblnt.icms.service.modules.common.model.complete;

import lombok.*;

/**
 * Service layer data model for Company.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CompleteCompany {

  private Long id;

  private String companyName;

  private CompleteAddress address1;

  private CompleteAddress address2;

  private CompleteAddress address3;

  private CompleteFinance finance;

  private String phoneNumber;

  private String emailAddress;

  private String other;

  private String modificationDate;

}
