package edu.school.zad_graphi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Graph {

	Map<String, Point_of_Graph> All_points = new HashMap();
	public Double the_shortest_path = Double.POSITIVE_INFINITY;

	class Point_of_Graph {

		public Point_of_Graph(String Name_of_the_point) {
			this.Name_of_the_point = Name_of_the_point;
		}

		private ArrayList<Connection_between_Points> Connections_from_me = new ArrayList<Connection_between_Points>();
		private String Name_of_the_point;

		public void add_Connection(Connection_between_Points connection) {
			Connections_from_me.add(connection);
		}

		public void recursive_finding(Double travelled_distance, String Name_of_Target_Point,
				ArrayList<Connection_between_Points> Connections_we_have_already_traveled) {
			if (Name_of_Target_Point == Name_of_the_point) {
				if (travelled_distance < the_shortest_path) {
					the_shortest_path = travelled_distance;
				}
			} else {
				for (int a = 0; a < Connections_from_me.size(); a++) {
					if (Connections_we_have_already_traveled.contains(Connections_from_me.get(a))) {
					} else {
						Connections_we_have_already_traveled.add(Connections_from_me.get(a));
						All_points.get(Connections_from_me.get(a).getEnding_Point()).recursive_finding(
								travelled_distance + Connections_from_me.get(a).get_Length(), Name_of_Target_Point,
								Connections_we_have_already_traveled);
						Connections_we_have_already_traveled.remove(Connections_we_have_already_traveled.size() - 1);
					}
				}
			}
		}

	}

	class Connection_between_Points {
		private String Starting_Point;
		private String Ending_Point;
		private Double length;

		public String getStarting_Point() {
			return Starting_Point;
		}

		public String getEnding_Point() {
			return Ending_Point;
		}

		public Double get_Length() {
			return length;
		}

		public Connection_between_Points(String Starting_Point, String Ending_Point, Double length) {
			this.Starting_Point = Starting_Point;
			this.Ending_Point = Ending_Point;
			this.length = length;
		}

	}

	public void add_Point(String Name_of_point) {
		Point_of_Graph point = new Point_of_Graph(Name_of_point);
		if (All_points.containsKey(Name_of_point)) {
			System.out.println("Cannot add point with the same name!");
		} else {
			All_points.put(Name_of_point, point);
		}
	}

	public void add_one_way_Connection_between_Points(String Name_of_starting_point, String Name_of_ending_point,
			Double lenght) {
		Connection_between_Points connection = new Connection_between_Points(Name_of_starting_point,
				Name_of_ending_point, lenght);
		All_points.get(Name_of_starting_point).add_Connection(connection);
	}

	public void add_two_way_Connection_between_Points(String Name_of_starting_point, String Name_of_ending_point,
			Double lenght) {
		Connection_between_Points first_connection = new Connection_between_Points(Name_of_starting_point,
				Name_of_ending_point, lenght);
		Connection_between_Points second_connection = new Connection_between_Points(Name_of_ending_point,
				Name_of_starting_point, lenght);
		All_points.get(Name_of_starting_point).add_Connection(first_connection);
		All_points.get(Name_of_ending_point).add_Connection(second_connection);
	}

	public void try_to_find_the_shortest_path(String Name_of_starting_point, String Name_of_target_point) {
		ArrayList<Connection_between_Points> travelled_connections = new ArrayList<Connection_between_Points>();
		All_points.get(Name_of_starting_point).recursive_finding(0.0, Name_of_target_point, travelled_connections);
		if (the_shortest_path == Double.POSITIVE_INFINITY) {
			System.out.println(
					"There is no path found between " + Name_of_starting_point + " and " + Name_of_target_point + "!");
		} else {
			System.out.println("The shortest path between " + Name_of_starting_point + " and " + Name_of_target_point
					+ " is " + the_shortest_path + "!");
		}
	}

}
