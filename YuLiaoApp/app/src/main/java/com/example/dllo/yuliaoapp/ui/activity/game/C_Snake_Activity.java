package com.example.dllo.yuliaoapp.ui.activity.game;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.dllo.yuliaoapp.R;
import com.example.dllo.yuliaoapp.ui.activity.C_AbsBaseActivity;
import com.example.dllo.yuliaoapp.ui.activity.game.game_snake.SnakeView;

/**
 * Created by dllo on 16/11/3.
 */
public class C_Snake_Activity extends C_AbsBaseActivity {

    private SnakeView mSnakeView;

    private Button mStart;

    private static String ICICLE_KEY = "snake-view";

    @Override
    protected void initData(Bundle savedInstanceState) {
        // ≈–∂œœ¬ ˝æ› «∑Ò”–±£¥Ê£¨√ª”–µƒª∞£¨æÕ÷ÿ–¬ø™ º”Œœ∑
        if (savedInstanceState == null) {
            // We were just launched -- set up a new game
            mSnakeView.setMode(SnakeView.READY);
        } else {
            // We are being restored
            Bundle map = savedInstanceState.getBundle(ICICLE_KEY);
            if (map != null) {
                mSnakeView.restoreState(map);
            } else {
                mSnakeView.setMode(SnakeView.PAUSE);
            }
        }
    }

    /**
     * Called when Activity is first created. Turns off the title bar, sets up
     * the content views, and fires up the SnakeView.
     */
    @Override
    protected int setLayout() {
        return R.layout.c_activity_snake;

    }

    @Override
    protected void initViews() {
        mSnakeView = (SnakeView) findViewById(R.id.snake);

        mSnakeView.setTextView((TextView) findViewById(R.id.text));
        mSnakeView.setStartButton((Button) findViewById(R.id.start));
        mSnakeView.setControlButton((Button) findViewById(R.id.left), (Button) findViewById(R.id.right),
                (Button) findViewById(R.id.top), (Button) findViewById(R.id.bottom));


    }


    @Override
    protected void onPause() {
        super.onPause();
        // Pause the game along with the activity
        mSnakeView.setMode(SnakeView.PAUSE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Store the game state
        outState.putBundle(ICICLE_KEY, mSnakeView.saveState());
    }
}
