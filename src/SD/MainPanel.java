package SD;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {

    private MapPanel mapPanel;

    private JLabel labelWidth;
    private JLabel labelHeight;
    private JLabel labelHouses;
    private JTextField textFieldWidth;
    private JTextField textFieldHeight;
    private JTextField textFieldHouses;

    private JSlider sliderWidth;
    private JSlider sliderHeight;
    private JSlider sliderHouses;

    private JButton buttonA;
    private JButton buttonB;
    private JButton buttonMap;
    private JButton buttonMan;
    private JButton buttonWay;

    {
        setSize(609, 678);
        setBackground(Color.LIGHT_GRAY);
        setLayout(null);
        mapPanel = new MapPanel();
        add(mapPanel).setBounds(0, 0, 500, 500);

        labelWidth = new JLabel("width");
        add(labelWidth).setBounds(10, 510, 50, 23);
        labelHeight = new JLabel("height");
        add(labelHeight).setBounds(10, 569, 50, 23);
        labelHouses = new JLabel("houses");
        add(labelHouses).setBounds(10, 622, 50, 23);

        textFieldWidth = new JTextField("10");
        add(textFieldWidth).setBounds(10, 533, 50, 23);
        textFieldHeight = new JTextField("10");
        add(textFieldHeight).setBounds(10, 592, 50, 23);
        textFieldHouses = new JTextField("30");
        add(textFieldHouses).setBounds(10, 645, 50, 23);

        sliderWidth = createSlider(2, 50, 10, 1, 12);
        add(sliderWidth).setBounds(70, 510, 420, 46);
        sliderHeight = createSlider(2, 50, 10, 1, 12);
        add(sliderHeight).setBounds(70, 569, 420, 46);
        sliderHouses = createSlider(2, 50, 30, 1, 156);
        add(sliderHouses).setBounds(70, 622, 420, 46);

        buttonA = new JButton(new ImageIcon("res/iconBtnA.png"));
        add(buttonA).setBounds(510, 10, 40, 40);
        buttonB = new JButton(new ImageIcon("res/iconBtnB.png"));
        add(buttonB).setBounds(560, 10, 40, 40);
        buttonMap = new JButton(new ImageIcon("res/iconBtnMap.png"));
        add(buttonMap).setBounds(510, 60, 90, 90);
        buttonMan = new JButton(new ImageIcon("res/iconBtnMan.png"));
        add(buttonMan).setBounds(510, 160, 90, 90);
        buttonWay = new JButton(new ImageIcon("res/iconBtnWay.png"));
        add(buttonWay).setBounds(510, 260, 90, 90);

        addListeners();
    }

    public MainPanel() {
    }

    private JSlider createSlider(int min, int max, int value, int minor, int major) {
        JSlider slider = new JSlider(min, max, value);
        slider.setMinorTickSpacing(minor);
        slider.setMajorTickSpacing(major);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        return slider;
    }

    private void coValues(JTextField textField, JSlider slider) {
        textField.addActionListener(e -> slider.setValue(Integer.parseInt(textField.getText())));
        slider.addChangeListener(e -> {
            textField.setText(String.valueOf(slider.getValue()));
            int s = sliderHeight.getValue() * sliderWidth.getValue();
            sliderHouses.setMaximum(s / 2);
            sliderHouses.setMinorTickSpacing(s / 49);
            sliderHouses.setMajorTickSpacing(s / 7);
        });
    }

    private void addListeners() {
        coValues(textFieldWidth, sliderWidth);
        coValues(textFieldHeight, sliderHeight);
        coValues(textFieldHouses, sliderHouses);
    }

    private int maxHomes() {

        return 0;
    }
}
