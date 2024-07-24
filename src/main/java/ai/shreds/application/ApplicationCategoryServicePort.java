package ai.shreds.application; 
  
 import ai.shreds.shared.SharedCategoryDTO; 
 import ai.shreds.shared.SharedCategoryListDTO; 
 import ai.shreds.shared.SharedRequestParams; 
  
 /** 
  * Interface for category services that defines the contract for category-related operations. 
  */ 
 public interface ApplicationCategoryServicePort { 
  
     /** 
      * Retrieves all categories based on provided filtering parameters. 
      * @param params the filtering and pagination parameters 
      * @return a list DTO of categories 
      */ 
     SharedCategoryListDTO getAllCategories(SharedRequestParams params); 
  
     /** 
      * Retrieves a specific category by its ID. 
      * @param id the unique identifier of the category 
      * @return the category DTO 
      */ 
     SharedCategoryDTO getCategoryById(Long id); 
  
     /** 
      * Retrieves a category by its slug. 
      * @param slug the slug of the category 
      * @return the category DTO 
      */ 
     SharedCategoryDTO getCategoryBySlug(String slug); 
 }