package sionnim.android.myrefinedcalendar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

public class CalendarPager extends LinearLayout {
    CalendarPagerAdapter calendarPagerAdapter;
    ViewPager2 viewPager2;
    public CalendarPager(Context context) {
        super(context);
        initializeViews(context);
    }

    public CalendarPager(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context);
    }

    public CalendarPager(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeViews(context);
    }

    public CalendarPager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initializeViews(context);
    }

    private void initializeViews(Context context) {
        this.setOrientation(LinearLayout.VERTICAL);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.layout_calendar, this);
        viewPager2 = v.findViewById(R.id.viewPager);
        calendarPagerAdapter = new CalendarPagerAdapter(new CalendarPagerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int year, int month, int date) {
            }
        });
        viewPager2.setAdapter(calendarPagerAdapter);

    }
}
