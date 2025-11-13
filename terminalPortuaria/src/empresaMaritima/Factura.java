package empresaMaritima;

import java.time.LocalDate;
import java.util.List;

import actores.ActorPortuario;
import containers.Container;
import serviciosDeContainer.Servicio;

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

    private double calcularTotal() {
        double totalAcumulado = 0;
        for (Servicio servicio : servicios) {
            totalAcumulado += servicio.calcularCosto(container);
        }
        return totalAcumulado;
    }

    public void enviarPorMail() {
        System.out.println("Enviando factura a " + responsable.getNombre() + " por $" + total);
    }

    public double getTotal() {
        return total;
    }
}
