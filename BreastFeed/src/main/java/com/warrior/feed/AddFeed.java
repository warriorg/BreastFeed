package com.warrior.feed;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.warrior.db.FeedDb;

import java.util.Calendar;

public class AddFeed extends ActionBarActivity {
    EditText quantity;
    DatePicker datePicker;
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feed);

        setContentView(R.layout.activity_add_feed);

        quantity = (EditText)findViewById(R.id.quantity);
        datePicker = (DatePicker)findViewById(R.id.datePicker);
        timePicker = (TimePicker)findViewById(R.id.timePicker);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu subMenu = menu.addSubMenu("保存");
        MenuItem menuItem = subMenu.getItem();
        menuItem.setTitle("保存");
        menuItem.setIcon(android.R.drawable.ic_menu_add);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                FeedDb db = new FeedDb(AddFeed.this);
                Calendar calendar = Calendar.getInstance();
                calendar.set(datePicker.getYear(),datePicker.getMonth(),datePicker.getMonth(),timePicker.getCurrentHour(),timePicker.getCurrentMinute());
                db.insert(calendar.getTime(), Float.valueOf(quantity.getText().toString()));

                setResult(Activity.RESULT_OK);
                finish();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

}
