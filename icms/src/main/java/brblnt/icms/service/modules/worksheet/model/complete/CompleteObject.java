package brblnt.icms.service.modules.worksheet.model.complete;

import lombok.*;

/**
 * Object service layer model.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CompleteObject {

  private Long id;

  private CompleteCustomer customer;

  private String itemName;

  private String itemBrand;

  private String itemType;

  private String itemSerial;

  private String other;

}
