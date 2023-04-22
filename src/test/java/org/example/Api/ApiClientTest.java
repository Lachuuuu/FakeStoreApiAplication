package org.example.Api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Api.Models.CartModels.Cart;
import org.example.Api.Models.CartModels.CartProduct;
import org.example.Api.Models.CartModels.Rating;
import org.example.Api.Models.ProductModels.Product;
import org.example.Api.Models.UserModels.Address;
import org.example.Api.Models.UserModels.Geolocation;
import org.example.Api.Models.UserModels.Name;
import org.example.Api.Models.UserModels.User;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class ApiClientTest {

   // #####################################################################
   // #----------------------- getUsersData  TESTS -----------------------#
   // #----------------------- getCartsData  TESTS -----------------------#
   // #----------------------- getProductsData  TESTS --------------------#
   // #####################################################################


   @Test
   public void shouldReturnEmptyListIfResponseIsNotValidJson() {
      //Given
      ApiClient apiClient = new ApiClient();
      ApiClient apiClient1 = spy(apiClient);
      //When
      when(apiClient1.sendRequest(anyString())).thenReturn("jtfjyjhh");
      //Then
      assertEquals(List.of(), apiClient1.getUsersData());
   }

   @Test
   public void shouldReturnCorrectListOfObjects() throws IOException {
      //Given
      ApiClient apiClient = new ApiClient();
      ApiClient apiClient1 = spy(apiClient);
      ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .findAndRegisterModules();

      Address user1Address = new Address(new Geolocation(1D, 1D), "Bydogszcz", "Urocza", 20L, "123-23");
      Name user1Name = new Name("Radosław", "Lach");
      User user1 = new User(0L, user1Address, "email@email.com", "Lachuu", "123", "123456789", user1Name, "0");
      Address user2Address = new Address(new Geolocation(1D, 1D), "Krakow", "Urocza", 20L, "123-23");
      Name user2Name = new Name("Marcin", "Kowalski");
      User user2 = new User(1L, user2Address, "email@email.com", "Lachuu", "123", "234234", user2Name, "0");

      Rating rating = new Rating(5.0, 10L);
      Product product1 = new Product(0L, "product1", 1.0, "desc", "category1", "", rating);
      Product product2 = new Product(1L, "product2", 0.53, "desc2", "category2", "", rating);

      CartProduct cartProduct1 = new CartProduct(1L, 10L);
      CartProduct cartProduct2 = new CartProduct(2L, 5L);
      Cart cart1 = new Cart(0L, 1L, LocalDateTime.now(), List.of(cartProduct1), null);
      Cart cart2 = new Cart(1L, 2L, LocalDateTime.now(), List.of(cartProduct1, cartProduct2), null);
      //When
      when(apiClient1.sendRequest("https://fakestoreapi.com/users")).thenReturn(mapper.writeValueAsString(List.of(user1, user2)));
      when(apiClient1.sendRequest("https://fakestoreapi.com/products")).thenReturn(mapper.writeValueAsString(List.of(product1, product2)));
      when(apiClient1.sendRequest("https://fakestoreapi.com/carts")).thenReturn(mapper.writeValueAsString(List.of(cart1, cart2)));
      //Then
      Optional<User> resultUser1 = apiClient1.getUsersData().stream()
            .filter(user -> user.getId() == 1L && Objects.equals(user.getUsername(), user2.getUsername()))
            .findFirst();
      Optional<User> resultUser2 = apiClient1.getUsersData().stream()
            .filter(user -> user.getId() == 0L && Objects.equals(user.getUsername(), user1.getUsername()))
            .findFirst();

      Optional<Product> resultProduct1 = apiClient1.getProductsData().stream()
            .filter(product -> product.getId() == 0L && Objects.equals(product.getTitle(), product1.getTitle()))
            .findFirst();
      Optional<Product> resultProduct2 = apiClient1.getProductsData().stream()
            .filter(product -> product.getId() == 1L && Objects.equals(product.getTitle(), product2.getTitle()))
            .findFirst();

      Optional<Cart> resultCart1 = apiClient1.getCartsData().stream()
            .filter(cart -> cart.getId() == 0L && cart.getProducts().size() == 1)
            .findFirst();
      Optional<Cart> resultCart2 = apiClient1.getCartsData().stream()
            .filter(cart -> cart.getId() == 1L && cart.getProducts().size() == 2)
            .findFirst();


      assertTrue(resultUser1.isPresent());
      assertTrue(resultUser2.isPresent());
      assertTrue(resultProduct1.isPresent());
      assertTrue(resultProduct2.isPresent());
      assertTrue(resultCart1.isPresent());
      assertTrue(resultCart2.isPresent());
   }

}
