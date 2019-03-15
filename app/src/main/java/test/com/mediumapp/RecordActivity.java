package test.com.mediumapp;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecordActivity extends AppCompatActivity {

    @BindView(R.id.btnplay)
    Button btnplay;
    @BindView(R.id.btnstop)
    Button btnstop;
    @BindView(R.id.btnrecord)
    Button btnrecord;
    @BindView(R.id.stplay)
    Button stplay;

    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;
    String outputFile = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        ButterKnife.bind(this);



        //atur tombol saat pertama masuk tombol yg bisa di klik adalah yang record saja
        btnplay.setEnabled(false);
        btnstop.setEnabled(false);
        stplay.setEnabled(false);


        //tempat penympanan
        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/record.3gp";

        //memasukan komponen ke dalam mediarecorder
        mediaRecorder = new MediaRecorder();


        //memasukan komponen media player
        mediaPlayer = new MediaPlayer();

    }

    @OnClick({R.id.btnplay, R.id.btnstop, R.id.btnrecord, R.id.stplay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnplay:
                try {
                    mediaPlayer.prepare();
                }catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    mediaPlayer.start();
                }catch (IllegalStateException e){
                    e.printStackTrace();
                }

                btnrecord.setEnabled(false);
                btnstop.setEnabled(false);
                stplay.setEnabled(false);
                btnplay.setEnabled(true);

                Toast.makeText(this,"Playying....",Toast.LENGTH_LONG).show();
                break;
            case R.id.btnstop:
                mediaRecorder.stop();
                //Todo berguna supaya audio tersimpan saat button stop di klik
                mediaRecorder.release();
                mediaRecorder = null;
                mediaRecorder = new MediaRecorder();

                btnplay.setEnabled(true);
                btnstop.setEnabled(true);
                btnrecord.setEnabled(true);
                stplay.setEnabled(false);


                Toast.makeText(this,"Stop recording ",Toast.LENGTH_LONG).show();



                break;
            case R.id.btnrecord:
                //sumber suara
                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                //format file
                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                //encoder file record
                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                //Memasukan file e dlm direktory
                mediaRecorder.setOutputFile(outputFile);
                mediaPlayer = new MediaPlayer();


                try {
                    mediaRecorder.prepare();
                }catch (IOException e ){
                    e.printStackTrace();
                }catch (Exception e){

                }

                mediaRecorder.start();
                break;
            case R.id.stplay:
                //jika masih memainkan suara
                try {
                    mediaPlayer.stop();
                }catch (IllegalStateException e) {
                    e.printStackTrace();
                }

                btnstop.setEnabled(false);
                stplay.setEnabled(false);
                btnrecord.setEnabled(true);
                btnplay.setEnabled(true);

                Toast.makeText(this,"Stop playing",Toast.LENGTH_LONG).show();
                break;
        }
    }
}
