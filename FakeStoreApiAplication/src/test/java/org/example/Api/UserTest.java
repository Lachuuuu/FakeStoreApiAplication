package org.example.Api;

import org.example.Api.Models.UserModels.Name;
import org.example.Api.Models.UserModels.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

   @Test
   public void shouldReturnCorrectUserString() {
      //Given
      Name user1Name = new Name("Radosław", "Lach");
      User user1 = new User(0L, null, null, null, null, null, user1Name, null);

      Name user2Name = new Name(null, null);
      User user2 = new User(null, null, null, null, null, null, null, null);

      Name user3Name = new Name(null, null);
      User user3 = new User(null, null, null, null, null, null, user3Name, null);

      //Then
      assertEquals("User{id=0, name=Radosław Lach}", user1.toString());
      assertEquals("User{id=null, name=null}", user2.toString());
      assertEquals("User{id=null, name=null null}", user3.toString());
   }
}
