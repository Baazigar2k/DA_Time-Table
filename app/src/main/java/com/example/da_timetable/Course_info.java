package com.example.da_timetable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

//import android.os.Build;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
//import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;

import com.example.da_timetable.Utils.LetterImageView;

public class Course_info extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);

        setupUIViews();
        initToolbar();

        setupListView();
    }

    private void setupUIViews(){
        toolbar = (Toolbar)findViewById(R.id.ToolbarInfo);
        listView = (ListView)findViewById(R.id.lvInfo);
    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Course_Information");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupListView(){
        String[] course = getResources().getStringArray(R.array.Courses);
        String[] course_fullName = getResources().getStringArray(R.array.CoursesFullName);

        CourseAdapter adapter = new CourseAdapter(this, R.layout.course_activity_single_item, course, course_fullName);

        listView.setAdapter(adapter);
    }

    public class CourseAdapter extends ArrayAdapter {

        private int resource;
        private LayoutInflater layoutInflater;
        private String[] courses = new String[]{};
        private String[] courses_fullName = new String[]{};

        public CourseAdapter(Context context, int resource, String[] objects, String[] objects1) {
            super(context, resource, objects);
            this.resource = resource;
            this.courses = objects;
            this.courses_fullName = objects1;
            layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView == null){
                holder = new ViewHolder();
                convertView = layoutInflater.inflate(resource, null);
                holder.ivLogo = convertView.findViewById(R.id.ivLetterInfo);
                holder.tvCourse = convertView.findViewById(R.id.tvSubjectInfo);
                holder.tvFullName = convertView.findViewById(R.id.tvFullInfo);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder)convertView.getTag();
            }

            holder.ivLogo.setOval(true);
            holder.ivLogo.setLetter(courses[position].charAt(0));
            holder.tvCourse.setText(courses[position]);
            holder.tvFullName.setText(courses_fullName[position]);

            return convertView;
        }

        class ViewHolder{
            private LetterImageView ivLogo;
            private TextView tvCourse, tvFullName;
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