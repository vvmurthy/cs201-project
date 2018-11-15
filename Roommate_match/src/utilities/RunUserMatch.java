package utilities;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class RunUserMatch {

	public static List<ProfileInfo> getMatches(FilledPreferences self, int selfUserId){
		
		// Check that self is not null
		if(self == null) {
			throw new AssertionError("self could not be found");
		}
		
		// Get all other user profiles from SQL
		List<FilledPreferences> others = SqlDriver.getOtherPreferences(selfUserId);
		
		// Get the historical matches
		List<ComparisonHolder> historical = SqlDriver.getHistoricalMatches(selfUserId, self);
		
		// compare the matches
		PriorityQueue<ComparisonHolder> sorted = new PriorityQueue<>();
		for(FilledPreferences other : others) {
			
			double percent = self.compare(other);
			ComparisonHolder ch = new ComparisonHolder();
			ch.self = self;
			ch.other = other;
			ch.percent = (int)(Math.round(percent  * 100) / 100);
			sorted.add(ch);
			
			// Remove the old match if applicable and add the new one in 
			// ONLY IF NOT GUEST
			if(selfUserId != -1) {
				SqlDriver.insertNewMatch(ch);
			}
		}
		
		// put only the top 5 matches in the response
		int matches = 5;
		List<ProfileInfo> profiles = new LinkedList<>();
		while(!sorted.isEmpty() && matches > 0) {
			ComparisonHolder ch = sorted.remove();
			ProfileInfo profile = SqlDriver.getUserProfile(ch.other.getUserId());
			profile.setComparisonHolder(ch);
			profiles.add(profile);
			matches--;
		}
		return profiles;
	}
	
}
