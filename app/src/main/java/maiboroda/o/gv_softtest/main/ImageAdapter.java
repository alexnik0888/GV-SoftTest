package maiboroda.o.gv_softtest.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bumptech.glide.Glide;

import java.util.List;

import maiboroda.o.gv_softtest.R;

/**
 * Adapter for images in RecyclerView in Task class.
 */

class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private List<String> urlArray;
    private OnImageClickListener listener;

    ImageAdapter(List<String> urlArray, OnImageClickListener listener) {
        this.urlArray = urlArray;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageView imageView = holder.imageView;
        Glide.with(imageView.getContext()).load(urlArray.get(position)).centerCrop().into(imageView);

        imageView.setOnClickListener(v -> listener.onImageClick(v));
    }

    @Override
    public int getItemCount() {
        return urlArray.size();
    }

    interface OnImageClickListener {
        void onImageClick(View v);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
