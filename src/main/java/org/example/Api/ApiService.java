package org.example.Api;

import lombok.NoArgsConstructor;
import org.example.Api.Models.CartModels.Cart;
import org.example.Api.Models.ProductModels.Product;
import org.example.Api.Models.UserModels.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@NoArgsConstructor
public class ApiService {
   public Map<String, Double> createCategoriesValues(List<Product> products) {
      Map<String, Double> categoriesValues = new HashMap<>();
      if (products != null) for (Product product : products) {
         if (productPriceAndCategoryIsCorrect(product))
            categoriesValues.merge(product.getCategory(), product.getPrice(), Double::sum);
      }
      return categoriesValues;
   }

   public String findCartWithHighestValueAndItsOwner(List<Cart> carts, List<Product> products, List<User> users) throws IllegalArgumentException {
      if (users == null || carts == null || products == null)
         throw new IllegalArgumentException("ERROR parameters cannot be null");
      double highestCartValue = -1D;
      Cart highestValueCart = null;
      Optional<User> highestValueCartOwner = Optional.empty();

      for (Cart cart : carts) {
         double currentCartValue = cart.getProducts()
               .stream()
               .mapToDouble(cartProduct -> {
                  Optional<Product> currentProduct = products.stream()
                        .filter(product -> product.getId().equals(cartProduct.getProductId()))
                        .findFirst();
                  return currentProduct.map(product -> product.getPrice() * cartProduct.getQuantity()).orElse(0D);
               })
               .sum();
         if (currentCartValue > highestCartValue) {
            highestCartValue = currentCartValue;
            highestValueCart = cart;
         }
      }

      if (highestValueCart != null) {
         Cart finalHighestValueCart = highestValueCart;
         highestValueCartOwner = users.stream().filter(user -> user.getId().equals(finalHighestValueCart.getUserId())).findFirst();
      }
      return "Highest cart value = " +
            highestCartValue +
            " | owned by = " +
            (highestValueCartOwner.isPresent() ? highestValueCartOwner.get().getName().getFirstname() : "") +
            " " +
            (highestValueCartOwner.isPresent() ? highestValueCartOwner.get().getName().getLastname() : "");
   }

   public String findTwoUsersLivingFurthestAway(List<User> users) throws IllegalArgumentException {
      if (users == null || users.size() < 2)
         throw new IllegalArgumentException("ERROR: list of users should contains at least 2 users");

      double maxDistance = -1D;
      User maxDistanceUser1 = null;
      User maxDistanceUser2 = null;
      for (User user1 : users) {
         double distance;
         if (!userAddressIsCorrect(user1)) continue;
         for (User user2 : users) {
            if (!user1.equals(user2)) {
               if (!userAddressIsCorrect(user2)) continue;
               distance = calculateDistance(
                     user1.getAddress().getGeolocation().getLat(),
                     user1.getAddress().getGeolocation().getLon(),
                     user2.getAddress().getGeolocation().getLat(),
                     user2.getAddress().getGeolocation().getLon()
               );

               if (distance > maxDistance) {
                  maxDistance = distance;
                  maxDistanceUser1 = user1;
                  maxDistanceUser2 = user2;
               }
            }
         }
      }

      return "Users that lives furthest away are: " +
            maxDistanceUser1 +
            " and " +
            maxDistanceUser2 +
            " distance between these 2 users = "
            + String.format("%.2f", maxDistance) + " km";
//            + " lat1=" + maxDistanceUser1.getAddress().getGeolocation().getLat()
//            + " lon1=" + maxDistanceUser1.getAddress().getGeolocation().getLon()
//            + " lat2=" + maxDistanceUser2.getAddress().getGeolocation().getLat()
//            + " lon2=" + maxDistanceUser2.getAddress().getGeolocation().getLon();
   }

   private boolean userAddressIsCorrect(User user) {
      return (user.getAddress() != null &&
            user.getAddress().getGeolocation() != null &&
            user.getAddress().getGeolocation().getLat() != null &&
            user.getAddress().getGeolocation().getLon() != null
      );
   }

   private boolean productPriceAndCategoryIsCorrect(Product product) {
      return (product != null &&
            product.getPrice() != null &&
            product.getCategory() != null
      );
   }

   private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
      double R = 6371;

      lat1 = Math.toRadians(lat1);
      lon1 = Math.toRadians(lon1);
      lat2 = Math.toRadians(lat2);
      lon2 = Math.toRadians(lon2);

      double dlat = lat2 - lat1;
      double dlon = lon2 - lon1;
      double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) + Math.cos(lat1) * Math.cos(lat2) * Math.sin(dlon / 2) * Math.sin(dlon / 2);
      double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
      return R * c;
   }

}
