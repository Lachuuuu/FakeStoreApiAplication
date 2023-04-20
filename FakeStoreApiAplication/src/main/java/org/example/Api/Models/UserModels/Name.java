package org.example.Api.Models.UserModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Name {
   @JsonProperty("firstname")
   private String firstname;

   @JsonProperty("lastname")
   private String lastname;
}
