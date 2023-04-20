package org.example.Api.Models.CartModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
