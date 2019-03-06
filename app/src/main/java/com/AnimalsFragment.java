package com;


import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rodolfo.aprendaingles.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnimalsFragment extends Fragment {


    public AnimalsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView picture;
        public TextView name;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_animals, parent, false));
            picture = itemView.findViewById(R.id.tile_picture);
            name = itemView.findViewById(R.id.tile_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                //Add sons
                @Override
                public void onClick(View v) {
                }
            });
        }
    }
    /**
     * Adapter to display recycler view.
     */
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        private static final int LENGTH = 16;
        private final String[] mAnimals;
        private final Drawable[] mAnimalsPictures;
        public ContentAdapter(Context context) {
            Resources resources = context.getResources();
            mAnimals = resources.getStringArray(R.array.animals);
            TypedArray a = resources.obtainTypedArray(R.array.animals_picture);
            mAnimalsPictures = new Drawable[a.length()];
            for (int i = 0; i < mAnimalsPictures.length; i++) {
                mAnimalsPictures[i] = a.getDrawable(i);
            }
            a.recycle();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.picture.setImageDrawable(mAnimalsPictures[position % mAnimalsPictures.length]);
            holder.name.setText(mAnimals[position % mAnimals.length]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }
}