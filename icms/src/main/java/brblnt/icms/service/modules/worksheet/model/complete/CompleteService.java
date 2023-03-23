package brblnt.icms.service.modules.worksheet.model.complete;

import lombok.*;

/**
 * ServiceObject service layer model.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CompleteService {

  private Long id;

  private String serviceName;

  private String customCode;

  private double priceN;

  private double priceB;

  private int vat;

}
