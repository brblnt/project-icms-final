package brblnt.icms.service.modules.common.repository.utility;

import java.util.List;
import java.util.Objects;

import brblnt.icms.data.modules.common.model.CompanyJPA;
import brblnt.icms.data.modules.common.repository.CompanyRepository;
import brblnt.icms.service.interfaces.UtilityInterface;
import brblnt.icms.service.modules.common.exceptions.CompanyNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Company Repository Utility.
 */
@Service
@RequiredArgsConstructor
public class CompanyRepositoryUtility implements UtilityInterface<CompanyJPA, CompanyNotFoundException> {

  private final CompanyRepository companyRepository;

  @Override
  public List<CompanyJPA> getAll() {
    return companyRepository.findAll().stream().toList();
  }

  @Override
  public CompanyJPA getById(Long id) throws CompanyNotFoundException {
    for (CompanyJPA company : getAll()) {
      if (Objects.equals(company.getId(), id)) {
        return company;
      }
    }
    throw new CompanyNotFoundException("No company with this id " + id);
  }

  /**
   * Not required.
   */
  @Override
  public CompanyJPA getById(String id) throws CompanyNotFoundException {
    return null;
  }

  @Override
  public void save(CompanyJPA company) {
    companyRepository.save(company);
  }

  @Override
  public void delete(Long id) {
  }
}
