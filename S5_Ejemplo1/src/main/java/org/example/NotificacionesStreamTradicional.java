package org.example;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NotificacionesStreamTradicional {
    public static void main(String[] args) throws InterruptedException {
        List<String> usuarios = Arrays.asList("Ana", "Luis", "Marta", "Carlos");


        System.out.println("📢 Enviando notificaciones con Stream API:");

        usuarios.forEach(NotificacionesStreamTradicional::enviarNotificacion);
    }

    private static void enviarNotificacion(String usuario) {
        try {
            TimeUnit.SECONDS.sleep(1); // Simula retraso en enviar la notificación
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("✅ Notificación enviada a: " + usuario);
    }
}