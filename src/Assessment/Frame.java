package Assessment;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;


public class Frame extends javax.swing.JFrame {

    private final JPanel conversion_panel;
    private final JComboBox<String> box;

    private Frame() {
        setSize(450, 560);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        conversion_panel = new JPanel();
        conversion_panel.setBorder(new EtchedBorder());
        JLabel jLabel1 = new JLabel("Select a Measured Unit");
        jLabel1.setFont(new java.awt.Font("Garamond", Font.BOLD, 12));
        box = new JComboBox<>(new String[]{"Length", "Lottery", "Weight", "Time", "Currency"});
        box.setSelectedIndex(0);
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(5, 0, 5, 0);
        gc.anchor = GridBagConstraints.ABOVE_BASELINE_TRAILING;
        gc.fill = GridBagConstraints.NONE;
        add(jLabel1, gc);
        gc.gridx = 1;
        add(box, gc);
        gc.gridx = 0;
        gc.gridy = 1;
        gc.gridwidth = 2;
        gc.gridheight = 9;
        gc.fill = GridBagConstraints.BOTH;
        add(conversion_panel, gc);
        conversion_panel.setLayout(new GridLayout(1, 1));
        conversion_panel.add(new Length());
        conversion_panel.setPreferredSize(new Dimension(408, 446));
        box.addActionListener(e -> {
            conversion_panel.removeAll();
            switch (box.getSelectedIndex()) {
                case 0 -> conversion_panel.add(new Length());
                case 1 -> conversion_panel.add(new Temperature());
                case 2 -> conversion_panel.add(new Weight());
                case 3 -> conversion_panel.add(new Time());
                case 4 -> conversion_panel.add(new Currency());
            }
            conversion_panel.revalidate();
            conversion_panel.repaint();
        });
    }

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {
        }

        java.awt.EventQueue.invokeLater(() -> new Frame().setVisible(true));
    }
}