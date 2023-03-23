package brblnt.icms.web.controller.superuser.rest;

import static brblnt.icms.service.utilities.Utilities.*;

import brblnt.icms.service.modules.common.model.complete.CompleteCompany;
import brblnt.icms.service.service.modules.common.CommonCompanyService;
import brblnt.icms.web.model.modules.common.request.CompanyRequest;
import brblnt.icms.web.model.modules.common.response.CompanyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Company Rest Controller.
 * '/api/super-user/basics'.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = API + SUPER_USER_BASICS)
public class CompanyRestController {

  private final CommonCompanyService commonCompanyService;
  private final Converter<CompleteCompany, CompanyResponse> completeCompanyCompanyResponseConvert;


  /**
   * Company get.
   */
  @GetMapping(path = ID_PATH_VARIABLE)
  @PreAuthorize(SUPER_USER_ROLE)
  public CompanyResponse getCompany(final @PathVariable Long id) {
    return completeCompanyCompanyResponseConvert.convert(commonCompanyService.getById(0L));
  }

  /**
   * Comapny update.
   */
  @PutMapping(path = ID_PATH_VARIABLE)
  @PreAuthorize(SUPER_USER_ROLE)
  public CompanyResponse updateCompany(final @PathVariable Long id, final @RequestBody CompanyRequest companyRequest) {
    commonCompanyService.save(companyRequest, null);
    return completeCompanyCompanyResponseConvert.convert(commonCompanyService.getById(0L));
  }
}
