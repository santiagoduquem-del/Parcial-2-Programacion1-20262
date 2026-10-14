package uniquindio.edu.co;
import javax.swing.JOptionPane;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args){

    String nombre = JOptionPane.showInputDialog("Escriba su nombre: ");

    String mensaje = JOptionPane.showMessageDialog(null, "Su nombre es: " + nombre);

    System.out.println(mensaje);
    }
    }
