package brblnt.icms.service.modules.worksheet.converter;

import brblnt.icms.data.modules.common.model.CompanyJPA;
import brblnt.icms.service.modules.common.model.complete.CompleteCompany;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Convert Complete Company to Company JPA.
 */
@Component
public class ConvertCompleteCompanyToCompanyJPA  implements Converter<CompleteCompany, CompanyJPA> {

  @Override
  public CompanyJPA convert(CompleteCompany source) {
    return new CompanyJPA(
            source.getId(),
            source.getCompanyName(),
            Math.toIntExact(source.getAddress1().getAddressID()),
            Math.toIntExact(source.getAddress2().getAddressID()),
            Math.toIntExact(source.getAddress3().getAddressID()),
            source.getFinance().getFinanceID(),
            source.getPhoneNumber(),
            source.getEmailAddress(),
            source.getOther(),
            source.getModificationDate());
  }
}
