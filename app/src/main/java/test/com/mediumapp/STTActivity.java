package test.com.mediumapp;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import javax.xml.transform.Result;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class STTActivity extends AppCompatActivity {

    @BindView(R.id.imgSpeak)
    ImageView imgSpeak;
    @BindView(R.id.txtsText)
    EditText txtsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stt);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.imgSpeak)
    public void onViewClicked() {
        //memanggil method dialog specch google
        prosesSuara();
        txtsText.setText("");

    }

    private void prosesSuara() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL
        ,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, new Locale  ("in", "INA"));
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "mulai bicara");


        try{
            startActivityForResult(intent,0);

        }catch (Exception e) {
            Toast.makeText(this, "Google speech tdk tersedia",Toast.LENGTH_LONG).show();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                //jika dialog google tampil proses data
                if (resultCode == RESULT_OK && null != data) {
                    //ambil data suara yang masuk dari dialog google
                    ArrayList<String> hasilSuara = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //tampilkan textview
                    txtsText.setText(hasilSuara.get(0));

                }

                break;
        }
    }
}
