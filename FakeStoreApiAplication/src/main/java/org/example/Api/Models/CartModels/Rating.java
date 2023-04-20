package org.example.Api.Models.CartModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Rating {
   @JsonProperty("rate")
   private Double rate;

   @JsonProperty("count")
   private Long count;
}
