/*
Name: Jihyung Park
Date: 11/10/2021
Section: CS2336.504
Instructor: Mohamed Amine Belkoura

Program describe: The information in the given file is classified and stored by classifying 
the patient's name, doctor's name, and reservation date, and BST classified as each doctor is generated and stored. 
Depending on the user's choice, patients can be added, removed, or changed, reservations can be checked within a week, 
and those without reservations for a year can be checked. When we close the program, we write it back to the given file.
 */

package assignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TestMain {
	//declare three BST for each doctor.
	public static BST Gilbert = new BST();
	public static BST Wong = new BST();
	public static BST Chavez = new BST();

	public static void main(String[] args) {
		ArrayList<Appointment> appointments = new ArrayList<>();

		try {
			//open file
			File file = new File("patient.txt");
			FileReader file_reader = new FileReader(file);
			BufferedReader bufReader = new BufferedReader(file_reader);
			String line = "";

			while ((line = bufReader.readLine()) != null) {
				Appointment ap = new Appointment(line);
				appointments.add(ap);
			}

			bufReader.close();
		} catch (FileNotFoundException e) {
			// TODO: handle exception
		} catch (IOException e) {
			System.out.println(e);
		}

		for (int j = 0; j < appointments.size(); j++) {
			insertPatient(appointments.get(j));
		}

		Scanner sc = new Scanner(System.in);

		String tempString1 = "";
		String tempString2 = "";
		String tempString3 = "";
		String tempString4 = "";

		Appointment app;

		while (true) {
			int choice;
			System.out.print(
					"1. add new patient | 2. delete exist patient | 3. edit a patient's appointment | 4.search appointment with in week by doctor \n5. search patient | 6. patient who need annual appointment | 7. all patient who have appointment with in week | 8. exit\n");
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				System.out.println("Please enter the patient's name");
				tempString1 = sc.nextLine();
				System.out.println("Please enter the Doctor's name");
				tempString2 = sc.nextLine();
				System.out.println("Please enter first appointment Date MM/DD/YY");
				tempString3 = sc.nextLine();
				System.out.println("Please enter second appointment Date MM/DD/YY(not required)");
				tempString4 = sc.nextLine();
				
				app = new Appointment(tempString1 + ", " + tempString2 + ", " + tempString3 + ", " + tempString4);
				
				
				insertPatient(app);

				break;
			case 2:
				System.out.println("Please enter the patient's name that you want to delete");
				tempString1 = sc.nextLine();
				System.out.println("Please enter the doctor's name");
				tempString2 = sc.nextLine();

				deletePatient(new Appointment(tempString1 + ", " + tempString2 + ", , "));

				break;
			case 3:
				System.out.println("Please enter the patient's name that you want to edit");
				tempString1 = sc.nextLine();
				System.out.println("Please enter the doctor's name");
				tempString2 = sc.nextLine();

				deletePatient(new Appointment(tempString1 + ", " + tempString2 + ", , "));
				
				System.out.println("now you can enter update information");
				System.out.println("Please enter the patient's name");
				tempString1 = sc.nextLine();
				System.out.println("Please enter the Doctor's name");
				tempString2 = sc.nextLine();
				System.out.println("Please enter first appointment Date MM/DD/YY");
				tempString3 = sc.nextLine();
				System.out.println("Please enter second appointment Date MM/DD/YY (not required)");
				tempString4 = sc.nextLine();
				
				app = new Appointment(tempString1 + ", " + tempString2 + ", " + tempString3 + ", " + tempString4);
				
				insertPatient(app);

				break;
			case 4:
				System.out.println("Please enter today's date MM/DD/YY");
				tempString1 = sc.nextLine();
				System.out.println("Please enter the doctor's name");
				tempString2 = sc.nextLine();
				searchNextWeek(tempString1, tempString2);

				break;
			case 5:
				System.out.println("Please enter the patient's name that you want to search");
				tempString1 = sc.nextLine();
				System.out.println("Please enter the doctor's name");
				tempString2 = sc.nextLine();

				searchPatient(new Appointment(tempString1 + ", " + tempString2 + ", , "));

				break;
			case 6:
				Gilbert.haveNext();
				Wong.haveNext();
				Chavez.haveNext();
				break;
			case 7:
				System.out.println("Please enter today's date MM/DD/YY");
				tempString1 = sc.nextLine();
				Gilbert.weekAgo(tempString1);
				Wong.weekAgo(tempString1);
				Chavez.weekAgo(tempString1);
				break;
			case 8:
				Gilbert.clearFile();
				
				Gilbert.writeFile();
				Wong.writeFile();
				Chavez.writeFile();
				return;
			default:
				System.out.println("wrong input");
				break;
			}
		}
	}

	public static void insertPatient(Appointment app) {
		switch (app.DocName) {
		case "Gilbert":
			Gilbert.insert(app);
			break;

		case "Wong":
			Wong.insert(app);
			break;

		case "Chavez":
			Chavez.insert(app);
			break;
		default:
			System.out.println("wrong doctor name");
			break;
		}
	}

	public static void deletePatient(Appointment app) {
		switch (app.DocName) {
		case "Gilbert":
			Gilbert.delete(app);
			break;

		case "Wong":
			Wong.delete(app);
			break;

		case "Chavez":
			Chavez.delete(app);
			break;
		}
	}

	public static void searchNextWeek(String Date, String DocName) {
		switch (DocName) {
		case "Gilbert":
			Gilbert.weekAgo(Date);
			break;

		case "Wong":
			Wong.weekAgo(Date);
			break;

		case "Chavez":
			Chavez.weekAgo(Date);
			break;
		default:
			System.out.println("wrong doctor name");
			break;
		}
	}

	public static void searchPatient(Appointment app) {
		switch (app.DocName) {
		case "Gilbert":
			if(Gilbert.search(app)) {
				System.out.println("Exist");
			}
			break;

		case "Wong":
			if(Wong.search(app)) {
				System.out.println("Exist");
			}
			break;

		case "Chavez":
			if(Chavez.search(app)) {
				System.out.println("Exist");
			}
			break;
		default:
			System.out.println("wrong doctor name");
			break;
		}
	}
}
