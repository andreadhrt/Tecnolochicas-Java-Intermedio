import java.util.*;
import java.util.stream.*;

public class SeguimientoEncuestas {

    public static void main(String[] args) {
        Sucursal centro = new Sucursal("Centro", List.of(
                new Encuesta("Ana", "El tiempo de espera fue largo", 2),
                new Encuesta("Luis", null, 4),
                new Encuesta("Marta", "No me explicaron bien el tratamiento", 3)
        ));

        Sucursal norte = new Sucursal("Norte", List.of(
                new Encuesta("Carlos", "La atención fue buena, pero la limpieza puede mejorar", 3),
                new Encuesta("Pedro", null, 2),
                new Encuesta("Lucía", null, 5)
        ));

        List<Sucursal> sucursales = List.of(centro, norte);

        sucursales.stream()
                .flatMap(sucursal ->
                        sucursal.getEncuestas().stream()
                                .filter(encuesta -> encuesta.getCalificacion() <= 3)
                                .map(encuesta ->
                                        encuesta.getComentario()
                                                .map(com -> "Sucursal " + sucursal.getNombre() + ": Seguimiento a paciente con comentario: \"" + com + "\"")
                                )
                                .filter(Optional::isPresent)
                                .map(Optional::get)
                )
                .forEach(System.out::println);
    }
}
