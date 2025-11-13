package empresaMaritima;
//Representa un Circuito Maritimo operado por una naviera. Un circuito es una 
//secuencia unidireccional de tramos entre terminales portuarias, que los buques recorren de manera periodica.
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

//incorpora un tramo al circuito
		public void agregarTramo(Tramo tramo) {
			tramos.add(tramo);
		}

//suma la duración de todos los tramos
		public long calcularTiempoTotalHoras() {
			long total = 0;
			for (Tramo tramo : tramos) {
				total += tramo.getDuracionEnHoras();
			}
			return total;
		}

//suma el precio de todos los tramos
		public double calcularPrecioTotal() {
			double total = 0;
			for (Tramo tramo : tramos) {
				total += tramo.getPrecio();
			}
			return total;
		}
//calcula el tiempo necesario hasta alcanzar  una terminal específica
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
	//indica si la ruta llega a una determinada terminal
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
		
 //calcula el precio acumulado hasta la terminal destino     
		public double calcularPrecioHasta(TerminalGestionada destino) {
			double total = 0;
			for (Tramo tramo : tramos) {
				total += tramo.getPrecio();
				if (tramo.getTerminalDestino().equals(destino)) {
					return total;
				}
			}
			return total;
		}
		
//cuenta cuántas escalas existen antes  de llegar al destino
		public int cantidadDeTerminalesIntermedias(TerminalGestionada destino) {
		    int cantidad = 0;
		    for (Tramo tramo : tramos) {
		        cantidad++;
		        if (tramo.getTerminalDestino().equals(destino)) {
		            break;
		        }
		    }
		    if (cantidad > 0) cantidad--;
		    return cantidad;
		}
}


