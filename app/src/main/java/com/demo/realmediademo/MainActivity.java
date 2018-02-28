package com.demo.realmediademo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import com.realmedianow.Placement;
import com.realmedianow.PlacementRequestConfig;
import com.realmedianow.PlacementResponse;
import com.realmedianow.PlacementResponseListener;
import com.realmedianow.RealMediaNow;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void requestPlacement(View view) {
        String[] keywords = {"sample2"};
        Set<String> keywordSet = new HashSet<>();
        Collections.addAll(keywordSet, keywords);
        PlacementRequestConfig config = new PlacementRequestConfig.Builder(153105, 214764, 300, 250)
                .setKeywords(keywordSet)
                .build();
        RealMediaNow realmedianow = new RealMediaNow();
        realmedianow.requestPlacement(config, new PlacementResponseListener() {
            @Override
            public void success(PlacementResponse response) {
                System.out.println(response.getStatus());
                for (Placement placement : response.getPlacements()) {
                    System.out.println(placement.getBannerId());
                }

                if (response.getPlacements().size() > 0) {
                    final Placement placement = response.getPlacements().get(0);
                    if (placement != null) {
                        ImageView imageView = (ImageView) findViewById(R.id.imageButton);

                        imageView.setVisibility(View.VISIBLE);
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse(placement.getRedirectUrl()));
                                startActivity(intent);
                            }
                        });

                        Picasso.with(getBaseContext())
                                .load(placement.getImageUrl())
                                .resize(placement.getWidth(), placement.getHeight())
                                .into(imageView);

                        placement.recordImpression();
                    }
                }
            }

            @Override
            public void error(Throwable throwable) {

            }
        });
    }

    public void requestPlacements(View view) {
        PlacementRequestConfig config1 = new PlacementRequestConfig.Builder(153105, 214764, 300, 250).build();

        String[] keywords = {"sample2"};
        Set<String> keywordSet = new HashSet<>();
        Collections.addAll(keywordSet, keywords);
        PlacementRequestConfig config2 = new PlacementRequestConfig.Builder(153105, 214764, 300, 250)
                .setKeywords(keywordSet)
                .build();

        List<PlacementRequestConfig> configs = new ArrayList<>();
        configs.add(config1);
        configs.add(config2);

        RealMediaNow realmedianow = new RealMediaNow();
        realmedianow.requestPlacements(configs, new PlacementResponseListener() {
            @Override
            public void success(PlacementResponse response) {
                System.out.println(response.getStatus());
                for (Placement placement : response.getPlacements()) {
                    System.out.println(placement.getBannerId());
                }
            }

            @Override
            public void error(Throwable throwable) {

            }
        });
    }

    public void requestPixel(View view) {
        RealMediaNow realmedianow = new RealMediaNow();
        realmedianow.requestPixel("https://ads.realmedianow.com/default_banner.gif");
    }

    public void recordImpression(View view) {
        String[] keywords = {"sample2"};
        Set<String> keywordSet = new HashSet<>();
        Collections.addAll(keywordSet, keywords);
        PlacementRequestConfig config = new PlacementRequestConfig.Builder(153105, 214764, 300, 250)
                .setKeywords(keywordSet)
                .build();

        RealMediaNow realmedianow = new RealMediaNow();
        realmedianow.requestPlacement(config, new PlacementResponseListener() {
            @Override
            public void success(PlacementResponse response) {
                System.out.println(response.getStatus());
                for (Placement placement : response.getPlacements()) {
                    placement.recordImpression();
                }
            }

            @Override
            public void error(Throwable throwable) {

            }
        });
    }

    public void recordClick(View view) {
        String[] keywords = {"sample2"};
        Set<String> keywordSet = new HashSet<>();
        Collections.addAll(keywordSet, keywords);
        PlacementRequestConfig config = new PlacementRequestConfig.Builder(153105, 214764, 300, 250)
                .setKeywords(keywordSet)
                .build();

        RealMediaNow realmedianow = new RealMediaNow();
        realmedianow.requestPlacement(config, new PlacementResponseListener() {
            @Override
            public void success(PlacementResponse response) {
                System.out.println(response.getStatus());
                for (Placement placement : response.getPlacements()) {
                    placement.recordClick();
                }
            }

            @Override
            public void error(Throwable throwable) {

            }
        });
    }
}
