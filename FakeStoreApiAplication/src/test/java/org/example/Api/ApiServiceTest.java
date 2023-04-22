package org.example.Api;

import org.example.Api.Models.CartModels.Cart;
import org.example.Api.Models.CartModels.CartProduct;
import org.example.Api.Models.ProductModels.Product;
import org.example.Api.Models.UserModels.Address;
import org.example.Api.Models.UserModels.Geolocation;
import org.example.Api.Models.UserModels.Name;
import org.example.Api.Models.UserModels.User;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


public class ApiServiceTest {

   ApiService apiService = new ApiService();

   // #######################################################################################
   // #----------------------- findTwoUsersLivingFurthestAway  TESTS -----------------------#
   // #######################################################################################
   @Test
   public void shouldReturnExceptionWhenLengthOfUsersListIsSmallerthan2() {
      //Given
      Address user1Address = new Address(new Geolocation(1D, 1D), null, null, null, null);
      Name user1Name = new Name("Radosław", "Lach");
      User user1 = new User(0L, user1Address, null, null, null, null, user1Name, null);

      //Then
      assertThrows(
            IllegalArgumentException.class,
            () -> apiService.findTwoUsersLivingFurthestAway(List.of(user1))
      );
      assertThrows(
            IllegalArgumentException.class,
            () -> apiService.findTwoUsersLivingFurthestAway(List.of())
      );
   }

   @Test
   public void shouldReturnExceptionWhenListOfUsersIsNull() {
      //Then
      assertThrows(
            IllegalArgumentException.class,
            () -> apiService.findTwoUsersLivingFurthestAway(null)
      );
   }

   @Test
   public void shouldReturnCorrectDistanceAndUsersNamesWhenCorrectListOfUsersIsGiven() {
      //Given
      Address user1Address = new Address(new Geolocation(1D, 1D), null, null, null, null);
      Name user1Name = new Name("Radosław", "Lach");
      User user1 = new User(0L, user1Address, null, null, null, null, user1Name, null);

      Address user2Address = new Address(new Geolocation(1D, 1D), null, null, null, null);
      Name user2Name = new Name("Marcin", "Kowalski");
      User user2 = new User(1L, user2Address, null, null, null, null, user2Name, null);

      Address user3Address = new Address(new Geolocation(2D, 1D), null, null, null, null);
      Name user3Name = new Name("Janusz", "Kowalski");
      User user3 = new User(2L, user3Address, null, null, null, null, user3Name, null);

      Address user4Address = new Address(new Geolocation(-37.3159, 81.1496), null, null, null, null);
      Name user4Name = new Name("Gabriela", "Kowalska");
      User user4 = new User(3L, user4Address, null, null, null, null, user4Name, null);

      Address user5Address = new Address(new Geolocation(40.3467, -40.131), null, null, null, null);
      Name user5Name = new Name("Anna", "Nowak");
      User user5 = new User(4L, user5Address, null, null, null, null, user5Name, null);

      //When
      String answer = apiService.findTwoUsersLivingFurthestAway(List.of(user1, user2));
      String answer2 = apiService.findTwoUsersLivingFurthestAway(List.of(user1, user2, user3));
      String answer3 = apiService.findTwoUsersLivingFurthestAway(List.of(user1, user2, user3, user4, user5));

      //Then
      assertEquals(
            "Users that lives furthest away are: " +
                  user1 +
                  " and " +
                  user2 +
                  " distance between these 2 users = "
                  + String.format("%.2f", 0.0) + " km",
            answer
      );
      assertEquals(
            "Users that lives furthest away are: " +
                  user1 +
                  " and " +
                  user3 +
                  " distance between these 2 users = "
                  + String.format("%.2f", 111.19) + " km",
            answer2
      );
      assertEquals(
            "Users that lives furthest away are: " +
                  user4 +
                  " and " +
                  user5 +
                  " distance between these 2 users = "
                  + String.format("%.2f", 15012.06) + " km",
            answer3
      );
   }

