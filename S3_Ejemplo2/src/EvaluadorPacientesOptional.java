import java.util.*;
import java.util.stream.*;

public class EvaluadorPacientesOptional {

    public static void main(String[] args) {
        List<Paciente> pacientes = List.of(
                new Paciente("Ana", 34, false, "ana@example.com"),
                new Paciente("Luis", 70, true, null),
                new Paciente("Marta", 45, true, "marta@example.com"),
                new Paciente("Pedro", 28, false, null)
        );

        System.out.println("📧 Correos electrónicos disponibles:");

        // 🏁 Iniciamos el stream sobre la lista de pacientes
        pacientes.stream()
                .map(Paciente::getCorreo) // 🔄 map transforma Paciente → Optional<String> (correo)
                .filter(Optional::isPresent) // 🔍 filter permite solo los Optionals que tienen valor (no vacíos)
                .map(Optional::get) // 📥 map extrae el valor del Optional
                .forEach(System.out::println); // 📤 forEach imprime los valores finales

        System.out.println("\n📝 Pacientes en observación (mayores de 40 años):");

        pacientes.stream()
                .filter(p -> p.isEnObservacion() && p.getEdad() > 40) // 🔍 filter aplica condición booleana
                .forEach(System.out::println); // 📤 forEach imprime los pacientes filtrados
    }
}