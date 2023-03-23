package brblnt.icms.service.modules.common.converter.creator;

import java.util.ArrayList;
import java.util.List;

import brblnt.icms.data.modules.common.model.CompanyJPA;
import brblnt.icms.service.interfaces.CompleteCreator;
import brblnt.icms.service.modules.common.exceptions.CompanyNotFoundException;
import brblnt.icms.service.modules.common.model.complete.CompleteCompany;
import brblnt.icms.service.modules.common.repository.utility.CompanyRepositoryUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * CompleteCompanyCreator.
 * Convert CompanyJpa to CompleteCompany.
 */
@Service
@RequiredArgsConstructor
public class CompleteCompanyCreator implements CompleteCreator<CompleteCompany> {

  private final CompleteAddressCreator completeAddressCreator;
  private final CompleteFinanceCreator completeFinanceCreator;
  private final CompanyRepositoryUtility companyRepositoryUtility;

  @Override
  public List<CompleteCompany> getAll() {
    List<CompleteCompany> list = new ArrayList<>();
    List<CompanyJPA> companies = companyRepositoryUtility.getAll();
    for (CompanyJPA company : companies) {
      list.add(createById(company.getId()));
    }
    return list;
  }

  @Override
  public CompleteCompany createById(Long companyID) {
    CompleteCompany cc = new CompleteCompany();

    CompanyJPA tempCompany;
    try {
      tempCompany = companyRepositoryUtility.getById(companyID);
    } catch (CompanyNotFoundException e) {
      throw new RuntimeException(e);
    }

    cc.setId(tempCompany.getId());
    cc.setCompanyName(tempCompany.getCompanyName());

    cc.setAddress1(completeAddressCreator.createById((long) tempCompany.getAddressID()));
    cc.setAddress2(completeAddressCreator.createById((long) tempCompany.getAddressID2()));
    cc.setAddress3(completeAddressCreator.createById((long) tempCompany.getAddressID3()));

    cc.setFinance(completeFinanceCreator.createById((long) tempCompany.getFinanceID()));

    cc.setPhoneNumber(tempCompany.getPhoneNumber());
    cc.setEmailAddress(tempCompany.getEmailAddress());
    cc.setOther(tempCompany.getOther());
    cc.setModificationDate(tempCompany.getModificationDate());

    return cc;
  }
}
