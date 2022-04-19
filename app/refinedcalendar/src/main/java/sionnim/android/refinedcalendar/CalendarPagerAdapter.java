package sionnim.android.refinedcalendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class CalendarPagerAdapter extends RecyclerView.Adapter {
    private ArrayList<CalendarAdapter> items = new ArrayList<CalendarAdapter>();
    public Selection selection;
    private final int[] months = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(int year, int month, int date);
    }

    public CalendarPagerAdapter(){
        selection = new Selection();
        setItems();
    }

    public CalendarPagerAdapter(int minYear, int maxYear){
        selection = new Selection(minYear, maxYear);
        setItems();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout_month, parent, false);
        return new CalendarPagerAdapter.PageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CalendarPagerAdapter.PageViewHolder pageViewHolder = (CalendarPagerAdapter.PageViewHolder) holder;
        pageViewHolder.setItem(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class PageViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        public PageViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recyclerView);
            StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(manager);
        }

        public void setItem(int position){
            recyclerView.setAdapter(items.get(position));
        }
    }

    public void setItems(){
        for (int year = selection.getMinYear(); year<= selection.getMaxYear(); year++){
            for (int month : months){
                items.add(new CalendarAdapter(year, month,(year- selection.getMinYear())*12 + month, new CalendarAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int page, int pos, int date) {
                        setItemIsSelected(selection.getSelectedPage(), selection.getSelectedPosition(), false);
                        setItemIsSelected(page, pos, true);
                        selection.setSelectedPage(page);
                        selection.setSelectedPosition(pos);
                        selection.setSelectedDate(date);
                        if (listener!=null) {
                            listener.onItemClick(selection.getCurrentYear(), selection.getCurrentMonth()
                                    , selection.getSelectedDate());
                        }
                    }
                }));
            }
        }
        setItemIsSelected(selection.getSelectedPage(), selection.getSelectedPosition(), true);
    }
    public void setItemIsSelected(int page, int pos, boolean tag){
        items.get(page).setDateItemIsSelected(pos, tag);
        items.get(page).notifyItemChanged(pos);
    }

    public void setListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
