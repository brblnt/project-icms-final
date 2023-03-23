package brblnt.icms.service.modules.worksheet.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import brblnt.icms.data.modules.worksheet.model.WorksheetJPA;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteFault;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteProduct;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteService;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteWorksheet;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Convert Complete Worksheet to Worksheet JPA.
 */
@Component
public class ConvertCompleteWorksheetToWorksheetJPA implements Converter<CompleteWorksheet, WorksheetJPA> {

  @Override
  public WorksheetJPA convert(CompleteWorksheet source) {
    StringBuilder faults = new StringBuilder();
    StringBuilder services = new StringBuilder();
    StringBuilder products = new StringBuilder();
    if (source.getProducts() != null) {
      for (CompleteProduct temp : source.getProducts()) {
        products.append(temp.getId()).append(";");
      }
    }
    if (source.getFaults() != null) {
      for (CompleteFault temp : source.getFaults()) {
        faults.append(temp.getId()).append(";");
      }
    }
    if (source.getServices() != null) {
      for (CompleteService temp : source.getServices()) {
        services.append(temp.getId()).append(";");
      }
    }
    return new WorksheetJPA(
            source.getId(),
            source.getState(),
            source.getEngineerCode(),
            Math.toIntExact(source.getCustomer().getId()),
            Math.toIntExact(source.getObject().getId()),
            faults.toString(),
            services.toString(),
            products.toString(),
            source.getCreateDate(),
            source.getFinishDate(),
            source.getOther()
    );
  }
}
