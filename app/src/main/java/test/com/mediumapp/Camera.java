package test.com.mediumapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Camera extends AppCompatActivity {

    @BindView(R.id.imgfoto)
    ImageView imgfoto;
    //Todo atribut dari kamera yang berfungsi ntuk pengunaan diroktori kamera
    String mCurrentPhotopath;

    //variabel penyimpan nomor requst
    private static final int ambil_foto_request_code = 100;
    public static final int type_foto_code = 1;


    //nama folder penyimpanana
    private static final String folder_foto = "kamerabuatan";

    //komponen pengambil foto dari diroktori
    private Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //pengecekan kamera pada perangkat
        if (!supportCamera()) {
            Snackbar.make(getWindow().getDecorView().getRootView(),"Tidak support kamera",Snackbar.LENGTH_LONG)
                    .setAction("Action",null).show();
        }

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               fab.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       //ambil foto
                       ambilfoto();
                   }


               });
               getSupportActionBar().setDisplayHomeAsUpEnabled(true);


            }


        });
    }

    private boolean supportCamera() {

        //ini belum dikasih statemnt
    }



    private void ambilfoto() {
        //tampilkan aplikasi yang memeiliki fitur foto
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //mengambil diroktori penyimpanana foto dari method pengaturan folder
        fileUri = ambilOutputMediaFileUri(type_foto_code);

            //mengambil alamat iroktori file
            // return Uri.fromFile(ambilOutputMediaFile(type_foto_code));

        return FileProvider.getUriForFile(Camera.this,
                BuildConfig.APPLICATION_ID + ".provider",
                ambilOutputMediaFileUri(type_foto_code));

        }



    private Uri ambilOutputMediaFileUri(int type_foto_code) {
        File penyimpinanMediaDir = new File(
                Environment.getExternalStorageDirectory(Environment.DIRECTORY_PICTURES)
                , folder_foto
        );
        Log.d("Diroctory file ku",penyimpinanMediaDir.getAbsolutePath());


        //cek keberadaan file
        if(penyimpinanMediaDir.exists()) {
            //cek jika membuat folder baru
            if (penyimpinanMediaDir.mkdir()) {
                Toast.makeText(this, "Gagal memebuat folder " +
                        folder_foto, Toast.LENGTH_LONG).show();
                return null;




            }



        }

        String waktu = new SimpleDateFormat("yyyyMMdd_hhMss"
                , Locale.getDefault()).format(new Date());
        Log.d("Waktu Pengambilan ",waktu);

        //variabel penampung nama
        File mediaFile;
        //pasang nama foto dengan waktu
        if (type_foto_code == type_foto_code) {
            mediaFile = new File(penyimpinanMediaDir.getPath() + File.separator
            +"IMG" + waktu + ".jpg");
            Log.d("nama File ", mediaFile.getAbsolutePath());
        }else {
            return null;
        }
        mCurrentPhotopath = "file:" + mediaFile.getAbsolutePath();
        Log.d("mCurrentPhotoPath:", mCurrentPhotopath);
        return mediaFile;


    }

    private boolean supportCamera() {

    }

}
