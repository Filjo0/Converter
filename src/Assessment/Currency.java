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

class Currency extends JPanel {
    private boolean lookForChange = true;
    private JTextField[] tf = new JTextField[4];

    Currency() {
        this.setSize(450, 500);
        DocumentListener dl = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                if (Currency.this.lookForChange) {
                    Currency.this.convert(e);
                }

            }

            public void removeUpdate(DocumentEvent e) {
            }

            public void changedUpdate(DocumentEvent e) {
                if (Currency.this.lookForChange) {
                    Currency.this.convert(e);
                }

            }
        };
        // adding different units of currencies
        String[] str = new String[]{"USD", "AUD", "RUB", "EUR"};
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = 2;
        gc.insets = new Insets(2, 5, 2, 5);
        gc.anchor = 11;

        for (int i = 0; i < 4; ++i) {
            JLabel[] labels = new JLabel[4];
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

    //algorithm of converting currencies
    private void convert(DocumentEvent e) {
        double toCurr = 0.0D;

        try {
            JTextField t = (JTextField) e.getDocument().getProperty("owner");
            if (t.getText().equals("")) {
                throw new Exception();
            }

            double input = Double.parseDouble(t.getText());
            int i;
            for (i = 0; i < 4 && !t.equals(this.tf[i]); ++i) {
            }

            switch (i) {
                case 0:
                    toCurr = input;
                    break;
                case 1:
                    toCurr = input * 1.4806D;
                    break;
                case 2:
                    toCurr = input / 64.7713D;
                    break;
                case 3:
                    toCurr = input * 0.9159D;
                    break;
            }

            double[] valueCurr = new double[4];
            this.lookForChange = false;
            valueCurr[0] = toCurr;
            valueCurr[1] = toCurr / 1.4806D;
            valueCurr[2] = toCurr * 64.7713D;
            valueCurr[3] = toCurr / 0.9159D;

            for (int j = 0; j < 4; ++j) {
                if (j != i) {
                    this.tf[j].setText(Double.toString(valueCurr[j]));
                }
            }

            this.lookForChange = true;
        } catch (Exception var10) {
            var10.printStackTrace();
        }

    }
}
