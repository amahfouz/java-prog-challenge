package mahfouz.util;

/**
 *
 */
public final class Numbers {

    public static int gcd(int a, int b) {
        int c;
        while(a != 0 && b != 0) {
           c = b;
           b = a % b;
           a = c;
        }
        return a + b; // either one is 0, so return the non-zero value
      }

      public static int lcm(int a, int b) {
          return a * b / gcd(a, b);
      }

}
