package empresaMaritima;

import java.time.LocalDate;
import java.util.List;

import actores.ActorPortuario;
import containers.Container;
import serviciosDeContainer.Servicio;
//Representa una factura emitida por la terminal portuaria por los servicios
//prestados sobre un contenedor. Cada factura está asociada a un actor portuario responsable del pago
//una lista de servicios realizados y el contenedor correspondiente.
public class Factura {
	
    LocalDate fechaEmision;
    ActorPortuario responsable;
    List<Servicio> servicios;
    double total;
    Container container;

    public Factura(LocalDate fechaEmision, ActorPortuario responsable, List<Servicio> servicios, Container container) {
       
        this.fechaEmision = fechaEmision;
        this.responsable = responsable;
        this.servicios = servicios;
        this.container = container;
        this.total = calcularTotal();
       
    }
//suma los costos de cada servicio aplicado
    private double calcularTotal() {
        double totalAcumulado = 0;
        for (Servicio servicio : servicios) {
            totalAcumulado += servicio.calcularCosto(container);
        }
        return totalAcumulado;
    }
// simula el envío de la factura al responsable
    public void enviarPorMail() {
        System.out.println("Enviando factura a " + responsable.getNombre() + " por $" + total);
    }
//devuelve el monto total de la factura
    public double getTotal() {
        return total;
    }
}
