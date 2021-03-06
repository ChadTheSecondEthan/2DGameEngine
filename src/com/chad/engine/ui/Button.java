package com.chad.engine.ui;

import com.chad.engine.utils.Mouse;

import java.awt.*;

@SuppressWarnings("unused")
public class Button extends UIElement {

    public Button() { this(true); }

    public Button(boolean useDefaultDrawable) {
        super();

        if (useDefaultDrawable)
            drawable = new ColorChanger(Color.lightGray);
    }

    @Override
    public void update(float dt) {
        super.update(dt);

        if (drawable instanceof ColorChanger) {
            if (mouseWithinBounds())
                ((ColorChanger) drawable).setColor(Mouse.pressed() ? Color.darkGray : Color.gray);
            else
                ((ColorChanger) drawable).setColor(Color.lightGray);
        }
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (!visible && drawable instanceof ColorChanger)
            ((ColorChanger) drawable).setCurrentColor(Color.lightGray);
    }

}
