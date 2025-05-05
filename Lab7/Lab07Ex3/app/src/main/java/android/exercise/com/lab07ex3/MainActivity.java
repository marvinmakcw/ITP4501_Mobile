package android.exercise.com.lab07ex3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView animImage;
    AnimationDrawable originalAnim, reverseAnim, randomAnim;
    Button btnOriginal, btnReverse, btnRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animImage = findViewById(R.id.vtc);
        btnOriginal = findViewById(R.id.btnOriginal);
        btnReverse = findViewById(R.id.btnReverse);
        btnRandom = findViewById(R.id.btnRandom);

        originalAnim = (AnimationDrawable) ResourcesCompat.getDrawable(getResources(),R.drawable.vtc, null);
        originalAnim.setOneShot(true); //setOneShot(boolean oneShot), T-no loop
        originalAnim.setVisible(true, true);//setVisible(bool visible, bool restart)

        reverseAnim = new AnimationDrawable();
        for (int i=originalAnim.getNumberOfFrames()-1; i>=0; i--)
            reverseAnim.addFrame(originalAnim.getFrame(i),
                    originalAnim.getDuration(i));
        reverseAnim.setOneShot(true); //setOneShot(boolean oneShot), T-no loop
        reverseAnim.setVisible(true, true);//setVisible(bool visible, bool restart)

        randomAnim = new AnimationDrawable();
        ArrayList<Integer> mylist = new ArrayList<Integer>();
        for (int i = originalAnim.getNumberOfFrames() - 1; i >= 0; i--) {
            mylist.add(i);
        }
        Collections.shuffle(mylist, new Random());
        for (int i = 0; i < mylist.size() - 1; i++) {
            randomAnim.addFrame(originalAnim.getFrame(mylist.get(i)), originalAnim.getDuration(mylist.get(i)));
        }
        randomAnim.setOneShot(true); //setOneShot(boolean oneShot), T-no loop
        randomAnim.setVisible(true, true);//setVisible(bool visible, bool restart)

        btnOriginal.setOnClickListener(this);
        btnReverse.setOnClickListener(this);
        btnRandom.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnOriginal) {
            animImage.setImageDrawable(originalAnim);
            originalAnim.start();
            originalAnim.setVisible(true, true);
        } else if (view == btnReverse) {
            animImage.setImageDrawable(reverseAnim);
            reverseAnim.start();
            reverseAnim.setVisible(true, true);
        } else if (view == btnRandom) {
            animImage.setImageDrawable(randomAnim);
            randomAnim.start();
            randomAnim.setVisible(true, true);        }
    }
}