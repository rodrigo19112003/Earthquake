package com.example.earthquakeapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earthquakeapp.databinding.EqListItemBinding;

import java.util.Objects;

public class EqAdapter extends ListAdapter<Earthquake, EqAdapter.EqViewHolder> {
    public static final DiffUtil.ItemCallback<Earthquake> DIFF_CALLBACK = new DiffUtil.ItemCallback<Earthquake>() {
        @Override
        public boolean areItemsTheSame(@NonNull Earthquake oldEq, @NonNull Earthquake newEq) {

            return oldEq.getId().equals(newEq.getId());
        }
        @Override
        public boolean areContentsTheSame(@NonNull Earthquake oldEq, @NonNull Earthquake newEq) {

            return Objects.equals(oldEq, newEq);
        }
    };

    protected EqAdapter() {
        super(DIFF_CALLBACK);
    }

    private OnItemClickListener onItemClickListener;
    interface OnItemClickListener {
        void onItemClick(Earthquake earthquake);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public EqAdapter.EqViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        EqListItemBinding binding = EqListItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new EqViewHolder(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull EqAdapter.EqViewHolder holder, int position) {
        Earthquake earthquake = getItem(position);
        holder.bind(earthquake);
    }
    class EqViewHolder extends RecyclerView.ViewHolder{
        private final EqListItemBinding binding;
        public EqViewHolder(@NonNull EqListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(Earthquake earthquake) {

            binding.magnitudeText.setText(String.valueOf(earthquake.getMagnitude()));
            binding.placeText.setText(earthquake.getPlace());
            binding.getRoot().setOnClickListener( v -> {
                onItemClickListener.onItemClick(earthquake);
            });
            binding.executePendingBindings();
        }
    }
}

