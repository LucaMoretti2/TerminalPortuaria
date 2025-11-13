package empresaMaritima;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import containers.Container;
import serviciosDeContainer.Servicio;

public class Factura {
	

    private LocalDate fechaEmision;
    private ActorPortuario responsable;
    private List<Servicio> servicios;
    private double total;
    private Container container;

    public Factura(LocalDate fechaEmision, ActorPortuario responsable, List<Servicio> servicios, Container container) {
       
        this.fechaEmision = fechaEmision;
        this.responsable = responsable;
        this.servicios = servicios;
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
