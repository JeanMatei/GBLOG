import controller.Controller;
import controller.EntregaController;
import controller.FilialController;
import controller.VeiculoController;
import dao.FilialDAO;
import model.Entrega;
import service.EntregaService;
import service.FilialService;
import service.VeiculoService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws Exception {

        FilialDAO filialDAO = new FilialDAO();

        EntregaService entregaService = new EntregaService();
        VeiculoService veiculoService = new VeiculoService();
        FilialService filialService = new FilialService(filialDAO);


        EntregaController entregaController = new EntregaController(entregaService);
        VeiculoController veiculoController = new VeiculoController(veiculoService);
        FilialController filialController = new FilialController(filialService);

        Controller controller = new Controller(filialController, entregaController, veiculoController);
        controller.selecionarFuncionalidade();





    }
}
