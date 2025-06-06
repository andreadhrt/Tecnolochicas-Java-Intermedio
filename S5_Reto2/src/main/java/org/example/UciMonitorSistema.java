package org.example;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

public class UciMonitorSistema {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("🏥 Iniciando monitoreo de pacientes en UCI...\n");

        // Flujo de cada paciente
        Flux<String> paciente1 = generarFlujoPaciente(1);
        Flux<String> paciente2 = generarFlujoPaciente(2);
        Flux<String> paciente3 = generarFlujoPaciente(3);

        // Fusión de flujos con manejo de backpressure
        Flux.merge(paciente1, paciente2, paciente3)
                .onBackpressureBuffer() // ✅ Esto evita OverflowException
                .filter(msg -> !msg.isEmpty())
                .delayElements(Duration.ofSeconds(1)) // Simula tiempo de procesamiento
                .subscribe(System.out::println);

        Thread.sleep(20000); // Mantener el programa corriendo durante 20 segundos
    }

    // Genera flujo simulado de signos vitales para un paciente
    private static Flux<String> generarFlujoPaciente(int id) {
        return Flux.interval(Duration.ofMillis(300))
                .map(i -> generarEventoCritico(id))
                .filter(msg -> !msg.isEmpty());
    }

    // Simula lectura de signos vitales y retorna eventos críticos
    private static String generarEventoCritico(int pacienteId) {
        int fc = ThreadLocalRandom.current().nextInt(40, 140); // Frecuencia cardíaca
        int sistolica = ThreadLocalRandom.current().nextInt(80, 160);
        int diastolica = ThreadLocalRandom.current().nextInt(50, 100);
        int spo2 = ThreadLocalRandom.current().nextInt(85, 100);

        // Prioridad 1: FC crítica
        if (fc < 50 || fc > 120) {
            return "⚠️ Paciente " + pacienteId + " - FC crítica: " + fc + " bpm";
        }

        // Prioridad 2: SpO2 bajo
        if (spo2 < 90) {
            return "⚠️ Paciente " + pacienteId + " - SpO2 baja: " + spo2 + "%";
        }

        // Prioridad 3: Presión arterial crítica
        if (sistolica > 140 || diastolica > 90 || sistolica < 90 || diastolica < 60) {
            return "⚠️ Paciente " + pacienteId + " - PA crítica: " + sistolica + "/" + diastolica + " mmHg";
        }

        return ""; // No crítico
    }
}
