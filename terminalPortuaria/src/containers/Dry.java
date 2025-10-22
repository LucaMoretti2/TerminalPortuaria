package containers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Dry extends Container {

	private List<BillOfLading> bls = new ArrayList<>();
	
	public Dry(int alto, int ancho, int largo, double pesoTotal, String idContainer, LocalDateTime fechaDeIngreso, LocalDateTime fechaDeRetiro) {
		super(alto, ancho, largo, pesoTotal, idContainer, fechaDeIngreso, fechaDeRetiro);
		// TODO Auto-generated constructor stub
	}

	public void addBl(BillOfLading bl) {
		bls.add(bl);	
	}
	
	public List<BillOfLading> getBls() {
        return List.copyOf(bls);
    }

	public double getPesoTotalDeBLs() {
		double pesoHastaAhora = 0;
		for (BillOfLading bl: bls) {
			pesoHastaAhora += bl.peso;
		}
		return pesoHastaAhora;
	}
	
	public boolean esDesconsolidado() {
        return bls.size() <= 1;
    }
}
