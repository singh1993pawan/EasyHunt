package in.easyhunt;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import retrofit2.Callback;

/**
 * Created by ebaraha 12 on 3/23/2018.
 */

public class ShowCategoryAdapter extends RecyclerView.Adapter<ShowCategoryAdapter.CategoryViewHolder>{

    String[] categoryName;
    String[] image;
    private Context context;
    int count=0;

    public ShowCategoryAdapter(String[] categoryName, String[] image, Context context) {
        this.categoryName = categoryName;
        this.image = image;
        this.context = context;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.categorylist, parent, false);
        return new CategoryViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, final int position) {
        String category = categoryName[position];
        holder.name.setText(category);

        /*holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++count;
            }
        });
*/
        String url = image[position];

        Picasso.with(context)
                .load(url.toString())
                .into(holder.iv);
       /* bitmap = getBitmapFromUrl(image[position]);
        holder.iv.setImageBitmap(bitmap);
*/

    }

    @Override
    public int getItemCount() {

        return categoryName.length;

    }
    public static class RetrieveFeedTask {
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {


        TextView name;
        ImageView iv;




        public CategoryViewHolder(View itemView) {
            super(itemView);


            name = (TextView) itemView.findViewById(R.id.finalCategory);
            iv = (ImageView) itemView.findViewById(R.id.image);

        }


    }

}
