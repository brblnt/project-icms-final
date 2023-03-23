package brblnt.icms.web.model.modules.worksheet.worksheet.response;

import java.util.ArrayList;

import brblnt.icms.data.modules.worksheet.model.FaultJPA;
import brblnt.icms.data.modules.worksheet.model.ServiceJPA;
import brblnt.icms.service.modules.worksheet.model.complete.*;

/**
 * Web layer model for worksheet.
 */
public record WorksheetResponse(
        Long id,
        String state,
        String engineerCode,
        CompleteCustomer customer,
        CompleteObject object,
        ArrayList<CompleteFault> faults,
        ArrayList<CompleteService> services,
        ArrayList<CompleteProduct> products,
        String createDate,
        String finishDate,
        String other) {
}
