package brblnt.icms.service.modules.worksheet.model.complete;

import brblnt.icms.service.modules.common.model.complete.CompleteAddress;
import brblnt.icms.service.modules.common.model.complete.CompleteFinance;
import lombok.*;

/**
 * Dealer service layer model.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CompleteDealer {

  private Long id;

  private String dealerName;

  private CompleteAddress address1;

  private CompleteAddress address2;

  private CompleteAddress address3;

  private CompleteFinance finance;

  private String phoneNumber;

  private String emailAddress;

  private String other;
}
