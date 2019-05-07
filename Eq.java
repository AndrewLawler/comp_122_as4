public class Eq {
     /** test euqlity of two arrays of double up to a given delta 
      *
      * @Param a one Array of doubles
      * @Param b another Array of doubles
      * @Param delta the error up to which we check equality
      * @return true if, and only if, a ≈ b up to error delta.
      * */
    public static boolean eq(double[] a, double[] b, double delta){
        boolean res = (a.length == b.length);
        if (res) {
            for (int i=0; i<a.length ; i++ ) {
                res = res && ( delta > Math.abs(a[i]-b[i]));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        double[] expected  = new double[]{ 1.0/3, 1.0/3, 1.0/3};
        double[] computed1 = new double[]{ 1.0/3, 1.0/3};
        double[] computed2 = new double[]{ 1.0/3, 1.0/3, 1.1/3};
        double[] computed3 = new double[]{ 1.0/3, 1.0/3, 1.0001/3};

        System.out.println(eq(expected, computed1, 0.01));
        System.out.println(eq(expected, computed2, 0.01));
        System.out.println(eq(expected, computed3, 0.001));
    }
}

