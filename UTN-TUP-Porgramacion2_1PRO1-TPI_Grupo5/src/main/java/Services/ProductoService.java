package Services;
import java.util.List;
import java.util.ArrayList;

import Entities.Categoria;
import Entities.Producto;
import Exceptions.StockInvalidoException;

public class ProductoService {

    private List<Producto> productos;

    public ProductoService(){
        productos = new ArrayList<>();
    }

    // Metodos

    // Crear Producto

    public void crearProducto(Producto producto) {
        if (producto.getPrecio() < 0) {
            throw new IllegalArgumentException(
                    "El precio no puede ser negativo."
            );
        }
        if (producto.getStock() < 0) {
            throw new StockInvalidoException(producto.getNombre(), );
        }

        productos.add(producto);
    }

    // Listar Producto

    public void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos cargados.");
            return;
        }

        for (Producto producto : productos) {
            if (!producto.isEliminado()) {
                System.out.println(producto);
            }
        }
    }

    // Buscar por ID

    public Producto buscarPorId(Long id) {
        for (Producto producto : productos) {
            if (producto.getId().equals(id) && !producto.isEliminado()) {
                return producto;
            }
        }

        return null;
    }

    // Editar Producto

    public boolean editarProducto(Long id,
                                  String nombre,
                                  Double precio,
                                  int stock,
                                  Categoria categoria) {
        Producto producto = buscarPorId(id);

        if(producto == null) {
            return false;
        }

        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setStock(stock);
        producto.setCategoria(categoria);

        return true;
    }
}
