package empresaMaritima;

	import java.time.LocalDateTime;
import java.util.ArrayList;
	import java.util.List;

	public class CircuitoMaritimo {
		    
		private String nombre;
		private List<Tramo> tramos =new ArrayList<>();
		private LocalDateTime fechaInicio;

		
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
		    
		    public long calcularDuracionHasta(TerminalGestionada destino) {
		        long totalHoras = 0;
		        for (Tramo tramo : tramos) {
		            totalHoras += tramo.getDuracionEnHoras();
		            if (tramo.getTerminalDestino().equals(destino)) {
		                return totalHoras;
		            }
		        }
				return totalHoras;
		    }
		    public boolean contieneTerminal(TerminalGestionada terminal) {
                for (Tramo tramo : tramos) {
                    if (tramo.getTerminalDestino().equals(terminal)) {
                        return true;
                    }
                }
                return false;
            }

            
            public LocalDateTime getFechaDeInicio() {
                return fechaInicio;
            }
	}


