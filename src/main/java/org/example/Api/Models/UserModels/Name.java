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
public class Name {

   @JsonProperty("firstname")
   private String firstname;

   @JsonProperty("lastname")
   private String lastname;

   @Override
   public String toString() {
      return firstname + " " + lastname;
   }
}
