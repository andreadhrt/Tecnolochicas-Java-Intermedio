import java.util.concurrent.*;

public class SistemaPedidos {

    public static void main(String[] args) {
        System.out.println("⚙️ Procesando pedido asincrónicamente...\n");

        CompletableFuture<Void> pedido = procesarPago()
                .thenApply(pago -> generarFactura(pago)) // 🔄 Encadena y transforma el resultado
                .thenAccept(factura -> enviarNotificacion(factura)) // 📤 Consume el resultado final
                .exceptionally(ex -> { // ❌ Maneja errores
                    System.out.println("🚨 Error en el proceso: " + ex.getMessage());
                    return null;
                });

        pedido.join(); // 🕒 Espera que termine todo el flujo
    }

    // 🏦 Simula el procesamiento de un pago
    public static CompletableFuture<String> procesarPago() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("💳 Procesando pago...");
            dormir(2);
            System.out.println("✅ Pago confirmado");
            return "Pago#123";
        });
    }

    // 🧾 Simula la generación de una factura
    public static String generarFactura(String pago) {
        System.out.println("📝 Generando factura para " + pago + "...");
        dormir(1);
        String factura = "Factura#456";
        System.out.println("✅ Factura generada: " + factura);
        return factura;
    }

    // 📧 Simula el envío de una notificación
    public static void enviarNotificacion(String factura) {
        System.out.println("📧 Enviando notificación por " + factura + "...");
        dormir(1);
        System.out.println("✅ Notificación enviada");
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