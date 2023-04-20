package org.example;

import org.example.Api.ApiClient;

public class Main {
   public static void main(String[] args) {
      ApiClient apiClient = new ApiClient();
      try {
         apiClient.getUsersData();
      }catch (Exception e){
         System.out.println(e);
      }
   }
}