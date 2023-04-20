package org.example.Api.Models.CartModels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CartProduct {
   @JsonProperty("productId")
   private Long id;

   @JsonProperty("quantity")
   private Long quantity;
}
