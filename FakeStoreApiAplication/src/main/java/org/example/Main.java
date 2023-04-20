package org.example;

import org.example.Api.ApiClient;
import org.example.Api.ApiService;
import org.example.Api.Models.CartModels.Cart;
import org.example.Api.Models.ProductModels.Product;
import org.example.Api.Models.UserModels.User;

import java.util.List;
import java.util.Map;

public class Main {
   public static void main(String[] args) {
      ApiClient apiClient = new ApiClient();
      ApiService apiService = new ApiService();

      List<User> users = null;
      List<Cart> carts = null;
      List<Product> products = null;
      try {
         users = apiClient.getUsersData();
         carts = apiClient.getCartsData();
         products = apiClient.getProductsData();
      } catch (Exception e) {
         System.out.println(e);
      }
      System.out.println(users);
      System.out.println(carts);
      System.out.println(products);

      Map<String, Double> categoriesValues = apiService.createCategoriesValues(products);

      System.out.println(categoriesValues);
      apiService.findCartWithHighestValueAndItsOwner(carts, products, users);
      apiService.findTwoUsersLivingFurthestAway(users);
   }
}