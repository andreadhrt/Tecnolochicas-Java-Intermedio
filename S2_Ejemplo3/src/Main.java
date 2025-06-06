public class Main {
    public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria();

        Runnable tarea = () -> {
            String nombreHilo = Thread.currentThread().getName();
            cuenta.retirar(nombreHilo, 300);
        };

        Thread t1 = new Thread(tarea, "Astronauta-1");
        Thread t2 = new Thread(tarea, "Astronauta-2");
        Thread t3 = new Thread(tarea, "Astronauta-3");

        t1.start();
        t2.start();
        t3.start();
    }
}