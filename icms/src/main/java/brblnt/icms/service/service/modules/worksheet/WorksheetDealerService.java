package brblnt.icms.service.service.modules.worksheet;

import java.util.List;

import brblnt.icms.data.modules.common.model.additional.DealerJPA;
import brblnt.icms.service.interfaces.ServiceInterface;
import brblnt.icms.service.modules.common.converter.creator.CompleteAddressCreator;
import brblnt.icms.service.modules.worksheet.converter.creator.CompleteDealerCreator;
import brblnt.icms.service.modules.worksheet.model.complete.CompleteDealer;
import brblnt.icms.service.modules.worksheet.repository.utility.DealerRepositoryUtility;
import brblnt.icms.service.service.modules.common.CommonFinanceService;
import brblnt.icms.web.model.modules.worksheet.dealer.request.DealerRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

/**
 * Dealer service.
 */
@RequiredArgsConstructor
@Service
public class WorksheetDealerService implements ServiceInterface<CompleteDealer, DealerRequest> {

  private final ModelMapper modelMapper;
  private final CompleteDealerCreator completeDealerCreator;
  private final DealerRepositoryUtility dealerRepositoryUtility;
  private final CompleteAddressCreator completeAddressCreator;
  private final Converter<CompleteDealer, DealerJPA> convertCompleteDealerToDealerJPA;
  private final CommonFinanceService commonFinanceService;


  /**
   * Get all dealer from complete creator.
   */
  @Override
  public List<CompleteDealer> getAll() {
    return completeDealerCreator.getAll();
  }

  /**
   * Get dealer by id from complete creator.
   */
  @Override
  public CompleteDealer getById(Long id) {
    return completeDealerCreator.createById(id);
  }

  /**
   * Not required.
   */
  @Override
  public boolean exist(Long id) {
    return false;
  }

  /**
   * Save dealer.
   */
  @Override
  public boolean save(DealerRequest dealerRequest, CompleteDealer completeDealer) {
    try {
      CompleteDealer save = create(dealerRequest);
      save.setId(completeDealer.getId());

      commonFinanceService.saveFinance(dealerRequest, "dealer", save.getFinance().getFinanceID());


      save.setAddress1(completeAddressCreator.createById(completeDealer.getAddress1().getAddressID()));
      save.setAddress2(completeAddressCreator.createById(completeDealer.getAddress2().getAddressID()));
      save.setAddress3(completeAddressCreator.createById(completeDealer.getAddress3().getAddressID()));

      if (completeDealer.getFinance().getFinanceID() == 0) {
        save.getFinance().setFinanceID(commonFinanceService.getLastSaveId());
      }

      dealerRepositoryUtility.save(convertCompleteDealerToDealerJPA.convert(save));
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Delete dealer by id.
   */
  @Override
  public boolean deleteById(Long id) {
    try {
      dealerRepositoryUtility.delete(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Map dealer request to complete dealer.
   */
  @Override
  public CompleteDealer create(DealerRequest dealerRequest) {
    return modelMapper.map(dealerRequest, CompleteDealer.class);
  }

  /**
   * Create empty dealer.
   */
  @Override
  public CompleteDealer createEmpty() {
    return completeDealerCreator.createEmpty();
  }

  /**
   * Clear address.
   */
  public boolean clearAddressId(Long id, int position) {
    try {
      DealerJPA cd = convertCompleteDealerToDealerJPA.convert(completeDealerCreator.createById(id));
      if (position == 2) {
        cd.setAddressID2(0);
        dealerRepositoryUtility.save(cd);
      } else if (position == 3) {
        cd.setAddressID3(0);
        dealerRepositoryUtility.save(cd);
      }
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Update address id.
   */
  public boolean updateDealerAddress(Long id, Long addressID, int position) {
    try {
      DealerJPA cd = convertCompleteDealerToDealerJPA.convert(completeDealerCreator.createById(id));
      if (position == 1) {
        cd.setAddressID(Math.toIntExact(addressID));
        dealerRepositoryUtility.save(cd);
        return true;
      } else if (position == 2) {
        cd.setAddressID2(Math.toIntExact(addressID));
        dealerRepositoryUtility.save(cd);
        return true;
      } else if (position == 3) {
        cd.setAddressID3(Math.toIntExact(addressID));
        dealerRepositoryUtility.save(cd);
        return true;
      } else {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
  }

}
