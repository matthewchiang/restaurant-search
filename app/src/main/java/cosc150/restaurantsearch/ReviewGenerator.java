package cosc150.restaurantsearch;

import java.util.Random;

public class ReviewGenerator{
	public static Review[] createReviews(double averageRating, int reviewCount){
		Random r = new Random();
		double minRating = Math.max(0.0, averageRating - 0.5);
		double maxRating = Math.min(5.0, averageRating + 0.5);
		
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
				case 0:
					userReview += awfulReview[r.nextInt(awfulReview.length)];
					break;
				case 1:
					userReview += badReview[r.nextInt(badReview.length)];
					break;
				case 2:
					userReview += okayReview[r.nextInt(okayReview.length)];
					break;
				case 3:
					userReview += goodReview[r.nextInt(goodReview.length)];
					break;
				default:
					userReview += greatReview[r.nextInt(greatReview.length)];
					break;
			}
			userReview += last[r.nextInt(last.length)];
			
			toReturn[i] = new Review(userReview, userRating);
		}
		
		return toReturn;
	}
}