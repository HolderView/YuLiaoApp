package com.example.dllo.yuliaoapp.ui.activity.game;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;

import com.example.dllo.yuliaoapp.R;
import com.example.dllo.yuliaoapp.ui.activity.C_AbsBaseActivity;
import com.example.dllo.yuliaoapp.ui.activity.game.game_2048.Game2048Layout;

/**
 * Created by dllo on 16/11/3.
 */
public class C_2048_Activity extends C_AbsBaseActivity implements Game2048Layout.OnGame2048Listener{
    private Game2048Layout mGame2048Layout;

    private TextView mScore;

    @Override
    protected void initData(Bundle savedInstanceState) {
        mGame2048Layout.setOnGame2048Listener(this);
    }

    @Override
    protected int setLayout() {
        return R.layout.c_activity_2048;
    }

    @Override
    protected void initViews() {
        mScore = (TextView) findViewById(R.id.id_score);
        mGame2048Layout = (Game2048Layout) findViewById(R.id.id_game2048);

    }



    @Override
    public void onScoreChange(int score) {
        mScore.setText("SCORE: " + score);
    }

    @Override
    public void onGameOver() {
        new AlertDialog.Builder(this).setTitle("GAME OVER")
                .setMessage("YOU HAVE GOT " + mScore.getText())
                .setPositiveButton("RESTART", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        mGame2048Layout.restart();
                    }
                }).setNegativeButton("EXIT", new DialogInterface.OnClickListener()
        {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                finish();
            }
        }).show();
    }
}
