import java.util.*;
import java.util.stream.*;

public class ProcesadorPedidos {

    public static void main(String[] args) {
        List<Pedido> pedidos = List.of(
                new Pedido("Juan", "domicilio", "555-1234"),
                new Pedido("Lucía", "local", null),
                new Pedido("Carlos", "domicilio", null),
                new Pedido("Ana", "domicilio", "555-5678")
        );

        pedidos.stream()
                .filter(p -> p.getTipoEntrega().equalsIgnoreCase("domicilio")) // Solo pedidos a domicilio
                .map(Pedido::getTelefono) // Mapea a Optional<String>
                .filter(Optional::isPresent) // Filtra los que sí tienen teléfono
                .map(Optional::get) // Extrae el valor
                .map(tel -> "📞 Confirmación enviada al número: " + tel) // Genera el mensaje
                .forEach(System.out::println); // Muestra los mensajes
    }
}
