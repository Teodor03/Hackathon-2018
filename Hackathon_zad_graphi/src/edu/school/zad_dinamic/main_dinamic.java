package edu.school.zad_dinamic;

import java.util.ArrayList;

public class main_dinamic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    Solution sol = new Solution();
    int [] A = {0, 1, 0, 1, 1, 1, 0};
    int [] X = {1, 2, 3, 4, 5, 6, 7};
    System.out.println(sol.solution(A, X));
	}

}

class Solution{
	
	int N;
	int maximum_number_of_cities=0;
	boolean can_i_go_anywhere_else;
	
	class City{
		private int location;
		private int index_in_the_array;
		private int amount_of_fuel_in_this_city;
		public int getLocation() {
			return location;
		}
        
		public void recursive_traveling(int number_of_visited_cities,int fuel_in_mine_fuel_tank,ArrayList<Integer> already_travelled_cities) {
	    can_i_go_anywhere_else=false;
		fuel_in_mine_fuel_tank=fuel_in_mine_fuel_tank+amount_of_fuel_in_this_city;
        for(int counter=0;counter<N;counter++) {
        if(Math.abs(array_of_cities[index_in_the_array].getLocation()-array_of_cities[counter].getLocation())<=fuel_in_mine_fuel_tank & already_travelled_cities.contains(counter)==false) {
        already_travelled_cities.add(counter);
        array_of_cities[counter].recursive_traveling(number_of_visited_cities+1,fuel_in_mine_fuel_tank-Math.abs(array_of_cities[index_in_the_array].getLocation()-array_of_cities[counter].getLocation()),already_travelled_cities);
        can_i_go_anywhere_else=true;
        already_travelled_cities.remove(already_travelled_cities.size()-1);
        }	
        }
        
        if(can_i_go_anywhere_else==false) {
        if(number_of_visited_cities>maximum_number_of_cities) {
        	maximum_number_of_cities=number_of_visited_cities;	
        }	
        }
		}
		
	}
	
	City [] array_of_cities;
	
	public int solution(int[] A,int[] X) {
	array_of_cities = new City [A.length];
	N=A.length;
	for(int counter=0;counter<N;counter++) {
	City city = new City();
	array_of_cities[counter]=city;
	array_of_cities[counter].location=X[counter];
	array_of_cities[counter].amount_of_fuel_in_this_city=A[counter];
	array_of_cities[counter].index_in_the_array=counter;
	}
	ArrayList<Integer> already_travelled_cities = new ArrayList<Integer>();
	
	for(int counter=0;counter<N;counter++) {
	already_travelled_cities.add(counter);
	array_of_cities[counter].recursive_traveling(0, 0, already_travelled_cities);
	already_travelled_cities.remove(0);
	}
	return maximum_number_of_cities+1; 
	
	}
}