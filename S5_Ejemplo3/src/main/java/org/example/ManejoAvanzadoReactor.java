package org.example;
import reactor.core.publisher.Flux;
import java.time.Duration;

public class ManejoAvanzadoReactor {

    public static void main(String[] args) throws InterruptedException {

        // Ejemplo 1️⃣: Sistema de pedidos con backpressure
        Flux.interval(Duration.ofMillis(500))
                .onBackpressureBuffer() // 👈 Añadido para evitar overflow
                .map(i -> new Pedido(i + 1, i % 3 == 0 ? "Prioritario" : "Normal"))
                .take(9)
                .filter(p -> p.getTipo().equals("Prioritario"))
                .doOnNext(p -> System.out.println("📥 Pedido recibido: " + p))
                .delayElements(Duration.ofSeconds(1))
                .subscribe(p -> System.out.println("✅ Pedido procesado: " + p));

        // Ejemplo 2️⃣: Alertas de sensores
        Flux.interval(Duration.ofMillis(400))
                .onBackpressureBuffer() // 👈 Añadido para evitar overflow
                .map(i -> (int) (Math.random() * 50))
                .take(7)
                .filter(temp -> temp > 30)
                .delayElements(Duration.ofMillis(800))
                .subscribe(temp -> System.out.println("⚠️ Alerta: Temperatura alta - " + temp + "°C"));

        // Ejemplo 3️⃣: Mensajes en redes sociales
        Flux.interval(Duration.ofMillis(300))
                .onBackpressureBuffer() // 👈 Añadido para evitar overflow
                .map(i -> "Mensaje #" + (i + 1))
                .take(5)
                .doOnNext(m -> System.out.println("💬 Mensaje recibido: " + m))
                .delayElements(Duration.ofMillis(1000))
                .subscribe(m -> System.out.println("📢 Procesado: " + m));

        Thread.sleep(10000); // Esperar todos los flujos
    }

    // Clase auxiliar Pedido
    static class Pedido {
        private final long id;
        private final String tipo;

        public Pedido(long id, String tipo) {
            this.id = id;
            this.tipo = tipo;
        }

        public String getTipo() { return tipo; }

        @Override
        public String toString() {
            return "Pedido#" + id + " [" + tipo + "]";
        }
    }
}