package org.example.Api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.example.Api.Models.CartModels.Cart;
import org.example.Api.Models.ProductModels.Product;
import org.example.Api.Models.UserModels.User;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

@NoArgsConstructor
public class ApiClient {
   private final ObjectMapper objectMapper = new ObjectMapper()
         .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
         .findAndRegisterModules();

   private HttpClient createHttpClient() {
      return HttpClient.newBuilder()
            .connectTimeout(Duration.ofMillis(3 * 1000))
            .build();
   }

   private HttpRequest createHttpRequest(String uri) {
      return HttpRequest.newBuilder()
            .uri(URI.create(uri))
            .timeout(Duration.ofSeconds(20))
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .GET()
            .build();
   }

   public String sendRequest(String uri) {
      HttpClient httpClient = createHttpClient();
      HttpResponse<String> response;
      try {
         HttpRequest request = createHttpRequest(uri);
         response = httpClient.send(
               request,
               HttpResponse.BodyHandlers.ofString()
         );
      } catch (Exception e) {
         return "";
      }
      return response.body();
   }

   public List<User> getUsersData() {
      List<User> users;
      try {
         String response = sendRequest("https://fakestoreapi.com/users");
         users = List.of(objectMapper.readValue(response, User[].class));
      } catch (Exception e) {
         users = List.of();
      }

      return users;
   }

   public List<Cart> getCartsData() {
      List<Cart> carts;
      try {
         String response = sendRequest("https://fakestoreapi.com/carts");
         carts = List.of(objectMapper.readValue(response, Cart[].class));
      } catch (Exception e) {
         carts = List.of();
      }

      return carts;
   }

   public List<Product> getProductsData() {
      List<Product> products;
      try {
         String response = sendRequest("https://fakestoreapi.com/products");
         products = List.of(objectMapper.readValue(response, Product[].class));
      } catch (Exception e) {
         products = List.of();
      }

      return products;
   }
}
