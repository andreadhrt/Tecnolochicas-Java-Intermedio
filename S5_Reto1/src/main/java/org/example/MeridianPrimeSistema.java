package org.example;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class MeridianPrimeSistema {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("🌐 Iniciando monitoreo de sistemas críticos en Meridian Prime...\n");

        // 🚗 Sensores de tráfico
        Flux.interval(Duration.ofMillis(500))
                .map(i -> ThreadLocalRandom.current().nextInt(0, 101))
                .filter(nivel -> nivel > 70)
                .onBackpressureBuffer()
                .map(nivel -> "🚗 Alerta: Congestión del " + nivel + "% en Avenida Solar")
                .subscribe(System.out::println);

        // 🌫️ Contaminación del aire
        Flux.interval(Duration.ofMillis(600))
                .map(i -> ThreadLocalRandom.current().nextInt(10, 100))
                .filter(pm -> pm > 50)
                .map(pm -> "🌫️ Alerta: Contaminación alta (PM2.5: " + pm + " ug/m3)")
                .subscribe(System.out::println);

        // 🚑 Accidentes viales
        List<String> prioridades = Arrays.asList("Baja", "Media", "Alta");
        Flux.interval(Duration.ofMillis(800))
                .map(i -> prioridades.get(ThreadLocalRandom.current().nextInt(0, 3)))
                .filter(p -> p.equalsIgnoreCase("Alta"))
                .map(p -> "🚑 Emergencia vial: Accidente con prioridad Alta")
                .subscribe(System.out::println);

        // 🚝 Trenes maglev
        Flux.interval(Duration.ofMillis(700))
                .map(i -> ThreadLocalRandom.current().nextInt(0, 11))
                .filter(min -> min > 5)
                .map(min -> "🚝 Tren maglev con retraso crítico: " + min + " minutos")
                .subscribe(System.out::println);

        // 🚦 Semáforos inteligentes
        List<String> estados = Arrays.asList("Verde", "Amarillo", "Rojo");
        AtomicInteger contadorRojos = new AtomicInteger(0);
        Flux.interval(Duration.ofMillis(400))
                .map(i -> estados.get(ThreadLocalRandom.current().nextInt(0, 3)))
                .doOnNext(estado -> {
                    if (estado.equals("Rojo")) {
                        if (contadorRojos.incrementAndGet() >= 3) {
                            System.out.println("🚦 Semáforo en Rojo detectado 3 veces seguidas en cruce Norte");
                            contadorRojos.set(0);
                        }
                    } else {
                        contadorRojos.set(0);
                    }
                })
                .subscribe();

        // 🧪 Alerta global: si ocurren 3 o más eventos críticos al mismo tiempo
        Flux.merge(
                        congestionCritica(),
                        contaminacionCritica(),
                        accidenteCritico(),
                        trenCritico(),
                        semaforoCritico()
                )
                .buffer(Duration.ofSeconds(1))
                .filter(eventos -> eventos.size() >= 3)
                .map(eventos -> "\n🚨 Alerta global: Múltiples eventos críticos detectados en Meridian Prime\n")
                .subscribe(System.out::println);

        Thread.sleep(15000); // Esperar la ejecución
    }

    // Flujos individuales para la alerta global
    private static Flux<String> congestionCritica() {
        return Flux.interval(Duration.ofMillis(500))
                .map(i -> ThreadLocalRandom.current().nextInt(0, 101))
                .filter(nivel -> nivel > 70)
                .map(nivel -> "🚗");
    }

    private static Flux<String> contaminacionCritica() {
        return Flux.interval(Duration.ofMillis(600))
                .map(i -> ThreadLocalRandom.current().nextInt(10, 100))
                .filter(pm -> pm > 50)
                .map(pm -> "🌫️");
    }

    private static Flux<String> accidenteCritico() {
        List<String> prioridades = Arrays.asList("Baja", "Media", "Alta");
        return Flux.interval(Duration.ofMillis(800))
                .map(i -> prioridades.get(ThreadLocalRandom.current().nextInt(0, 3)))
                .filter(p -> p.equalsIgnoreCase("Alta"))
                .map(p -> "🚑");
    }

    private static Flux<String> trenCritico() {
        return Flux.interval(Duration.ofMillis(700))
                .map(i -> ThreadLocalRandom.current().nextInt(0, 11))
                .filter(min -> min > 5)
                .map(min -> "🚝");
    }

    private static Flux<String> semaforoCritico() {
        List<String> estados = Arrays.asList("Verde", "Amarillo", "Rojo");
        AtomicInteger contadorRojos = new AtomicInteger(0);
        return Flux.interval(Duration.ofMillis(400))
                .map(i -> estados.get(ThreadLocalRandom.current().nextInt(0, 3)))
                .filter(estado -> {
                    if (estado.equals("Rojo")) {
                        return contadorRojos.incrementAndGet() == 3;
                    } else {
                        contadorRojos.set(0);
                        return false;
                    }
                })
                .map(e -> "🚦");
    }
}
