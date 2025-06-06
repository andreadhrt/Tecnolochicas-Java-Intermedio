public class Main {
    public static void main(String[] args) {
        Thread hilo1 = new Thread(new Tarea("Tarea 1"));
        Thread hilo2 = new Thread(new Tarea("Tarea 2"));

        hilo1.start(); // Inicia la ejecución del hilo 1
        hilo2.start(); // Inicia la ejecución del hilo 2

        System.out.println("Hilos iniciados desde el hilo principal: " +
                Thread.currentThread().getName());
    }
}