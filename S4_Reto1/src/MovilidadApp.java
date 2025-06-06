import java.util.concurrent.*;
import java.util.concurrent.ThreadLocalRandom;

public class MovilidadApp {

    // Simula el cálculo de la ruta (2 a 3 segundos)
    public static CompletableFuture<String> calcularRuta() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("🚦 Calculando ruta...");
                Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 3001)); // 2-3 segundos
                return "Centro -> Norte";
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al calcular la ruta");
            }
        });
    }

    // Simula la estimación de la tarifa (1 a 2 segundos)
    public static CompletableFuture<Double> estimarTarifa() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("💰 Estimando tarifa...");
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2001)); // 1-2 segundos
                // Descomenta esta línea para simular un error:
                // if (true) throw new RuntimeException("Fallo en la estimación de tarifa");
                return 75.50;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al estimar la tarifa");
            }
        });
    }

    public static void main(String[] args) {
        CompletableFuture<String> rutaFuture = calcularRuta();
        CompletableFuture<Double> tarifaFuture = estimarTarifa();

        rutaFuture.thenCombine(tarifaFuture, (ruta, tarifa) -> {
            return "✅ 🚗 Ruta calculada: " + ruta + " | Tarifa estimada: $" + tarifa;
        }).exceptionally(ex -> {
            return "❌ Ocurrió un error durante el procesamiento: " + ex.getMessage();
        }).thenAccept(System.out::println);

        // Esperar a que terminen las tareas antes de cerrar el programa
        try {
            Thread.sleep(4000); // Espera suficiente para que todo termine
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
