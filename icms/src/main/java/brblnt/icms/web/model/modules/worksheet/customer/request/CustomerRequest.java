package brblnt.icms.web.model.modules.worksheet.customer.request;

import lombok.*;

/**
 * Web layer model for customer.
 */
@Builder
@EqualsAndHashCode
@Setter
@Getter
@ToString
public class CustomerRequest {

  private String customerName;

  private String address1;

  private String address2;

  private String address3;

  private String financeID;

  private String bankAccountNumber;

  private String bankAccountNumberInternational;

  private String numberVAT;

  private String euNumberVAT;

  private String phoneNumber;

  private String emailAddress;

  private String other;

  private String registrationDate;
}
