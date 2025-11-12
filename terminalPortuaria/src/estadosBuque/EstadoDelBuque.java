package estadosBuque;

import empresaMaritima.Buque;
import empresaMaritima.TerminalGestionada;

public interface EstadoDelBuque {

	String nombreEstado();
	
	void actualizarPosicion(Buque buque, Double nuevalocalizacion, TerminalGestionada terminal);
	
	void informarExportacion(Buque buque);
	
	void informarImportacion(Buque buque);
	
	void realizarPagos(Buque buque);
	
	
	
}
