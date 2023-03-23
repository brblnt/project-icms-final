package brblnt.icms.service.service.modules.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import brblnt.icms.data.modules.common.model.CompanyJPA;
import brblnt.icms.service.interfaces.ServiceInterface;
import brblnt.icms.service.modules.common.converter.creator.CompleteCompanyCreator;
import brblnt.icms.service.modules.common.model.complete.CompleteCompany;
import brblnt.icms.service.modules.common.repository.utility.CompanyRepositoryUtility;
import brblnt.icms.web.model.modules.common.request.CompanyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

/**
 * Company service.
 */
@Service
@RequiredArgsConstructor
public class CommonCompanyService implements ServiceInterface<CompleteCompany, CompanyRequest> {

  private final Converter<CompanyRequest, CompleteCompany> convertCompanyRequestToCompleteCompany;
  private final Converter<CompleteCompany, CompanyJPA> convertCompleteCompanyToCompanyJPA;
  private final CompanyRepositoryUtility companyRepositoryUtility;
  private final CompleteCompanyCreator completeCompanyCreator;
  private final CommonFinanceService commonFinanceService;

  @Override
  public List<CompleteCompany> getAll() {
    return null;
  }

  @Override
  public CompleteCompany getById(Long id) {
    return completeCompanyCreator.getAll().get(completeCompanyCreator.getAll().size() - 1);
  }

  @Override
  public boolean exist(Long id) {
    return false;
  }

  @Override
  public boolean save(CompanyRequest companyRequest, CompleteCompany o) {
    try {
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      Date date = new Date();
      CompanyJPA save = convertCompleteCompanyToCompanyJPA.convert(create(companyRequest));
      save.setId(getById(0L).getId());
      save.setModificationDate(formatter.format(date));
      commonFinanceService.saveFinance(companyRequest, "company", save.getFinanceID());
      save.setFinanceID(commonFinanceService.getLastSaveId());
      companyRepositoryUtility.save(save);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Clear address.
   */
  public boolean clearAddress(int position) {
    try {
      CompanyJPA save = convertCompleteCompanyToCompanyJPA.convert(getById(0L));
      if (position == 2) {
        save.setAddressID2(0);
      } else if (position == 3) {
        save.setAddressID3(0);
      }
      companyRepositoryUtility.save(save);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Update address id.
   */
  public boolean updateCompanyAddress(Long addressID, int position) {
    try {
      CompanyJPA save = convertCompleteCompanyToCompanyJPA.convert(getById(0L));
      if (position == 1) {
        save.setAddressID(Math.toIntExact(addressID));
        companyRepositoryUtility.save(save);
        return true;
      } else if (position == 2) {
        save.setAddressID2(Math.toIntExact(addressID));
        companyRepositoryUtility.save(save);
        return true;
      } else if (position == 3) {
        save.setAddressID3(Math.toIntExact(addressID));
        companyRepositoryUtility.save(save);
        return true;
      } else {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public boolean deleteById(Long id) {
    return false;
  }

  @Override
  public CompleteCompany create(CompanyRequest companyRequest) {
    return convertCompanyRequestToCompleteCompany.convert(companyRequest);
  }

  @Override
  public CompleteCompany createEmpty() {
    return null;
  }
}
