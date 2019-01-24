package ir.takdev.Pic2pdf.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import ir.takdev.Pic2pdf.R;
import ir.takdev.Pic2pdf.Utility.RecyclerViewClickListener;
import ir.takdev.Pic2pdf.model.PDF;


import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class PDFListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PDF> mPDFs = new ArrayList<>();
    private RecyclerViewClickListener mListener;
    private boolean isMultiselect =false;
    private Context context;

    public PDFListAdapter(Context context) {
        this.context = context;
    }

    public void setOnClickListener(RecyclerViewClickListener listener) {
        mListener = listener;
    }

    public void setMultiselect(boolean multiselect) {
        isMultiselect = multiselect;
    }

    public void update(List<PDF> pdfs) {
        mPDFs.clear();
        mPDFs.addAll(pdfs);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pdfs_layout, parent, false);
        return new PDFViewHolder(v, mListener,context);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PDFViewHolder pdfViewHolder = (PDFViewHolder) holder;
        PDF pdf = mPDFs.get(position);
        pdfViewHolder.mNameTextView.setText(pdf.getName());
        pdfViewHolder.mSize.setText(pdf.getSize());
        pdfViewHolder.setId(pdf.getId());
    }

    @Override
    public int getItemCount() {
        return mPDFs.size();
    }

    @Override
    public long getItemId(int position) {
        return mPDFs.get(position).getId();
    }

    private static class PDFViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener,View.OnClickListener{

        private long id;
        TextView mNameTextView;
        TextView mSize;
        Context context;
        public void setId(long id) {
            this.id = id;
        }
        public long getId() {
            return id;
        }

        private RecyclerViewClickListener mListener;

        public PDFViewHolder(View itemView, RecyclerViewClickListener listener,Context context) {
            super(itemView);
            this.context=context;
            mListener = listener;
            mNameTextView = itemView.findViewById(R.id.pdf_title);
            mSize=itemView.findViewById(R.id.pdf_size);
            itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
        }
        @SuppressLint("ResourceAsColor")
        @Override
        public boolean onLongClick(View view) {
            int pos = getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                view.setBackgroundColor(R.color.blue300);
                //  mListener.onClick(view, pos);
                Toast.makeText(context,"Long Click",Toast.LENGTH_LONG).show();
            }
            return true;
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                view.setBackgroundColor(R.color.blue300);
            }
                else {
                    mListener.onClick(view, pos);
                }
            }
        }
}
