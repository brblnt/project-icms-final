package brblnt.icms.web.controller.admin.rest;

import static brblnt.icms.service.utilities.Utilities.*;
import static brblnt.icms.service.utilities.Utilities.ID_PATH_VARIABLE;

import java.util.List;

import brblnt.icms.service.modules.users.model.complete.CompleteUser;
import brblnt.icms.service.service.AdminUserService;
import brblnt.icms.web.model.modules.users.response.UserResponse;
import brblnt.icms.web.model.modules.worksheet.customer.request.CustomerRequest;
import brblnt.icms.web.model.modules.worksheet.customer.response.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Admin user rest controller.
 * '/api/admin/user'
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = API + ADMIN_USER)
public class AdminUserRestController {

  private final AdminUserService adminUserService;
  private final Converter<CompleteUser, UserResponse> completeUserUserResponseConverter;

  /**
   * Get all user.
   */
  @GetMapping(path = ROOT_URL)
  @PreAuthorize(ADMIN_ROLE)
  public List<UserResponse> getUsers() {
    return adminUserService.getAll().stream()
            .map(completeUserUserResponseConverter::convert)
            .toList();
  }

  /**
   * Get user by id.
   */
  @GetMapping(path = ID_PATH_VARIABLE)
  @PreAuthorize(ADMIN_ROLE)
  public UserResponse getUser(final @PathVariable Long id) {
    return completeUserUserResponseConverter.convert(adminUserService.getById(id));
  }


}
