package org.example;

import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Connection;


public class Operation {

    private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String username = "root";
    private static final String password = "vaibhav04";

    private static Connection connection;


    public  static void info(){
        try {
            connection = DriverManager.getConnection(url, username, password);

            System.out.println("----------------------------------");
            System.out.println("Enter 1 for new Reservation");
            System.out.println("Enter 2 to view Reservations");
            System.out.println("Enter 3 for get room number");
            System.out.println("Enter 4 for update reservation");
            System.out.println("Enter 5 for delete reservation");
            System.out.println("Enter 0 to exit");
            System.out.println("----------------------------------");

            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            operation(n,connection);
        }catch (Exception ex){
            System.out.println("Some exception in db has occured");
        }
    }

    private static void operation(int n,Connection connection) {
        Process process = new Process();
        Scanner sc = new Scanner(System.in);
        switch (n){
            case 1:
                process.newReservation(connection);
                System.out.println("---------------------------------------");
                System.out.println("Enter 1 to go on Main Page\nEnter any number to terminate");
                System.out.println("----------------------------------------");
                if(sc.nextInt() == 1) Operation.info();
                break;
            case 2:
                process.viewReservation(connection);
                System.out.println("---------------------------------------");
                System.out.println("Enter 1 to go on Main Page\nEnter any number to terminate");
                System.out.println("----------------------------------------");
                if(sc.nextInt() == 1) Operation.info();
                break;
            case 3:
                process.getRoomNumber(connection,sc);
                System.out.println("---------------------------------------");
                System.out.println("Enter 1 to go on Main Page\nEnter any number to terminate");
                System.out.println("----------------------------------------");
                if(sc.nextInt() == 1) Operation.info();
                break;
            case 4:
                process.updateReservation(connection,sc);
                System.out.println("---------------------------------------");
                System.out.println("Enter 1 to go on Main Page\nEnter any number to terminate");
                System.out.println("----------------------------------------");
                if(sc.nextInt() == 1) Operation.info();
                break;
            case 5:
                process.deleteReservation(connection,sc);
                System.out.println("---------------------------------------");
                System.out.println("Enter 1 to go on Main Page\nEnter any number to terminate");
                System.out.println("----------------------------------------");
                if(sc.nextInt() == 1) Operation.info();
                break;
            case 0:
                break;
            default:
                System.out.println("------------------------");
                System.out.println("you entered wrong input");
        }
    }
}
