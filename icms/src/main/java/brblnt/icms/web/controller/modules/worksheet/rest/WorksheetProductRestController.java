package brblnt.icms.web.controller.modules.worksheet.rest;

import static brblnt.icms.service.utilities.Utilities.*;

import java.util.List;

import brblnt.icms.service.modules.worksheet.model.complete.CompleteProduct;
import brblnt.icms.service.service.modules.worksheet.WorksheetProductService;
import brblnt.icms.web.model.modules.worksheet.product.request.ProductRequest;
import brblnt.icms.web.model.modules.worksheet.product.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Product Rest Controller.
 * '/api/worksheet/products'.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = API + WORKSHEET_PRODUCT_URL)
public class WorksheetProductRestController {

  private final WorksheetProductService worksheetProductService;
  private final Converter<CompleteProduct, ProductResponse> completeProductProductResponseConverter;

  /**
   * Get product list.
   */
  @GetMapping(path = ROOT_URL)
  public List<ProductResponse> getProducts() {
    return worksheetProductService.getAll().stream()
            .map(completeProductProductResponseConverter::convert)
            .toList();
  }

  /**
   * Get product by id.
   */
  @GetMapping(path = ID_PATH_VARIABLE)
  public ProductResponse getProduct(final @PathVariable Long id) {
    return completeProductProductResponseConverter.convert(worksheetProductService.getById(id));
  }

  /**
   * Create product.
   */
  @PostMapping
  public ProductResponse createProduct(final @RequestBody ProductRequest productRequest) {
    if (worksheetProductService.exist(productRequest.getCustomCode(), "")) {
      return null;
    }
    worksheetProductService.save(productRequest, worksheetProductService.createEmpty());
    return completeProductProductResponseConverter.convert(
            worksheetProductService.create(productRequest));
  }

  /**
   * Update product.
   */
  @PutMapping(path = ID_PATH_VARIABLE)
  public ProductResponse updateProduct(final @PathVariable Long id, final @RequestBody ProductRequest productRequest) {
    if (worksheetProductService.exist(
            productRequest.getCustomCode(), worksheetProductService.getById(id).getCustomCode())) {
      return null;
    }
    worksheetProductService.save(productRequest, worksheetProductService.getById(id));
    return completeProductProductResponseConverter.convert(worksheetProductService.getById(id));
  }

  /**
   * Delete product.
   */
  @DeleteMapping(path = ID_PATH_VARIABLE)
  public ResponseEntity<Void> deleteProduct(final @PathVariable Long id) {
    worksheetProductService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
