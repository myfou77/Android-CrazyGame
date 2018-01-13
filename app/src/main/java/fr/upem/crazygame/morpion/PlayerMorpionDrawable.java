package fr.upem.crazygame.morpion;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;


/**
 * This class draws a cross if it's the player 1 who
 * has to play, else it's a circle
 */
public class PlayerMorpionDrawable extends Drawable {

    private final Paint paint;
    private final float widthBox; // Width of a box
    private final float heightBox; // Height of a box
    private final float startXBoard; // x coordinate of the start board
    private final float startYBoard; // y coordinate of the start board
    private final float stopXBoard; // x coordinate of the stop board
    private final float stopYBoard; // y coordinate of the stop board
    private final float eventX; // x clic of the user
    private final float eventY; // y clic of the user
    private Players player;

    public PlayerMorpionDrawable(float widthBox, float heightBox, float startXBoard, float startYBoard, float stopXBoard, float stopYBoard, float eventX, float eventY, Players player) {
        this.widthBox = widthBox;
        this.heightBox = heightBox;
        this.startXBoard = startXBoard;
        this.startYBoard = startYBoard;
        this.stopXBoard = startXBoard;
        this.stopYBoard = startYBoard;
        this.eventX = eventX;
        this.eventY = eventY;
        this.player = player;
        this.paint = new Paint();
    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    @Override
    public void setAlpha(int alpha) {}

    @Override
    public void setColorFilter(ColorFilter cf) {}
}
