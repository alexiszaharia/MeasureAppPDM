package com.example.stud.measureapp;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by stud on 3/28/2018.
 */

public class WidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MasuratoareWidgetFactory(getApplicationContext());
    }
}
