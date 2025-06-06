public class Tarea implements Runnable {

    private String nombre;

    public Tarea(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Ejecutando " + nombre + " - Iteración " + i +
                    " - Hilo: " + Thread.currentThread().getName());
            try {
                Thread.sleep(500); // Simula una operación que toma tiempo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(nombre + " fue interrumpido");
            }
        }
    }
}