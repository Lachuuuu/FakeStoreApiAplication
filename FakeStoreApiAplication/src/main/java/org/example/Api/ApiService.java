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

      for (Product product : products) {
         categoriesValues.merge(product.getCategory(), product.getPrice(), Double::sum);
      }

      return categoriesValues;
   }

   public void findCartWithHighestValueAndItsOwner(List<Cart> carts, List<Product> products, List<User> users) {
      double highestCartValue = -1D;
      Cart highestValueCart = null;
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
         Optional<User> highestValueCartOwner = users.stream().filter(user -> user.getId().equals(finalHighestValueCart.getUserId())).findFirst();
         System.out.println(
               "najwieksze value carta = " +
                     highestCartValue +
                     " wlascicielem jest " +
                     highestValueCartOwner.get().getName().getFirstname() +
                     " " +
                     highestValueCartOwner.get().getName().getLastname()
         );
      }
   }

   public void findTwoUsersLivingFurthestAway(List<User> users) {
      double maxDistance = 0D;
      User maxDistanceUser1 = null;
      User maxDistanceUser2 = null;
      for (User user1 : users) {
         double distance;
         for (User user2 : users) {
            if (!user1.equals(user2)) {
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

      System.out.println("___________________");
      System.out.println(maxDistance);
      System.out.println(maxDistanceUser1.getName().getFirstname() + " " + maxDistanceUser1.getName().getLastname());
      System.out.println(maxDistanceUser1.getAddress().getGeolocation().getLat());
      System.out.println(maxDistanceUser1.getAddress().getGeolocation().getLon());
      System.out.println(maxDistanceUser2.getName().getFirstname() + " " + maxDistanceUser2.getName().getLastname());
      System.out.println(maxDistanceUser2.getAddress().getGeolocation().getLat());
      System.out.println(maxDistanceUser2.getAddress().getGeolocation().getLon());

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


   //acos(sin(lat1)*sin(lat2)+cos(lat1)*cos(lat2)*cos(lon2-lon1))*6371

}
