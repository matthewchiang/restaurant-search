package cosc150.restaurantsearch;

public class Review {
	private String userReview;
	private double userRating;
	
	Review(String userReview, double userRating){
		this.userReview = userReview;
		this.userRating = userRating;
	}
	
	public String getUserReview(){
		return userReview;
	}
	
	public double getUserRating(){
		return userRating;
	}
}
