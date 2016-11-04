package com.example.dllo.yuliaoapp.ui.activity.game.game_snake;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.example.dllo.yuliaoapp.R;

/**
 * Created by dllo on 16/11/4.
 */
public class TileView extends View{
    /**
     * Parameters controlling the size of the tiles and their range within view.
     * Width/Height are in pixels, and Drawables will be scaled to fit to these
     * dimensions. X/Y Tile Counts are the number of tiles that will be drawn.
     */

    protected static int mTileSize; // µÿÕºtileµƒ¥Û–°°£∆‰ µæÕ «µ„µƒøÌ∫Õ∏ﬂ£® «“ª—˘µƒ÷µ£©

    protected static int mXTileCount;// µÿÕº…œx÷·ƒ‹πª»›ƒ…µƒtileµƒ ˝¡ø°£œ¬√Ê¿‡À∆
    protected static int mYTileCount;

    private static int mXOffset;// µÿÕºµƒ∆ º◊¯±Í
    private static int mYOffset;

    /**
     * A hash that maps integer handles specified by the subclasser to the
     * drawable that will be used for that reference
     */
    private Bitmap[] mTileArray;
    // µÿÕº…œtile∂‘”¶µƒÕº∆¨ ˝◊È°£√ø“ª÷÷tile∂º∂‘”¶“ª∏ˆbitmap°£±»»ÁmTileArray[1]æÕ «≤›µÿµƒbitmap°£ø…“‘¿‡Õ∆°£

    /**
     * A two-dimensional array of integers in which the number represents the
     * index of the tile that should be drawn at that locations
     */
    private int[][] mTileGrid;
    // µÿÕº…œµƒtileµƒ ˝◊È°£±»»Áint[1][1]=0Àµ√˜’‚∏ˆµ„ «≤›µÿ°£int[1][2]=1Àµ√˜’‚∏ˆµ„ «∆ªπ˚
    // ∆‰ µÀºœÎæÕ «’‚√¥ºÚµ•°£∑Ω Ωø…“‘”–∏˜÷÷∏˜—˘

    private final Paint mPaint = new Paint();// ª≠± °£ª≠Õº–Ë“™± ¿¥ª≠°£”¶∏√∫‹»›“◊¿ÌΩ‚°£∏˜÷÷± °£∫⁄…´°£∫Ï…´°£

    public TileView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // ’‚¿Ô”√µΩµƒTypeArray°£≤ª∂ÆµƒÕØ–¨“™»•googleœ¬°£ «google≈™≥ˆ¿¥µƒ“ª÷÷—˘ Ω ˝◊È£¨∆‰ µæÕœÒ∂®“Â“ª∏ˆøÿº˛µƒ Ù–‘µƒºØ∫œ°£
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TileView);

        mTileSize = a.getInt(R.styleable.TileView_tileSize, 12);

        a.recycle();
    }

    public TileView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TileView);

        mTileSize = a.getInt(R.styleable.TileView_tileSize, 12);

        a.recycle();
    }

    /**
     * Rests the internal array of Bitmaps used for drawing tiles, and sets the
     * maximum index of tiles to be inserted
     *
     * @param tilecount
     */

    public void resetTiles(int tilecount) {
        mTileArray = new Bitmap[tilecount];
    }

    // ∏ˆ»À»œŒ™°£’‚∏ˆ∫Ø ˝ «±»Ωœ”–“‚Àºµƒ°£’‚∏ˆ «viewµƒ“ª∏ˆªÿµ˜∫Ø ˝°£◊Óø™ º≥ı ºª∞µƒ ±∫Úviewµƒ¥Û–°∂º «0°£µ±Ω¯––layout÷Æ∫Û°£√ø∏ˆview∂º»∑∂®¡À¥Û–°°£’‚—˘æÕø™ ºªÿµ˜’‚∏ˆ∫Ø ˝°£
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mXTileCount = (int) Math.floor(w / mTileSize);
        mYTileCount = (int) Math.floor(h / mTileSize);

        mXOffset = ((w - (mTileSize * mXTileCount)) / 2);
        mYOffset = ((h - (mTileSize * mYTileCount)) / 2);

        mTileGrid = new int[mXTileCount][mYTileCount];
        clearTiles();
    }

    /**
     * Function to set the specified Drawable as the tile for a particular
     * integer key.
     *
     * @param key
     * @param tile
     *            ’‚∫Ø ˝æÕ «∏˘æ›Key¥˙±Ìtile÷÷¿‡°£¿¥º”‘ÿµÿÕºtileµƒÕº∆¨£®’‚¿Ô «“ª∏ˆdrawable°£“™±‰≥…bitmap£©
     */
    public void loadTile(int key, Drawable tile) {
        Bitmap bitmap = Bitmap.createBitmap(mTileSize, mTileSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        tile.setBounds(0, 0, mTileSize, mTileSize);
        tile.draw(canvas);

        mTileArray[key] = bitmap;
    }

    /**
     * Resets all tiles to 0 (empty)
     *
     */
    public void clearTiles() {
        for (int x = 0; x < mXTileCount; x++) {
            for (int y = 0; y < mYTileCount; y++) {
                setTile(0, x, y);
            }
        }
    }

    /**
     * Used to indicate that a particular tile (set with loadTile and referenced
     * by an integer) should be drawn at the given x/y coordinates during the
     * next invalidate/draw cycle.
     *
     * @param tileindex
     * @param x
     * @param y
     *  ’‚±ﬂæÕ «…Ë÷√√ø“ª∏ˆtile£®µÿÕº…œµƒµ„£©∂‘”¶µƒ «ƒƒ“ª÷÷Õº∆¨°£’‚¿ÔµƒtileindexæÕ «¥˙±Ì¡ÀmTileArray[]÷–µƒindex
     */
    public void setTile(int tileindex, int x, int y) {
        mTileGrid[x][y] = tileindex;
    }

    // ’‚∏ˆ∫Ø ˝æÕ «ª≠≥ˆµÿÕº¡À°£±È¿˙µÿÕºµƒµ„£¨»ª∫Û∞—√ø∏ˆtileµƒ◊¯±Í∂ºº∆À„≥ˆ¿¥£¨»ª∫Û“ª∏ˆ∏ˆµƒtile∂ºdrawµΩcanvas…œπ˛
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int x = 0; x < mXTileCount; x += 1) {
            for (int y = 0; y < mYTileCount; y += 1) {
                if (mTileGrid[x][y] > 0) {
                    canvas.drawBitmap(mTileArray[mTileGrid[x][y]], mXOffset + x * mTileSize, mYOffset + y * mTileSize, mPaint);
                }
            }
        }

    }
}
