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
public class Rating {
   @JsonProperty("rate")
   private Double rate;

   @JsonProperty("count")
   private Long count;
}
