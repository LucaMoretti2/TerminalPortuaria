package containers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Dry extends Container {
//Un container Dry puede transportar uno o varios Bill of Lading, por lo que mantiene
// una lista de BL asociados a la mercadería que contiene.
	private List<BillOfLading> bls = new ArrayList<>();
	
	public Dry(int alto, int ancho, int largo, double pesoTotal, String idContainer, LocalDateTime fechaDeIngreso, LocalDateTime fechaDeRetiro) {
		super(alto, ancho, largo, pesoTotal, idContainer, fechaDeIngreso, fechaDeRetiro);
//Aca le pasa el tipo que es
		this.tipo = "Dry";
		// TODO Auto-generated constructor stub
	}
//agrega un Bill of Lading al contenedor
	public void addBl(BillOfLading bl) {
		bls.add(bl);	
	}
//devuelve una copia inmutable de los BL registrados
	public List<BillOfLading> getBls() {
        return List.copyOf(bls);
    }

//calcula el peso total de la carga contenida en los BL

	public double getPesoTotalDeBLs() {
		double pesoHastaAhora = 0;
		for (BillOfLading bl: bls) {
			pesoHastaAhora += bl.getPeso();
		}
		return pesoHastaAhora;
	}
//indica si el contenedor tiene un único BL (o ninguno)
	public boolean esDesconsolidado() {
        return bls.size() <= 1;
    }
}
