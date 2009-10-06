public class SinSeries {
    public static void main(String[] args) {
        System.out.println("Sin Series - 5.000 steps");
        System.out.println("************************");
        System.out.println("single precision");
        float s1 = sinSerieS(5000,(float)0.998);
        float s2 = sinSerieS(5000,(float)0.995);
        float s3 = sinSerieS(5000,(float)0.994);
        float s4 = sinSerieS(5000,(float)12.345);
        System.out.println("x=0.998:   " + s1);
        System.out.println("x=0.995:   " + s2);
        System.out.println("x=0.994:   " + s3);
        System.out.println("x=12.345: " + s4);
        System.out.println("\ndouble precision");
        double d1 = sinSerieD(5000,(double)0.998);
        double d2 = sinSerieD(5000,(double)0.995);
        double d3 = sinSerieD(5000,(double)0.994);
        double d4 = sinSerieD(5000,(double)12.345);
        System.out.println("x=0.998:   " + d1);
        System.out.println("x=0.995:   " + d2);
        System.out.println("x=0.994:   " + d3);
        System.out.println("x=12.345: " + d4);
        System.out.println("\ndifference (in double precision)");
        System.out.println("x=0.998:  " + (double)(Math.abs(s1 - d1)));
        System.out.println("x=0.995:  " + (double)(Math.abs(s2 - d2)));
        System.out.println("x=0.994:  " + (double)(Math.abs(s3 - d3)));
        System.out.println("x=12.345: " + (double)(Math.abs(s4 - d4)));
        System.out.println("\nbuilt-in sin (single precision)");
        float bs1 = (float)Math.sin((float)0.998);
        float bs2 = (float)Math.sin((float)0.995);
        float bs3 = (float)Math.sin((float)0.994);
        float bs4 = (float)Math.sin((float)12.345);
        System.out.println("x=0.998:   " + bs1);
        System.out.println("x=0.995:   " + bs2);
        System.out.println("x=0.994:   " + bs3);
        System.out.println("x=12.345: " + bs4);
        System.out.println("\nbuilt-in sin (double precision)");
        double bd1 = Math.sin((double)0.998);
        double bd2 = Math.sin((double)0.995);
        double bd3 = Math.sin((double)0.994);
        double bd4 = Math.sin((double)12.345);
        System.out.println("x=0.998:   " + bd1);
        System.out.println("x=0.995:   " + bd2);
        System.out.println("x=0.994:   " + bd3);
        System.out.println("x=12.345: " + bd4);
        System.out.println("\ndifference (in double precision)");
        System.out.println("x=0.998:  " + (double)(Math.abs(bs1 - bd1)));
        System.out.println("x=0.995:  " + (double)(Math.abs(bs2 - bd2)));
        System.out.println("x=0.994:  " + (double)(Math.abs(bs3 - bd3)));
        System.out.println("x=12.345: " + (double)(Math.abs(bs4 - bd4)));
        System.out.println("\ndifference (in double precision) between custom and built-in function");
        System.out.println("x=0.998:  " + (double)(Math.abs(d1 - bd1)));
        System.out.println("x=0.995:  " + (double)(Math.abs(d2 - bd2)));
        System.out.println("x=0.994:  " + (double)(Math.abs(d3 - bd3)));
        System.out.println("x=12.345: " + (double)(Math.abs(d4 - bd4)));
        System.out.println("x=123.45:" + Math.sin(123.45) + " " + sinSerieD(5000,123.45) + " " + sinSerieS(5000,(float) 123.45));
    }
    
    public static float sinSerieS(int steps, float x) {
        // following lines were too smart
//        if((x % (float)Math.PI) == 0) return 0;
//        if((x % (float)(Math.PI / 2)) == 0) return 1;
//        while(x > (Math.PI)) {
//            x -= (float)(2 * (float)Math.PI);
//        }
        float anm1 = x, an1 = 0, sum = x, ratio = 0;
        float xsquare = (float)Math.pow(x, 2);
        for(int i = 2; i < steps+1; i++) {
            ratio = ((2 * i) - 2) * ((2 * i) - 1);
            an1 = (float)(-1) * ((xsquare / ratio) * anm1);
            sum += an1;
            anm1 = an1;
        }
        return sum;
    }
    
    public static double sinSerieD(int steps, double x) {
        // following lines were too smart
//        if((x % Math.PI) == 0) return 0;
//        if((x % (Math.PI / 2)) == 0) return 1;
//        while(x > (Math.PI)) {
//            x -= (2 * Math.PI);
//        }
        double anm1 = x, an1 = 0, sum = x, ratio = 0;
        double xsquare = Math.pow(x, 2);
        for(int i = 2; i < steps+1; i++) {
            ratio = ((2 * i) - 2) * ((2 * i) - 1);
            an1 = -(xsquare / ratio) * anm1;
            sum += an1;
            anm1 = an1;
            }
        return sum;
    }
}