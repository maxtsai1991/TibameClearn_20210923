package idv.tfp10207.nowclearnnow0818.mycommodity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import idv.tfp10207.nowclearnnow0818.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Produce> mData;

    MyAdapter(List<Produce> data) {
        mData = data;
    }

    // 建立ViewHolder
    class ViewHolder extends RecyclerView.ViewHolder{
        // 宣告元件
        private TextView txtItem;
        private ImageView imageView1;
        private Button btnRemove;
        private LinearLayout test1;

        ViewHolder(View itemView) {
            super(itemView);
            txtItem = (TextView) itemView.findViewById(R.id.txtItem);
            btnRemove = (Button) itemView.findViewById(R.id.btnRemove);
            imageView1 = (ImageView) itemView.findViewById(R.id.imageView1);
            test1 = (LinearLayout) itemView.findViewById(R.id.test1);
            // 點擊項目時
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),
                            "click " +getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });

            // 點擊項目中的Button時
//            btnRemove.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    // 按下Button要做的事
//                    test1.setVisibility(View.GONE);
//                }
//            });
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 連結項目布局檔list_item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 設置txtItem要顯示的內容
        Produce produce = mData.get(position);
        holder.txtItem.setText(produce.getTitle());
        holder.imageView1.setImageResource(produce.getImageId());
       holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 按下Button要做的事
                mData.remove(position);
                notifyItemRemoved(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}