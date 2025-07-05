import java.util.*;

class FoodMenu {
    private String menuName;
    private int price;
    private double rating;
    private String review;

    public FoodMenu(String menuName, int price, double rating, String review) {
        this.menuName = menuName;
        this.price = price;
        this.rating = rating;
        this.review = review;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }
}

class Restaurant {
    private String restaurantName;
    private String location;
    private List<FoodMenu> foodmenu;

    public Restaurant(String restaurantName, String location, List<FoodMenu> foodmenu) {
        this.restaurantName = restaurantName;
        this.location = location;
        this.foodmenu = foodmenu;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getLocation() {
        return location;
    }

    public List<FoodMenu> getFoodmenu() {
        return foodmenu;
    }
}

class RestaurantSolution {
    public static void FindRestaurantCountByLocation(List<Restaurant> restaurants, String location) {
        int count = 0;
        for (Restaurant r : restaurants) {
            if (r.getLocation().equalsIgnoreCase(location)) {
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No restaurants available for " + location);
        } else {
            System.out.println(count);
        }
    }

    public static void FindTheMostPreferredFoodByRating(List<Restaurant> restaurants, String restaurantName) {
        Restaurant target = null;

        for (Restaurant r : restaurants) {
            if (r.getRestaurantName().equalsIgnoreCase(restaurantName)) {
                target = r;
                break;
            }
        }

        if (target == null) {
            System.out.println("Restaurant not available");
            return;
        }

        FoodMenu best = null;
        for (FoodMenu fm : target.getFoodmenu()) {
            if (best == null || fm.getRating() > best.getRating()) {
                best = fm;
            }
        }

        if (best != null) {
            System.out.println("Most preferred menu in " + restaurantName + " is " + best.getMenuName() + " with rating " + best.getRating());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numRestaurants = Integer.parseInt(sc.nextLine());
        List<Restaurant> restaurants = new ArrayList<>();

        for (int i = 0; i < numRestaurants; i++) {
            String name = sc.nextLine();
            String location = sc.nextLine();
            int numFoodItems = Integer.parseInt(sc.nextLine());

            List<FoodMenu> foodItems = new ArrayList<>();
            for (int j = 0; j < numFoodItems; j++) {
                String menuName = sc.nextLine();
                int price = Integer.parseInt(sc.nextLine());
                double rating = Double.parseDouble(sc.nextLine());
                String review = sc.nextLine();
                foodItems.add(new FoodMenu(menuName, price, rating, review));
            }

            restaurants.add(new Restaurant(name, location, foodItems));
        }

        String searchLocation = sc.nextLine();
        String searchRestaurant = sc.nextLine();

        RestaurantSolution.FindRestaurantCountByLocation(restaurants, searchLocation);
        RestaurantSolution.FindTheMostPreferredFoodByRating(restaurants, searchRestaurant);
    }
}
