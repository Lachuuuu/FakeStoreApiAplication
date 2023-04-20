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
public class User {
   
   @JsonProperty("id")
   private Long id;

   @JsonProperty("address")
   private Address address;

   @JsonProperty("email")
   private String email;

   @JsonProperty("username")
   private String username;

   @JsonProperty("password")
   private String password;

   @JsonProperty("phone")
   private String phone;

   @JsonProperty("name")
   private Name name;

   @JsonProperty("__v")
   private String v;


}
