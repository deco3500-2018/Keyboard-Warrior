package com.kw.kw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.kw.kw.activity.GuideActivity;
import com.kw.kw.activity.MainActivity;
import com.kw.kw.utils.CacheUtils;

import static com.kw.kw.R.id.rl_splahs_root;

public class SplashActivity extends Activity {

    //静态常量
    public static final String START_MAIN = "start_main";
    private RelativeLayout rl_splahs_root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rl_splahs_root = findViewById(R.id.rl_splahs_root);

        //渐变动画
        AlphaAnimation aa = new AlphaAnimation(0,1);
        //aa.setDuration(500);
        aa.setFillAfter(true);


       // 缩放
       // ScaleAnimation sa = new ScaleAnimation(0,1,0,1, ScaleAnimation.RELATIVE_TO_SELF,0.5f,ScaleAnimation.RELATIVE_TO_SELF,0.5f);
        //sa.setDuration(500);
        //sa.setFillAfter(true);

        AnimationSet set = new AnimationSet(false);
        //set.addAnimation(sa);
        set.addAnimation(aa);
        set.setDuration(1000);

        rl_splahs_root.startAnimation(set);

        set.setAnimationListener(new myAnimationListener());
    }

    class myAnimationListener implements Animation.AnimationListener{

        public static final String START_MAIN = "start_main";

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {

            //判断是否进入主页面
            boolean isStartMain = CacheUtils.getBoolean(SplashActivity.this, START_MAIN);
            Intent intent;
            if(isStartMain){
                 intent = new Intent(SplashActivity.this,MainActivity.class);

            }else{
                 intent = new Intent(SplashActivity.this, GuideActivity.class);

            }
            startActivity(intent);
//            Toast.makeText(SplashActivity.this, "Finished", Toast.LENGTH_SHORT);
            finish();


        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }


    }
}
