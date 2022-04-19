package sionnim.android.myrefinedcalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import sionnim.android.refinedcalendar.CalendarPager;
import sionnim.android.refinedcalendar.CalendarPagerAdapter;

public class MainActivity extends AppCompatActivity {
    TextView yearText;
    TextView monthText;
    Button yearPrevButton;
    Button yearNextButton;
    Button monthPrevButton;
    Button monthNextButton;
    CalendarPager calendarPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        yearText = findViewById(R.id.yearText);
        monthText = findViewById(R.id.monthText);

        calendarPager = findViewById(R.id.calendarPager);

        yearText.setText(""+calendarPager.getCurrentYear());
        monthText.setText(""+(calendarPager.getCurrentMonth()+1));

        calendarPager.setOnPageSelectedListener(new CalendarPager.OnCalendarPageSelectedListener() {
            @Override
            public void onPageSelected(int year, int month, int page) {
                yearText.setText(""+year);
                monthText.setText(""+(month+1));
            }
        });

        calendarPager.setOnItemClickListener(new CalendarPagerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int year, int month, int date) {
                Toast.makeText(getApplicationContext(),""+year+"/"+(month+1)+"/"+date, Toast.LENGTH_SHORT).show();
            }
        });

        yearPrevButton = findViewById(R.id.yearPrevButton);
        yearNextButton = findViewById(R.id.yearNextButton);
        monthPrevButton = findViewById(R.id.monthPrevButton);
        monthNextButton = findViewById(R.id.monthNextButton);

        yearPrevButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                calendarPager.moveCalendarPage(-12);
            }
        });
        yearNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                calendarPager.moveCalendarPage(12);
            }
        });
        monthPrevButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                calendarPager.moveCalendarPage(-1);
            }
        });
        monthNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                calendarPager.moveCalendarPage(1);
            }
        });
    }
}