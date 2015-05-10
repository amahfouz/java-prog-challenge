/*
 * CONFIDENTIAL
 * Copyright 2013 Webalo, Inc.  All rights reserved.
 */

package mahfouz.google.codejam.y2013.round1a;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Scanner;

/**
 * Solution of "Bullseye" problem (Round 1A 2013)
 */
public final class BullsEye {

    // Tricks:
    //  - Use an arithmetic sequence sum of the painted areas
    //  - Do not use double as precision will be lost in computation

    private static final BigDecimal SQRT_DIG = new BigDecimal(150);
    private static final BigDecimal SQRT_PRE = new BigDecimal(10).pow(SQRT_DIG.intValue());

    /**
     * Compute the square root of a BigDecimal.
     */
    private static BigDecimal sqrtNewtonRaphson  (BigDecimal c, BigDecimal xn, BigDecimal precision){
        BigDecimal fx = xn.pow(2).add(c.negate());
        BigDecimal fpx = xn.multiply(new BigDecimal(2));
        BigDecimal xn1 = fx.divide(fpx,2*SQRT_DIG.intValue(),RoundingMode.HALF_DOWN);
        xn1 = xn.add(xn1.negate());
        BigDecimal currentSquare = xn1.pow(2);
        BigDecimal currentPrecision = currentSquare.subtract(c);
        currentPrecision = currentPrecision.abs();
        if (currentPrecision.compareTo(precision) <= -1){
            return xn1;
        }
        return sqrtNewtonRaphson(c, xn1, precision);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();

        for (int i = 0; i < numCases; i++) {
            BigInteger r = scanner.nextBigInteger();
            BigInteger t = scanner.nextBigInteger();

            solve(i + 1, r, t);
        }
    }

    private static void solve(int caseNum, BigInteger r, BigInteger t) {

        BigDecimal a = new BigDecimal(2);
        BigDecimal b = new BigDecimal(r.multiply(BigInteger.valueOf(2)).subtract(BigInteger.ONE));
        BigDecimal c = new BigDecimal(t.negate());

        BigDecimal twoA = a.multiply(new BigDecimal(2));
        BigDecimal bSquared = b.multiply(b);
        BigDecimal fourAc = a.multiply(c).multiply(new BigDecimal(4));

        BigDecimal sqrt = sqrtNewtonRaphson(bSquared.subtract(fourAc), b, new BigDecimal(1).divide(SQRT_PRE));

        BigDecimal quadRoot = (b.negate().add(sqrt)).divide(twoA);

        System.out.println("Case #" + caseNum + ": " + quadRoot.toBigInteger());
    }
}
