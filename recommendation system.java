import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.io.IOException;
import java.util.List;
public class RecommendationSystem {

    public static void main(String[] args) throws TasteException, IOException {
        // Load user-item interaction data
        DataModel dataModel = new FileDataModel(new File("user-item-data.csv"));

        // Create a user-based recommender
        UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
        NearestNUserNeighborhood neighborhood = new NearestNUserNeighborhood(10, similarity, dataModel);
        Recommender recommender = new GenericUserBasedRecommender(dataModel, neighborhood, similarity);

        // Get recommendations for a user
        long userID = 1;
        List<RecommendedItem> recommendations = recommender.recommend(userID, 10);

        // Print recommendations
        System.out.println("Recommendations for user " + userID + ":");
        for (RecommendedItem recommendation : recommendations) {
            System.out.println(recommendation);
        }
    }
}
