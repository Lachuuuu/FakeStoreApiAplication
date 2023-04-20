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

   private final HttpClient httpClient = createHttpClient();

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

   private HttpResponse<String> sendRequest(String uri) throws IOException, InterruptedException {
      return httpClient.send(
            createHttpRequest(uri),
            HttpResponse.BodyHandlers.ofString()
      );
   }

   public List<User> getUsersData() throws IOException, InterruptedException {
      HttpResponse<String> response = sendRequest("https://fakestoreapi.com/users");
      String responseJSON = response.body();

      return List.of(objectMapper.readValue(responseJSON, User[].class));
   }

   public List<Cart> getCartsData() throws IOException, InterruptedException {
      HttpResponse<String> response = sendRequest("https://fakestoreapi.com/carts");

      String responseJSON = response.body();
      return List.of(objectMapper.readValue(responseJSON, Cart[].class));
   }

   public List<Product> getProductsData() throws IOException, InterruptedException {
      HttpResponse<String> response = sendRequest("https://fakestoreapi.com/products");

      String responseJSON = response.body();
      return List.of(objectMapper.readValue(responseJSON, Product[].class));
   }
}
