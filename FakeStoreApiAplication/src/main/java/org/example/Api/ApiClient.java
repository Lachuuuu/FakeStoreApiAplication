package org.example.Api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.example.Api.Models.CartModels.Cart;
import org.example.Api.Models.ProductModels.Product;
import org.example.Api.Models.UserModels.User;

import java.io.IOException;
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

   public void getUsersData() throws IOException, InterruptedException {
      HttpClient httpClient = createHttpClient();
      HttpResponse<String> response = httpClient.send(
            createHttpRequest("https://fakestoreapi.com/users"),
            HttpResponse.BodyHandlers.ofString()
      );

      HttpResponse<String> responseCarts = httpClient.send(
            createHttpRequest("https://fakestoreapi.com/carts"),
            HttpResponse.BodyHandlers.ofString()
      );

      HttpResponse<String> responseProducts = httpClient.send(
            createHttpRequest("https://fakestoreapi.com/products"),
            HttpResponse.BodyHandlers.ofString()
      );

      String responseUsersJson = response.body();
      String responseCartsJson = responseCarts.body();
      String responseProductsJson = responseProducts.body();

      List<User> users = List.of(objectMapper.readValue(responseUsersJson, User[].class));
      List<Cart> carts = List.of(objectMapper.readValue(responseCartsJson, Cart[].class));
      List<Product> products = List.of(objectMapper.readValue(responseProductsJson, Product[].class));

      System.out.println(users);

   }
}
