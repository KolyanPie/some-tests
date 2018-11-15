package SD;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class DigitFilter extends DocumentFilter {
    private static final String DIGITS = "\\d+";
    private JTextField textField;

    public DigitFilter(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {

        if (string.matches(DIGITS) && (textField.getText().length() + string.length() < 5)) {
            super.insertString(fb, offset, string, attr);
            System.out.println(1);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attrs) throws BadLocationException {
        int l = 0;
        if (textField.getSelectedText() != null) {
            l = textField.getSelectedText().length();
        }

        if (string.matches(DIGITS) && (textField.getText().length() + string.length() - l < 6)) {
            super.replace(fb, offset, length, string, attrs);
        }
    }


}
