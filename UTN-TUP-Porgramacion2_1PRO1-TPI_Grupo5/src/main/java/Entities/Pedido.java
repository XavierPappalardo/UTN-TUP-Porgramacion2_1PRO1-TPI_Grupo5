package Entities;

import Enums.Estado;
import Enums.FormaPago;

import java.time.LocalDate;

public class Pedido extends Base{

    private LocalDate fecha;

    private Estado estado;

    private Double total;

    private FormaPago formaPago;

    public void addDetallePedido(int i, Double j, Pedido k){



    }

    public DetallePedido findeDetallePedidoByProducto(Producto producto){



    }

    public void deleteDetallePedidoByProducto(Producto){



    }

}
