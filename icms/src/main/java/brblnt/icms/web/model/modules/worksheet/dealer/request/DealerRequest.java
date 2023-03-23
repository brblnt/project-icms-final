package brblnt.icms.web.model.modules.worksheet.dealer.request;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Web layer model for dealer.
 */
@Builder
@EqualsAndHashCode
@Getter
@ToString
public class DealerRequest {

  private String dealerName;

  private int address1;

  private int address2;

  private int address3;

  private int financeID;

  private String bankAccountNumber;

  private String bankAccountNumberInternational;

  private String numberVAT;

  private String euNumberVAT;

  private String phoneNumber;

  private String emailAddress;

  private String other;

}
