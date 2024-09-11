package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Process {
    private static  Statement statement;
    public static void newReservation(Connection connection){

        Person person = new Person();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the name of guest");
        person.setName(sc.next());

        System.out.println("Enter the room to allocate");
        person.setRoomNu(sc.nextInt());

        System.out.println("Enter the phone number");
        person.setPhoneNu(sc.next());

        String sql = "INSERT INTO reservation(guest_name,room_number,contact_number)"+
                "VALUES('"+person.getName()+"',"+person.getRoomNu()+",'"+person.getPhoneNu()+"')";

        try{
            statement = connection.createStatement();
            int row = statement.executeUpdate(sql);
            if(row>0) System.out.println("Data inserted successfully");
            else System.out.println("Data not inserted");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void viewReservation(Connection connection){
        String sql = "SELECT * FROM reservation";
        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            System.out.println("------------------------------------------------------------");
            while(resultSet.next()){

                System.out.println("Reservation ID: "+resultSet.getInt("reservation_id"));
                System.out.println("Guest Name: "+resultSet.getString("guest_name"));
                System.out.println("Room Number: "+resultSet.getInt("room_number"));
                System.out.println("Reservation Timing: "+resultSet.getTimestamp("reservation_date").toString());
                System.out.println("------------------------------------------------------------");
              
            }
            System.out.println("--------------------------------------------------------------");

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void getRoomNumber(Connection connection, Scanner sc){
        System.out.println("Enter the reservation ID");
        int resID = sc.nextInt();
        System.out.println("Enter the name of guest");
        String name = sc.next();

        String sql = "SELECT room_number FROM reservation WHERE reservation_id = "+resID+" AND guest_name = '"+name+"'";


        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                int roomNumber = resultSet.getInt("room_number");
                System.out.println(" the room number for given data is"+roomNumber);
            }else{
                System.out.println("No data found for the given input");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void updateReservation(Connection connection,Scanner sc){
        System.out.println("Enter the ID to update");
        int id = sc.nextInt();
        if(!reservationExists(connection,id)){
            System.out.println("This id not exists");
            return;
        }
        Person person = new Person();
        System.out.println("Enter new guest name");
        person.setName(sc.next());
        System.out.println("Enter new room number");
        person.setRoomNu(sc.nextInt());
        System.out.println("Enter new phone number");
        person.setPhoneNu(sc.next());

        String sql = "UPDATE reservation SET guest_name = '"+person.getName()+"',"+
                     " room_number ="+person.getRoomNu()+","+
                     " contact_number ='"+person.getPhoneNu()+"'"+
                     "WHERE reservation_id = "+id;

        try{
            Statement statement = connection.createStatement();
            int row = statement.executeUpdate(sql);
            if(row>0) System.out.println("Updation successful");
            else System.out.println("Updation not successful");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private static boolean reservationExists(Connection connection, int id) {
        String sql = "SELECT reservation_id from reservation WHERE reservation_id = "+id;
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            return resultSet.next();
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public void deleteReservation(Connection connection, Scanner sc) {
        System.out.println("Enter the reservation_id to delete");
        int id = sc.nextInt();
        if(!reservationExists(connection,id)){
            System.out.println("No such id exists");
            return;
        }

        String sql = "DELETE from reservation WHERE reservation_id = "+id;
        try{
            Statement statement = connection.createStatement();
            int row = statement.executeUpdate(sql);
            if(row>0) System.out.println("Deletion successful");
            else System.out.println("Deletion not successful");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
