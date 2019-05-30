package nmdl.express.ecomm;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;


public class SplashActivity extends AppCompatActivity
{
    CircleImageView image;
    TextView name;
    ImageView sign;
    private static SharedPrefManager mInstance;
    private static Context mCtx;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Intent intent = new Intent(this,log.class);
        final Thread thread = new Thread(){
            public void run(){
                try
                {
                    sleep(2000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    if (SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn())
                    {
                        startActivity(new Intent(getApplicationContext(), Main.class));
                    }
                    else
                    {
                        startActivity(intent);
                    }
                }
            }
        };
        thread.start();
    }
}