package lab00.bonus.src.main;

import java.math.BigInteger;

public class SquareRoot {
    public static BigInteger integerSquareRoot(BigInteger n) {
        if (n.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Square root is only defined for non-negative numbers");
        }
        
        BigInteger left = BigInteger.ZERO;
        BigInteger right = n;
        
        while (left.compareTo(right) <= 0) {
            BigInteger mid = left.add(right).shiftRight(1);
            BigInteger square = mid.multiply(mid);
            if (square.compareTo(n) == 0) {
                return mid;
            } else if (square.compareTo(n) < 0) {
                left = mid.add(BigInteger.ONE);
            } else {
                right = mid.subtract(BigInteger.ONE);
            }
        }
        
        return right;
    }

    public static void main(String[] args) {
        BigInteger num = new BigInteger("100");
        System.out.println("Integer part of square root of " + num + ": " + integerSquareRoot(num));
    }
}
