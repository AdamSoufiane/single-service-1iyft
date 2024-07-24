package ai.shreds.domain; 
  
 import lombok.Getter; 
 import lombok.Setter; 
 import lombok.EqualsAndHashCode; 
 import javax.persistence.Entity; 
 import javax.persistence.Table; 
 import javax.persistence.Id; 
 import javax.persistence.Column; 
  
 /** 
  * Domain model for Category entity corresponding to 'wp_terms' table in WordPress database. 
  */ 
 @Getter 
 @Setter 
 @EqualsAndHashCode 
 @Entity 
 @Table(name = "wp_terms") 
 public class DomainCategoryEntity { 
     @Id 
     @Column(name = "term_id") 
     private Long id; 
     @Column(name = "name", nullable = false) 
     private String name; 
     @Column(name = "slug", nullable = false) 
     private String slug; 
     @Column(name = "description") 
     private String description; 
     @Column(name = "parent") 
     private Long parent; 
     @Column(name = "count", nullable = false) 
     private Long count; 
  
     public DomainCategoryEntity() { 
     } 
  
     public DomainCategoryEntity(Long id, String name, String slug, String description, Long parent, Long count) { 
         this.id = id; 
         this.name = name; 
         this.slug = slug; 
         this.description = description; 
         this.parent = parent; 
         this.count = count; 
     } 
  
     @Override 
     public boolean equals(Object o) { 
         if (this == o) return true; 
         if (o == null || getClass() != o.getClass()) return false; 
  
         DomainCategoryEntity that = (DomainCategoryEntity) o; 
  
         return id != null ? id.equals(that.id) : that.id == null; 
     } 
  
     @Override 
     public int hashCode() { 
         return id != null ? id.hashCode() : 0; 
     } 
  
     @Override 
     public String toString() { 
         return "DomainCategoryEntity{" + 
                 "id=" + id + 
                 ", name='" + name + '\'' + 
                 ", slug='" + slug + '\'' + 
                 ", description='" + description + '\'' + 
                 ", parent=" + parent + 
                 ", count=" + count + 
                 '}'; 
     } 
 }