package brblnt.icms.web.model.modules.worksheet.services.request;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Web layer model for service.
 */
@Builder
@EqualsAndHashCode
@Getter
@ToString
public class ServiceRequest {

  private String serviceName;

  private String customCode;

  private double priceN;

  private double priceB;

  private int vat;

}
