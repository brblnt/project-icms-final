package brblnt.icms.web.converter.modules.common;

import brblnt.icms.service.modules.common.model.complete.CompleteCompany;
import brblnt.icms.web.model.modules.common.response.CompanyResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter Complete Company to Company Response.
 */
@Component
public class ConvertCompleteCompanyToCompanyResponse implements Converter<CompleteCompany, CompanyResponse> {

  @Override
  public CompanyResponse convert(CompleteCompany source) {
    return new CompanyResponse(
            source.getId(),
            source.getCompanyName(),
            source.getAddress1(),
            source.getAddress2(),
            source.getAddress3(),
            source.getFinance(),
            source.getPhoneNumber(),
            source.getEmailAddress(),
            source.getOther(),
            source.getModificationDate()
    );
  }
}
