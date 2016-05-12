package cosc150.restaurantsearch;

import java.util.Random;

public class ReviewGenerator{
	//This fuction creates an array of user reviews, based on the Review class.
	//It takes as parameters the overall rating of a restaurant and the number of user reviews to be generated
	public static Review[] createReviews(double averageRating, int reviewCount){
		Random r = new Random();
		//Reviews can be generated in a determined range away from the overall rating
		double minRating = Math.max(0.0, averageRating - 1.5); 
		double maxRating = Math.min(5.0, averageRating + 1.5);
		
		//Several string options exist for parcing a review sentence, depending on the score of the generated review
		String first[] = {"What a ", "This is such a ", "I can't get over this ", "Go here if you like a ", "Never seen such a "};
		
		String awfulReview[] = {"disgusting, overrated, miserable ", "lowest common denominator of a ", "literally life-ruining levels of bad ", "honestly horrendous "};
		String badReview[] = {"below mediocre ", "not worth experiencing ", "waste of money ", "aggressively mediocre "};
		String okayReview[] = {"aggressively mediocre ", "not particular desireable ", "good enough I suppose "};
		String goodReview[] = {"worth every penny ", "nice and filling ", "pretty good ", "not bad in the slightest "};
		String greatReview[] = {"absolute pinnacle of a ", "amazing beyond all comparison ", "tippity-top rate ", "mind-blowing, world-changing, crazy-good "};
		
		String last[] = {"restaurant.", "experience.", "meal.", "establishment."};
		
		Review[] toReturn = new Review[reviewCount];
		for (int i = 0; i < reviewCount; i++){
			double userRating = minRating + (maxRating - minRating) * r.nextDouble();
			int reviewCase = (int)Math.floor(userRating);

			String userReview = first[r.nextInt(first.length)];
			switch (reviewCase){
				case 0: //if generated user rating rounds to 0, attach a random string from awfulReview
					userReview += awfulReview[r.nextInt(awfulReview.length)];
					break;
				case 1: //if generated user rating rounds to 1, attach a random string from badReview
					userReview += badReview[r.nextInt(badReview.length)];
					break;
				case 2: //if generated user rating rounds to 2, attach a random string from okayReview
					userReview += okayReview[r.nextInt(okayReview.length)];
					break;
				case 3: //if generated user rating rounds to 3, attach a random string from goodReview
					userReview += goodReview[r.nextInt(goodReview.length)];
					break;
				default: //any other higher rating, attach a random string from greatReview
					userReview += greatReview[r.nextInt(greatReview.length)];
					break;
			}
			userReview += last[r.nextInt(last.length)]; //attach a closing string to the user review
			
			toReturn[i] = new Review(userReview, userRating); //add completed review to the array
		}
		
		return toReturn;
	}
}
