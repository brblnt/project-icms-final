package brblnt.icms.service.modules.common.model.complete;

import lombok.*;

/**
 * Service layer data model for address.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CompleteAddress {

  private Long addressID;

  private String postalCode;

  private String cityName;

  private String address;

  private String other;
}
