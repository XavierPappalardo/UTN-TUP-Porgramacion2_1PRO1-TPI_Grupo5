package Services;

import Entities.*;
import Enums.Estado;
import Exceptions.EntidadNoEncontradaException;
import Exceptions.StockInvalidoException;

import java.util.ArrayList;
import java.util.List;

public class PedidoService {

    private List<Pedido> pedidos;

    public PedidoService() { pedidos = new ArrayList<>(); }

    // Metodos

    // Crear Pedido

    public void crearPedido(Pedido pedido) {

        if (pedido.getUsuario() == null) {
            throw new EntidadNoEncontradaException("El pedido debe tener un usuario asociado.");
        }

        pedidos.add(pedido);
    }

    // Listar Pedidos

    public void listarPedidos() {
        boolean hayPedidos = false;

        for (Pedido pedido : pedidos) {

            if (!pedido.isEliminado()) {
                System.out.println(pedido);
                hayPedidos = true;
            }
        }

        if (!hayPedidos) {
            System.out.println("No hay pedidos cargados.");
        }
    }

    // Buscar por ID

    public Pedido buscarPorId(Long id) {

        for (Pedido pedido : pedidos) {

            if (pedido.getId().equals(id)
                    && !pedido.isEliminado()) {

                return pedido;
            }
        }

        throw new EntidadNoEncontradaException(
                "Pedido",
                id
        );
    }

    // Agregar Producto al Pedido

    public void agregarProductoAlPedido(Long idPedido,
                                        Producto producto,
                                        int cantidad)
            throws StockInvalidoException {

        Pedido pedido = buscarPorId(idPedido);

        if (producto == null) {
            throw new EntidadNoEncontradaException(
                    "El producto no existe."
            );
        }

        if (cantidad > producto.getStock()) {

            throw new StockInvalidoException(
                    producto.getNombre(),
                    cantidad,
                    producto.getStock()
            );
        }

        pedido.addDetallePedido(
                cantidad,
                producto.getPrecio(),
                producto
        );

        producto.setStock(
                producto.getStock() - cantidad
        );
    }

    // Cambiar Estado del Pedido

    public void cambiarEstado(Long idPedido,
                              Estado estado) {

        Pedido pedido = buscarPorId(idPedido);

        pedido.setEstado(estado);
    }

    // Eliminar Pedido

    public void eliminarPedido(Long id) {
        Pedido pedido = buscarPorId(id);

        pedido.setEliminado(true);
    }

    // Obtener la lista

    public List<Pedido> getPedidos() { return pedidos; }
}
