package com.example.mylove;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;


public class BackgroundImageAdapter extends RecyclerView.Adapter<BackgroundImageAdapter.ImageViewHolder> {
    private Context context;
    private List<Integer> imageIds;

    public BackgroundImageAdapter(Context context, List<Integer> imageIds) {
        this.context = context;
        this.imageIds = imageIds;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_background_image, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        int imageId = imageIds.get(position % imageIds.size());
        Glide.with(context)
                .load(imageId)
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imageIds.size() == 0 ? 0 : Integer.MAX_VALUE;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
