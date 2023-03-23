package brblnt.icms.service.modules.common.model.complete;

import lombok.*;

/**
 * Service layer data model for Finance.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CompleteFinance {

  private int financeID;

  private String bankAccountNumber;

  private String bankAccountNumberInternational;

  private String numberVAT;

  private String euNumberVAT;

}
