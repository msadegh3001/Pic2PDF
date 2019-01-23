package ir.takdev.Pic2PDF.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import ir.takdev.Pic2pdf.model.Img;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
public class ImgAdapter extends RecyclerView.Adapter<ImgAdapter.MyViewHolder> {
    List<Img> rcvlist;
    Context mcontext;
    public ImgAdapter(List<Img> rcvlist, Context mcontext) {
        this.rcvlist = rcvlist;
        this.mcontext = mcontext;
    }
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Img it= rcvlist.get(position);
        //holder.pic.setImageResource(it.getPic());
    }

    @Override
    public int getItemCount() {
        return rcvlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public int pic;
        public String name;

        public MyViewHolder(View itemView) {
            super(itemView);
            //  pic = itemView.findViewById(R.id.img_avatar);
            //  name = itemView.findViewById(R.id.txt_name);
        }
    }
}
