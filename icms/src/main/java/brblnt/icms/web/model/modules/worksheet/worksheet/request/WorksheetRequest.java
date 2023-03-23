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
public class WorksheetRequest {

  private String state;

  private String engineerCode;

  private String customer;

  private String object;

  private String faults;

  private String services;

  private String products;

  private String createDate;

  private String finishDate;

  private String other;
}
