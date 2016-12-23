package com.mwaqaspervez.chefsburger;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener
            = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
            if (result.isFailure()) {
                Log.d("InTabPurchaseFinished", "Error purchasing: " + result);
            } else if (purchase.getSku().equals("burger_max")) {
                Log.i("InTabPurchaseFinished", "Burger Bought");
            }
        }
    };
    private FloatingActionButton fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inAppPayment();
        fb = (FloatingActionButton) findViewById(R.id.main_fab);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Checkout.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
            }
        });

        findViewById(R.id.tv_starters).setOnClickListener(this);
        findViewById(R.id.tv_chicken).setOnClickListener(this);
        findViewById(R.id.tv_beef).setOnClickListener(this);
        findViewById(R.id.tv_beverage).setOnClickListener(this);
        findViewById(R.id.tv_dessert).setOnClickListener(this);
        findViewById(R.id.tv_icecream).setOnClickListener(this);

        if (getSharedPreferences("basket", MODE_PRIVATE).getString("item", null) == null)
            fb.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(view.getContext(), ItemsDetails.class);
        switch (view.getId()) {
            case R.id.tv_starters:
                intent.putExtra("selected", "starters");
                break;
            case R.id.tv_chicken:
                intent.putExtra("selected", "chicken");
                break;
            case R.id.tv_beef:
                intent.putExtra("selected", "beef");
                break;
            case R.id.tv_beverage:
                intent.putExtra("selected", "beverage");
                break;
            case R.id.tv_icecream:
                intent.putExtra("selected", "icecream");
                break;
            case R.id.tv_dessert:
                intent.putExtra("selected", "dessert");
                break;
        }

        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_left);
    }

    @Override
    protected void onResume() {

        if (getSharedPreferences("basket", MODE_PRIVATE).getString("item", null) == null) {
            if (fb != null)
                fb.setVisibility(View.GONE);

        } else {
            if (fb != null)
                fb.setVisibility(View.VISIBLE);
        }
        super.onResume();
    }

    void inAppPayment() {

        final IabHelper mHelper;

        // ...
        final String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApna/po50Gf0zxqGpyaWFZizFPAwLd+oAapy+xfDGjHM+OTQghFK0f0/wl6cs2KV8g0PnXulIH1DqUoYCGBIT5uN/1/d/aC/SItkugXPLh3MnwcHPSFoR0vGR4MvDE6vNlaTT2r/Jp7b2zqNbeZfi5vAHuvaGAQwW25ztN5biu3UvJI/MOrPYnm2TVEgoACzvZV1AtTwmSqq1B2fyDbrDptnlGHwkaValZVEHNGEsFMthm4bcycreaDdXp3i0q1ycp9ZumYkaIZFKGQ3ITyjNtNkzAS7VQb+7DaDVXmKuFTibVsvwnOCaoKTvzjbNGrlwxX8/JblTnSP21vfL1sWwtQIDAQAB";

        // compute your public key and store it in base64EncodedPublicKey
        mHelper = new IabHelper(this, base64EncodedPublicKey);

        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                if (!result.isSuccess()) {
                    // Oh no, there was a problem.
                    Log.i("InAppProblem", "Problem setting up In-app Billing: " + result);
                }
                Log.i("InAppSuccess", "Successfully connected" + result.getResponse());
                try {
                    mHelper.launchPurchaseFlow(MainActivity.this, "burger_max", 10001,
                            mPurchaseFinishedListener, base64EncodedPublicKey);
                } catch (IabHelper.IabAsyncInProgressException e) {
                    e.printStackTrace();
                }
            }
        });


    }


}
