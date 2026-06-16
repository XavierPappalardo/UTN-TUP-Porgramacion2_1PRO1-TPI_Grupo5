package Entities;

import Enums.Estado;
import Enums.FormaPago;
import Interfaces.Calculable;

import java.time.LocalDate;

public class Pedido extends Base implements Calculable {

    private LocalDate fecha;

    private Estado estado;

    private Double total;

    private FormaPago formaPago;



    public void addDetallePedido(int i, Double j, Pedido k){

        @Override

        public void calcularTotal();

    }

    public DetallePedido findeDetallePedidoByProducto(Producto producto){



    }

    public void deleteDetallePedidoByProducto(Producto producto){



    }

}
