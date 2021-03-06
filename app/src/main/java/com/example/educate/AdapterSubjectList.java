package com.example.educate;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterSubjectList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SubjectClass> items = new ArrayList<>();

    private Context ctx;
    private com.example.educate.AdapterSubjectList.OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view,SubjectClass obj, int position);
    }

    public void setOnItemClickListener(final com.example.educate.AdapterSubjectList.OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public AdapterSubjectList(Context context, List<SubjectClass> items) {
        this.items = items;
        ctx = context;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public TextView name,subject_index,desc;
        public ImageButton subject_enter;
        public LinearLayout layout_parent;

        public OriginalViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.item_subject_name);
            desc = v.findViewById(R.id.subject_desc);
            subject_index = v.findViewById(R.id.subject_index);
            subject_enter = v.findViewById(R.id.subject_enter);
            layout_parent = v.findViewById(R.id.lyt_parent);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_recyclerview_item, parent, false);
        vh = new com.example.educate.AdapterSubjectList.OriginalViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof com.example.educate.AdapterSubjectList.OriginalViewHolder) {
            com.example.educate.AdapterSubjectList.OriginalViewHolder view = (com.example.educate.AdapterSubjectList.OriginalViewHolder) holder;

            SubjectClass p = items.get(position);
            view.name.setText(p.getName());
            view.subject_index.setText(String.valueOf(position+1));
            view.desc.setText(p.getDescription());

            //    Tools.displayImageRound(ctx, view.image, p.image);
            view.layout_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(view, items.get(position), position);
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}