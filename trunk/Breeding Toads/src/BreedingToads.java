import java.util.ArrayList;

/**
 * 
 *
 */
public class BreedingToads {
	ArrayList<Toad> toads = new ArrayList<Toad>();
	final static double MAX_DISTANCE = 2829.0; // sqrt(pow(2000,2)+pow(2000,2))
	
	public static void main(String[] args) {
		BreedingToads bt = new BreedingToads();
		bt.run();
	}
	
	/**
	 * starts the routine
	 */
	public void run() {
		readInput();
		for(int i = 0; i < toads.size(); i++){
			toads.get(i).calculateNearest();
			toads.get(i).calculateRadius();
		}
        System.out.println("THE UPPER LIMIT OF TERITORY SIZE IS " + smallestRadius() + " UNITS");
		printAllToads();
	}
	
	/**
	 * reads in the toads from the file "Toats.in"
	 */
	public void readInput() {
		FileInput in = new FileInput("Toats.in");
		String[] inStr;
		double x, y;
		for(int i = 0; i < in.getLineCount(); i++) {
			inStr = in.getString(i).split(" ");
			if(inStr.length == 2) {
				x = Double.parseDouble(inStr[0]);
				y = Double.parseDouble(inStr[1]);
				toads.add(i, new Toad(i,x,y));
			}
		}
	}
	
	public void printAllToads() {
		for(int i = 0; i < toads.size(); i++) {
            System.out.println("\nToad " + i + " is (" + toads.get(i).x + ", " + toads.get(i).y + ") with id " + toads.get(i).id);                                                                                                              
            System.out.println("Nearest:");                                                                       
            for(int j = 0; j < 12; j++) {                                                                          
            	System.out.print(toads.get(i).nearest[j].id + ", ");                                          
            }                                                                                                     
            System.out.println("Radius: " + toads.get(i).circleRadius);                                           
            System.out.println("\n");    
		}
	}                                          

	public double smallestRadius() {
    	double smallest = 2001.0;
    	for(int i = 0; i < toads.size(); i++) {
    		if(toads.get(i).circleRadius < smallest) {
    			smallest = toads.get(i).circleRadius;
    		}                                            
    	}                                                    
    	return smallest;                                     
	}  
	
	/**
	 * 
	 *
	 */
	public class Toad{
		int id;
		double x, y;
		Toad[] nearest = new Toad[13];
		double circleRadius;
		
		/**
		 * constructor for a toad
		 */
		public Toad(int id, double x, double y) {
			this.id = id;
			this.x = x;
			this.y = y;
		}
		
		/**
		 * calculates the 12 nearest neighbors of this toad
		 * and stores the distances in the array
		 */
		public void calculateNearest() {
			int smallestIndex = -1;
			double smallest = MAX_DISTANCE;
			double[] distances = new double[toads.size()];
			// calculates the distances between this and all other toads
			for(int i = 0; i < toads.size(); i++) {
				if(i != this.id) {
					distances[i] = calculateDistance(toads.get(i));
				}
				else {
					distances[i] = MAX_DISTANCE;
				}
			}
			
			// searches for the 12 smallest distances
			// after this loop these 12 distances are set to be MAX_DISTANCE
			// --> previous calculated distances are deleted
			for(int i = 0; i < 13; i++) {
				smallestIndex = -1;
				smallest = MAX_DISTANCE;
				for(int z = 0; z < distances.length; z++) {
					if(distances[z] < smallest) {
						smallestIndex = z;
						smallest = distances[z];
					}
				}
				distances[smallestIndex] = MAX_DISTANCE;
				nearest[i] = toads.get(smallestIndex);
			}
		}
		
		/**
		 * 
		 */
		public void calculateRadius() {
			double max = 0.0;
			for(int i = 0; i < 12; i++) {
				for(int n = i+1; n < 12; n++) {
					double distance = calculateDistance(nearest[i],nearest[n]);
					if(distance > max ) max = distance;
				}
			}
			for(int m = 0; m < nearest.length; m++) {
				double distance = calculateDistance(nearest[m]);
				if(distance > max ) max = distance;
			}
			this.circleRadius = max / 2;
		}
		
		/**
		 * calculates the distance between this toad and toad
		 * @param toad
		 * @return distance between this and toad
		 */
		public double calculateDistance(Toad toad) {
			double horizontal = Math.abs(this.x - toad.x);
			double vertical = Math.abs(this.y - toad.y);
			double distance = Math.sqrt(Math.pow(horizontal,2) + Math.pow(vertical,2));
			return distance;
		}
		
		/**
		 * calculates the distance between toad1 and toad2
		 * @param toad1
		 * @param toad2
		 * @return distance between toad1 and toad2
		 */
		public double calculateDistance(Toad toad1, Toad toad2) {
			double horizontal = Math.abs(toad1.x - toad2.x);
			double vertical = Math.abs(toad1.y - toad2.y);
			double distance = Math.sqrt(Math.pow(horizontal,2) + Math.pow(vertical,2));
			return distance;
		}
	}
}