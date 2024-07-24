package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainCategoryEntity; 
 import ai.shreds.domain.DomainCategoryRepositoryPort; 
 import ai.shreds.shared.SharedRequestParams; 
 import org.springframework.data.domain.Page; 
 import org.springframework.data.domain.Pageable; 
 import org.springframework.data.jpa.repository.JpaRepository; 
 import org.springframework.stereotype.Repository; 
 import org.springframework.transaction.annotation.Transactional; 
 import org.springframework.dao.DataAccessException; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import java.util.Optional; 
 import java.util.regex.Pattern; 
  
 @Repository 
 public class InfrastructureCategoryRepositoryImpl implements DomainCategoryRepositoryPort { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureCategoryRepositoryImpl.class); 
     private final JpaRepository<DomainCategoryEntity, Long> repository; 
  
     public InfrastructureCategoryRepositoryImpl(JpaRepository<DomainCategoryEntity, Long> repository) { 
         this.repository = repository; 
     } 
  
     @Override 
     public DomainCategoryEntity findById(Long id) { 
         logger.info("Fetching category by ID: {}", id); 
         return repository.findById(id).orElse(null); 
     } 
  
     @Override 
     public Page<DomainCategoryEntity> findAll(SharedRequestParams params, Pageable pageable) { 
         logger.info("Fetching all categories with parameters: {}", params); 
         return repository.findAll(pageable); 
     } 
  
     @Override 
     public Optional<DomainCategoryEntity> findBySlug(String slug) { 
         logger.info("Fetching category by slug: {}", slug); 
         if (!Pattern.matches("^[a-zA-Z0-9-]+$", slug)) { 
             throw new IllegalArgumentException("Invalid slug format"); 
         } 
         return repository.findBySlug(slug); 
     } 
  
     @Transactional 
     @Override 
     public void save(DomainCategoryEntity entity) { 
         logger.info("Saving category: {}", entity); 
         repository.save(entity); 
     } 
  
     @Transactional 
     @Override 
     public void delete(DomainCategoryEntity entity) { 
         logger.info("Deleting category: {}", entity); 
         repository.delete(entity); 
     } 
 }