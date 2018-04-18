package webb.todd.cannongame;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.ImageReader;

public class Cannonball extends GameElement {

    private float velocityX;
	private boolean onScreen;

	private Bitmap ballImage;
	private Matrix matrix = new Matrix();

	// constructor
	public Cannonball (CannonView view, int color, int soundId, int x,
    	int y, int radius, float velocityX, float velocityY ) {
    	super(view, color, soundId, x, y, 2 * radius, 2 * radius, velocityY);
        this.velocityX = velocityX;
        onScreen = true;
        ballImage = view.getBitmap( "SoccerBall.png" );
	}

	private int getRadius() {
        return (shape.right - shape.left) / 2;
    }

	// test whether Cannonball collides with the given GameElement
    public boolean collidesWith(GameElement element) {
        return (Rect.intersects(shape, element.shape) && velocityX > 0);
    }

 	// returns true if this Cannonball is on the screen
    public boolean isOnScreen() {
       return onScreen;
	}

    // reverses the Cannonball's horizontal velocity
    public void reverseVelocityX(){
	    velocityX *= -1;
    }

    @Override
	public void update(double interval) {
        super.update(interval); // updates Cannonball's vertical position

        // update horizontal position
        shape.offset((int) (velocityX * interval), 0);
        // if Cannonball goes off the screen
        if (shape.top < 0 || shape.left < 0 ||
                	shape.bottom > view.getScreenHeight() ||
                	shape.right > view.getScreenWidth()) {
            onScreen = false; // set it to be removed
        }
    }

    @Override
    public void draw(Canvas canvas) {
	    if( velocityX < 0 ) {
            canvas.drawBitmap( ballImage, shape.right - ballImage.getWidth(),
                    shape.top -(ballImage.getHeight() - shape.height())/2, null);
        }
        else {
            canvas.drawCircle(shape.left + getRadius(),
                    shape.top + getRadius(), getRadius(), paint);
        }
	}

}
