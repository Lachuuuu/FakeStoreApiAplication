package org.example.Api.Models.CartModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class Cart {
   @JsonProperty("id")
   private Long id;

   @JsonProperty("userId")
   private Long userId;

   @JsonProperty("date")
   private LocalDateTime date;

   @JsonProperty("products")
   private List<CartProduct> products;

   @JsonProperty("__v")
   private String v;
}
