package com.example.stud.measureapp;

import android.content.Context;
import android.database.Cursor;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by stud on 3/28/2018.
 */

public class MasuratoareWidgetFactory implements RemoteViewsService.RemoteViewsFactory {
    private List<Masuratoare> listaMasuratori;
    private Context context;

    public MasuratoareWidgetFactory(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        //preluare date BD

        Cursor cursor = context.getContentResolver().query(ContentProviderMasuratori.CONTENT_URI, null, null, null, null);

        listaMasuratori = new ArrayList<>();

        while (cursor.moveToNext()) {
            Masuratoare masuratoare = new Masuratoare();
            masuratoare.setDenumire(cursor.getString(1));
            masuratoare.setData(cursor.getString(2));
            masuratoare.setId(cursor.getInt(1));

            Punct punct1 = new Punct(40, 45);
            Punct punct2 = new Punct(50, 60);
            Punct punct3 = new Punct(60, 75);
            ArrayList<Punct> listaPuncte = new ArrayList<>();
            listaPuncte.add(punct1);
            listaPuncte.add(punct2);
            listaPuncte.add(punct3);
            //Masuratoare masuratoare = new Masuratoare("mas1", listaPuncte, new Date().toString(), 1);
            masuratoare.setListaPuncte(listaPuncte);

            listaMasuratori.add(masuratoare);
        }
        cursor.close();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return listaMasuratori.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.element_lista_widget);
        rv.setTextViewText(R.id.tv_denumire, listaMasuratori.get(position).getDenumire());
        rv.setTextViewText(R.id.tv_data, listaMasuratori.get(position).getData());
        rv.setTextViewText(R.id.tv_suprafata, listaMasuratori.get(position).getSuprafata() + "mp");

        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
