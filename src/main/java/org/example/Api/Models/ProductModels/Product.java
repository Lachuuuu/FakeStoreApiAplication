package org.example.Api.Models.ProductModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Api.Models.CartModels.Rating;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
   @JsonProperty("id")
   private Long id;

   @JsonProperty("title")
   private String title;

   @JsonProperty("price")
   private Double price;

   @JsonProperty("description")
   private String description;

   @JsonProperty("category")
   private String category;

   @JsonProperty("image")
   private String image;

   @JsonProperty("rating")
   private Rating rating;
}
