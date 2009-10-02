public class HarmonicSums {
  public static void main(String[] args) {
    System.out.println("Harmonic Sums - 5.000 steps");
    System.out.println("***************************");
    System.out.println("single precision");
    float s1 = harmonicSum(1000);
    float s2 = harmonicSum(2000);
    float s3 = harmonicSum(3000);
    float s4 = harmonicSum(4000);
    float s5 = harmonicSum(5000);
    float sr1 = harmonicSumReverse(1000);
    float sr2 = harmonicSumReverse(2000);
    float sr3 = harmonicSumReverse(3000);
    float sr4 = harmonicSumReverse(4000);
    float sr5 = harmonicSumReverse(5000);
    System.out.println("ascending  (1k): " + s1);
    System.out.println("descending (1k): " + sr1);
    System.out.println("difference (1k): " + (float)Math.abs((float)(s1-sr1)));
    System.out.println("ascending  (2k): " + s2);
    System.out.println("descending (2k): " + sr2);
    System.out.println("difference (2k): " + (float)Math.abs((float)(s2-sr2)));
    System.out.println("ascending  (3k): " + s3);
    System.out.println("descending (3k): " + sr3);
    System.out.println("difference (3k): " + (float)Math.abs((float)(s3-sr3)));
    System.out.println("ascending  (4k): " + s4);
    System.out.println("descending (4k): " + sr4);
    System.out.println("difference (4k): " + (float)Math.abs((float)(s4-sr4)));
    System.out.println("ascending  (5k): " + s5);
    System.out.println("descending (5k): " + sr5);
    System.out.println("difference (5k): " + (float)Math.abs((float)(s5-sr5)));
    System.out.println("\ndouble precision");
    double d1 = harmonicSumDouble(1000);
    double d2 = harmonicSumDouble(2000);
    double d3 = harmonicSumDouble(3000);
    double d4 = harmonicSumDouble(4000);
    double d5 = harmonicSumDouble(5000);
    double dr1 = harmonicSumDoubleReverse(1000);
    double dr2 = harmonicSumDoubleReverse(2000);
    double dr3 = harmonicSumDoubleReverse(3000);
    double dr4 = harmonicSumDoubleReverse(4000);
    double dr5 = harmonicSumDoubleReverse(5000);
    System.out.println("ascending  (1k): " + d1);
    System.out.println("descending (1k): " + dr1);
    System.out.println("difference (1k): " + Math.abs((double)(d1-dr1)));
    System.out.println("ascending  (2k): " + d2);
    System.out.println("descending (2k): " + dr2);
    System.out.println("difference (2k): " + Math.abs((double)(d2-dr2)));
    System.out.println("ascending  (3k): " + d3);
    System.out.println("descending (3k): " + dr3);
    System.out.println("difference (3k): " + Math.abs((double)(d3-dr3)));
    System.out.println("ascending  (4k): " + d4);
    System.out.println("descending (4k): " + dr4);
    System.out.println("difference (4k): " + Math.abs((double)(d4-dr4)));
    System.out.println("ascending  (5k): " + d5);
    System.out.println("descending (5k): " + dr5);
    System.out.println("difference (5k): " + Math.abs((double)(d5-dr5)));
    System.out.println("\ndifference between single and double");
    System.out.println("ascending  (1k): " + Math.abs((double)(s1-d1)));
    System.out.println("descending (1k): " + Math.abs((double)(sr1-dr1)));
    System.out.println("ascending  (2k): " + Math.abs((double)(s2-d2)));
    System.out.println("descending (2k): " + Math.abs((double)(sr2-dr2)));
    System.out.println("ascending  (3k): " + Math.abs((double)(s3-d3)));
    System.out.println("descending (3k): " + Math.abs((double)(sr3-dr3)));
    System.out.println("ascending  (4k): " + Math.abs((double)(s4-d4)));
    System.out.println("descending (4k): " + Math.abs((double)(sr4-dr4)));
    System.out.println("ascending  (5k): " + Math.abs((double)(s5-d5)));
    System.out.println("descending (5k): " + Math.abs((double)(sr5-dr5)));
  }
  
  public static float harmonicSum(int steps) {
    float sum = 0;
    for(int i = 1; i < steps+1; i++) {
      sum += (float)((float)1 / (float)i);
    }
    return sum;
  }
  
  public static float harmonicSumReverse(int steps) {
    float sum = 0;
    for(int i = steps; i > 0; i--) {
      sum += (float)((float)1 / (float)i);
    }
    return sum;
  }
  
  public static double harmonicSumDouble(int steps) {
    double sum = 0;
    for(int i = 1; i < steps+1; i++) {
      sum += (double)( (double)1 / (double)i);
    }
    return sum;
  }
  
  public static double harmonicSumDoubleReverse(int steps) {
    double sum = 0;
    for(int i = steps; i > 0; i--) {
      sum += (double)((double)1 / (double)i);
    }
    return sum;
  }
}