package brblnt.icms.service.modules.worksheet.converter.creator;

import java.util.ArrayList;
import java.util.List;

import brblnt.icms.data.modules.common.model.additional.DealerJPA;
import brblnt.icms.service.interfaces.CompleteCreator;
import brblnt.icms.service.modules.common.converter.creator.CompleteAddressCreator;
import brblnt.icms.service.modules.common.converter.creator.CompleteFinanceCreator;
import brblnt.icms.service.modules.worksheet.exceptions.DealerNotFoundException;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteDealer;
import brblnt.icms.service.modules.worksheet.repository.utility.DealerRepositoryUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Create Complete Dealer.
 * Convert JPA to CompleteObject.
 */
@Service
@RequiredArgsConstructor
public class CompleteDealerCreator implements CompleteCreator<CompleteDealer> {

  private final CompleteAddressCreator completeAddressCreator;
  private final CompleteFinanceCreator completeFinanceCreator;
  private final DealerRepositoryUtility dealerRepositoryUtility;

  @Override
  public List<CompleteDealer> getAll() {
    List<CompleteDealer> list = new ArrayList<>();
    List<DealerJPA> dealers = dealerRepositoryUtility.getAll();
    for (DealerJPA dealer : dealers) {
      list.add(createById(dealer.getId()));
    }
    return list;
  }

  @Override
  public CompleteDealer createById(Long dealerID) {
    CompleteDealer cd = new CompleteDealer();

    if (dealerID == 0) {
      return  createEmpty();
    }

    DealerJPA tempDealer;
    try {
      tempDealer = dealerRepositoryUtility.getById(dealerID);
    } catch (DealerNotFoundException e) {
      throw new RuntimeException(e);
    }

    cd.setId(tempDealer.getId());
    cd.setDealerName(tempDealer.getDealerName());

    cd.setAddress1(completeAddressCreator.createById((long) tempDealer.getAddressID()));
    cd.setAddress2(completeAddressCreator.createById((long) tempDealer.getAddressID2()));
    cd.setAddress3(completeAddressCreator.createById((long) tempDealer.getAddressID3()));

    cd.setFinance(completeFinanceCreator.createById((long) tempDealer.getFinanceID()));

    cd.setPhoneNumber(tempDealer.getPhoneNumber());
    cd.setEmailAddress(tempDealer.getEmailAddress());
    cd.setOther(tempDealer.getOther());

    return cd;
  }

  /**
   * Create empty dealer.
   */
  public CompleteDealer createEmpty() {
    CompleteDealer cd = new CompleteDealer();
    cd.setId(0L);
    cd.setDealerName("");
    cd.setAddress1(completeAddressCreator.createById(0L));
    cd.setAddress2(completeAddressCreator.createById(0L));
    cd.setAddress3(completeAddressCreator.createById(0L));
    cd.setFinance(completeFinanceCreator.createById(0L));
    cd.setPhoneNumber("");
    cd.setEmailAddress("");
    cd.setOther("");
    return cd;
  }
}
