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

class Time extends JPanel {
    private boolean lookForChange = true;
    private JTextField[] tf = new JTextField[10];

    Time() {
        this.setSize(450, 500);
        DocumentListener dl = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                if (Time.this.lookForChange) {
                    Time.this.convert(e);
                }

            }

            public void removeUpdate(DocumentEvent e) {
            }

            public void changedUpdate(DocumentEvent e) {
                if (Time.this.lookForChange) {
                    Time.this.convert(e);
                }

            }
        };

        //adding different units of time
        String[] str = new String[]{"Millisecond", "Second", "Minute", "Hour", "Day", "Week", "Month", "Year", "Decade", "Century"};
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = 2;
        gc.insets = new Insets(2, 5, 2, 5);
        gc.anchor = 11;

        for (int i = 0; i < 10; ++i) {
            JLabel[] labels = new JLabel[10];
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

    //algorithm of converting
    private void convert(DocumentEvent e) {
        double toSec = 0.0D;

        try {
            JTextField t = (JTextField) e.getDocument().getProperty("owner");
            if (t.getText().equals("")) {
                throw new Exception();
            }

            double input = Double.parseDouble(t.getText());

            int i;
            for (i = 0; i < 10 && !t.equals(this.tf[i]); ++i) {
            }

            switch (i) {
                case 0:
                    toSec = input * Math.pow(10.0D, -3.0D);
                    break;
                case 1:
                    toSec = input;
                    break;
                case 2:
                    toSec = input * 60.0D;
                    break;
                case 3:
                    toSec = input * 3600.0D;
                    break;
                case 4:
                    toSec = input * 86400.0D;
                    break;
                case 5:
                    toSec = input * 604800.0D;
                    break;
                case 6:
                    toSec = input * 2.63D * Math.pow(10.0D, 6.0D);
                    break;
                case 7:
                    toSec = input * 3.156D * Math.pow(10.0D, 7.0D);
                    break;
                case 8:
                    toSec = input * 3.156D * Math.pow(10.0D, 8.0D);
                    break;
                case 9:
                    toSec = input * 3.156D * Math.pow(10.0D, 9.0D);
            }

            double[] valueSec = new double[10];
            this.lookForChange = false;
            valueSec[0] = toSec / Math.pow(10.0D, -3.0D);
            valueSec[1] = toSec;
            valueSec[2] = toSec / 60.0D;
            valueSec[3] = toSec / 3600.0D;
            valueSec[4] = toSec / 86400.0D;
            valueSec[5] = toSec / 604800.0D;
            valueSec[6] = toSec / (2.63D * Math.pow(10.0D, 6.0D));
            valueSec[7] = toSec / (3.156D * Math.pow(10.0D, 7.0D));
            valueSec[8] = toSec / (3.156D * Math.pow(10.0D, 8.0D));
            valueSec[9] = toSec / (3.156D * Math.pow(10.0D, 9.0D));

            for (int j = 0; j < 10; ++j) {
                if (j != i) {
                    this.tf[j].setText(Double.toString(valueSec[j]));
                }
            }

            this.lookForChange = true;
        } catch (Exception var10) {
            var10.printStackTrace();
        }

    }
}
