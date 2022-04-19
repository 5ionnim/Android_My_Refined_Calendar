package sionnim.android.refinedcalendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarAdapter extends RecyclerView.Adapter {
    private final int DAY_TYPE = 0;
    private final int SUN_TYPE = 1;
    private final int SAT_TYPE = 2;
    private final int BLANK_TYPE = 3;
    private final int pagePosition;
    private ArrayList<DateItem> items = new ArrayList<DateItem>();

    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(int page, int pos, int date);
    }


    public CalendarAdapter(int year, int month, int pagePosition, OnItemClickListener listener){
        this.pagePosition = pagePosition;
        this.listener = listener;
        setItems(year, month);
    }
    @Override
    public int getItemViewType(int position){
        DateItem item = items.get(position);
        if (item.getDate()==null){
            return BLANK_TYPE;
        } else if (item.getWeekend()==null){
            return DAY_TYPE;
        } else if (item.getWeekend()==1){
            return SUN_TYPE;
        } else {
            return SAT_TYPE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView;
        switch(viewType){
            case BLANK_TYPE:
                itemView = inflater.inflate(R.layout.layout_blank, parent, false);
                return new BlankViewHolder(itemView);
            case DAY_TYPE:
                itemView = inflater.inflate(R.layout.layout_day, parent, false);
                return new DayViewHolder(itemView);
            case SUN_TYPE:
                itemView = inflater.inflate(R.layout.layout_sun, parent, false);
                return new SunViewHolder(itemView);
            default:
                itemView = inflater.inflate(R.layout.layout_sat, parent, false);
                return new SatViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch(viewType){
            case BLANK_TYPE:
                BlankViewHolder blankViewHolder = (BlankViewHolder) holder;
                break;
            case DAY_TYPE:
                DayViewHolder dayViewHolder = (DayViewHolder) holder;
                dayViewHolder.setItem(position);
                break;
            case SUN_TYPE:
                SunViewHolder sunViewHolder = (SunViewHolder) holder;
                sunViewHolder.setItem(position);
                break;
            case SAT_TYPE:
                SatViewHolder satViewHolder = (SatViewHolder) holder;
                satViewHolder.setItem(position);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class SunViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CardView cardView;
        public SunViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            textView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        if (listener != null){
                            listener.onItemClick(pagePosition, pos, items.get(pos).getDate());
                        }
                    }
                }
            });
        }

        public void setItem(int position){
            textView.setText(items.get(position).getDate().toString());
            if (items.get(position).getIsSelectd()){
                cardView.setSelected(true);
            } else {
                cardView.setSelected(false);
            }
        }
    }
    public class SatViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CardView cardView;
        public SatViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            textView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        if (listener != null){
                            listener.onItemClick(pagePosition, pos, items.get(pos).getDate());
                        }
                    }
                }
            });
        }

        public void setItem(int position){
            textView.setText(items.get(position).getDate().toString());
            if (items.get(position).getIsSelectd()){
                cardView.setSelected(true);
            } else {
                cardView.setSelected(false);
            }
        }
    }
    public class DayViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CardView cardView;
        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            textView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        if (listener != null){
                            listener.onItemClick(pagePosition, pos, items.get(pos).getDate());
                        }
                    }
                }
            });
        }

        public void setItem(int position){
            textView.setText(items.get(position).getDate().toString());
            if (items.get(position).getIsSelectd()){
                cardView.setSelected(true);
            } else {
                cardView.setSelected(false);
            }
        }
    }
    public class BlankViewHolder extends RecyclerView.ViewHolder {
        public BlankViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void setItems(int year, int month){
        GregorianCalendar firstDate = new GregorianCalendar(year, month, 1, 0, 0, 0);
        int dayOfWeek = firstDate.get(Calendar.DAY_OF_WEEK);
        int lastDay = firstDate.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i=1; i<dayOfWeek; i++){
            items.add(new DateItem());
        }
        for (int i=1; i<=lastDay; i++){
            if ((dayOfWeek+i-1)%7 == 1){
                items.add(new DateItem(i, 1));
            } else if ((dayOfWeek+i-1)%7 == 0){
                items.add(new DateItem(i, 7));
            } else {
                items.add(new DateItem(i));
            }
        }
        if ((dayOfWeek+lastDay-1)%7!=0){
            for (int i=0; i<=(7-(dayOfWeek+lastDay)%7)%7; i++){
                items.add(new DateItem());
            }
        }
    }
    public void setDateItemIsSelected(int pos, boolean tag){
        items.get(pos).setIsSelected(tag);
    }
}
