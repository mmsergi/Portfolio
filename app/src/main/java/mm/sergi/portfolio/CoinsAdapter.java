package mm.sergi.portfolio;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergi on 05/12/2017.
 */

public class CoinsAdapter extends RecyclerView.Adapter<CoinsAdapter.MyViewHolder> implements Filterable {
    private Context context;
    private List<Coin> coinList;
    private List<Coin> coinListFiltered;
    private CoinsAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name, percentage;
        private ImageView thumbnail;

        private MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            percentage = view.findViewById(R.id.percentage);
            thumbnail = view.findViewById(R.id.logo);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(coinListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }


    public CoinsAdapter(Context context, List<Coin> coinList, CoinsAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.coinList = coinList;
        this.coinListFiltered = coinList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Coin coin = coinListFiltered.get(position);
        holder.name.setText(coin.getName());
        holder.percentage.setText(Float.toString(coin.getPrice_usd()));
        Glide.with(context)
                .load("https://www.cryptocompare.com/" + AppController.getImageURL(coin.getSymbol()))
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return coinListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    coinListFiltered = coinList;
                } else {
                    List<Coin> filteredList = new ArrayList<>();
                    for (Coin row : coinList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or percentage number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    coinListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = coinListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                coinListFiltered = (ArrayList<Coin>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface CoinsAdapterListener {
        void onContactSelected(Coin coin);
    }
}
