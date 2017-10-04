package io.jeti.components;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

/**
 * This {@link Activity} function simply displays a {@link Base64} image on a
 * chosen background color. To change the image or background color, you can
 * override {@link #getBase64Image()} and {@link #getBackgroundColor()},
 * respectively. By default, the background is white, and the image is the Pcess
 * logo:
 *
 * <p/>
 *
 * <img src=
 * "data:image/png;base64,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 L3BjZXNzLnN2Z6SbFIkAAAAASUVORK5CYII="
 * />
 *
 * <p/>
 *
 * Note that the advantage of using base64 images is that we can hardcode them
 * and avoid the need for saving resources.
 */
abstract public class ImageActivity extends Activity {

    /**
     * ---------------------------------------------
     *
     * Private Fields
     *
     * ---------------------------------------------
     */
    private ImageView imageView = null;

    /**
     * ---------------------------------------------
     *
     * Getters
     *
     * ---------------------------------------------
     */
    /**
     * Get the {@link ImageView}. This is useful if you want ot change the
     * {@link Bitmap} directly.
     */
    public ImageView getImageView() {
        return imageView;
    }

    /** Get the background color. */
    public int getBackgroundColor() {
        return 0xFFFFFFFF;
    }

    /** Get the {@link Base64}-encoded version of the image. */
    public String getBase64Image() {
        return Base64Images.pcess768();
    }

    /**
     * ---------------------------------------------
     *
     * {@link Activity} Methods
     *
     * ---------------------------------------------
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /** Default creation code. */
        super.onCreate(savedInstanceState);

        /**
         * Create the layout that will hold the image. We use a relative layout
         * so that it is easy to center the image.
         */
        RelativeLayout mainLayout = new RelativeLayout(this);
        mainLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        /** Set the background color. */
        mainLayout.setBackgroundColor(getBackgroundColor());

        /**
         * Create an ImageView that will hold the logo and add it to the
         * mainLayout.
         */
        imageView = new ImageView(this);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageBitmap(Base64Images.toBitmap(getBase64Image()));
        mainLayout.addView(imageView);

        /** Set the mainLayout as the content view */
        setContentView(mainLayout);
    }
}