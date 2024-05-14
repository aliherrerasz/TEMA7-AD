package clase;

import java.sql.*;
import java.util.Scanner;

public class Ejemplos {
    private static String cc = "jdbc:mysql://192.168.40.107/Carreras";
    private static String u = "dam";
    private static String p = "java";
    public static void main(String[] args) {
       // ejemplo1();
       // ejemplo2();
        ejercicio1();
    }

    private static void ejemplo1() {
        try(
                Connection c = DriverManager.getConnection(cc, u, p)
                ){
            // el statement solo no porque es más dificil
            System.out.println("Escribe el nombre del alumno: ");
            String nombre = new Scanner(System.in).nextLine();
            System.out.println("Escribe la edad del alumno: ");
            int edad = new Scanner(System.in).nextInt();

            String sql = "INSERT INTO personas(nombre,edad) values (?,?)";
            PreparedStatement s = c.prepareStatement(sql);
            s.setString(1,nombre);
            s.setInt(2,edad);
            // SELECT --> executeQuery
            // INSERT/UPDATE/DELETE --> executeUpdate
            s.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error" +e.getMessage());
        }
    }

    private static void ejemplo2() {
        try(
                Connection c = DriverManager.getConnection(cc, u, p)
                ){
            System.out.println("Escribe una edad: ");
            int edad = new Scanner(System.in).nextInt();
            String sql = "SELECT * FROM personas WHERE edad=?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1, edad);
            ResultSet r = p.executeQuery();
            while(r.next()){
                String nombrePersona  = r.getString("nombre");
                int edadPersona = r.getInt("edad");
                System.out.println(nombrePersona + " "+ edadPersona);
            }
        }catch (SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    private static void ejercicio1() {
        try(
                Connection c = DriverManager.getConnection(cc, u, p);
                ){
            System.out.println("Introduce una edad: ");
            int edad1 = new Scanner(System.in).nextInt();
            System.out.println("Introduce otra edad: ");
            int edad2 = new Scanner(System.in).nextInt();
            String sql= "SELECT * FROM personas WHERE edad >= ? AND edad <= ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, edad1);
            s.setInt(2, edad2);
            ResultSet r = s.executeQuery();
            while(r.next()){
                int id = r.getInt("id");
                String nombre = r.getString("nombre");
                System.out.println(nombre + " "+ id);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
