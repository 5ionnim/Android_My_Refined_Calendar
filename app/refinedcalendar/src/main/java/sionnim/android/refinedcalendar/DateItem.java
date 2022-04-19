package sionnim.android.refinedcalendar;

public class DateItem {
    private Integer date = null;
    private Integer weekend = null;
    private boolean isSelected = false;
    public DateItem(){
    }
    public DateItem(Integer date, Integer weekend){
        this.date = date;
        this.weekend = weekend;
    }
    public DateItem(Integer date, Integer weekend, boolean isSelected){
        this.date = date;
        this.weekend = weekend;
        this.isSelected = isSelected;
    }

    public DateItem(Integer date){
        this.date = date;
    }

    public Integer getDate(){
        return date;
    }

    public Integer getWeekend(){
        return weekend;
    }

    public boolean getIsSelectd() { return isSelected; }

    public void setDate(Integer date){
        this.date = date;
    }

    public void setWeekend(Integer weekend){
        this.weekend = weekend;
    }

    public void setIsSelected(boolean isSelected) { this.isSelected = isSelected; }
}
