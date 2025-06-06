import java.util.List;

public class Sucursal {
    private final String nombre;
    private final List<Pedido> pedidos;

    public Sucursal(String nombre, List<Pedido> pedidos) {
        this.nombre = nombre;
        this.pedidos = pedidos;
    }

    public List<Pedido> getPedidos() { return pedidos; }
    public String getNombre() { return nombre; }
}