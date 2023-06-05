# FakeStoreApiAplication  

# Used Technologies  
- Lombok  
- Jackson  
- JUnit  
- Mockito  
- Maven  

# Additional Informations
- Main -> Contains working program that:   
  1. Retrieves user, product and shopping cart data  
  2. Creates a data structure containing all available product categories and the total value of
  products of a given category  
  3. Finds a cart with the highest value, determines its value and full name of its owner  
  4. Finds the two users living furthest away from each other
  
- ApiService -> Provides functions to:
  1. Find cart with highest Value and it's owner based on given lists of carts, products and users
  2. Create Map<String,Double> containing all products categories and total value of products in category, based on list of products
  3. Find two users living furthest away, based on list of users
  
- ApiClient -> Provides functions to:  
  1. Get data from "https://fakestoreapi.com/users"    
  2. Get data from "https://fakestoreapi.com/carts"  
  3. Get data from "https://fakestoreapi.com/products"  
