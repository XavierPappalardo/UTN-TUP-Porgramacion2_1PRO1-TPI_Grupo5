package Entities;

import java.time.LocalDateTime;

public class DetallePedido extends Base{

    // Variables
    private int cantidad;
    private Double subtotal;
    private Producto producto;

    //Constructor

    public DetallePedido(Long id,
                         boolean eliminado,
                         LocalDateTime createdAt,
                         int cantidad,
                         Double subtotal,
                         Producto producto) {
        super(id, eliminado, createdAt);
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.producto = producto;
    }

    // Getters y Setters

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    // toString

    @Override
    public String toString() {
        return "DetallePedido{" +
                "cantidad=" + cantidad +
                ", subtotal=" + subtotal +
                ", producto=" + producto +
                '}';
    }
}
