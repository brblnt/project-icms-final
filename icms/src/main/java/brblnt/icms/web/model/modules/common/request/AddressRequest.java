package brblnt.icms.web.model.modules.common.request;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Web layer model for address.
 */
@Builder
@EqualsAndHashCode
@Getter
@ToString
public class AddressRequest {

  private String postalCode;

  private String cityName;

  private String address;

  private String other;

}
