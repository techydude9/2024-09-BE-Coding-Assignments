package projects;
/*
 * Author is Bob Ruzga
 * BackEnd 2024 09 26 Cohort 
 * Week 7,8, 9, 10 coding assignments
 */

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import projects.entity.Project;
import projects.exception.DbException;
import projects.service.ProjectService;

public class ProjectsApp {

	// @formatter:off
	private List<String> operations = List.of(
		"1) Add a project",
		"2) List projects",
		"3) Select a project",
		"4) Update project details"
	);
	// @formatter:on
	
	private Scanner scanner = new Scanner(System.in);
	private ProjectService projectService = new ProjectService();
	private Project curProject;
	
// Start of main method -------
	public static void main(String[] args) {
		
		new ProjectsApp().processUserSelections();
			
	} // end of main

	private void processUserSelections() {
		boolean done = false;
				
		while(!done) {
			try {
				int selection = getUserSelection();
				
				switch(selection) {
				
				case -1: 
					done = exitMenu();
					break;
					
				case 1:
					createProject();
					break;
				
				case 2:
					listProjects();
					break;
					
				case 3:
					selectProject();
					break;
					
				case 4:
					updateProjectDetails();
					break;
					
				default:
					System.out.println("\n" + selection +  " is not valid. Try again.");
				} // end of switch
			}
			catch(Exception e) {
				System.out.println("\nError: " + e + " Try again.");
			}
		} // end of while loop
		
	} // end of processUserSelections method
	
	private void updateProjectDetails() {
		
		if(Objects.isNull(curProject)) {
			System.out.println("\nPlease select a project.");
			return;
		} 

		String projectName = getStringInput("Enter the project name [" + curProject.getProjectName() + "]" );
		BigDecimal estimatedHours = getDecimalInput("Enter the estimated hours [" + curProject.getEstimatedHours() + "]");
		BigDecimal actualHours = getDecimalInput("Enter the actual hours [" + curProject.getActualHours() + "]");
		Integer difficulty = getIntInput("Enter the project difficulty [" + curProject.getDifficulty() + "]");
		String notes = getStringInput("Enter the project notes [" + curProject.getNotes() + "]");
		
		Project project = new Project();
		
		project.setProjectName(Objects.isNull(projectName) ? curProject.getProjectName() : projectName);
		project.setEstimatedHours(Objects.isNull(estimatedHours) ? curProject.getEstimatedHours() : estimatedHours);
		project.setActualHours(Objects.isNull(actualHours) ? curProject.getActualHours() : actualHours);
		project.setDifficulty(Objects.isNull(difficulty) ? curProject.getDifficulty() : difficulty);
		project.setNotes(Objects.isNull(notes) ? curProject.getNotes() : notes);
		
		project.setProjectId(curProject.getProjectId());
		
		projectService.modifyProjectDetails(project);
		curProject = projectService.fetchProjectById(curProject.getProjectId());
		
	} // end of updateProjectDetails method -----

	private void selectProject() {
		
		listProjects();
	
		Integer projectId = getIntInput("Enter a project ID to select a project");
		
		// set/reset curProject to null
		curProject = null;
		
		curProject = projectService.fetchProjectById(projectId);
	} // end of selectProject method -------

	private void listProjects() {
		List<Project> projects = projectService.fetchAllProjects();
		
		System.out.println("\nProjects:");
		
		projects.forEach(project -> System.out.println("   " +
				project.getProjectId() + ": " + project.getProjectName()));
		
	} // end of listProjects method

	private void createProject() {
		String projectName = getStringInput("Enter the project name");
		BigDecimal estimatedHours = getDecimalInput("Enter the estimated hours");
		BigDecimal actualHours = getDecimalInput("Enter the actual hours");
		Integer difficulty = getIntInput("Enter the project difficulty (1-5)");
		String notes = getStringInput("Enter the project notes");
		
		Project project = new Project();
		
		project.setProjectName(projectName);
		project.setEstimatedHours(estimatedHours);
		project.setActualHours(actualHours);
		project.setDifficulty(difficulty);
		project.setNotes(notes);
		
		Project dbproject = projectService.addProject(project);
		System.out.println("You have successfully create project: " + dbproject);
				
	} // end of createProject method ------

	private BigDecimal getDecimalInput(String prompt) {
		String input = getStringInput(prompt);
		
		if(Objects.isNull(input)) {
			return null;
		}
		
		try {
			return new BigDecimal(input).setScale(2);
		}
		catch(NumberFormatException e) {
			throw new DbException(input + " is not a valid decimal number.");
		}
	} // end of getDecimalInput method -----

	private boolean exitMenu() {
		System.out.println("\nExiting the menu.");
		return true;
	}  // end of exitMenu method -------

	private int getUserSelection() {
		printOperations();
		
		Integer input = getIntInput("Enter a menu selection");
		
		return Objects.isNull(input) ? -1 : input;
	}  // end of getUserSelection method

	private void printOperations() {
		System.out.println("\nThese are the available selections. Press the Enter key to quit");
		
		operations.forEach(line -> System.out.println("  " + line));
		
		if(Objects.isNull(curProject)) {
			System.out.println("\nYou are not working with a project");
		} else {
			System.out.println("\nYou are working with project: " + curProject);
		}
		
	} // end of printOperations method
	
	private Integer getIntInput(String prompt) {
		String input = getStringInput(prompt);
		
		if(Objects.isNull(input)) {
			return null;
		}
		
		try {
			return Integer.valueOf(input);
		}
		catch(NumberFormatException e) {
			throw new DbException(input + " is not a valid number.");
		}
	} // end of getIntInput

	private String getStringInput(String prompt) {
		System.out.println(prompt + ": ");
		String input = scanner.nextLine();
		
		return input.isBlank() ? null : input.trim();
	} // end of getStringInput ----------

}  // end of ProjectsApp class
