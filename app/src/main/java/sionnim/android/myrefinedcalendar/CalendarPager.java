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
    private OnCalendarPageSelectedListener listener;

    public interface OnCalendarPageSelectedListener{
        void onPageSelected(int year, int month, int position);
    }

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
        calendarPagerAdapter = new CalendarPagerAdapter();
        viewPager2.setAdapter(calendarPagerAdapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                calendarPagerAdapter.selection.calculateCurrentYear(position);
                calendarPagerAdapter.selection.calculateCurrentMonth(position);
                calendarPagerAdapter.selection.setCurrentPage(position);
                if (listener != null){
                    listener.onPageSelected(getCurrentYear()
                            , getCurrentMonth()
                            , position);
                }
            }
        });
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        viewPager2.setCurrentItem(calendarPagerAdapter.selection.getCurrentPage());
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

    public int getCurrentDate(){
        return calendarPagerAdapter.selection.getCurrentDate();
    }
}
