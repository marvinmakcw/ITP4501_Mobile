package android.exercise.com.tic_tac_toe_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {
    //long startTime;

    TextView tvDuration, tvTitle;
    Timer timer;
    TimerTask timerTask;
    Double time = 0.0;
    int round, placePointer, duration;
    Boolean clickable = true, playerTurn = true, end = false;
    Random random;
    String status = "Draw";

    ImageButton[] imageButtons = new ImageButton[9];
    int[] place = new int[9];

    SQLiteDatabase db;
    int newID;

    private SoundPool soundPool;
    private int userClick, cpuClick, btnClick, endSound;

    LottieAnimationView lottieLightSaber, background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        ImageButton btnContinue = (ImageButton) findViewById(R.id.btnContinue);
        btnContinue.setVisibility(View.INVISIBLE);
        ImageButton btnHome = (ImageButton) findViewById(R.id.btnHome);
        btnHome.setVisibility(View.INVISIBLE);
        tvDuration = (TextView) findViewById(R.id.tvDuration);
        tvTitle = (TextView) findViewById(R.id.tvT);

        lottieLightSaber = findViewById(R.id.lightSaber);
        lottieLightSaber.setVisibility(View.INVISIBLE);
        background = findViewById(R.id.background);
        //lottieLightSaber.setMinAndMaxProgress(0.2f, 1.0f);
        //lottieLightSaber.playAnimation();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setUsage(AudioAttributes.USAGE_GAME).build();

            soundPool = new SoundPool.Builder().setMaxStreams(2).setAudioAttributes(audioAttributes).build();
        } else {
            soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        }
        userClick = soundPool.load(this, R.raw.user_click, 1);
        cpuClick = soundPool.load(this, R.raw.cpu_click, 1);
        btnClick = soundPool.load(this, R.raw.button_pressed, 1);
        endSound = soundPool.load(this, R.raw.end_sound, 1);

        timer = new Timer();
        startTimer();

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(btnClick, 1, 1, 1, 0, 1);
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                soundPool.release();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(btnClick, 1, 1, 1, 0, 1);
                soundPool.release();
                android.os.Process.killProcess(android.os.Process.myPid());
                startActivity(new Intent(PlayActivity.this, MainActivity.class));
            }
        });

        for (int i = 0; i < imageButtons.length; i++) {
            place[i] = 0; // 0 = no one click, 1 = player click, 2 =  CPU click
            int resID = getResources().getIdentifier("btn" + i, "id", this.getPackageName());
            imageButtons[i] = (ImageButton) findViewById(resID);
            imageButtons[i].setOnClickListener(this);
        }
        round = 0;
    }

    private void startTimer() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time++;
                        tvDuration.setText("Duration: " + getTimerText());
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    private String getTimerText() {
        int rounded = (int) Math.round(time);
        return String.valueOf(rounded);
    }

    @Override
    public void onClick(View view) {
        String btnID = view.getResources().getResourceEntryName(view.getId()); //btn2
        placePointer = Integer.parseInt(btnID.substring(btnID.length() - 1, btnID.length()));
        if (place[placePointer] == 0 && end != true) {
            if (clickable && round <= 9) {
                soundPool.play(userClick, 1, 1, 1, 0, 1);
                ((ImageButton) view).setImageResource(R.drawable.circle);

                place[placePointer] = 1; // player click
                round++;
                Log.i("round", "" + round);
            } else {
                return;
            }
            clickable = false;
            if (checkEnd()) {
                Log.i("status", "" + status);
                endGame();
            } else {
                playerTurn = false;
                cpuTurn();
                Log.i("status", "" + status);
                round++;
            }
        }
    }

    private void endGame() {
        //soundPool.play(endSound, 1, 1, 1, 0, 1);
        timerTask.cancel();
        duration = Integer.parseInt(getTimerText());
        Log.i("duration", "" + duration);
        ImageButton btnContinue = (ImageButton) findViewById(R.id.btnContinue);
        btnContinue.setVisibility(View.VISIBLE);
        btnContinue.setImageResource(R.drawable.reset);

        if (status == "Win") {
            tvTitle.setText("You Win!!");
        }else if (status == "Lose") {
            tvTitle.setText("You Lose!!");
        }else {
            tvTitle.setText("Draw!!");
        }


        ImageButton btnHome = (ImageButton) findViewById(R.id.btnHome);
        btnHome.setVisibility(View.VISIBLE);
        btnHome.setImageResource(R.drawable.home);
        saveRecord();
    }

    private void saveRecord() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-M-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        String currentTime = new SimpleDateFormat("HH:mm a", Locale.getDefault()).format(new Date());

        Log.i("ID", "" + newID);
        Log.i("Date", "" + formattedDate);
        Log.i("Time", "" + currentTime);
        Log.i("Status", "" + status);
        Log.i("Duration", duration + " sec");
        try {
            db = SQLiteDatabase.openDatabase("/data/data/android.exercise.com.tic_tac_toe_assignment/RecordsDB", null, SQLiteDatabase.CREATE_IF_NECESSARY);
            db.execSQL("INSERT INTO GamesLog(gameID, playDate, playTime, duration, winningStatus) values"
                    + "(NULL, '" + formattedDate + "', '" + currentTime + "', '" + duration + "', '" + status + "'); ");
        } catch (SQLiteException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private boolean checkEnd() {
        if (place[0] != 0 && place[0] == place[3] && place[3] == place[6]) {
            lottieLightSaber.setScaleX(5f);
            lottieLightSaber.setScaleY(4.5f);
            lottieLightSaber.setVisibility(View.VISIBLE);
            lottieLightSaber.playAnimation();
            soundPool.play(endSound, 1, 1, 1, 0, 1);
            end = true;
            if (playerTurn) {
                status = "Win";
            } else {
                status = "Lose";
            }
        }
        else if (place[1] != 0 && place[1] == place[4] && place[4] == place[7]) {
            lottieLightSaber.setX(360);
            lottieLightSaber.setScaleX(5f);
            lottieLightSaber.setScaleY(4.5f);
            lottieLightSaber.setVisibility(View.VISIBLE);
            lottieLightSaber.playAnimation();
            soundPool.play(endSound, 1, 1, 1, 0, 1);
            end = true;
            if (playerTurn) {
                status = "Win";
            } else {
                status = "Lose";
            }
        }
        else if (place[2] != 0 && place[2] == place[5] && place[5] == place[8]) {
            lottieLightSaber.setX(700);
            lottieLightSaber.setScaleX(5f);
            lottieLightSaber.setScaleY(4.5f);
            lottieLightSaber.setVisibility(View.VISIBLE);
            lottieLightSaber.playAnimation();
            soundPool.play(endSound, 1, 1, 1, 0, 1);
            end = true;
            if (playerTurn) {
                status = "Win";
            } else {
                status = "Lose";
            }
        }

        else if (place[0] != 0 && place[0] == place[1] && place[1] == place[2]) {
            lottieLightSaber.setX(440);
            lottieLightSaber.setY(-400);
            lottieLightSaber.setRotation(90);
            lottieLightSaber.setScaleX(5f);
            lottieLightSaber.setScaleY(4.5f);
            lottieLightSaber.setVisibility(View.VISIBLE);
            lottieLightSaber.playAnimation();
            soundPool.play(endSound, 1, 1, 1, 0, 1);
            end = true;
            if (playerTurn) {
                status = "Win";
            } else {
                status = "Lose";
            }
        }
        else if (place[3] != 0 && place[3] == place[4] && place[4] == place[5]) {
            lottieLightSaber.setX(440);
            lottieLightSaber.setY(-50);
            lottieLightSaber.setRotation(90);
            lottieLightSaber.setScaleX(5f);
            lottieLightSaber.setScaleY(4.5f);
            lottieLightSaber.setVisibility(View.VISIBLE);
            lottieLightSaber.playAnimation();
            soundPool.play(endSound, 1, 1, 1, 0, 1);
            end = true;
            if (playerTurn) {
                status = "Win";
            } else {
                status = "Lose";
            }
        }
        else if (place[6] != 0 && place[6] == place[7] && place[7] == place[8]) {
            lottieLightSaber.setX(440);
            lottieLightSaber.setY(320);
            lottieLightSaber.setRotation(90);
            lottieLightSaber.setScaleX(5f);
            lottieLightSaber.setScaleY(4.5f);
            lottieLightSaber.setVisibility(View.VISIBLE);
            lottieLightSaber.playAnimation();
            soundPool.play(endSound, 1, 1, 1, 0, 1);
            end = true;
            if (playerTurn) {
                status = "Win";
            } else {
                status = "Lose";
            }
        }

        else if (place[2] != 0 && place[2] == place[4] && place[4] == place[6]) {
            lottieLightSaber.setX(440);
            lottieLightSaber.setY(-120);
            lottieLightSaber.setRotation(45);
            lottieLightSaber.setScaleX(5f);
            lottieLightSaber.setScaleY(6f);
            lottieLightSaber.setVisibility(View.VISIBLE);
            lottieLightSaber.playAnimation();
            soundPool.play(endSound, 1, 1, 1, 0, 1);
            end = true;
            if (playerTurn) {
                status = "Win";
            } else {
                status = "Lose";
            }
        }
        else if (place[0] != 0 && place[0] == place[4] && place[4] == place[8]) {
            lottieLightSaber.setX(330);
            lottieLightSaber.setRotation(-45);
            lottieLightSaber.setScaleX(5f);
            lottieLightSaber.setScaleY(6f);
            lottieLightSaber.setVisibility(View.VISIBLE);
            lottieLightSaber.playAnimation();
            soundPool.play(endSound, 1, 1, 1, 0, 1);
            end = true;
            if (playerTurn) {
                status = "Win";
            } else {
                status = "Lose";
            }
        }else if (round == 9) {
            end = true;
        }
        return end;
    }

    private void cpuTurn() {
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                clickable = false;
                soundPool.play(cpuClick, 1, 1, 1, 0, 1);
                random = new Random();
                int newPlacePointer = random.nextInt(8);

                while (place[newPlacePointer] != 0) {
                    newPlacePointer++;
                    if (newPlacePointer > 8) {
                        newPlacePointer = 0;
                    }
                }
                place[newPlacePointer] = 2;// CPU click
                Log.i("board", "" + place[0] + place[1] + place[2]);
                Log.i("board", "" + place[3] + place[4] + place[5]);
                Log.i("board", "" + place[6] + place[7] + place[8]);
                int cpuResID = getResources().getIdentifier("btn" + newPlacePointer, "id", getPackageName());
                ImageButton cpuBtn = (ImageButton) findViewById(cpuResID);
                cpuBtn.setImageResource(R.drawable.cross);
                if (checkEnd()) {
                    Log.i("status", "" + status);
                    endGame();
                } else {
                    playerTurn = true;
                    clickable = true;
                }
            }
        }, 500);

    }

    class Panel extends View {
        public Panel(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas c) {
            super.onDraw(c);

            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(Color.LTGRAY);
            paint.setStrokeWidth(3);
            c.drawPaint(paint);
            paint.setColor(Color.BLUE);
            c.drawRect(20, 30, 300, 200, paint);
            c.drawLine(0, 0, getWidth() - 1, getHeight() - 1, paint);
        }
    }
}