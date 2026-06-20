package Entities;

import Enums.Estado;
import Enums.FormaPago;
import Interfaces.Calculable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido extends Base implements Calculable {

    // Variables

    private LocalDate fecha;
    private Estado estado;
    private Double total;
    private FormaPago formaPago;
    private Usuario usuario;
    private List<DetallePedido> detalles;

    //Constructor

    public Pedido(Long id,
                  boolean eliminado,
                  LocalDateTime createdAt,
                  LocalDate fecha,
                  Estado estado,
                  Double total,
                  FormaPago formaPago,
                  Usuario usuario,
                  List<DetallePedido> detalles) {
        super(id, eliminado, createdAt);
        this.fecha = fecha;
        this.estado = estado;
        this.formaPago = formaPago;
        this.usuario = usuario;

        this.total = 0.0;
        this.detalles = new ArrayList<>();
    }

    // Getters y Setters

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }


    // Metodos

    public void addDetallePedido(int cantidad, Producto producto){

        double subtotal = cantidad * producto.getPrecio();

        DetallePedido detallePedido = new DetallePedido(0L,false, LocalDateTime.now(), cantidad , subtotal , producto);

        detalles.add(detallePedido);

        calcularTotal();

    }

    @Override
    public void calcularTotal() {
        total = 0.0;

        for(DetallePedido d : detalles){
            total += d.getSubtotal();
        }
    }

    public DetallePedido findeDetallePedidoByProducto(Producto producto){

        for (DetallePedido detalle : detalles) {
            if (detalle.getProducto().equals(producto)) {
                return detalle;
            }
        }

        return null;
    }

    public void deleteDetallePedidoByProducto(Producto producto){

        DetallePedido detalle = findeDetallePedidoByProducto(producto);

        if (detalle != null) {
            detalles.remove(detalle);
            calcularTotal();
        }

    }

    // toString

    @Override
    public String toString() {
        return "Pedido{" +
                "fecha=" + fecha +
                ", estado=" + estado +
                ", total=" + total +
                ", formaPago=" + formaPago +
                ", usuario=" + usuario +
                ", detalles=" + detalles +
                '}';
    }
}
