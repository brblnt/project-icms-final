package brblnt.icms.web.converter.modules.common;

import brblnt.icms.service.modules.common.converter.creator.CompleteAddressCreator;
import brblnt.icms.service.modules.common.converter.creator.CompleteFinanceCreator;
import brblnt.icms.service.modules.common.model.complete.CompleteAddress;
import brblnt.icms.service.modules.common.model.complete.CompleteCompany;
import brblnt.icms.service.modules.common.model.complete.CompleteFinance;
import brblnt.icms.web.model.modules.common.request.CompanyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Convert Company Request to Complete Company.
 */
@Component
@RequiredArgsConstructor
public class ConvertCompanyRequestToCompleteCompany implements Converter<CompanyRequest, CompleteCompany> {

  private final CompleteAddressCreator completeAddressCreator;
  private final CompleteFinanceCreator completeFinanceCreator;

  @Override
  public CompleteCompany convert(CompanyRequest source) {
    CompleteAddress empty1 = completeAddressCreator.createEmpty();
    CompleteAddress empty2 = completeAddressCreator.createEmpty();
    CompleteAddress empty3 = completeAddressCreator.createEmpty();
    empty1.setAddressID(Long.parseLong(source.getAddress1()));
    empty2.setAddressID(Long.parseLong(source.getAddress2()));
    empty3.setAddressID(Long.parseLong(source.getAddress3()));
    CompleteFinance emptyFinance = completeFinanceCreator.createById(0L);
    emptyFinance.setFinanceID(Integer.parseInt(source.getFinance()));
    return new CompleteCompany(
            null,
            source.getCompanyName(),
            empty1,
            empty2,
            empty3,
            emptyFinance,
            source.getPhoneNumber(),
            source.getEmailAddress(),
            source.getOther(),
            ""
    );
  }
}
