package cosc150.restaurantsearch;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ResAdapter extends ArrayAdapter<Restaurant>{

    private Context context;
    private int resource;
    private ArrayList<Restaurant> restaurantList;


    public ResAdapter(Context context, int resource, ArrayList<Restaurant> restaurantList) {
        super(context, resource, restaurantList);

        this.context = context;
        this.resource = resource;
        this.restaurantList = restaurantList;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater= ((Activity)context).getLayoutInflater();
        row = inflater.inflate(resource, parent, false);
        TextView restaurantName = (TextView)row.findViewById(R.id.restName);
        TextView restaurantDescription = (TextView)row.findViewById(R.id.restDescription);
        TextView restaurantRating = (TextView)row.findViewById(R.id.restRating);
        ImageView restaurantImage = (ImageView)row.findViewById(R.id.restImage);

        restaurantName.setText(restaurantList.get(position).restaurantName);
        restaurantDescription.setText(restaurantList.get(position).restaurantDescription);
        restaurantDescription.setTextSize(8);
        restaurantRating.setText(Double.toString(restaurantList.get(position).restaurantRating));
        restaurantRating.setTextSize(10);

        final Restaurant pos = restaurantList.get(position);

        switch (restaurantList.get(position).restaurantCategory.toLowerCase()){
            case "aztec":
                restaurantImage.setImageResource(R.drawable.aztec);
                break;
            case "chinese":
                restaurantImage.setImageResource(R.drawable.chinese);
                break;
            case "japanese":
                restaurantImage.setImageResource(R.drawable.japanese);
                break;
            case "italian":
                restaurantImage.setImageResource(R.drawable.italian);
                break;
            case "french":
                restaurantImage.setImageResource(R.drawable.french);
                break;
            case "middle eastern":
                restaurantImage.setImageResource(R.drawable.middle_east);
                break;
            case "canadian":
                restaurantImage.setImageResource(R.drawable.canadian);
                break;
            case "mexican":
                restaurantImage.setImageResource(R.drawable.mexican);
                break;
        }

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent select = new Intent(view.getContext(), SelectedRestaurant.class);
                select.putExtra("restaurant_name", pos.restaurantName);
                select.putExtra("description", pos.restaurantDescription);
                select.putExtra("overall_rating", pos.restaurantRating);
                view.getContext().startActivity(select);
            }
        });

        return row;
    }
}
