package org.example;

import org.example.Api.ApiClient;
import org.example.Api.ApiService;
import org.example.Api.Models.CartModels.Cart;
import org.example.Api.Models.ProductModels.Product;
import org.example.Api.Models.UserModels.User;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
   public static void main(String[] args) throws IOException {
      ApiClient apiClient = new ApiClient();
      ApiService apiService = new ApiService();

      // task 1
      List<User> users = apiClient.getUsersData();
      List<Cart> carts = apiClient.getCartsData();
      List<Product> products = apiClient.getProductsData();

      // task 2
      Map<String, Double> categoriesValues = apiService.createCategoriesValues(products);
      System.out.println("Data structure containing all available product categories and the total value of\n" +
            "products of a given category " + categoriesValues + "\n");

      // task 3
      System.out.println(apiService.findCartWithHighestValueAndItsOwner(carts, products, users) + "\n");

      // task 4
      System.out.println(apiService.findTwoUsersLivingFurthestAway(users) + "\n");

      System.out.println("\nPress enter to exit");
      System.in.read();
   }
}