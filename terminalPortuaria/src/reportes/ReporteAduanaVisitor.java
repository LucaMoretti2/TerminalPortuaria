package reportes;

import containers.Container;
import empresaMaritima.Buque;
//Solo indica nombre del buque , fecha de arribo y partida, y lista de
//contenedores (indicando tipo y ID). Este documento deberá ser en HTML.

public class ReporteAduanaVisitor implements ReporteVisitor {

     @Override
        public void visit(Buque buque) {
            System.out.println("<html><body>");
            System.out.println("Buque: " + buque.getNombre());
            System.out.println("<ul>");

            for (Container c : buque.getCargas()) {
                System.out.println("<li>" + c.getTipo() + " - " + c.getIDContainer() + "</li>");
            }

            System.out.println("</ul>");
            System.out.println("</body></html>");
        }


    @Override
    public void visit(Container container) {
        System.out.println("<li>" + container.getTipo() + " - " + container.getIDContainer() + "</li>");
    }
}