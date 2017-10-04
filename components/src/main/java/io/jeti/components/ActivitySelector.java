package io.jeti.components;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import io.jeti.layoutparams.MatchZero1;
import java.util.List;
import java.util.Map;

/**
 * This {@link Activity} is a simple UI for selecting another {@link Activity}
 * to start. To define the {@link Activity}s which are selectable, you must
 * override the {@link #getActivityClasses()} method and return a {@link List}
 * of {@link Item}s, where the {@link Item} names are strings that will be
 * displayed to the user, and the {@link Item} classes are the {@link Activity}
 * classes to start.
 *
 * <p/>
 *
 * Note that you can also modify the look of the UI by overriding the
 * {@link #getTextNormalColor()}, {@link #getTextPressedColor()},
 * {@link #getBackgroundNormalColor()}, {@link #getBackgroundPressedColor()},
 * and {@link #getCornerRadius()} methods.
 */
abstract public class ActivitySelector extends Activity {

    public static class Item {

        private final String                    name;
        private final Class<? extends Activity> clazz;

        public Item(String name, Class<? extends Activity> clazz) {
            this.name = name;
            this.clazz = clazz;
        }

        public String getName() {
            return name;
        }

        public Class<? extends Activity> getClazz() {
            return clazz;
        }
    }

    /**
     * @return the {@link Class}es of the {@link Activity}s that this
     *         {@link Activity} can start, where the key of the {@link Map} is
     *         the String that will be displayed to the user for each
     *         {@link Activity} class.
     */
    abstract public List<Item> getActivityClasses();

    /**
     * @return the text color of the {@link Button}s when they are not pressed.
     *         Note that this should be a color, such as
     *         <code>Color.rgb(0, 100,200)</code>, not a resource ID.
     */
    public int getTextNormalColor() {
        return Color.rgb(0, 124, 253);
    }

    /**
     * @return the text color of the {@link Button}s while they are being
     *         pressed. Note that this should be a color, such as
     *         <code>Color.rgb(0, 100,200)</code>, not a resource ID.
     */
    public int getTextPressedColor() {
        return Color.WHITE;
    }

    /**
     * @return the background color of the {@link Button}s when they are not
     *         pressed. Note that this should be a color, such as
     *         <code>Color.rgb(0, 100,200)</code>, not a resource ID.
     */
    public int getBackgroundNormalColor() {
        return Color.BLACK;
    }

    /**
     * @return the background color of the {@link Button}s while they are being
     *         pressed. Note that this should be a color, such as
     *         <code>Color.rgb(0, 100,200)</code>, not a resource ID.
     */
    public int getBackgroundPressedColor() {
        return Color.BLACK;
    }

    /**
     * @return the corner radius of each of the buttons. A value of 0 means
     *         sharp corners.
     */
    public int getCornerRadius() {
        return 0;
    }

    /*
     * ---------------------------------------------
     *
     * {@link Activity} Methods
     *
     * ---------------------------------------------
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /* Default creation code */
        super.onCreate(savedInstanceState);

        /* Set up the layout which will hold the buttons. */
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        /* Create a button for each Activity */
        List<Item> classes = getActivityClasses();
        for (final Item item : classes) {
            final Button button = new GlowButton(this, getTextNormalColor(), getTextPressedColor(),
                    getBackgroundNormalColor(), getBackgroundPressedColor(), getCornerRadius());
            button.setText(item.getName());
            button.setMaxLines(1);
            button.post(new Runnable() {

                @Override
                public void run() {
                    TextViewResizer.resize(button);
                }
            });
            button.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    startActivity(new Intent(ActivitySelector.this, item.getClazz()));
                }
            });
            linearLayout.addView(button, new MatchZero1());
        }

        /* Set this Activity's layout */
        setContentView(linearLayout);
    }
}
