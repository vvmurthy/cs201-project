package utilities;

/**
 * Stores a preference object of another person,
 * self, and a match value which the objects are sorted by
 * @author veda
 *
 */
public class ComparisonHolder implements Comparable<ComparisonHolder>{

	public double percent;
	public FilledPreferences self;
	public FilledPreferences other;
	
	@Override
	public int compareTo(ComparisonHolder arg0) {
		// TODO Auto-generated method stub
		if(percent > arg0.percent) {
			return 1;
		}else if(percent < arg0.percent) {
			return -1;
		}
	
		return 0;
	}
	
	@Override
	public boolean equals(Object o) {
		
		if(!(o instanceof ComparisonHolder)){
			return false;
		}
		
		ComparisonHolder other = (ComparisonHolder)o;
		
		return other.self.userId == self.userId && 
				other.other.userId == this.other.userId;		
	}

}
