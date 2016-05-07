package cosc150.restaurantsearch;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
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
        LayoutInflater inflater= ((Activity)context).getLayoutInflater();
        View row = inflater.inflate(resource, parent, false);
        TextView restaurantName = (TextView)row.findViewById(R.id.restName);
        TextView restaurantDescription = (TextView)row.findViewById(R.id.restDescription);
        TextView restaurantRating = (TextView)row.findViewById(R.id.restRating);
        ImageView restaurantImage = (ImageView)row.findViewById(R.id.restImage);

        restaurantName.setText(restaurantList.get(position).restaurantName);
        restaurantDescription.setText(restaurantList.get(position).restaurantDescription);
        restaurantRating.setText(Double.toString(restaurantList.get(position).restaurantRating));

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

        return row;
    }
}
