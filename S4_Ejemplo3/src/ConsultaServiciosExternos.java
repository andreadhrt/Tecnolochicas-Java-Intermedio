import java.util.concurrent.*;

public class ConsultaServiciosExternos {

    public static void main(String[] args) {
        System.out.println("🌐 Iniciando consultas a servicios externos...\n");

        CompletableFuture<String> climaFuture = obtenerClima();
        CompletableFuture<String> traficoFuture = obtenerTrafico();

        // 🔗 Combina ambas consultas en un solo reporte
        CompletableFuture<Void> reporteFinal = climaFuture.thenCombine(traficoFuture,
                        (clima, trafico) -> {
                            return "📊 Reporte del día:\n- Clima: " + clima + "\n- Tráfico: " + trafico;
                        })
                .thenAccept(System.out::println) // 📤 Imprimir reporte
                .exceptionally(ex -> { // ❌ Manejo de errores
                    System.out.println("🚨 Error al generar el reporte: " + ex.getMessage());
                    return null;
                });

        // Esperar que todo termine
        reporteFinal.join();
    }

    // 🌦️ Simula consulta a un servicio de clima
    public static CompletableFuture<String> obtenerClima() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("🌦️ Consultando clima...");
            dormir(3); // Latencia simulada
            return "Soleado, 25°C";
        });
    }

    // 🚗 Simula consulta a un servicio de tráfico
    public static CompletableFuture<String> obtenerTrafico() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("🚗 Consultando tráfico...");
            dormir(2); // Latencia simulada
            return "Moderado en el centro";
        });
    }

    // 💤 Método auxiliar para simular latencia
    public static void dormir(int segundos) {
        try {
            TimeUnit.SECONDS.sleep(segundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}