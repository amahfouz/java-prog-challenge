package mahfouz.hackerrank;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Solution for 
 * https://www.hackerrank.com/challenges/real-estate-broker
 */
public final class RealEstateBroker {


    static class Solution {

        /*
         * Convert to objects
         */
        static int realEstateBroker(int[][] clients, int[][] houses) {
            HouseSpec[] houseSpecs = new HouseSpec[houses.length];
            for (int i = 0; i < houseSpecs.length; i++) 
                houseSpecs[i] = new HouseSpec(houses[i][0], houses[i][1]);

            HouseSpec[] clientSpecs = new HouseSpec[clients.length];
            for (int i = 0; i < clientSpecs.length; i++) 
                clientSpecs[i] = new HouseSpec(clients[i][0], clients[i][1]);

            return computeNumSales(clientSpecs, houseSpecs); 
        }

        /**
         * Sort houses by actual area then by price.
         * Sort buyers by price.
         *
         * For every house:
         *   - Find a matching buyer who is willing to pay the least
         */
        
        public static int computeNumSales(HouseSpec[] buyers, HouseSpec[] houses) {
            Arrays.sort(houses, new ByArea());
            Arrays.sort(buyers, new ByPrice());
            boolean [] bought = new boolean[buyers.length];

            int sold = 0;
            int buyerIndex;
            
            for (HouseSpec house : houses) {
                buyerIndex = pickCheapestBuyer(house, buyers, bought);
                if (buyerIndex >= 0) {
                    bought[buyerIndex] = true;
                    sold++;
                }
            }
            return sold;
        }
        
        private static int pickCheapestBuyer
            (HouseSpec house, HouseSpec[] buyers, boolean[] bought) {

            int prospect = Arrays.binarySearch(buyers, house, new ByPrice());
            if (prospect < 0)
                prospect = - (prospect + 1);

            while (prospect < buyers.length) {
                HouseSpec buyer = buyers[prospect];
                
                if (! bought[prospect] && (buyer.area < house.area)) 
                    return prospect;
                
                prospect++;
            }
            
            return -1;
        }
        
        private static final class HouseSpec {

            private final int area;
            private final int price;
            
            public HouseSpec(int area, int price) {
                this.area = area;
                this.price = price;
            }
            
            public String toString() {
                return area + " " + price;
            }
        }    

        /**
         * Ascending by price.
         */
        private static final class ByPrice 
            implements Comparator<HouseSpec> {
    
            @Override
            public int compare(HouseSpec b1, HouseSpec b2) {
                return b1.price - b2.price;
            }
        }

        /**
         * Ascending area and ascending price
         */
        private static final class ByArea 
            implements Comparator<HouseSpec> {

            @Override
            public int compare(HouseSpec h1, HouseSpec h2) {
                return h1.area - h2.area;
            }
        }
        
        private static final Scanner scanner = new Scanner(System.in);

        public static void main(String[] args) throws IOException {

            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0].trim());

            int m = Integer.parseInt(nm[1].trim());

            int[][] clients = new int[n][2];

            for (int clientsRowItr = 0; clientsRowItr < n; clientsRowItr++) {
                String[] clientsRowItems = scanner.nextLine().split(" ");

                for (int clientsColumnItr = 0; clientsColumnItr < 2; clientsColumnItr++) {
                    int clientsItem = Integer.parseInt(clientsRowItems[clientsColumnItr].trim());
                    clients[clientsRowItr][clientsColumnItr] = clientsItem;
                }
            }

            int[][] houses = new int[m][2];

            for (int housesRowItr = 0; housesRowItr < m; housesRowItr++) {
                String[] housesRowItems = scanner.nextLine().split(" ");

                for (int housesColumnItr = 0; housesColumnItr < 2; housesColumnItr++) {
                    int housesItem = Integer.parseInt(housesRowItems[housesColumnItr].trim());
                    houses[housesRowItr][housesColumnItr] = housesItem;
                }
            }

            int result = realEstateBroker(clients, houses);

            System.out.println(String.valueOf(result));
        }
    }
}
