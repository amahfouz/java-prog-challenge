import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigInteger;
import java.util.ArrayList;

public class ManageYourEnergyModel {

        public static String getMaxGain(int E, int R, ArrayList<Integer> vArr){
                int current = E;
                if(R > E) R=E;
                BigInteger totalGain = new BigInteger("0");
                int n = (E+R-1)/R;
                for(int i = 0; i < vArr.size(); i++){
                        int v = vArr.get(i);
                        int nextMax = getNextMaxPos(vArr, v, i, n);
                        if(nextMax != -1){
                                int energy = (nextMax -i)*R;
                                //Energy we can spend on current task
                                int x = current+energy-E;
                                if(x < 0){
                                        x = 0;
                                }
                                if(x > current){
                                        x = current;
                                }
                                BigInteger gain = new BigInteger(""+x);
                                gain = gain.multiply(new BigInteger(""+v));
                                totalGain = totalGain.add(gain);
                                //Enery for next task
                                current = current -x + R;
                                if(current > E)
                                {
                                        current = E;
                                }
                        }else{
                                BigInteger gain = new BigInteger(""+current);
                                gain = gain.multiply(new BigInteger(""+v));
                                totalGain = totalGain.add(gain);
                                current = R;
                        }
                }
                return totalGain.toString();
        }

        private static int getNextMaxPos(ArrayList<Integer> vArr, int val, int curPos, int n){
                int maxPos = -1;
                for(int i = curPos+1; i < vArr.size() && i < curPos+1+n; i++){
                        if(vArr.get(i) >= val){
                                return i;
                        }
                }
                return maxPos;
        }
        public static void main(String[] argv) {
                try {
                        long startTime = System.currentTimeMillis();
                        Reader reader = new InputStreamReader(System.in);
                        BufferedReader bufReader = new BufferedReader(reader);
                        String x = bufReader.readLine();
                        int numOfTestCases = Integer.parseInt(x);
                        int count = 0;

                        for(count =1; count<= numOfTestCases; count++) {
                                ArrayList<Integer> firstLine = getIntegerList(bufReader.readLine());

                                String output = getMaxGain(firstLine.get(0), firstLine.get(1), getIntegerList(bufReader.readLine()));
                                System.out.println("Case #" + count + ": " + output);
                        }
                        System.out.println("Total time = " + (System.currentTimeMillis() - startTime));
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        private static ArrayList<Integer> getIntegerList(String s) {
                ArrayList<Integer> intList = new ArrayList<Integer>();
                String[] strArr = s.split(" ");
                for (String str : strArr) {
                        intList.add(Integer.parseInt(str.trim()));
                }
                return intList;
        }
}