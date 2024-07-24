package ai.shreds.shared; 
  
 import java.util.List; 
 import com.fasterxml.jackson.annotation.JsonProperty; 
 import lombok.Data; 
  
 @Data 
 public class SharedCategoryListDTO { 
     @JsonProperty("categories") 
     private List<SharedCategoryDTO> categories; 
 }