package brblnt.icms.web.model.modules.common.request;

import brblnt.icms.service.modules.common.model.complete.CompleteAddress;
import brblnt.icms.service.modules.common.model.complete.CompleteFinance;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Web layer model for company.
 */
@Builder
@EqualsAndHashCode
@Getter
@ToString
public class CompanyRequest {

  private String companyName;

  private String address1;

  private String address2;

  private String address3;

  private String finance;

  private String bankAccountNumber;

  private String bankAccountNumberInternational;

  private String numberVAT;

  private String euNumberVAT;

  private String phoneNumber;

  private String emailAddress;

  private String other;

}
