import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class SimuladorRecursosMedicos {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("🏥 Iniciando acceso a la Sala de cirugía...\n");

        // Crear el recurso compartido
        RecursoMedico salaCirugia = new RecursoMedico("Sala de cirugía");

        // Crear tareas que simulan profesionales intentando usar el recurso
        Runnable draSanchez = () -> salaCirugia.usar("👩‍⚕️ Dra. Sánchez");
        Runnable drGomez = () -> salaCirugia.usar("👨‍⚕️ Dr. Gómez");
        Runnable enfermeraLopez = () -> salaCirugia.usar("👩‍⚕️ Enfermera López");
        Runnable drRamirez = () -> salaCirugia.usar("👨‍⚕️ Dr. Ramírez");

        // Crear el pool de hilos
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Enviar tareas al pool
        executor.execute(draSanchez);
        executor.execute(drGomez);
        executor.execute(enfermeraLopez);
        executor.execute(drRamirez);

        // Cerrar el executor
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }
}

// Clase que representa un recurso médico exclusivo
class RecursoMedico {
    private final String nombre;
    private final ReentrantLock lock = new ReentrantLock();

    public RecursoMedico(String nombre) {
        this.nombre = nombre;
    }

    public void usar(String profesional) {
        lock.lock(); // Adquiere el bloqueo (si otro ya lo tiene, espera)
        try {
            System.out.println(profesional + " ha ingresado a " + nombre);
            Thread.sleep(2000); // Simula tiempo de uso
            System.out.println("✅ " + profesional + " ha salido de " + nombre);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock(); // Libera el recurso
        }
    }
}
