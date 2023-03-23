package brblnt.icms.service.modules.common.converter.creator;

import java.util.ArrayList;
import java.util.List;

import brblnt.icms.data.modules.common.model.additional.FinanceJPA;
import brblnt.icms.service.interfaces.CompleteCreator;
import brblnt.icms.service.modules.common.exceptions.NotFoundException;
import brblnt.icms.service.modules.common.model.complete.CompleteFinance;
import brblnt.icms.service.modules.common.repository.utility.additional.FinanceRepositoryUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * CompleteFinanceCreator.
 * Convert FinanceJpa to CompleteFinance.
 */
@Service
@RequiredArgsConstructor
public class CompleteFinanceCreator implements CompleteCreator<CompleteFinance> {

  private final FinanceRepositoryUtility financeRepositoryUtility;

  @Override
  public List<CompleteFinance> getAll() {
    List<CompleteFinance> list = new ArrayList<>();
    List<FinanceJPA> finances = financeRepositoryUtility.getAll();
    for (FinanceJPA finance : finances) {
      list.add(createById(finance.getId()));
    }
    return list;
  }

  @Override
  public CompleteFinance createById(Long financeID) {

    CompleteFinance cf = new CompleteFinance();

    FinanceJPA finance;

    if (financeID == 0) {
      cf.setFinanceID(0);
      cf.setBankAccountNumber("");
      cf.setBankAccountNumberInternational("");
      cf.setNumberVAT("");
      cf.setEuNumberVAT("");
      return cf;
    }

    try {
      finance = financeRepositoryUtility.getById(financeID);
    } catch (NotFoundException e) {
      throw new RuntimeException(e);
    }
    cf.setFinanceID(Math.toIntExact(finance.getId()));
    cf.setBankAccountNumber(finance.getBankAccountNumber());
    cf.setBankAccountNumberInternational(finance.getBankAccountNumberInternational());
    cf.setNumberVAT(finance.getNumberVAT());
    cf.setEuNumberVAT(finance.getEuNumberVAT());
    return cf;
  }
}
