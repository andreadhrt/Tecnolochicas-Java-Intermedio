package org.example;

import reactor.core.publisher.Flux;
import java.time.Duration;

public class NotificacionesFluxReactivo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("\n⚡ Enviando notificaciones con Flux:");

        Flux<String> usuariosFlux = Flux.just("Ana", "Luis", "Marta", "Carlos")
                .delayElements(Duration.ofSeconds(1));

        usuariosFlux.subscribe(usuario ->
                System.out.println("✅ Notificación enviada a: " + usuario)
        );

        Thread.sleep(5000); // Espera para ver los mensajes
    }
}
