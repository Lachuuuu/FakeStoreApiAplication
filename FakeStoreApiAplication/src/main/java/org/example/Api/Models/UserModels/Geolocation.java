package org.example.Api.Models.UserModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Geolocation {
   @JsonProperty("lat")
   private Double lat;

   @JsonProperty("long")
   private Double lon;
}
