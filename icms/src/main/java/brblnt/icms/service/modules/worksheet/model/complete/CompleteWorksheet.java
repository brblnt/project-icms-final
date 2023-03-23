package brblnt.icms.service.modules.worksheet.model.complete;

import java.util.ArrayList;

import brblnt.icms.data.modules.worksheet.model.FaultJPA;
import brblnt.icms.data.modules.worksheet.model.ServiceJPA;
import lombok.*;

/**
 * Worksheet service layer model.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CompleteWorksheet {

  private Long id;

  private String state;

  private String engineerCode;

  private CompleteCustomer customer;

  private CompleteObject object;

  private ArrayList<CompleteFault> faults;

  private ArrayList<CompleteService> services;

  private ArrayList<CompleteProduct> products;

  private String createDate;

  private String finishDate;

  private String other;

}
