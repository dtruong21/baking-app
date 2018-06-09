package udacity.cmtruong.com.caketime.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import udacity.cmtruong.com.caketime.R;
import udacity.cmtruong.com.caketime.adapter.event.OnStepHandledListener;
import udacity.cmtruong.com.caketime.model.Step;

/**
 * Step Adapter for RecyclerView
 *
 * @author davidetruong
 * @version 1.0
 * @since May, 9th
 */
public class RecipeStepAdapter extends RecyclerView.Adapter<RecipeStepAdapter.StepViewHolder> {
    private static final String TAG = RecipeStepAdapter.class.getSimpleName();
    private List<Step> steps;
    private OnStepHandledListener listener;

    public RecipeStepAdapter(List<Step> steps) {
        this.steps = steps;

    }

    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutId = R.layout.step_list_item;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layoutId, parent, false);
        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + position);
        Step step = steps.get(position);
        holder.bind(step);
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    public void setOnItemClickedListener(OnStepHandledListener listener) {
        this.listener = listener;
    }


    class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.step_name_tv)
        TextView step_tv;

        Step mStep;

        public StepViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        void bind(Step step) {
            mStep = step;
            String name = mStep.getId() + " - " + mStep.getShortDescription();
            step_tv.setText(name);
        }


        @Override
        public void onClick(View v) {
            listener.onStepClicked(v, getAdapterPosition());
        }
    }
}
