package ai.shreds.domain; 
  
 import ai.shreds.shared.SharedCategoryDTO; 
 import ai.shreds.shared.SharedCategoryListDTO; 
 import ai.shreds.shared.SharedRequestParams; 
 import java.util.List; 
 import java.util.stream.Collectors; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.dao.DataAccessException; 
 import javax.validation.Valid; 
  
 public class DomainCategoryService { 
  
     private static final Logger logger = LoggerFactory.getLogger(DomainCategoryService.class); 
     private final DomainCategoryRepositoryPort categoryRepository; 
  
     public DomainCategoryService(DomainCategoryRepositoryPort categoryRepository) { 
         this.categoryRepository = categoryRepository; 
     } 
  
     public SharedCategoryListDTO getAllCategories(@Valid SharedRequestParams params) { 
         logger.info("Fetching all categories with params: Page {} Per_Page {}", params.getPage(), params.getPerPage()); 
         try { 
             List<DomainCategoryEntity> categories = categoryRepository.findAll(params); 
             List<SharedCategoryDTO> categoryDTOs = categories.stream() 
                 .map(this::mapToDTO) 
                 .collect(Collectors.toList()); 
             logger.info("Fetched {} categories.", categoryDTOs.size()); 
             return new SharedCategoryListDTO(categoryDTOs); 
         } catch (DataAccessException e) { 
             logger.error("Error accessing data", e); 
             throw new RuntimeException("Database access error", e); 
         } 
     } 
  
     public SharedCategoryDTO getCategoryById(Long id) { 
         logger.info("Fetching category by ID: {}", id); 
         try { 
             DomainCategoryEntity category = categoryRepository.findById(id); 
             return mapToDTO(category); 
         } catch (DataAccessException e) { 
             logger.error("Error accessing data for ID: " + id, e); 
             throw new RuntimeException("Database access error", e); 
         } 
     } 
  
     public SharedCategoryDTO getCategoryBySlug(String slug) { 
         logger.info("Fetching category by slug: {}", slug); 
         try { 
             DomainCategoryEntity category = categoryRepository.findBySlug(slug); 
             return mapToDTO(category); 
         } catch (DataException e) { 
             logger.error("Error accessing data for slug: " + slug, e); 
             throw new RuntimeException("Database access error", e); 
         } 
     } 
  
     private SharedCategoryDTO mapToDTO(DomainCategoryEntity entity) { 
         SharedCategoryDTO dto = new SharedCategoryDTO(); 
         dto.setId(entity.getId()); 
         dto.setName(entity.getName()); 
         dto.setSlug(entity.getSlug()); 
         dto.setDescription(entity.getDescription()); 
         dto.setParent(entity.getParent()); 
         dto.setCount(entity.getCount()); 
         return dto; 
     } 
 }