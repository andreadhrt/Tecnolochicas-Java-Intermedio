public class Main {
    public static void main(String[] args) {
        // 🧺 Almacén de ropa
        Almacen<String> almacenRopa = new Almacen<>();
        System.out.println("¿Almacén de ropa vacío? " + almacenRopa.estaVacio());
        almacenRopa.guardarProducto("Camisa");
        almacenRopa.mostrarTipoProducto();

        // 🔢 Almacén de números
        Almacen<Integer> almacenNumeros = new Almacen<>();
        almacenNumeros.guardarProducto(42);
        almacenNumeros.mostrarTipoProducto();

        // 🍏 Almacén de alimentos
        Almacen<String> almacenAlimentos = new Almacen<>();
        almacenAlimentos.guardarProducto("Manzana");
        almacenAlimentos.mostrarTipoProducto();

        // 🎯 Mostrar productos recuperados
        System.out.println("\n🎯 Productos recuperados:");
        System.out.println("🧺 Ropa: " + almacenRopa.obtenerProducto());
        System.out.println("🔢 Número: " + almacenNumeros.obtenerProducto());
        System.out.println("🍏 Alimento: " + almacenAlimentos.obtenerProducto());
    }
}