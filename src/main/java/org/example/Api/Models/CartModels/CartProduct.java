package org.example.Api.Models.CartModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartProduct {
   @JsonProperty("productId")
   private Long productId;

   @JsonProperty("quantity")
   private Long quantity;
}
