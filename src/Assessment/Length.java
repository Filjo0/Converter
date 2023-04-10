package Assessment;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

class Length extends JPanel {
    private boolean lookForChange = true;
    private JTextField[] tf = new JTextField[8];

    Length() {
        this.setSize(450, 500);
        DocumentListener dl = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                if (Length.this.lookForChange) {
                    Length.this.convert(e);
                }
            }

            public void removeUpdate(DocumentEvent e) {
            }

            public void changedUpdate(DocumentEvent e) {
                if (Length.this.lookForChange) {
                    Length.this.convert(e);
                }
            }
        };
        //adding units of Length
        String[] str = new String[]{"Meter", "Kilometer", "Centimeter", "Millimeter", "Mile", "Yard", "Foot", "Inch"};
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = 2;
        gc.insets = new Insets(2, 5, 2, 5);

        for (int i = 0; i < 8; ++i) {
            JLabel[] labels = new JLabel[8];
            labels[i] = new JLabel(str[i]);
            labels[i].setPreferredSize(new Dimension(185, 20));
            this.tf[i] = new JTextField(20);
            this.tf[i].getDocument().putProperty("owner", this.tf[i]);
            this.tf[i].setPreferredSize(new Dimension(135, 20));
            gc.gridwidth = 1;
            gc.gridx = 0;
            gc.gridy = i;
            this.add(labels[i], gc);
            gc.gridx = 1;
            gc.gridwidth = 2;
            this.add(this.tf[i], gc);
            this.tf[i].getDocument().addDocumentListener(dl);
        }

    }

    //algorithm of converting units of length
    private void convert(DocumentEvent e) {
        double toMetres = 0.0D;

        try {
            JTextField t = (JTextField) e.getDocument().getProperty("owner");
            if (t.getText().equals("")) {
                throw new Exception();
            }

            double input = Double.parseDouble(t.getText());

            int i;
            for (i = 0; i < 8 && !t.equals(this.tf[i]); ++i) {
            }

            switch (i) {
                case 0 -> toMetres = input;
                case 1 -> toMetres = input * 1000.0D;
                case 2 -> toMetres = input / 100.0D;
                case 3 -> toMetres = input / 1000.0D;
                case 4 -> toMetres = input * 1609.34D;
                case 5 -> toMetres = input * 0.9144D;
                case 6 -> toMetres = input * 0.3048D;
                case 7 -> toMetres = input * 0.0254D;
            }

            double[] valueLength = new double[17];
            this.lookForChange = false;
            valueLength[0] = toMetres;
            valueLength[1] = toMetres / 1000.0D;
            valueLength[2] = toMetres * 100.0D;
            valueLength[3] = toMetres * 1000.0D;
            valueLength[4] = toMetres / 1609.34D;
            valueLength[5] = toMetres / 0.9144D;
            valueLength[6] = toMetres / 0.3048D;
            valueLength[7] = toMetres / 0.0254D;

            for (int j = 0; j < 8; ++j) {
                if (j != i) {
                    this.tf[j].setText(Double.toString(valueLength[j]));
                }
            }

            this.lookForChange = true;
        } catch (Exception var10) {
            var10.printStackTrace();
        }
    }
}
