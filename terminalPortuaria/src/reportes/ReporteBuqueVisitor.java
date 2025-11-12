package reportes;

import containers.Container;
import empresaMaritima.Buque;

public class ReporteBuqueVisitor implements ReporteVisitor {

    @Override
    public void visit(Buque buque) {
        System.out.println("<report>");
        System.out.println("<import>");
        for (Container c : buque.getCargas()) {
            System.out.println("<item>" + c.getIDContainer() + "</item>");
        }
        System.out.println("</import>");
        System.out.println("<export>");
        System.out.println("</export>");
        System.out.println("</report>");
    }

    @Override
    public void visit(Container container) {
  
    }
}
