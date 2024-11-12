import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

class ClienteGUI {
    private Pedido pedido;
    private JFrame frame;
    private JTextField statusField;
    private JTextField newStatusField;
    private JLabel resultLabel;

    public ClienteGUI() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            pedido = (Pedido) registry.lookup("Pedido");
            createGUI();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void createGUI() {
        frame = new JFrame("Cliente de Pedido");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel statusLabel = new JLabel("Status atual:");
        statusLabel.setBounds(10, 20, 80, 25);
        panel.add(statusLabel);

        statusField = new JTextField(20);
        statusField.setBounds(100, 20, 165, 25);
        panel.add(statusField);

        JButton consultButton = new JButton("Consultar Status");
        consultButton.setBounds(10, 50, 150, 25);
        panel.add(consultButton);
        consultButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultarStatus();
            }
        });

        JLabel newStatusLabel = new JLabel("Novo Status:");
        newStatusLabel.setBounds(10, 80, 80, 25);
        panel.add(newStatusLabel);

        newStatusField = new JTextField(20);
        newStatusField.setBounds(100, 80, 165, 25);
        panel.add(newStatusField);

        JButton updateButton = new JButton("Atualizar Status");
        updateButton.setBounds(10, 110, 150, 25);
        panel.add(updateButton);
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                atualizarStatus();
            }
        });

        resultLabel = new JLabel("");
        resultLabel.setBounds(10, 140, 300, 25);
        panel.add(resultLabel);
    }

    private void consultarStatus() {
        try {
            String status = pedido.consultar_status();
            statusField.setText(status);
            resultLabel.setText("Status consultado com sucesso.");
        } catch (Exception e) {
            resultLabel.setText("Erro ao consultar status: " + e.getMessage());
        }
    }

    private void atualizarStatus() {
        try {
            String novoStatus = newStatusField.getText();
            pedido.atualizar_status(novoStatus);
            consultarStatus(); // Atualiza o campo status com o novo valor
            resultLabel.setText("Status atualizado para: " + novoStatus);
        } catch (Exception e) {
            resultLabel.setText("Erro ao atualizar status: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new ClienteGUI();
    }
}
