import java.util.concurrent.*;

public class ProcesadorDePedidos {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Callable<String> pedido1 = () -> {
            Thread.sleep(1200); // Simula procesamiento
            return "📦 Pedido #1 entregado en 1.2 segundos";
        };

        Callable<String> pedido2 = () -> {
            Thread.sleep(800);
            return "📦 Pedido #2 entregado en 0.8 segundos";
        };

        Callable<String> pedido3 = () -> {
            Thread.sleep(1500);
            return "📦 Pedido #3 entregado en 1.5 segundos";
        };

        System.out.println("🛒 Procesando pedidos...");
        Future<String> r1 = executor.submit(pedido1);
        Future<String> r2 = executor.submit(pedido2);
        Future<String> r3 = executor.submit(pedido3);

        System.out.println(r1.get());
        System.out.println(r2.get());
        System.out.println(r3.get());

        executor.shutdown();
        System.out.println("✅ Todos los pedidos fueron procesados.");
    }
}