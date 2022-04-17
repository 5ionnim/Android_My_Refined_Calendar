package sionnim.android.myrefinedcalendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final int maxYear = 2050;
    final int minYear = 1990;
    ViewPager2 viewPager;
    TextView yearText;
    TextView monthText;
    Button yearPrevButton;
    Button yearNextButton;
    Button monthPrevButton;
    Button monthNextButton;
    CalendarPagerAdapter calendarPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        yearText = findViewById(R.id.yearText);
        monthText = findViewById(R.id.monthText);
        calendarPagerAdapter = new CalendarPagerAdapter(minYear, maxYear, new CalendarPagerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int year, int month, int date) {
                Toast.makeText(getApplicationContext(),""+year+"/"+(month+1)+"/"+date, Toast.LENGTH_SHORT).show();
            }
        });
        viewPager.setAdapter(calendarPagerAdapter);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                yearText.setText(""+calendarPagerAdapter.selection.calculateCurrentYear(position));
                monthText.setText(""+(calendarPagerAdapter.selection.calculateCurrentMonth(position)+1));
                calendarPagerAdapter.selection.setCurrentPage(position);
            }
        });
        viewPager.setCurrentItem(calendarPagerAdapter.selection.getCurrentPage());

        yearPrevButton = findViewById(R.id.yearPrevButton);
        yearNextButton = findViewById(R.id.yearNextButton);
        monthPrevButton = findViewById(R.id.monthPrevButton);
        monthNextButton = findViewById(R.id.monthNextButton);

        yearPrevButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                viewPager.setCurrentItem(viewPager.getCurrentItem()-12);
            }
        });
        yearNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                viewPager.setCurrentItem(viewPager.getCurrentItem()+12);
            }
        });
        monthPrevButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                viewPager.setCurrentItem(viewPager.getCurrentItem()-1); }
        });
        monthNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }
        });
    }
}