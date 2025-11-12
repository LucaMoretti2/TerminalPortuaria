package reportes;

import containers.Container;
import empresaMaritima.Buque;

public class ReporteAduanaVisitor implements ReporteVisitor {

    @Override
    public void visit(Buque buque) {
        System.out.println("<html><body>");
        System.out.println("<h1>Reporte Aduana</h1>");
        System.out.println("<p>Buque: " + buque.getNombre() + "</p>");
        System.out.println("<ul>");
        for (Container c : buque.getCargas()) {
            c.accept(this);
        }
        System.out.println("</ul>");
        System.out.println("</body></html>");
    }

    @Override
    public void visit(Container container) {
        System.out.println("<li>" + container.getTipo() + " - " + container.getIDContainer() + "</li>");
    }
}
