package ai.shreds.domain; 
  
 import ai.shreds.shared.DomainCategoryEntity; 
 import ai.shreds.shared.SharedRequestParams; 
 import java.util.List; 
  
 /** 
  * Port interface for category repository operations in the domain layer. 
  * This interface abstracts the data access mechanisms for categories. 
  */ 
 public interface DomainCategoryRepositoryPort { 
     /** 
      * Finds a category by its unique ID. 
      * @param id The unique identifier of the category. 
      * @return The category entity or null if not found. 
      */ 
     DomainCategoryEntity findById(Long id) throws DataAccessException; 
  
     /** 
      * Finds all categories based on the provided request parameters. 
      * @param params The filtering and pagination parameters. 
      * @return A list of category entities matching the criteria. 
      */ 
     List<DomainCategoryEntity> findAll(SharedRequestParams params) throws DataAccessException; 
  
     /** 
      * Finds a category by its unique slug. 
      * @param slug The slug of the category. 
      * @return The category entity or null if not found. 
      */ 
     DomainCategoryEntity findBySlug(String slug) throws DataAccessException; 
 }