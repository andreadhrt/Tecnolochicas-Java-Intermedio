import java.util.*;
import java.util.function.Predicate;

public class PlataformaEducativa {

    public static void mostrarMateriales(List<? extends MaterialCurso> lista) {
        System.out.println("📚 Materiales del curso:");
        for (MaterialCurso m : lista) {
            m.mostrarDetalle();
        }
        System.out.println();
    }

    public static void contarDuracionVideos(List<? extends Video> lista) {
        int total = 0;
        for (Video v : lista) {
            total += v.getDuracion();
        }
        System.out.println("🎥 Duración total de videos: " + total + " minutos\n");
    }

    public static void marcarEjerciciosRevisados(List<? super Ejercicio> lista) {
        for (Object obj : lista) {
            if (obj instanceof Ejercicio e) {
                e.marcarRevisado();
            }
        }
        System.out.println();
    }

    public static void filtrarPorAutor(List<? extends MaterialCurso> lista, Predicate<MaterialCurso> criterio) {
        System.out.println("🔍 Filtrando materiales por autor:");
        for (MaterialCurso m : lista) {
            if (criterio.test(m)) {
                m.mostrarDetalle();
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<MaterialCurso> materiales = new ArrayList<>();
        materiales.add(new Video("Introducción a Java", "Mario", 15));
        materiales.add(new Video("POO en Java", "Carlos", 20));
        materiales.add(new Articulo("Historia de Java", "Ana", 1200));
        materiales.add(new Articulo("Tipos de datos", "Luis", 800));
        materiales.add(new Ejercicio("Variables y tipos", "Luis"));
        materiales.add(new Ejercicio("Condicionales", "Mario"));

        // Mostrar todos los materiales
        mostrarMateriales(materiales);

        // Contar duración de videos
        contarDuracionVideos(materiales.stream()
                .filter(m -> m instanceof Video)
                .map(m -> (Video) m)
                .toList()
        );

        // Marcar ejercicios como revisados
        marcarEjerciciosRevisados(materiales);

        // Filtrar materiales por autor (ej. Mario)
        filtrarPorAutor(materiales, m -> m.getAutor().equals("Mario"));
    }
}
