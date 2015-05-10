package mahfouz.hackerrank;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public final class AJourneyToTheMoon {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		int L = s.nextInt();
		
		HashSet<Integer> unclassified = new HashSet<Integer>();
		for (int i = 0; i < N; i++) 
			unclassified.add(i);

		ArrayList<HashSet<Integer>> countries = new ArrayList<HashSet<Integer>>();
		
		for (int i = 0; i < L; i++) {
        	int firstGuy = s.nextInt();
        	int secondGuy = s.nextInt();
        	
        	int firstCountryIndex = findCountryFor(firstGuy, countries);
        	int secondCountryIndex = findCountryFor(secondGuy, countries);
        	
        	if (firstCountryIndex < 0) {
        		if (secondCountryIndex < 0) {
        			// both not found
        			HashSet<Integer> newCountry = new HashSet<Integer>();
        			countries.add(newCountry);
        			newCountry.add(firstGuy);
        			newCountry.add(secondGuy);
        		}
        		else {
        			// only second found
        			countries.get(secondCountryIndex).add(firstGuy);
        		}
        	}
        	else {
        		if (secondCountryIndex < 0) {
        			// only first found
        			countries.get(firstCountryIndex).add(secondGuy);
        		}
        		else {
        			// both found - combine countries
        			if (firstCountryIndex != secondCountryIndex) {
        				// remove the country with the higher index to keep lower index intact
        				if (firstCountryIndex > secondCountryIndex)
        					countries.get(secondCountryIndex).addAll(countries.remove(firstCountryIndex));
        				else
        					countries.get(firstCountryIndex).addAll(countries.remove(secondCountryIndex));
        			}
        		}
        	}
        	
        	unclassified.remove(firstGuy);
        	unclassified.remove(secondGuy);
        }
		
		// now count permutations
		
		long combinations = 0;

		// may also add a country for every single guy, but will timeout for big test cases
		
		long numSingles = unclassified.size();
		if (numSingles > 0) {
			// pair them with each other
			combinations += (numSingles * (numSingles - 1)) / 2;
			// pair them with all others
			combinations += numSingles * ((long)N - numSingles);
		}

		int numCountries = countries.size();
		
		for (int i = 0; i < numCountries - 1; i++)
			for (int j = i+1; j < numCountries; j++) 
				combinations += countries.get(i).size() * countries.get(j).size();
		
		System.out.println(combinations);
	}

	private static int findCountryFor
		(int astronaut, ArrayList<HashSet<Integer>> countries) {
		
		for (int i = 0; i < countries.size(); i++) {
			HashSet<Integer> country = countries.get(i);
			if (country.contains(astronaut))
				return i;
		}
		return -1;
	}
}
