package air.texnodev.weatherapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import air.texnodev.weatherapp.OnItemsClick;
import air.texnodev.weatherapp.R;

public class SAdapter extends RecyclerView.Adapter<SAdapter.ViewHolder>{

    Context context;
    private LayoutInflater inflater;
    private List<AModel> list;
    public final OnItemsClick onItemsClick;

    public SAdapter(Context context, List<AModel> list, OnItemsClick onItemsClick1) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.list = list;
        this.onItemsClick = onItemsClick1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_searching, parent, false);

        return new ViewHolder(view, onItemsClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AModel aModel = list.get(position);
        holder.name.setText(aModel.name + ", " + aModel.country);
        holder.visibly.setText(aModel.lon);
        holder.lng.setText(aModel.lng);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static  class ViewHolder extends RecyclerView.ViewHolder{

        TextView name, visibly, lng;
        public ViewHolder(@NonNull View itemView, OnItemsClick onItemsClick) {
            super(itemView);

            name = itemView.findViewById(R.id.City_Country);
            visibly = itemView.findViewById(R.id.Long);
            lng = itemView.findViewById(R.id.Lng);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemsClick != null){

                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION){
                            onItemsClick.onClick(view, pos);
                        }
                    }
                }
            });
        }


    }
}
