import java.util.*;

public class PlantaIndustrial {

    public static void mostrarOrdenes(List<? extends OrdenProduccion> lista) {
        System.out.println("📋 Órdenes registradas:");
        for (OrdenProduccion orden : lista) {
            orden.mostrarResumen();
        }
        System.out.println();
    }

    public static void procesarPersonalizadas(List<? super OrdenPersonalizada> lista, int costoAdicional) {
        System.out.println("💰 Procesando órdenes personalizadas...");
        for (Object obj : lista) {
            if (obj instanceof OrdenPersonalizada personalizada) {
                personalizada.agregarCostoAdicional(costoAdicional);
            }
        }
        System.out.println();
    }

    public static void contarOrdenes(List<OrdenProduccion> todas) {
        int masa = 0, personalizadas = 0, prototipos = 0;
        for (OrdenProduccion o : todas) {
            if (o instanceof OrdenMasa) masa++;
            else if (o instanceof OrdenPersonalizada) personalizadas++;
            else if (o instanceof OrdenPrototipo) prototipos++;
        }
        System.out.println("📊 Resumen total de órdenes:");
        System.out.println("🔧 Producción en masa: " + masa);
        System.out.println("🛠️ Personalizadas: " + personalizadas);
        System.out.println("🧪 Prototipos: " + prototipos);
    }

    public static void main(String[] args) {
        List<OrdenMasa> listaMasa = Arrays.asList(
                new OrdenMasa("A123", 500),
                new OrdenMasa("A124", 750)
        );

        List<OrdenPersonalizada> listaPersonalizadas = Arrays.asList(
                new OrdenPersonalizada("P456", 100, "ClienteX"),
                new OrdenPersonalizada("P789", 150, "ClienteY")
        );

        List<OrdenPrototipo> listaPrototipos = Arrays.asList(
                new OrdenPrototipo("T789", 10, "Diseño"),
                new OrdenPrototipo("T790", 5, "Pruebas")
        );

        mostrarOrdenes(listaMasa);
        mostrarOrdenes(listaPersonalizadas);
        mostrarOrdenes(listaPrototipos);

        procesarPersonalizadas(new ArrayList<>(listaPersonalizadas), 200);

        List<OrdenProduccion> todas = new ArrayList<>();
        todas.addAll(listaMasa);
        todas.addAll(listaPersonalizadas);
        todas.addAll(listaPrototipos);

        contarOrdenes(todas);
    }
}
