package ai.shreds.adapters.primary; 
  
 import ai.shreds.shared.SharedRequestParams; 
 import ai.shreds.shared.SharedCategoryDTO; 
 import ai.shreds.shared.SharedCategoryListDTO; 
 import ai.shreds.application.ApplicationCategoryServicePort; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.web.bind.annotation.GetMapping; 
 import org.springframework.web.bind.annotation.PathVariable; 
 import org.springframework.web.bind.annotation.RestController; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.security.access.prepost.PreAuthorize; 
 import org.springframework.transaction.annotation.Transactional; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
 import org.springframework.dao.DataAccessException; 
 import org.springframework.web.client.HttpClientErrorException; 
 import org.springframework.http.HttpStatus; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import javax.validation.Valid; 
 import org.springframework.cache.annotation.Cacheable; 
  
 @RestController 
 public class AdapterCategoryController { 
  
     private static final Logger logger = LoggerFactory.getLogger(AdapterCategoryController.class); 
  
     @Autowired 
     private ApplicationCategoryServicePort applicationCategoryService; 
  
     @GetMapping("/wp-json/wp/v2/categories") 
     @PreAuthorize("hasRole('ADMIN')") 
     @Cacheable(value = "categoriesCache", key = "#params") 
     public ResponseEntity<SharedCategoryListDTO> getAllCategories(@Valid SharedRequestParams params) { 
         logger.info("Fetching all categories with params: {}", params); 
         try { 
             SharedCategoryListDTO categories = applicationCategoryService.getAllCategories(params); 
             return ResponseEntity.ok(categories); 
         } catch (DataAccessException e) { 
             logger.error("Database access error", e); 
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); 
         } catch (IllegalArgumentException e) { 
             logger.error("Invalid request parameters", e); 
             return ResponseEntity.badRequest().body(null); 
         } 
     } 
  
     @GetMapping("/wp-json/wp/v2/categories/{id}") 
     @PreAuthorize("hasRole('USER')") 
     public ResponseEntity<SharedCategoryDTO> getCategoryById(@PathVariable Long id) { 
         logger.info("Fetching category by ID: {}", id); 
         try { 
             SharedCategoryDTO category = applicationCategoryService.getCategoryById(id); 
             return ResponseEntity.ok(category); 
         } catch (HttpClientErrorException.NotFound e) { 
             logger.error("Category not found", e); 
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
         } 
     } 
  
     @GetMapping("/wp-json/wp/v2/categories/slug/{slug}") 
     @PreAuthorize("hasRole('USER')") 
     public ResponseEntity<SharedCategoryDTO> getCategoryBySlug(@PathVariable String slug) { 
         logger.info("Fetching category by slug: {}", slug); 
         try { 
             SharedCategoryDTO category = applicationCategoryService.getCategoryBySlug(slug); 
             return ResponseEntity.ok(category); 
         } catch (HttpClientErrorException.NotFound e) { 
             logger.error("Category not found", e); 
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
         } 
     } 
 }