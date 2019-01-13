package edu.school.zad_graphi;

import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    Solution sol = new Solution();
    int [] A = {0};
    int [] B = {1};
    System.out.println(sol.solution(A, B));
	}

}


class Solution{
	
	int result=0;
	
	class Node{
	int index_of_this_node;	
	private ArrayList<Integer> Connected_nodes_to_me = new ArrayList<Integer>();
	
	void add_connection(int index_of_ending_node) {
		Connected_nodes_to_me.add(index_of_ending_node);
	}
	
	void recursive_finding(int index_of_ending_node, int number_of_travelled_links, ArrayList<Integer> nodes_i_have_already_travelled) {
		if(nodes_i_have_already_travelled.contains(index_of_this_node)) {
		}else {
		if(index_of_this_node==index_of_ending_node) {
		Memorized_roads road = new Memorized_roads();
		if(number_of_travelled_links%2==1)road.is_their_link_odd=true;
		
		if(number_of_travelled_links%2==1) {
		result++;
		}	
		}else {
		for(int counter=0;counter<Connected_nodes_to_me.size();counter++) {
		nodes_i_have_already_travelled.add(index_of_this_node);
		array_of_nodes[Connected_nodes_to_me.get(counter)].recursive_finding(index_of_ending_node, number_of_travelled_links+1,nodes_i_have_already_travelled);
		nodes_i_have_already_travelled.remove(nodes_i_have_already_travelled.size() - 1);
		}
		}
		}
		
		
	}
	
	
	
	
	
	}
	
	Node [] array_of_nodes;
	
    class Memorized_roads{
    int index_of_starting_road;	
    int index_of_ending_road;	
    boolean is_their_link_odd=false;		
    }
	
    ArrayList<Memorized_roads> memo_roads = new ArrayList<Memorized_roads>();
	
	public int solution(int A[], int B[]) {
	array_of_nodes = new Node[A.length+1];
	for(int counter=0;counter<array_of_nodes.length;counter++) {
	Node node = new Node();
	array_of_nodes[counter]=node;
	array_of_nodes[counter].index_of_this_node=counter;
	}
	
	for(int counter=0;counter<A.length;counter++) {
	array_of_nodes [A[counter]].add_connection(B[counter]);	
	}
	for(int counter=0;counter<A.length;counter++) {
	array_of_nodes [B[counter]].add_connection(A[counter]);	
	}
	
	ArrayList<Integer> nodes_i_have_already_travelled = new ArrayList<Integer>();
	for(int first_index=0;first_index<A.length;first_index++) {
	for(int second_index=first_index;second_index<=A.length;second_index++) {
	array_of_nodes[first_index].recursive_finding(second_index, 0, nodes_i_have_already_travelled);
	}
	}
	return result;
	}
}