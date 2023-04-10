package Assessment;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

class Weight extends JPanel {
    private boolean lookForChange = true;
    private JTextField[] tf = new JTextField[6];

    Weight() {
        this.setSize(450, 500);
        DocumentListener dl = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                if (Weight.this.lookForChange) {
                    Weight.this.convert(e);
                }
            }

            public void removeUpdate(DocumentEvent e) {
            }

            public void changedUpdate(DocumentEvent e) {
                if (Weight.this.lookForChange) {
                    Weight.this.convert(e);
                }
            }
        };

        //adding different units of weight
        String[] str = new String[]{"Metric ton", "Kilogram", "Gram", "Milligram", "Pound (lbs)", "Ounce"};
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = 2;
        gc.insets = new Insets(2, 5, 2, 5);
        gc.anchor = 11;

        for (int i = 0; i < 6; ++i) {
            JLabel[] labels = new JLabel[6];
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

    //algorithm of converting weight
    private void convert(DocumentEvent e) {
        double toKg = 0.0D;

        try {
            JTextField t = (JTextField) e.getDocument().getProperty("owner");
            if (t.getText().equals("")) {
                throw new Exception();
            }

            double input = Double.parseDouble(t.getText());

            int i;
            for (i = 0; i < 6 && !t.equals(this.tf[i]); ++i) {
            }

            switch (i) {
                case 0 -> toKg = input * 1000.0D;
                case 1 -> toKg = input;
                case 2 -> toKg = input * 0.001D;
                case 3 -> toKg = input * Math.pow(10.0D, -6.0D);
                case 4 -> toKg = input * 0.453592D;
                case 5 -> toKg = input * 0.0283495D;
            }

            double[] valueWeight = new double[6];
            this.lookForChange = false;
            valueWeight[0] = toKg / 1000.0D;
            valueWeight[1] = toKg;
            valueWeight[2] = toKg / 0.001D;
            valueWeight[3] = toKg * Math.pow(10.0D, 6.0D);
            valueWeight[4] = toKg / 0.453592D;
            valueWeight[5] = toKg / 0.0283495D;

            for (int j = 0; j < 6; ++j) {
                if (j != i) {
                    this.tf[j].setText(Double.toString(valueWeight[j]));
                }
            }

            this.lookForChange = true;
        } catch (Exception var10) {
            var10.printStackTrace();
        }
    }
}
