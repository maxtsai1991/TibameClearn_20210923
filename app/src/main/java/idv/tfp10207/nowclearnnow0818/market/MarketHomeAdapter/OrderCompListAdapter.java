package idv.tfp10207.nowclearnnow0818.market.MarketHomeAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import idv.tfp10207.nowclearnnow0818.R;
import idv.tfp10207.nowclearnnow0818.market.ShoppingCarMerch;


public class OrderCompListAdapter extends RecyclerView.Adapter<OrderCompListAdapter.OrderCompListViewHolder>{

    private static final String TAG = "TAG_OrderComp_List_Ad";
    private static final String SHOPPINGCARLIST = "shoppingCarList";
    //private String memberID = "";


    //欄位 : Context物件、選項資料物件
    private Context context;
    public List<ShoppingCarMerch> shoppingList;
    //public boolean checkAllItem;

    //建構子 : 2個參數(Context型態、選項資料的型態)，用來初始化2欄位
    public OrderCompListAdapter(Context context, List<ShoppingCarMerch> shoppingList){
        this.context = context;
        this.shoppingList = shoppingList;

    }

    @Override
    public int getItemCount() {
        return shoppingList == null ? 0 : shoppingList.size();
    }

    @NonNull
    @Override
    public OrderCompListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //宣告ItemView，並載⼊選項容器元件的外觀
        View itemView = LayoutInflater.from(context).inflate(R.layout.ordercomplete_item_view, parent, false);
        return new OrderCompListViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull OrderCompListViewHolder holder, int position) {

        ShoppingCarMerch shoppingCarMerch = shoppingList.get(position);

        holder.tv_OrderDetItviewMerch_05.setText(shoppingCarMerch.getMerchName());
        holder.tv_OrderDetItviewMerchPrice_05.setText("$ " +  shoppingCarMerch.getMerchPrice());
        holder.tv_OrderDetItviewNumber_05.setText("購買數量 " + shoppingCarMerch.getMerchNumber());
        holder.iv_OrderDetItviewMerch_05.setImageResource(shoppingCarMerch.getDrawableID());

    }



    //內部類別:⾃定義ViewHolder類別
    class OrderCompListViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_OrderDetItviewMerch_05, tv_OrderDetItviewMerchPrice_05, tv_OrderDetItviewNumber_05;
        private ImageView iv_OrderDetItviewMerch_05;

        public OrderCompListViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_OrderDetItviewMerch_05 = itemView.findViewById(R.id.tv_OrderDetItviewMerch_05);
            tv_OrderDetItviewMerchPrice_05 = itemView.findViewById(R.id.tv_OrderDetItviewMerchPrice_05);
            tv_OrderDetItviewNumber_05 = itemView.findViewById(R.id.tv_OrderDetItviewNumber_05);
            iv_OrderDetItviewMerch_05 = itemView.findViewById(R.id.iv_OrderDetItviewMerch_05);

        }
    }

}
