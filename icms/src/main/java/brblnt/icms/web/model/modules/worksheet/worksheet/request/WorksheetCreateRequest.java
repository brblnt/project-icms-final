package brblnt.icms.web.model.modules.worksheet.worksheet.request;

import java.util.ArrayList;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Web layer model for worksheet.
 */
@Builder
@EqualsAndHashCode
@Getter
@ToString
public class WorksheetCreateRequest {

  private String state;

  private int customer;

  private int object;

  private String faults;

  private String other;
}
