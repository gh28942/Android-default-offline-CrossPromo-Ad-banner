package your.package.name.here;

import android.content.Intent;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * created by http://bit.ly/GerhGithub
 */

public class YourActivity extends AppCompatActivity {

  String ad_url = "http://bit.ly/fitClock";
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.yourlayout);
  }

  //TODO: you should call loadBanner() in your 'onBannerLoadFailed' method
  public void loadBanner(){

        //Default Banner
        final ImageView imageViewBanner = findViewById(R.id.imageViewBanner);
        final ImageView imageViewBannerExit = findViewById(R.id.imageViewBannerExit);
        final ConstraintLayout default_ad_banner_layout = findViewById(R.id.default_ad_banner_layout);
        final Animation animationFadeOut = AnimationUtils.loadAnimation(YourActivity.this, R.anim.fadeout);

        //Use larger banner on Tablets
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float yInches= metrics.heightPixels/metrics.ydpi;
        float xInches= metrics.widthPixels/metrics.xdpi;
        double diagonalInches = Math.sqrt(xInches*xInches + yInches*yInches);
        if (diagonalInches>=7)
            imageViewBanner.setImageResource(R.mipmap.default_ad_banner_large);

        //Click to go to url
        imageViewBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uriUrl = Uri.parse(ad_url);
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });

        //Click X to remove the banner
        imageViewBannerExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationFadeOut.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }
                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        default_ad_banner_layout.setVisibility(View.GONE);
                    }
                });
                default_ad_banner_layout.startAnimation(animationFadeOut);
            }
        });

        //Introduce banner ad by sliding it in from the right
        final Animation animationIntro = AnimationUtils.loadAnimation(YourActivity.this, R.anim.intro_toleft);
        animationIntro.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }
        });
        default_ad_banner_layout.bringToFront();
        default_ad_banner_layout.setVisibility(View.VISIBLE);
        default_ad_banner_layout.startAnimation(animationIntro);
    }
}
