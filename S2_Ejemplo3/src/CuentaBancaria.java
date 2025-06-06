import java.util.concurrent.locks.ReentrantLock;

public class CuentaBancaria {
    private int saldo = 1000;
    private final ReentrantLock lock = new ReentrantLock();

    public void retirar(String nombre, int cantidad) {
        lock.lock();
        try {
            if (saldo >= cantidad) {
                System.out.println(nombre + " está retirando $" + cantidad);
                saldo -= cantidad;
                System.out.println(nombre + " completó el retiro. Saldo restante: $" + saldo);
            } else {
                System.out.println(nombre + " no pudo retirar: fondos insuficientes.");
            }
        } finally {
            lock.unlock(); // ⚠️ ¡Siempre liberar el lock!
        }
    }

    public int getSaldo() {
        return saldo;
    }
}