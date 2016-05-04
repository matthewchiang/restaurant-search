package cosc150.restaurantsearch;

public class Restaurant {
    String restaurantCategory;
    String restaurantName;
    String restaurantDescription;
    double restaurantRating;

    public Restaurant(){
        this.restaurantCategory = "";
        this.restaurantName = "";
        this.restaurantDescription = "";
        this.restaurantRating = 0;
    }

    public Restaurant(String restaurantCategory, String restaurantName, String restaurantDescription, double restaurantRating){
        this.restaurantCategory = restaurantCategory;
        this.restaurantName = restaurantName;
        this.restaurantDescription = restaurantDescription;
        this.restaurantRating = restaurantRating;
    }
}
