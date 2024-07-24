package ai.shreds.shared; 
  
 import lombok.Data; 
 import com.fasterxml.jackson.annotation.JsonProperty; 
  
 @Data 
 public class SharedCategoryDTO { 
     @JsonProperty("id") 
     private Long id; 
     @JsonProperty("name") 
     private String name; 
     @JsonProperty("slug") 
     private String slug; 
     @JsonProperty("description") 
     private String description; 
     @JsonProperty("parent") 
     private Long parent; 
     @JsonProperty("count") 
     private Long count; 
 }