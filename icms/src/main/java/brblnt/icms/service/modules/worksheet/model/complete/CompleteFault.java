package brblnt.icms.service.modules.worksheet.model.complete;

import lombok.*;

/**
 * Fault service layer model.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CompleteFault {

  private Long id;

  private String faultName;

  private String faultOther;
}
