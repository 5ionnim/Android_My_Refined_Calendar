package sionnim.android.refinedcalendar;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Selection {
    private int minYear = 1990;
    private int maxYear = 2050;
    private int selectedPage;
    private int selectedPosition;
    private int selectedDate;
    private int currentYear;
    private int currentMonth;

    public Selection(){
        initialSetting();
    }
    public Selection(int minYear, int maxYear){
        this.minYear = minYear;
        this.maxYear = maxYear;
        initialSetting();
    }

    public int getMinYear(){ return minYear; }
    public int getMaxYear(){ return maxYear; }
    public int getSelectedPage(){ return selectedPage; }
    public void setSelectedPage(int selectedPage){ this.selectedPage = selectedPage; }
    public int getSelectedPosition(){ return selectedPosition; }
    public void setSelectedPosition(int selectedPosition){ this.selectedPosition = selectedPosition; }
    public int getCurrentYear(){ return currentYear; }
    public void setCurrentYear(int currentYear){ this.currentYear = currentYear; }
    public int getCurrentMonth(){ return currentMonth; }
    public void setCurrentMonth(int currentMonth){ this.currentMonth = currentMonth; }
    public int getSelectedDate(){ return selectedDate; }
    public void setSelectedDate(int selectedDate){ this.selectedDate = selectedDate; }

    private void initialSetting(){
        GregorianCalendar today = new GregorianCalendar();
        currentYear = today.get(Calendar.YEAR);
        currentMonth = today.get(Calendar.MONTH);
        selectedDate = today.get(Calendar.DATE);
        selectedPage = (currentYear-minYear)*12 + currentMonth;

        GregorianCalendar firstDate = new GregorianCalendar(currentYear, currentMonth, 1, 0, 0, 0);
        selectedPosition = firstDate.get(Calendar.DAY_OF_WEEK)+selectedDate-2;
    }

    public int calculateCurrentMonth(int position){
        currentMonth = position%12;
        return currentMonth;
    }

    public int calculateCurrentYear(int position){
        currentYear = minYear + position/12;
        return currentYear;
    }

}
