package empresaMaritima;

import java.util.*;

import containers.BillOfLading;
import containers.Container;
import containers.Dry;
import containers.Reefer;
import containers.Tanque;
import estadosBuque.EstadoDelBuque;
import reportes.ReporteVisitable;
import reportes.ReporteVisitor;

public class Buque implements ReporteVisitable {

    public String nombreBuque;
    private Double gps;

    private Set<Container> cargas = new HashSet<>();
    private List<Viaje> viajes = new ArrayList<>();

    private EstadoDelBuque estado;
    private TerminalGestionada terminalDestino;
    private TerminalGestionada terminalOrigen;


    public Buque(String nombre, Double gps, EstadoDelBuque estado) {
        this.nombreBuque = nombre;
        this.gps = gps;
        this.estado = estado;
    }

    public void addContainer(Container container) {
        cargas.add(container);
    }

    public Set<Container> getCargas() {
        return Collections.unmodifiableSet(cargas);
    }

    public void actualizarPosicion(Buque buque, Double gps) {
        this.gps = gps;
        if (estado != null) {
            estado.actualizarPosicion(this, gps, terminalDestino);
        }
    }

    public EstadoDelBuque getEstadoBuque() {
        return this.estado;
    }

    public void setEstado(EstadoDelBuque estadoNuevo) {
        System.out.println("Cambiando estado: " + estadoNuevo.nombreEstado());
        this.estado = estadoNuevo;
    }

    public Double getGps() {
        return gps;
    }

    public String getNombre() {
        return nombreBuque;
    }
    public void setCargas(Set<Container> cargas) {
        this.cargas = cargas;
    }
    @Override
    public String toString() {
        return nombreBuque;
    }

    @Override
    public void accept(ReporteVisitor visitor) {
        visitor.visit(this);

        for (Container c : cargas) {
            c.accept(visitor);
        }
    }

    public void agregarViaje(Viaje viaje) {
        viajes.add(viaje);
    }

    public List<Viaje> getViajes() {
        return List.copyOf(viajes);
    }

    public List<String> getRegistroDeViajes() {
        List<String> registros = new ArrayList<>();

        for (Viaje v : viajes) {
            registros.add(
                "Viaje " + v.getId() +
                " | Origen: " + v.getTerminalOrigen().getNombre() +
                " | Destino: " + v.getTerminalDestino().getNombre() +
                " | Salida: " + v.getPartida() +
                " | Arribo: " + v.getArribo()
            );
        }

        return registros;
    }

    public List<String> getTerminalesVisitadas() {
        List<String> terminales = new ArrayList<>();

        for (Viaje v : viajes) {
            terminales.addAll(v.getTerminalesRecorridas());
        }

        return terminales.stream().distinct().toList();
    }

    public List<BillOfLading> getCargaTransportada() {
        List<BillOfLading> bls = new ArrayList<>();

        for (Container c : cargas) {

            if (c instanceof Dry dry) {
                bls.addAll(dry.getBls());
            }

            else if (c instanceof Reefer reefer) {
                if (reefer.getBl() != null) bls.add(reefer.getBl());
            }

            else if (c instanceof Tanque tanque) {
                if (tanque.getBl() != null) bls.add(tanque.getBl());
            }
        }

        return bls;
    }

    public List<String> getDetallesCargaPorContenedor() {
        List<String> detalles = new ArrayList<>();

        for (Container c : cargas) {
            StringBuilder sb = new StringBuilder();

            sb.append("Contenedor ")
              .append(c.getIDContainer())
              .append(" (").append(c.getTipo()).append(")")
              .append(" transportó: ");

            if (c instanceof Dry dry) {
                sb.append(dry.getBls());
            }

            else if (c instanceof Reefer reefer) {
                sb.append(reefer.getBl());
            }

            else if (c instanceof Tanque tanque) {
                sb.append(tanque.getBl());
            }

            detalles.add(sb.toString());
        }

        return detalles;
    }
}
