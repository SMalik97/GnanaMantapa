package com.goldenfuturecommunication.gnanamantapa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class audioPlay extends AppCompatActivity {
TextView ct,cb;
Button playaudio,downloadaudio;
String pt,pb,pa;
    private boolean playPause;
    private MediaPlayer mediaPlayer;
    private ProgressDialog progressDialog;
    private boolean initialStage = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_play);
        ct=(TextView)findViewById(R.id.ct);
        cb=(TextView)findViewById(R.id.cb);
        playaudio=(Button) findViewById(R.id.playaudio);
        downloadaudio=(Button) findViewById(R.id.downloadaudio);


        //initialize media player
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        progressDialog = new ProgressDialog(audioPlay.this);


        pt=getIntent().getExtras().getString("title");
        pb=getIntent().getExtras().getString("content");
        pa=getIntent().getExtras().getString("link");

        ct.setText(pt);
        cb.setText(pb);

        playaudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!playPause) {
                       // audioStream.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);

                        if (initialStage) {
                            new Player().execute(pa);
                        } else {
                            if (!mediaPlayer.isPlaying())
                                mediaPlayer.start();
                        }

                        playPause = true;

                    } else {
                       // audioStream.setImageResource(R.drawable.ic_play_arrow_black_24dp);

                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.pause();
                        }

                        playPause = false;
                    }
            }
        });


    }

    class Player extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... strings) {
            Boolean prepared = false;

            try {
                mediaPlayer.setDataSource(strings[0]);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        initialStage = true;
                        playPause = false;
                        //show play icon
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });

                mediaPlayer.prepare();
                prepared = true;

            } catch (Exception e) {

                prepared = false;
            }

            return prepared;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

            mediaPlayer.start();
            initialStage = false;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Buffering...");
            progressDialog.show();
        }
    }
    @Override
    public void onPause() {
        super.onPause();

        if (mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

}
