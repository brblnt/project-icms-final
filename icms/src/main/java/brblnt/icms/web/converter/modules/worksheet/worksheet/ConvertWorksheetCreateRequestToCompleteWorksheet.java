package brblnt.icms.web.converter.modules.worksheet.worksheet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import brblnt.icms.service.modules.worksheet.converter.creator.CompleteCustomerCreator;
import brblnt.icms.service.modules.worksheet.converter.creator.CompleteObjectCreator;
import brblnt.icms.service.modules.worksheet.converter.creator.CompleteWorksheetCreator;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteWorksheet;
import brblnt.icms.web.model.modules.worksheet.worksheet.request.WorksheetCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter WorksheetRequest to CompleteWorksheet.
 */
@Component
@RequiredArgsConstructor
public class ConvertWorksheetCreateRequestToCompleteWorksheet  implements Converter<WorksheetCreateRequest, CompleteWorksheet> {

  private final CompleteCustomerCreator completeCustomerCreator;
  private final CompleteObjectCreator completeObjectCreator;
  private final CompleteWorksheetCreator completeWorksheetCreator;

  @Override
  public CompleteWorksheet convert(WorksheetCreateRequest source) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    ArrayList<Integer> faults = new ArrayList<>();
    String[] arrayFault = source.getFaults().split(",");
    for (int i = 0; i < arrayFault.length; i++) {
      if (Integer.parseInt(arrayFault[i]) != 0) {
        faults.add(Integer.parseInt(arrayFault[i]));
      }
    }
    return new CompleteWorksheet(
            null,
            source.getState(),
            "",
            completeCustomerCreator.createById((long) source.getCustomer()),
            completeObjectCreator.createById((long) source.getObject()),
            completeWorksheetCreator.getAllFaultOnWorksheet(faults),
            null,
            null,
            formatter.format(date),
            "",
            source.getOther()
    );
  }
}
