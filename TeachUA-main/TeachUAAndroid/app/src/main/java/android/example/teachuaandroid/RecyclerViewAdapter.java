package android.example.teachuaandroid;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewViewHolder> {

    private ArrayList<RecyclerViewItem> arrayList;
    public Context context;

    public class RecyclerViewViewHolder extends RecyclerView.ViewHolder {


        public ImageView imageView;
        public TextView text;
        public Button button;


        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageInMainScreenCard);
            text = itemView.findViewById(R.id.textInMainScreenCard);
            button = itemView.findViewById(R.id.buttonInMainScreenCardMoreInfo);
        }

    }

    public RecyclerViewAdapter(ArrayList<RecyclerViewItem> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_item, viewGroup, false);

        RecyclerViewViewHolder recyclerViewViewHolder = new RecyclerViewViewHolder(view);
        return recyclerViewViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewViewHolder holder, int position) {

        RecyclerViewItem recyclerViewItem = arrayList.get(position);

        holder.imageView.setImageResource(recyclerViewItem.getImageResource());
        holder.text.setText(recyclerViewItem.getTextInfoAboutGroups());
        holder.button.setOnClickListener((View.OnClickListener) recyclerViewItem.getButton());

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position = holder.getAdapterPosition();
                RecyclerViewItem recyclerViewItem1 = arrayList.get(position);

                Intent intent = new Intent(context, AboutUsActivity.class);
                int imageID = recyclerViewItem1.getImageResource();

                intent.putExtra("imageResources", imageID);
                intent.putExtra("textInfoAboutGroups", recyclerViewItem1.getTextInfoAboutGroups());
                intent.putExtra("description", recyclerViewItem1.getDescription());

                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}
