//                                  
//  BreadingToads.java              
//                                  
//                                  
//  Created by Nicholas Frandsen & Torbjoern Klatt on 10/2/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.    
//                                                            
import java.util.*;                                           

public class BreadingToads_old {

        ArrayList<Toad> toads = new ArrayList<Toad>();

        public static void main(String[] args){
                BreadingToads_old bt = new BreadingToads_old();
                bt.run();                              
        }                                              

        public void run(){
                readInput();
                for(int i = 0; i < toads.size(); i++){
                        toads.get(i).calculateNearest();
                        toads.get(i).calculateRadius(); 
                }                                       
                System.out.println("THE UPPER LIMIT OF TERITORY SIZE IS " + smallestRadius() + " UNITS");
                printAllToads();                                                                         
        }                                                                                                

        public void readInput(){
                int id = 0;     
                Scanner sc = new Scanner(System.in);
                while(sc.hasNext()){                
                        double x = sc.nextDouble(); 
                        double y = sc.nextDouble(); 
                        toads.add(id, new Toad(id,x,y));
                        id++;                           
                }                                       
        }                                               

        public double smallestRadius(){
                double smallest = 2001.0;
                for(int i = 0; i < toads.size(); i++){
                        if(toads.get(i).circleRadius < smallest){
                                smallest = toads.get(i).circleRadius;
                        }                                            
                }                                                    
                return smallest;                                     
        }                                                            

        public void printAllToads(){
                for(int i = 0; i < toads.size(); i++){
                        System.out.println("\nToad " + i + " is (" + toads.get(i).x + ", " + toads.get(i).y + ") with id " + toads.get(i).id);                                                                                                              
                        System.out.println("Nearest:");                                                                       
                        for(int j = 0; j < 12; j++){                                                                          
                                System.out.print(toads.get(i).nearest[j].id + ", ");                                          
                        }                                                                                                     
                        System.out.println("Radius: " + toads.get(i).circleRadius);                                           
                        System.out.println("\n");                                                                             
                }                                                                                                             
        }                                                                                                                     

        public class Toad{

                int id;
                double x, y;
                Toad[] nearest = new Toad[12];
                double circleRadius;          

                public Toad(int id, double x, double y){
                        this.id = id;                   
                        this.x = x;                     
                        this.y = y;                     
                }                                       

                public void calculateNearest(){
                        double[] distances = new double[toads.size()];
                        for(int i = 0; i < toads.size(); i++){        
                                if( i != this.id ){                   
                                        distances[i] = calculateDistance(toads.get(i));
                                } else{                                                
                                        distances[i] = 2001.0;                         
                                }                                                      
                        }                                                              
                        for(int i = 0; i < 12; i++){                                   
                                int smallestIndex = -1;                                
                                double smallest = 2001.0;                              
                                for(int z = 0; z < distances.length; z++){             
                                        if( distances[z] < smallest ){                 
                                                smallestIndex = z;                     
                                                smallest = distances[z];
                                        }
                                }
                                distances[smallestIndex] = 2001.0;
                                nearest[i] = toads.get(smallestIndex);
                        }

                }

                public void calculateRadius(){
                        double max = 0.0;
                        for(int i = 0; i < 12; i++){
                                for(int n = i+1; n < 12; n++){
                                        double distance = calculateDistance(nearest[i],nearest[n]);
                                        if(distance > max ){
                                                max = distance;
                                        }
                                }
                        }
                        for(int m = 0; m < nearest.length; m++){
                                double distance = calculateDistance(nearest[m]);
                                if(distance > max ){
                                        max = distance;
                                }
                        }
                        this.circleRadius = max/2;
                }

                public double calculateDistance(Toad toad){
                        double horizontal = Math.abs(this.x - toad.x);
                        double vertical = Math.abs(this.y - toad.y);
                        double distance = Math.sqrt(Math.pow(horizontal,2) + Math.pow(vertical,2));
                        return distance;
                }

                public double calculateDistance(Toad toad1, Toad toad2){
                        double horizontal = Math.abs(toad1.x - toad2.x);
                        double vertical = Math.abs(toad1.y - toad2.y);
                        double distance = Math.sqrt(Math.pow(horizontal,2) + Math.pow(vertical,2));
                        return distance;
                }
        }
}
