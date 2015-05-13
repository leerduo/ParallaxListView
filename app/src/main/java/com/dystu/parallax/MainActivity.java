package com.dystu.parallax;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {

    private ParallaxList  listView;

    private View header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ParallaxList ) findViewById(R.id.listView);


        listView.setOverScrollMode(AbsListView.OVER_SCROLL_NEVER);

        header = View.inflate(this,R.layout.layout_header,null);
        final ImageView parallaxView = (ImageView) header.findViewById(R.id.parallaxImageView);
        listView.addHeaderView(header);

        header.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                listView.setParallaxView(parallaxView);
                header.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        listView.setAdapter(new MyAdapter(this));
    }

}
