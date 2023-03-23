package brblnt.icms.web.model.modules.worksheet.object.request;

import lombok.*;

/**
 * Web layer model for object.
 */
@Builder
@EqualsAndHashCode
@Getter
@ToString
public class ObjectRequest {

  private int customerID;

  private String itemName;

  private String itemBrand;

  private String itemType;

  private String itemSerial;

  private String other;
}
