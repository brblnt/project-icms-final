package brblnt.icms.web.converter.modules.worksheet.worksheet;

import java.util.ArrayList;

import brblnt.icms.service.modules.worksheet.converter.creator.CompleteCustomerCreator;
import brblnt.icms.service.modules.worksheet.converter.creator.CompleteObjectCreator;
import brblnt.icms.service.modules.worksheet.converter.creator.CompleteWorksheetCreator;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteWorksheet;
import brblnt.icms.web.model.modules.worksheet.worksheet.request.WorksheetRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter.
 * Convert Worksheet Request to Complete Worksheet.
 */
@Component
@RequiredArgsConstructor
public class ConvertWorksheetRequestToCompleteWorksheet implements Converter<WorksheetRequest, CompleteWorksheet> {

  private final CompleteCustomerCreator completeCustomerCreator;
  private final CompleteObjectCreator completeObjectCreator;
  private final CompleteWorksheetCreator completeWorksheetCreator;

  @Override
  public CompleteWorksheet convert(@NonNull final WorksheetRequest source) {

    ArrayList<Integer> faults = new ArrayList<>();
    String[] arrayFault = source.getFaults().split(";");
    for (int i = 0; i < arrayFault.length; i++) {
      if (Integer.parseInt(arrayFault[i]) != 0) {
        faults.add(Integer.parseInt(arrayFault[i]));
      }
    }

    ArrayList<Integer> services = new ArrayList<>();
    String[] arrayService = source.getServices().split(";");
    for (int i = 0; i < arrayService.length; i++) {
      if (Integer.parseInt(arrayService[i]) != 0) {
        services.add(Integer.parseInt(arrayService[i]));
      }
    }

    ArrayList<Integer> products = new ArrayList<>();
    String[] arrayProduct = source.getProducts().split(";");
    for (int i = 0; i < arrayProduct.length; i++) {
      if (Integer.parseInt(arrayProduct[i]) != 0) {
        products.add(Integer.parseInt(arrayProduct[i]));
      }
    }

    return new CompleteWorksheet(
            null,
            source.getState(),
            source.getEngineerCode(),
            completeCustomerCreator.createById(Long.parseLong(source.getCustomer())),
            completeObjectCreator.createById(Long.parseLong(source.getObject())),
            completeWorksheetCreator.getAllFaultOnWorksheet(faults),
            completeWorksheetCreator.getAllServiceOnWorksheet(services),
            completeWorksheetCreator.getAllProductOnWorksheet(products),
            source.getCreateDate(),
            source.getFinishDate(),
            source.getOther()

    );
  }
}
