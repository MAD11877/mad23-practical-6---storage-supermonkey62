package sg.edu.np.mad.kennethsmadpractical4;


import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class BrandViewHolder extends RecyclerView.ViewHolder {

    ImageView item_view_Image;
    TextView box1View, box2View;

    ImageView item_view_Image2;



    public BrandViewHolder(View itemView) {
        super(itemView);
        item_view_Image = itemView.findViewById(R.id.item_view_image);
        box1View = itemView.findViewById(R.id.box1);
        box2View = itemView.findViewById(R.id.box2);
        item_view_Image2 = itemView.findViewById(R.id.item_view_image2);


    }
}