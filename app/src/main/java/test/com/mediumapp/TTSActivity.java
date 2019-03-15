package test.com.mediumapp;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TTSActivity extends AppCompatActivity  implements TextToSpeech.OnInitListener {


    @BindView(R.id.txtText)
    EditText txtText;
    @BindView(R.id.btnSpeech)
    Button btnSpeech;

    //kita membuat atribut
    private android.speech.tts.TextToSpeech textToSpeech;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tts);
        ButterKnife.bind(this);

        //masukan komponen tts
        textToSpeech = new android.speech.tts.TextToSpeech(this, this);

    }



    @Override
    public void onInit(int i) {
        //jika mengakses komponen ttts
        if (i == TextToSpeech.SUCCESS) {
            //atur bahasa ke indonesia
            Locale indo = new Locale("in", "ina");
            //variabel ntuk memasukan bahasa ke dalam tts
            int result = textToSpeech.setLanguage(indo);


            //jika bahasa tdk tersedia di perangkat atau perangkat tdk mendukung
            //kenapa menggunakan TextToSpeech karena kelas bisa meminta izin ke perangkat
            //sedangkan kalu objek tdk bisa meminta izin ke perangkat
            //tanda || berarti atau
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(this, "Bahasa tdk ditemukan atau perangakat tdk mendukung bahasa", Toast.LENGTH_LONG).show();

                } else {
                //tampilkan tombol
                btnSpeech.setEnabled(true);
                //method mengambil data teks dari Edittext
                mulaiBicara();
            }
        }else{
            //jika komponen dk terjangkau
            Toast.makeText(this,"Inisialisasi gagal",Toast.LENGTH_LONG).show();

        }


    }

    @OnClick(R.id.btnSpeech)
    public void onViewClicked() {


        mulaiBicara();
    }

    private void mulaiBicara() {
        //variabel penampung teks
        String teks = txtText.getText().toString();
        //proses tts
        textToSpeech.speak(teks, TextToSpeech.QUEUE_FLUSH, null);
    }
}
