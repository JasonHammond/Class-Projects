/*	 Jason Hammond
     CSCI 4500 - Mobile Computing
     Cueva-Parra
     Homework Assignment 3

	 Javascript code for an online gradebook. Homework assignment.
	 Intended for use gradebook.html. */

/*****************************************************************************/


// Changes grade of selected student to desired grade.
// Precondition: None.
// Postcondition: Selected grade on table is changed.

function changeGrade() {
										//Stores table for row retrieval
	var table = document.getElementById("grtable");
	var	rows = 									//Stores rows of table
			table.getElementsByTagName("tbody")[0].getElementsByTagName("tr");

	var student;					//Subroutine
	var assignment;					//Subroutine
	var score;						//Subroutine


	//Prompt user for student ID
	//Precondition: None.
	//Postcondition: Desired student is located;
		//student ID, row number, and name provided to changeGrade()	

	while (student == null) {
		student = (function whichSid() {
			var	sid;
			var result;		
		
			var sidChoice = prompt("Select Student by ID:","ex: 123", "");

			for (var i = 1; i < rows.length; i++) {
				sid = rows[i].getElementsByTagName("td")[0].innerHTML;
				if (sidChoice === sid) { 
					name = rows[i].getElementsByTagName("td")[1].innerHTML;
					result = [sid, i, name];
					return result;
				}
			}
			alert("Student not found! Please try again.");
			return null;
		})();
	}

	//Prompt user for assignment choice
	//Precondition: None.
	//Postcondition: Desired assignment is located;
		//Column number and assignment name returned to changeGrade()

	while (assignment == null) {
		var assignment = (function whichAssignment() {
			var headRow = rows[0].getElementsByTagName("th");
			var result;

			var assigChoice = prompt(
				"For which assignment would you like to change the grade?", 
				"ex: Midterm");

			for (var i=2; i<headRow.length; i++) {
				if (assigChoice === headRow[i].innerHTML) { 
					result = [assigChoice, i];
					return result; }
			}
			alert("Assignment not found! Please try again.");
			return null;
		})();
	}

	//Prompt user for grade
	//Precondition: None.
	//Postcondition: Desired grade is retrieved
		//Grade is returned to changeGrade()

	while (grade == null) {
		var grade = (function whichGrade() {
			var score = prompt("What score would you like to enter?", "ex: 75");
			
			if(score >= 0) { return score; }
			else {
				alert("Please enter a positive score.");
				return null;
			}			
		})();
	}

/*****************************************************************************/
	

	//Change appropriate cell to desired grade value
	rows[student[1]].getElementsByTagName("td")[assignment[1]].innerHTML = grade;

	//Report success to user
 	alert(assignment[0] + " grade for student " + student[2] + " (# " + student[0] +
		") updated to " + grade + ".");
};
