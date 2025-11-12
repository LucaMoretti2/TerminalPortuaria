package reportes;

import containers.Container;
import empresaMaritima.Buque;

public class ReporteMuelleVisitor implements ReporteVisitor {

    @Override
    public void visit(Buque buque) {
        System.out.println("=== Reporte Muelle ===");
        System.out.println("Buque: " + buque.getNombre());
        System.out.println("Cantidad de contenedores: " + buque.getCargas().size());
        System.out.println("=======================");
    }

    @Override
    public void visit(Container container) {
    }
}
