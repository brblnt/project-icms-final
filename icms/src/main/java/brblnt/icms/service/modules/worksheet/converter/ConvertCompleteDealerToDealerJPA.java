package brblnt.icms.service.modules.worksheet.converter;

import brblnt.icms.data.modules.common.model.additional.DealerJPA;
import brblnt.icms.data.modules.worksheet.model.CustomerJPA;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteCustomer;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteDealer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter.
 * Convert CompleteCustomer tor CustomerJPA.
 */
@Component
public class ConvertCompleteDealerToDealerJPA implements Converter<CompleteDealer, DealerJPA> {

  @Override
  public DealerJPA convert(CompleteDealer source) {
    return new DealerJPA(
            source.getId(),
            source.getDealerName(),
            Math.toIntExact(source.getAddress1().getAddressID()),
            Math.toIntExact(source.getAddress2().getAddressID()),
            Math.toIntExact(source.getAddress3().getAddressID()),
            source.getFinance().getFinanceID(),
            source.getPhoneNumber(),
            source.getEmailAddress(),
            source.getOther()
    );
  }
}
