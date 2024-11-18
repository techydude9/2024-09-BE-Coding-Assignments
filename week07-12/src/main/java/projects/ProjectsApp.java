package projects;
/*
 * Author is Bob Ruzga
 * BackEnd 2024 09 26 Cohort 
 * Week 7 coding assignment
 */

import projects.dao.DbConnection;

public class ProjectsApp {

	public static void main(String[] args) {
		// Make a connection to the MySql db names Projects
		DbConnection.getConnection();

	} // end of main

}  // end of ProjectsApp class
