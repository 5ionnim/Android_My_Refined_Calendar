package sionnim.android.refinedcalendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

public class CalendarPager extends LinearLayout {
    CalendarPagerAdapter calendarPagerAdapter;
    ViewPager2 viewPager2;
    private OnCalendarPageSelectedListener listener;

    public interface OnCalendarPageSelectedListener{
        void onPageSelected(int year, int month, int page);
    }

    public CalendarPager(Context context) {
        super(context);
        initializeViews(context, null);
    }

    public CalendarPager(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context, attrs);
    }

    public CalendarPager(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeViews(context, attrs);
    }

    public CalendarPager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initializeViews(context, attrs);
    }

    private void initializeViews(Context context, AttributeSet attrs) {
        this.setOrientation(LinearLayout.VERTICAL);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.layout_calendar, this);
        viewPager2 = v.findViewById(R.id.viewPager);

        if (attrs!=null){
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CalendarPager);
            int minYear = typedArray.getInteger(R.styleable.CalendarPager_minYear, 1990);
            int maxYear = typedArray.getInteger(R.styleable.CalendarPager_maxYear, 2050);
            Log.d("year",""+minYear+","+maxYear);
            calendarPagerAdapter = new CalendarPagerAdapter(minYear, maxYear);
        } else {
            calendarPagerAdapter = new CalendarPagerAdapter();
        }
        viewPager2.setAdapter(calendarPagerAdapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int page) {
                super.onPageSelected(page);
                calendarPagerAdapter.selection.calculateCurrentYear(page);
                calendarPagerAdapter.selection.calculateCurrentMonth(page);
                //calendarPagerAdapter.selection.setCurrentPage(page);
                if (listener != null){
                    listener.onPageSelected(getCurrentYear()
                            , getCurrentMonth()
                            , page);
                }
            }
        });
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        viewPager2.setCurrentItem(calendarPagerAdapter.selection.getSelectedPage());
    }

    public void setOnItemClickListener(CalendarPagerAdapter.OnItemClickListener listener){
        calendarPagerAdapter.setListener(listener);
    }

    public void setOnPageSelectedListener(OnCalendarPageSelectedListener listener){
        this.listener = listener;
    }

    public void moveCalendarPage(int num){
        viewPager2.setCurrentItem(viewPager2.getCurrentItem()+num);
    }

    public int getCurrentYear(){
        return calendarPagerAdapter.selection.getCurrentYear();
    }

    public int getCurrentMonth(){
        return calendarPagerAdapter.selection.getCurrentMonth();
    }

    public int getSelectedDate(){
        return calendarPagerAdapter.selection.getSelectedDate();
    }
}
