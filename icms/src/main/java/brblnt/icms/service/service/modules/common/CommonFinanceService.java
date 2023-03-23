package brblnt.icms.service.service.modules.common;

import brblnt.icms.data.modules.common.model.additional.FinanceJPA;
import brblnt.icms.service.modules.common.model.complete.CompleteFinance;
import brblnt.icms.service.modules.common.repository.utility.additional.FinanceRepositoryUtility;
import brblnt.icms.web.model.modules.common.request.CompanyRequest;
import brblnt.icms.web.model.modules.worksheet.customer.request.CustomerRequest;
import brblnt.icms.web.model.modules.worksheet.dealer.request.DealerRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * Finance service.
 */
@Service
@RequiredArgsConstructor
public class CommonFinanceService {

  private final FinanceRepositoryUtility financeRepositoryUtility;
  private final ModelMapper modelMapper;

  /**
   * Save finance with by type of parent.
   */
  public void saveFinance(Object o, String type, int financeID) {
    int saveID = 0;
    CompleteFinance actualUserFinance = new CompleteFinance();
    if (type.equals("customer")) {
      CustomerRequest request = (CustomerRequest) o;
      if (financeID != 0) {
        actualUserFinance.setFinanceID(Integer.parseInt(request.getFinanceID()));
      }
      actualUserFinance.setBankAccountNumber(request.getBankAccountNumber());
      actualUserFinance.setBankAccountNumberInternational(request.getBankAccountNumberInternational());
      actualUserFinance.setEuNumberVAT(request.getEuNumberVAT());
      actualUserFinance.setNumberVAT(request.getNumberVAT());
    } else if (type.equals("company")) {
      CompanyRequest request = (CompanyRequest) o;
      if (financeID != 0) {
        actualUserFinance.setFinanceID(Integer.parseInt(request.getFinance()));
      }
      actualUserFinance.setBankAccountNumber(request.getBankAccountNumber());
      actualUserFinance.setBankAccountNumberInternational(request.getBankAccountNumberInternational());
      actualUserFinance.setEuNumberVAT(request.getEuNumberVAT());
      actualUserFinance.setNumberVAT(request.getNumberVAT());
    } else if (type.equals("dealer")) {
      DealerRequest request = (DealerRequest) o;
      if (financeID != 0) {
        actualUserFinance.setFinanceID(request.getFinanceID());
      }
      actualUserFinance.setBankAccountNumber(request.getBankAccountNumber());
      actualUserFinance.setBankAccountNumberInternational(request.getBankAccountNumberInternational());
      actualUserFinance.setEuNumberVAT(request.getEuNumberVAT());
      actualUserFinance.setNumberVAT(request.getNumberVAT());
    }

    financeRepositoryUtility.save(modelMapper.map(actualUserFinance, FinanceJPA.class));
  }

  /**
   * Get last finance id.
   */
  public int getLastSaveId() {
    return Math.toIntExact(financeRepositoryUtility.getAll().get(financeRepositoryUtility.getAll().size() - 1).getId());
  }
}
