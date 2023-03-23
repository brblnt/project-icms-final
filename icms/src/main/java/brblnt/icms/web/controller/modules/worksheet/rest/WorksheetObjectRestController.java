package brblnt.icms.web.controller.modules.worksheet.rest;

import static brblnt.icms.service.utilities.Utilities.*;

import java.util.List;

import brblnt.icms.service.modules.worksheet.model.complete.CompleteObject;
import brblnt.icms.service.service.modules.worksheet.WorksheetObjectService;
import brblnt.icms.web.model.modules.worksheet.object.request.ObjectRequest;
import brblnt.icms.web.model.modules.worksheet.object.response.ObjectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Object Rest Controller.
 * '/api/worksheet/items'.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = API + WORKSHEET_OBJECT_URL)
public class WorksheetObjectRestController {

  private final WorksheetObjectService worksheetObjectService;
  private final Converter<CompleteObject, ObjectResponse> completeObjectToObjectResponse;

  /**
   * Get object list.
   */
  @GetMapping(path = ROOT_URL)
  public List<ObjectResponse> getObjects() {
    return worksheetObjectService.getAll().stream()
            .map(completeObjectToObjectResponse::convert)
            .toList();
  }

  /**
   * Get object by id.
   */
  @GetMapping(path = ID_PATH_VARIABLE)
  public ObjectResponse getObject(final @PathVariable Long id) {
    return completeObjectToObjectResponse.convert(worksheetObjectService.getById(id));
  }

  /**
   * Create object.
   */
  @PostMapping
  public ObjectResponse createObject(final @RequestBody ObjectRequest objectRequest) {
    worksheetObjectService.save(objectRequest, worksheetObjectService.createEmpty());
    return completeObjectToObjectResponse.convert(
            worksheetObjectService.create(objectRequest));
  }

  /**
   * Update object.
   */
  @PutMapping(path = ID_PATH_VARIABLE)
  public ObjectResponse updateObject(final @PathVariable Long id, final @RequestBody ObjectRequest objectRequest) {
    worksheetObjectService.save(objectRequest, worksheetObjectService.getById(id));
    return completeObjectToObjectResponse.convert(worksheetObjectService.getById(id));
  }

  /**
   * Delete object.
   */
  @DeleteMapping(path = ID_PATH_VARIABLE)
  public ResponseEntity<Void> deleteObject(final @PathVariable Long id) {
    worksheetObjectService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
