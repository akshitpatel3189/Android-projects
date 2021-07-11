package com.example.mho;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.allyants.notifyme.NotifyMe;

import java.util.Calendar;

public class NotificationActivity extends AppCompatActivity {

    TextView main,sub,pickdate;
    final Calendar now = Calendar.getInstance();
    TimePickerDialog tpd;
    NotifyMe notifyMe;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nofication);

        main = findViewById(R.id.main_title);
        sub = findViewById(R.id.sub_title);
        pickdate = findViewById(R.id.pick_a_date);

        pickdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_notification(pickdate);
            }
        });
    }

    private TextView show_notification(final TextView pickdate) {

        final TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                now.set(Calendar.HOUR_OF_DAY,hourOfDay);
                now.set(Calendar.MINUTE,minute);
                notifyMe = new NotifyMe.Builder(getApplicationContext())
                        .title(main.getText().toString())
                        .content(sub.getText().toString())
                        .color(255,0,0,255)
                        .led_color(255,255,255,255)
                        .time(now)
                        .addAction(new Intent(),"Snooze",false)
                        .key("test")
                        .addAction(new Intent(),"Dismiss",true,false)
                        .addAction(new Intent(),"Done")
                        .build();
            }
        };
        tpd = new TimePickerDialog(NotificationActivity.this, timeSetListener, now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE),false);

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                now.set(Calendar.YEAR,year);
                now.set(Calendar.MONTH,month);
                now.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                String dates="Next checkup date is " + dayOfMonth + "/" + month + "/" + year;
                sub.setText(dates);
                tpd.show();
            }
        };
        new DatePickerDialog(NotificationActivity.this, dateSetListener, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH)).show();
        return pickdate;
    }
}