   @Test
   public void shouldReturnCorrectDistanceEvenIfListIncludeUserWithNullAddressOrGeoLocation() {
      //Given
      Address user1Address = new Address(new Geolocation(1D, 1D), null, null, null, null);
      Name user1Name = new Name("Radosław", "Lach");
      User user1 = new User(0L, user1Address, null, null, null, null, user1Name, null);

      Name user2Name = new Name("Marcin", "Kowalski");
      User user2 = new User(1L, null, null, null, null, null, user2Name, null);

      Address user3Address = new Address(new Geolocation(2D, 1D), null, null, null, null);
      Name user3Name = new Name("Janusz", "Kowalski");
      User user3 = new User(2L, user3Address, null, null, null, null, user3Name, null);

      Address user4Address = new Address(null, null, null, null, null);
      Name user4Name = new Name("Zbigniew", "Kowalski");
      User user4 = new User(3L, user4Address, null, null, null, null, user4Name, null);

      //When
      String answer = apiService.findTwoUsersLivingFurthestAway(List.of(user1, user2, user3, user4));

      //Then
      assertEquals(
            "Users that lives furthest away are: " +
                  user1 +
                  " and " +
                  user3 +
                  " distance between these 2 users = "
                  + String.format("%.2f", 111.19) + " km",
            answer
      );
   }

   // ###############################################################################
   // #----------------------- createCategoriesValues  TESTS -----------------------#
   // ###############################################################################

   @Test
   public void shouldReturnEmptyMapWhenListOfProductsIsNull() {
      //Then
      assertEquals(new HashMap<String, Double>(), apiService.createCategoriesValues(null));
   }

   @Test
   public void shouldReturnCorrectMapContainingAllAvailableProductCategoriesAndTheTotalValueOfProductsOfGivenCategory() {
      //Given
      Product product1 = new Product(0L, null, 1.0, null, "category1", null, null);
      Product product2 = new Product(1L, null, 0.53, null, "category1", null, null);
      Product product3 = new Product(2L, null, 2.0, null, "category1", null, null);

      Product product4 = new Product(3L, null, 3.51, null, "category2", null, null);
      Product product5 = new Product(4L, null, 4.2312, null, "category2", null, null);

      Product product6 = new Product(5L, null, 4.2312, null, "category3", null, null);

      //Then
      HashMap<String, Double> expectedResult1 = new HashMap<>();
      expectedResult1.put("category1", product1.getPrice() + product2.getPrice() + product3.getPrice());
      expectedResult1.put("category2", product4.getPrice() + product5.getPrice());
      expectedResult1.put("category3", product6.getPrice());

      HashMap<String, Double> expectedResult2 = new HashMap<>();
      expectedResult2.put("category1", 1.0);
      assertEquals(expectedResult1, apiService.createCategoriesValues(List.of(product1, product2, product3, product4, product5, product6)));
      assertEquals(expectedResult2, apiService.createCategoriesValues(List.of(product1)));
   }

   @Test
   public void shouldReturnCorrectMapIgnoringProductsWithNullCategoryOrNullPrice() {
      //Given
      Product product1 = new Product(0L, null, 1.0, null, "category1", null, null);
      Product product2 = new Product(1L, null, 0.53, null, "category1", null, null);
      Product product3 = new Product(2L, null, null, null, "category1", null, null);

      Product product4 = new Product(3L, null, null, null, null, null, null);
      Product product5 = new Product(4L, null, 4.2312, null, "category2", null, null);

      Product product6 = new Product(5L, null, 4.2312, null, null, null, null);

      //Then
      HashMap<String, Double> expectedResult1 = new HashMap<>();
      expectedResult1.put("category1", product1.getPrice() + product2.getPrice());
      expectedResult1.put("category2", product5.getPrice());

      assertEquals(expectedResult1, apiService.createCategoriesValues(List.of(product1, product2, product3, product4, product5, product6)));
   }

   // ############################################################################################
   // #----------------------- findCartWithHighestValueAndItsOwner  TESTS -----------------------#
   // ############################################################################################

