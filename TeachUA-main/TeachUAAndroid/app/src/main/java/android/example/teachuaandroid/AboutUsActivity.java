package android.example.teachuaandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);


        TextView textView4 = findViewById(R.id.textView4);
        ImageView imageView = findViewById(R.id.imageView2);
        TextView  textView5 = findViewById(R.id.textView5);


        Intent intent = getIntent();
        if(intent != null){
            textView4.setText(intent.getStringExtra("textInfoAboutGroups"));
            textView5.setText(intent.getStringExtra("description"));

            int resourceName = intent.getIntExtra("imageResources", 0);
            imageView.setImageResource(resourceName);
        }
    }
}