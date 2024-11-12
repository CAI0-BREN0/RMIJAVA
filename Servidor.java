import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {
    public static void main(String[] args) {
        try {
            Pedido pedido = new PedidoImpl("12345");
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("Pedido", pedido);
            System.out.println("Servidor pronto e aguardando conexões...");
        } catch (Exception e) {
            System.err.println("Erro no servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}