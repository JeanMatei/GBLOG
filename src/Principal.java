import controller.Controller;
import controller.EntregaController;
import controller.FilialController;
import controller.VeiculoController;
import model.Entrega;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        EntregaController entregaController = new EntregaController();
        VeiculoController veiculoController = new VeiculoController();
        FilialController filialController = new FilialController();

        Controller controller = new Controller(filialController, entregaController, veiculoController);
        controller.selecionarFuncionalidade();





    }
}
