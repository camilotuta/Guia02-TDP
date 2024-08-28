import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class prueba {

    private static String rutaArchivo = "C:\\Users\\tutaa\\Downloads\\Archivo.txt";

    public static void escribirEnArchivo(String infoGrabar) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            bw.write(infoGrabar);
            bw.newLine();
            bw.flush();

        } catch (IOException error) {
            System.out.println("Error E/S: " + String.valueOf(error));
        }
    }

    public static String leerArchivo() {
        StringBuilder contenido = new StringBuilder();
        String linea;

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        } catch (IOException error) {
            System.out.println("Error E/S: " + String.valueOf(error));
        }

        return contenido.toString();
    }

    public static void main(String[] args) {
        escribirEnArchivo("jajjajaja");
        escribirEnArchivo("hola");
        System.out.println(leerArchivo());
    }
}
