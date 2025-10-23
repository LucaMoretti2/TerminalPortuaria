package circuitoMaritimo;

import java.util.ArrayList;
import java.util.List;

import tramo.Tramo;

public class circuitoMaritimo {
	public class CircuitoMaritimo {
	    private String nombre;
	    private List<Tramo> tramos;

	
	    public CircuitoMaritimo(String nombre) {
	        this.nombre = nombre;
	        this.tramos = new ArrayList<>();
	    }

	   
	    public String getNombre() {
	        return nombre;
	    }

	    public List<Tramo> getTramos() {
	        return tramos;
	    }


	    public void agregarTramo(Tramo tramo) {
	        tramos.add(tramo);
	    }


	    public long calcularTiempoTotalHoras() {
	        long total = 0;
	        for (Tramo tramo : tramos) {
	            total += tramo.getDuracionEnHoras();
	        }
	        return total;
	    }


	    public double calcularPrecioTotal() {
	        double total = 0;
	        for (Tramo tramo : tramos) {
	            total += tramo.getPrecio();
	        }
	        return total;
	    }

	    // Falta mostrar el circuito
}
}

