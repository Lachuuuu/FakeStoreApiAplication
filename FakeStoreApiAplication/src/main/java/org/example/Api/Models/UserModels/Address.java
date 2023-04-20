package org.example.Api.Models.UserModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
