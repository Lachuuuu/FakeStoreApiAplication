package org.example.Api;

import org.example.Api.Models.UserModels.Name;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NameTest {
   @Test
   public void shouldReturnCorrectFullNameString() {
      //Given
      Name name = new Name("Radosław", "Lach");
      Name name2 = new Name(null, null);

      //Then
      assertEquals("Radosław Lach", name.toString());
      assertEquals("null null", name2.toString());
   }
}
