package com.example.homescreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CollectionAdapter extends ArrayAdapter<Collection> implements Filterable {
    private Context context;
    private int resource;
    private ArrayList<Collection> data;
    private ArrayList<Collection> originData;

    public CollectionAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Collection> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
        this.originData = (ArrayList<Collection>) data.clone();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(data.size() > position) {
            Collection collection = data.get(position);

            DecimalFormat dcf = new DecimalFormat("###.##");

            viewHolder.imgCollection.setImageResource(collection.getImageURL());
            viewHolder.txtName.setText(collection.getName());
            viewHolder.txtAuthor.setText(collection.getAuthor());
            viewHolder.txtPrice.setText(dcf.format(collection.getPrice()));

            viewHolder.btnDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Detail button click", Toast.LENGTH_SHORT).show();
                }
            });
        }

        return convertView;
    }

    private class ViewHolder {
      ImageView imgCollection;
      TextView txtName;
      TextView txtAuthor;
      TextView txtPrice;
      AppCompatButton btnDetail;

      ViewHolder(View view) {
          this.imgCollection = view.findViewById(R.id.imgNFT);
          this.txtName = view.findViewById(R.id.txtName);
          this.txtAuthor = view.findViewById(R.id.txtAuthor);
          this.txtPrice = view.findViewById(R.id.txtPrice);
          this.btnDetail = view.findViewById(R.id.btnDetail);
      }
    }

    @NonNull
    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();

                if(((String) constraint).isEmpty()) {
                    filterResults.count = originData.size();
                    filterResults.values = originData;
                } else {
                    ArrayList<Collection> dataResult = new ArrayList<>();

                    constraint = constraint.toString().toLowerCase(Locale.ROOT);

                    for(Collection nft : originData) {
                        String nftName = nft.getName().toLowerCase(Locale.ROOT);
                        String authorName = nft.getAuthor().toLowerCase(Locale.ROOT);

                        if(nftName.contains(constraint) || authorName.contains((constraint))) {
                            dataResult.add(nft);
                        }
                    }

                    filterResults.count = dataResult.size();
                    filterResults.values = dataResult;
                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                data.clear();
                data.addAll((java.util.Collection<? extends Collection>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }

}
