package ai.shreds.shared; 
  
 import lombok.Data; 
 import java.util.List; 
  
 /** 
  * SharedRequestParams encapsulates all query parameters for category retrieval requests. 
  * Includes validation to ensure parameters meet expected formats and values. 
  */ 
 @Data 
 public class SharedRequestParams { 
     private String context; 
     private Integer page; 
     private Integer per_page; 
     private String search; 
     private List<Long> exclude; 
     private List<Long> include; 
     private String order; 
     private String orderby; 
     private Boolean hide_empty; 
     private Long parent; 
     private Long post; 
     private String slug; 
  
     public void setContext(String context) { 
         if (!List.of("view", "embed", "edit").contains(context)) { 
             throw new IllegalArgumentException("Invalid context value: " + context); 
         } 
         this.context = context; 
     } 
  
     public void setPage(Integer page) { 
         if (page == null || page < 1) { 
             throw new IllegalArgumentException("Page number must be greater than 0."); 
         } 
         this.page = page; 
     } 
  
     public void setPerPage(Integer per_page) { 
         if (per_page == null || per_page < 1) { 
             throw new IllegalArgumentException("Per page must be greater than 0."); 
         } 
         this.per_page = per_page; 
     } 
  
     public void setSearch(String search) { 
         this.search = sanitizeSearchField(search); 
     } 
  
     public void setOrder(String order) { 
         if (!"asc".equals(order) && !"desc".equals(order)) { 
             throw new IllegalArgumentException("Order must be 'asc' or 'desc'."); 
         } 
         this.order = order; 
     } 
  
     private String sanitizeSearchField(String search) { 
         // Sanitization logic here 
         return search; 
     } 
  
     @Override 
     public String toString() { 
         return "SharedRequestParams{" + 
                 "context='" + context + '\'' + 
                 ", page=" + page + 
                 ", per_page=" + per_page + 
                 ", search='" + search + '\'' + 
                 ", exclude=" + exclude + 
                 ", include=" + include + 
                 ", order='" + order + '\'' + 
                 ", orderby='" + orderby + '\'' + 
                 ", hide_empty=" + hide_empty + 
                 ", parent=" + parent + 
                 ", post=" + post + 
                 ", slug='" + slug + '\'' + 
                 '}'; 
     } 
 }