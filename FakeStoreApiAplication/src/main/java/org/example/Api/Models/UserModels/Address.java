package org.example.Api.Models.UserModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Address {
   @JsonProperty("geolocation")
   private Geolocation geolocation;

   @JsonProperty("city")
   private String city;

   @JsonProperty("street")
   private String street;

   @JsonProperty("number")
   private Long number;

   @JsonProperty("zipcode")
   private String zipcode;
}
