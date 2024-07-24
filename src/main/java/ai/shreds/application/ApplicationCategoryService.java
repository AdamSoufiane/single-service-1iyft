package ai.shreds.application; 
  
 import ai.shreds.shared.SharedCategoryDTO; 
 import ai.shreds.shared.SharedCategoryListDTO; 
 import ai.shreds.shared.SharedRequestParams; 
 import ai.shreds.domain.DomainCategoryRepositoryPort; 
 import ai.shreds.domain.DomainCategoryEntity; 
 import java.util.List; 
 import java.util.stream.Collectors; 
 import org.springframework.dao.DataAccessException; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 public class ApplicationCategoryService implements ApplicationCategoryServicePort { 
  
     private static final Logger logger = LoggerFactory.getLogger(ApplicationCategoryService.class); 
     private final DomainCategoryRepositoryPort categoryRepository; 
  
     public ApplicationCategoryService(DomainCategoryRepositoryPort categoryRepository) { 
         this.categoryRepository = categoryRepository; 
     } 
  
     private void validateRequestParams(SharedRequestParams params) throws InvalidParameterException { 
         if (params == null) { 
             throw new InvalidParameterException("Request parameters cannot be null."); 
         } 
         // Additional validation logic here 
     } 
  
     @Override 
     public SharedCategoryListDTO getAllCategories(SharedRequestParams params) { 
         validateRequestParams(params); 
         logger.info("Fetching all categories with parameters: {}", params); 
         try { 
             List<DomainCategoryEntity> categories = categoryRepository.findAll(params); 
             return new SharedCategoryListDTO(categories.stream() 
                 .map(CategoryMapper::toDTO) 
                 .collect(Collectors.toList())); 
         } catch (DataAccessException e) { 
             logger.error("Database access error occurred", e); 
             throw new DatabaseOperationException("Database access error occurred", e); 
         } 
     } 
  
     @Override 
     public SharedCategoryDTO getCategoryById(Long id) { 
         logger.info("Fetching category by ID: {}", id); 
         try { 
             DomainCategoryEntity category = categoryRepository.findById(id); 
             return CategoryMapper.toDTO(category); 
         } catch (DataAccessException e) { 
             logger.error("Unable to retrieve category by ID", e); 
             throw new CategoryNotFoundException("Category not found for ID: " + id, e); 
         } 
     } 
  
     @Override 
     public SharedCategoryDTO getCategoryBySlug(String slug) { 
         logger.info("Fetching category by slug: {}", slug); 
         try { 
             DomainCategoryEntity category = categoryRepository.findBySlug(slug); 
             return CategoryMapper.toDTO(category); 
         } catch (DataAccessException e) { 
             logger.error("Unable to retrieve category by slug", e); 
             throw new CategoryNotFoundException("Category not found for slug: " + slug, e); 
         } 
     } 
 } 
  
 // Custom exceptions 
 class InvalidParameterException extends RuntimeException { 
     public InvalidParameterException(String message) { 
         super(message); 
     } 
 } 
  
 class DatabaseOperationException extends RuntimeException { 
     public DatabaseOperationException(String message, Throwable cause) { 
         super(message, cause); 
     } 
 } 
  
 class CategoryNotFoundException extends RuntimeException { 
     public CategoryNotFoundException(String message, Throwable cause) { 
         super(message, cause); 
     } 
 }