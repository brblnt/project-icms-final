package brblnt.icms.web.model.modules.worksheet.fault.request;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Web layer model for fault.
 */
@Builder
@EqualsAndHashCode
@Getter
@ToString
public class FaultRequest {

  private String faultName;

  private String faultOther;
}
