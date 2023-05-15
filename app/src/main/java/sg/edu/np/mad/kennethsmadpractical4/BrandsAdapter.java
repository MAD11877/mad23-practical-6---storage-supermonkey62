package sg.edu.np.mad.kennethsmadpractical4;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BrandsAdapter extends RecyclerView.Adapter<BrandViewHolder> {

    private Context context;
    private List<User> userList;
    private SelectListener listener;

    public BrandsAdapter(Context context, List<User> userList, SelectListener listener) {
        this.context = context;
        this.userList = userList;
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        User user = userList.get(position);
        String name = user.getName();
        if (name != null && name.length() > 0) {
            char lastChar = name.charAt(name.length() - 1);
            if (lastChar == '7') {
                Log.v("BrandsAdapter",user.getName());
                return R.layout.item_view_2;
            }
        }
        return R.layout.item_view;
    }

    @Override
    public BrandViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(viewType, parent, false);
        return new BrandViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BrandViewHolder holder, int position) {
        User user = userList.get(position);
        holder.box1View.setText(user.getName());
        holder.box2View.setText(user.getDescription());

        if (getItemViewType(position) == R.layout.item_view_2) {
            holder.item_view_Image.setImageResource(R.drawable.combined_icon);
            holder.item_view_Image2.setImageResource(R.drawable.combined_icon);
            Log.v("BrandsAdapter","unique");
        } else {

            holder.item_view_Image.setImageResource(R.drawable.combined_icon);
        }

        // Set the click listener for the image
        holder.item_view_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
