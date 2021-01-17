package com.example.da_timetable;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;

import com.example.da_timetable.Utils.LetterImageView;

public class Today_activity extends AppCompatActivity {

    private ListView listView;
    private Toolbar toolbar;
    public static String[] Monday;
    public static String[] Tuesday;
    public static String[] Wednesday;
    public static String[] Thursday;
    public static String[] Friday;
    public static String[] Saturday;
    public static String[] Time1;
    public static String[] Time2;
    public static String[] Time3;
    public static String[] Time4;
    public static String[] Time5;
    public static String[] Time6;
    private String[] My_Day;
    private String[] My_Time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_activity);

        setupUIViews();
        initToolbar();
        setupListView();
    }

    private void setupUIViews(){
        listView = (ListView)findViewById(R.id.lvToday);
        toolbar = (Toolbar) findViewById(R.id.ToolbarToday);
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(MainActivity.sharedPreferences.getString(MainActivity.DAY_CHOSEN, null));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupListView(){

        Monday = getResources().getStringArray(R.array.Monday);
        Tuesday = getResources().getStringArray(R.array.Tuesday);
        Wednesday = getResources().getStringArray(R.array.Wednesday);
        Thursday = getResources().getStringArray(R.array.Thursday);
        Friday = getResources().getStringArray(R.array.Friday);
        Saturday = getResources().getStringArray(R.array.Saturday);

        Time1 = getResources().getStringArray(R.array.time1);
        Time2 = getResources().getStringArray(R.array.time2);
        Time3 = getResources().getStringArray(R.array.time3);
        Time4 = getResources().getStringArray(R.array.time4);
        Time5 = getResources().getStringArray(R.array.time5);
        Time6 = getResources().getStringArray(R.array.time6);

        String selected_day = MainActivity.sharedPreferences.getString(MainActivity.DAY_CHOSEN, null);

        if(selected_day.equalsIgnoreCase("Monday")){
            My_Day = Monday;
            My_Time = Time1;
        }else if(selected_day.equalsIgnoreCase("Tuesday")){
            My_Day = Tuesday;
            My_Time = Time2;
        }else if(selected_day.equalsIgnoreCase("Wednesday")){
            My_Day = Wednesday;
            My_Time = Time3;
        }else if(selected_day.equalsIgnoreCase("Thursday")){
            My_Day = Thursday;
            My_Time = Time4;
        }else if(selected_day.equalsIgnoreCase("Friday")){
            My_Day = Friday;
            My_Time = Time5;
        }else{
            My_Day = Saturday;
            My_Time = Time6;
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(Today_activity.this, My_Day, My_Time);
        listView.setAdapter(simpleAdapter);

    }

    public class SimpleAdapter extends BaseAdapter {

        private Context my_Context;
        private LayoutInflater layoutInflater;
        private TextView subject, time;
        private String[] subjectArray;
        private String[] timeArray;
        private LetterImageView letterImageView;

        public SimpleAdapter(Context context, String[] subjectArray, String[] timeArray){
            my_Context = context;
            this.subjectArray = subjectArray;
            this.timeArray = timeArray;
            layoutInflater = LayoutInflater.from(context);
        }


        @Override
        public int getCount() {
            return subjectArray.length;
        }

        @Override
        public Object getItem(int position) {
            return subjectArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = layoutInflater.inflate(R.layout.today_activity_single_item, null);
            }

            subject = (TextView)convertView.findViewById(R.id.tvSubjectToday);
            time = (TextView)convertView.findViewById(R.id.tvTimeToday);
            letterImageView = (LetterImageView)convertView.findViewById(R.id.ivLetterToday);

            subject.setText(subjectArray[position]);
            time.setText(timeArray[position]);

            letterImageView.setOval(true);
            letterImageView.setLetter(subjectArray[position].charAt(0));

            return convertView;

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home : {
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}