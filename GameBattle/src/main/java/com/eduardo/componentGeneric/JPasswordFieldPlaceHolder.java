package com.eduardo.componentGeneric;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

public class JPasswordFieldPlaceHolder extends JPasswordField implements FocusListener {

    private String placeholder = "";

    public JPasswordFieldPlaceHolder() {
        placeholder = getPlaceholder();
        addFocusListener(this);
        setBorder(new EmptyBorder(10, 10, 10, 10));
        loadPropertiesComponent();
    }

    public void loadPropertiesComponent() {

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                setText(placeholder);
                timer.cancel();
            }
        };
        timer.schedule(task, 100);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (getText().equals(placeholder)) {
            setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (getText().equals("")) {
            setText(placeholder);
        }
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

}
