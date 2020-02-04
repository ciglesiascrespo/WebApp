package c.iglesias.testerweb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class SettingActivity extends AppCompatActivity {

    Button btnGuardar;
    EditText edtUrl;
    private AdView mAdViewHor, mAdViewVertDer, mAdViewVertIz;
    private InterstitialAd mInterstitialAd;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initAdMob();
        btnGuardar = findViewById(R.id.idBtnGuardar);
        edtUrl = findViewById(R.id.idTxtUrl);

        sharedPreferences = this.getSharedPreferences("url", MODE_PRIVATE);
        edtUrl.setText(sharedPreferences.getString("url", "https://www.google.es/"));

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtUrl.getText().toString().isEmpty()) {
                    Toast.makeText(getParent(), "Guarde una URL", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("url", edtUrl.getText().toString());
                    editor.commit();
                }
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                finish();

            }
        });
    }

    private void initAdMob() {


        mAdViewHor = findViewById(R.id.adViewHorizontal);
        AdRequest adRequestHor = new AdRequest.Builder().build();
        mAdViewHor.loadAd(adRequestHor);

        mAdViewVertDer = findViewById(R.id.adViewVerticalDer);
        AdRequest adRequestVertDer = new AdRequest.Builder().build();
        mAdViewVertDer.loadAd(adRequestVertDer);

        mAdViewVertIz = findViewById(R.id.adViewVerticalIz);
        AdRequest adRequestVertIz = new AdRequest.Builder().build();
        mAdViewVertIz.loadAd(adRequestVertIz);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-9724478137720633/5993623514");

        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }
}
