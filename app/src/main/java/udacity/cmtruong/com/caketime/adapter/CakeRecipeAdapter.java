package udacity.cmtruong.com.caketime.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import udacity.cmtruong.com.caketime.R;
import udacity.cmtruong.com.caketime.databinding.CakeItemBinding;
import udacity.cmtruong.com.caketime.model.Cake;
import udacity.cmtruong.com.caketime.vm.CakeItemViewModel;

/**
 * My cake receipt adapter
 *
 * @author davidetruong
 * @version 1.0
 * @since May, 8th
 */
public class CakeRecipeAdapter extends RecyclerView.Adapter<CakeRecipeAdapter.CakeViewHolder> {
    private static final String TAG = CakeRecipeAdapter.class.getSimpleName();
    private List<Cake> cakes;

    public CakeRecipeAdapter() {
        cakes = new ArrayList<>();
    }

    @NonNull
    @Override
    public CakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CakeItemBinding itemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.cake_item, parent, false
        );
        Log.d(TAG, "onCreateViewHolder: [new View]");
        return new CakeViewHolder(itemBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull CakeViewHolder holder, int position) {
        CakeItemViewModel mViewModel = new CakeItemViewModel(cakes.get(position));
        Log.d(TAG, "onBindViewHolder: [Position] " + position);
        holder.itemBinding.setViewModel(mViewModel);
    }

    @Override
    public int getItemCount() {
        return cakes.size();
    }


    public void addItem(Cake cake) {
        cakes.add(cake);
        notifyItemInserted(cakes.size() - 1);
    }

    class CakeViewHolder extends RecyclerView.ViewHolder {
        CakeItemBinding itemBinding;

        public CakeViewHolder(CakeItemBinding itemBinding) {
            super(itemBinding.cakeCardView);
            this.itemBinding = itemBinding;
        }


    }
}
