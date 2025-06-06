import java.util.concurrent.*;
import java.util.concurrent.ThreadLocalRandom;

public class AeropuertoControl {

    private static final ExecutorService executor = Executors.newFixedThreadPool(4);

    public static CompletableFuture<Boolean> verificarPista() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000 + ThreadLocalRandom.current().nextInt(1000));
                boolean disponible = ThreadLocalRandom.current().nextInt(100) < 80; // 80% chance
                System.out.println("🛣️ Pista disponible: " + disponible);
                return disponible;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al verificar pista");
            }
        }, executor);
    }

    public static CompletableFuture<Boolean> verificarClima() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000 + ThreadLocalRandom.current().nextInt(1000));
                boolean favorable = ThreadLocalRandom.current().nextInt(100) < 85; // 85% chance
                System.out.println("🌦️ Clima favorable: " + favorable);
                return favorable;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al verificar clima");
            }
        }, executor);
    }

    public static CompletableFuture<Boolean> verificarTraficoAereo() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000 + ThreadLocalRandom.current().nextInt(1000));
                boolean despejado = ThreadLocalRandom.current().nextInt(100) < 90; // 90% chance
                System.out.println("🚦 Tráfico aéreo despejado: " + despejado);
                return despejado;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al verificar tráfico aéreo");
            }
        }, executor);
    }

    public static CompletableFuture<Boolean> verificarPersonalTierra() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000 + ThreadLocalRandom.current().nextInt(1000));
                boolean disponible = ThreadLocalRandom.current().nextInt(100) < 95; // 95% chance
                System.out.println("👷‍♂️ Personal disponible: " + disponible);
                return disponible;
            } catch (InterruptedException e) {
                throw new RuntimeException("Error al verificar personal en tierra");
            }
        }, executor);
    }

    public static void main(String[] args) {
        System.out.println("🛫 Verificando condiciones para aterrizaje...\n");

        CompletableFuture<Boolean> pista = verificarPista();
        CompletableFuture<Boolean> clima = verificarClima();
        CompletableFuture<Boolean> trafico = verificarTraficoAereo();
        CompletableFuture<Boolean> personal = verificarPersonalTierra();

        // Combinar todos los resultados
        CompletableFuture<Void> validaciones = CompletableFuture.allOf(pista, clima, trafico, personal);

        validaciones
                .thenApply(v -> {
                    try {
                        // Esperar resultados individuales
                        boolean okPista = pista.get();
                        boolean okClima = clima.get();
                        boolean okTrafico = trafico.get();
                        boolean okPersonal = personal.get();

                        return okPista && okClima && okTrafico && okPersonal;
                    } catch (Exception e) {
                        throw new CompletionException("❌ Error al obtener los resultados", e);
                    }
                })
                .thenAccept(autorizado -> {
                    if (autorizado) {
                        System.out.println("\n🛬 Aterrizaje autorizado: todas las condiciones óptimas.");
                    } else {
                        System.out.println("\n🚫 Aterrizaje denegado: condiciones no óptimas.");
                    }
                })
                .exceptionally(ex -> {
                    System.out.println("\n🚨 Error en el proceso de verificación: " + ex.getMessage());
                    return null;
                })
                .thenRun(() -> executor.shutdown());
    }
}
