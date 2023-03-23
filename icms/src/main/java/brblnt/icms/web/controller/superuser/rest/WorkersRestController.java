package brblnt.icms.web.controller.superuser.rest;

import static brblnt.icms.service.utilities.Utilities.*;

import java.util.List;

import brblnt.icms.service.modules.common.model.complete.CompleteColleague;
import brblnt.icms.service.service.modules.common.CommonColleagueService;
import brblnt.icms.web.model.modules.common.request.ColleagueRequest;
import brblnt.icms.web.model.modules.common.response.ColleagueResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Workers Rest Controller.
 * '/api/super-user/settings/workers'.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = API + SUPER_USER_WORKERS)
public class WorkersRestController {

  private final CommonColleagueService commonColleagueService;

  private final Converter<CompleteColleague, ColleagueResponse> completeColleagueColleagueResponseConverter;

  /**
   * Get all worker.
   */
  @GetMapping(path = ROOT_URL)
  @PreAuthorize(SUPER_USER_ROLE)
  public List<ColleagueResponse> getWorkers() {
    return commonColleagueService.getAll().stream()
            .map(completeColleagueColleagueResponseConverter::convert)
            .toList();
  }

  /**
   * Get worker by id.
   */
  @GetMapping(path = ID_PATH_VARIABLE)
  @PreAuthorize(SUPER_USER_ROLE)
  public ColleagueResponse getWorker(final @PathVariable Long id) {
    return completeColleagueColleagueResponseConverter.convert(commonColleagueService.getById(id));
  }

  /**
   * Create worker.
   */
  @PostMapping
  @PreAuthorize(SUPER_USER_ROLE)
  public ColleagueResponse createWorker(final @RequestBody ColleagueRequest workerRequest) {
    commonColleagueService.save(workerRequest, commonColleagueService.createEmpty());
    return completeColleagueColleagueResponseConverter.convert(
            commonColleagueService.create(workerRequest));
  }

  /**
   * Update worker.
   */
  @PutMapping(path = ID_PATH_VARIABLE)
  @PreAuthorize(SUPER_USER_ROLE)
  public ColleagueResponse updateWorker(final @PathVariable Long id, final @RequestBody ColleagueRequest workerRequest) {
    commonColleagueService.save(workerRequest, commonColleagueService.getById(id));
    return completeColleagueColleagueResponseConverter.convert(commonColleagueService.getById(id));
  }

  /**
   * Delete worker.
   */
  @DeleteMapping(path = ID_PATH_VARIABLE)
  @PreAuthorize(SUPER_USER_ROLE)
  public ResponseEntity<Void> deleteWorker(final @PathVariable Long id) {
    commonColleagueService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
