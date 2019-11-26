package Assessment;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

class Temperature extends JPanel {
    private boolean lookForChange = true;
    private JTextField[] tf = new JTextField[7];

    Temperature() {
        this.setSize(450, 500);
        DocumentListener dl = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                if (Temperature.this.lookForChange) {
                    Temperature.this.convert(e);
                }

            }

            public void removeUpdate(DocumentEvent e) {
            }

            public void changedUpdate(DocumentEvent e) {
                if (Temperature.this.lookForChange) {
                    Temperature.this.convert(e);
                }

            }
        };

        //adding different units of temperature
        String[] str = new String[]{"Celsius", "Fahrenheit", "Kalvin"};
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = 2;
        gc.insets = new Insets(2, 5, 2, 5);
        gc.anchor = 11;

        for(int i = 0; i < 3; ++i) {
            JLabel[] labels = new JLabel[7];
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

    //algorithm of converting temperature
    private void convert(DocumentEvent e) {
        double toCels = 0.0D;

        try {
            JTextField t = (JTextField)e.getDocument().getProperty("owner");
            if (t.getText().equals("")) {
                throw new Exception();
            }

            double input = Double.parseDouble(t.getText());

            int i;
            for(i = 0; i < 3 && !t.equals(this.tf[i]); ++i) {
            }

            switch(i) {
                case 0:
                    toCels = input;
                    break;
                case 1:
                    toCels = input - 273.15D;
                    break;
                case 2:
                    toCels = (input - 32.0D) * 5.0D / 9.0D;
                    break;
            }

            double[] valueTemp = new double[3];
            this.lookForChange = false;
            valueTemp[0] = toCels;
            valueTemp[1] = toCels + 273.15D;
            valueTemp[2] = toCels * 9.0D / 5.0D + 32.0D;

            for(int j = 0; j < 3; ++j) {
                if (j != i) {
                    this.tf[j].setText(Double.toString(valueTemp[j]));
                }
            }

            this.lookForChange = true;
        } catch (Exception var10) {
            var10.printStackTrace();
        }

    }
}