   @Test
   public void shouldReturnExceptionWhenAtleastOneOfListsIsEmpty() {
      //Given
      Product product1 = new Product(0L, null, 1.0, null, "category1", null, null);
      Product product2 = new Product(1L, null, 0.53, null, "category1", null, null);

      Address user1Address = new Address(new Geolocation(1D, 1D), null, null, null, null);
      Name user1Name = new Name("Radosław", "Lach");
      User user1 = new User(0L, user1Address, null, null, null, null, user1Name, null);
      Address user2Address = new Address(new Geolocation(1D, 1D), null, null, null, null);
      Name user2Name = new Name("Marcin", "Kowalski");
      User user2 = new User(1L, user2Address, null, null, null, null, user2Name, null);


      CartProduct cartProduct1 = new CartProduct(1L, 10L);
      CartProduct cartProduct2 = new CartProduct(2L, 5L);
      Cart cart1 = new Cart(0L, 1L, LocalDateTime.now(), List.of(cartProduct1), null);
      Cart cart2 = new Cart(1L, 2L, LocalDateTime.now(), List.of(cartProduct1, cartProduct2), null);

      //Then
      assertThrows(
            IllegalArgumentException.class,
            () -> apiService.findCartWithHighestValueAndItsOwner(null, null, null)
      );
      assertThrows(
            IllegalArgumentException.class,
            () -> apiService.findCartWithHighestValueAndItsOwner(List.of(cart1, cart2), null, null)
      );

      assertThrows(
            IllegalArgumentException.class,
            () -> apiService.findCartWithHighestValueAndItsOwner(List.of(cart1, cart2), List.of(product1, product2), null)
      );

      assertThrows(
            IllegalArgumentException.class,
            () -> apiService.findCartWithHighestValueAndItsOwner(List.of(cart1, cart2), null, List.of(user1, user2))
      );

   }

   @Test
   public void shouldReturnCorrectInformationAboutHighestValueCartAndItsOwner() {
      //Given
      Product product1 = new Product(0L, null, 1.0, null, "category1", null, null);
      Product product2 = new Product(1L, null, 0.53, null, "category1", null, null);
      Product product3 = new Product(2L, null, 10.0, null, "category1", null, null);

      Address user1Address = new Address(new Geolocation(1D, 1D), null, null, null, null);
      Name user1Name = new Name("Radosław", "Lach");
      User user1 = new User(0L, user1Address, null, null, null, null, user1Name, null);
      Address user2Address = new Address(new Geolocation(1D, 1D), null, null, null, null);
      Name user2Name = new Name("Marcin", "Kowalski");
      User user2 = new User(1L, user2Address, null, null, null, null, user2Name, null);


      CartProduct cartProduct1 = new CartProduct(0L, 10L);
      CartProduct cartProduct2 = new CartProduct(1L, 5L);
      CartProduct cartProduct3 = new CartProduct(2L, 5L);

      Cart cart1 = new Cart(0L, 0L, LocalDateTime.now(), List.of(cartProduct1), null);
      Cart cart2 = new Cart(1L, 1L, LocalDateTime.now(), List.of(cartProduct1, cartProduct2), null);
      Cart cart3 = new Cart(2L, 1L, LocalDateTime.now(), List.of(cartProduct1, cartProduct3), null);

      //Then
      assertEquals("highest cart value = " +
                  60.0 +
                  " | owned by = Marcin" +
                  " Kowalski",
            apiService.findCartWithHighestValueAndItsOwner(
                  List.of(cart1, cart2, cart3),
                  List.of(product1, product2, product3),
                  List.of(user1, user2)
            )
      );
   }

   @Test
   public void shouldIgnoreCartProductsThatAreNotAvailableAtProductsList() {
      //Given
      Product product1 = new Product(0L, null, 1.0, null, "category1", null, null);
      Product product2 = new Product(1L, null, 0.53, null, "category1", null, null);
      Product product3 = new Product(2L, null, 10.0, null, "category1", null, null);

      Address user1Address = new Address(new Geolocation(1D, 1D), null, null, null, null);
      Name user1Name = new Name("Radosław", "Lach");
      User user1 = new User(0L, user1Address, null, null, null, null, user1Name, null);
      Address user2Address = new Address(new Geolocation(1D, 1D), null, null, null, null);
      Name user2Name = new Name("Marcin", "Kowalski");
      User user2 = new User(1L, user2Address, null, null, null, null, user2Name, null);


      CartProduct cartProduct1 = new CartProduct(0L, 10L);
      CartProduct cartProduct2 = new CartProduct(1L, 5L);
      CartProduct cartProduct3 = new CartProduct(20L, 222225L);

      Cart cart1 = new Cart(0L, 0L, LocalDateTime.now(), List.of(cartProduct1), null);
      Cart cart2 = new Cart(1L, 1L, LocalDateTime.now(), List.of(cartProduct1, cartProduct2), null);
      Cart cart3 = new Cart(2L, 1L, LocalDateTime.now(), List.of(cartProduct1, cartProduct3), null);

      //Then
      assertEquals("highest cart value = " +
                  12.65 +
                  " | owned by = Marcin" +
                  " Kowalski",
            apiService.findCartWithHighestValueAndItsOwner(
                  List.of(cart1, cart2, cart3),
                  List.of(product1, product2, product3),
                  List.of(user1, user2)
            )
      );
   }

}