package idv.tfp10207.nowclearnnow0818.market.MarketHomeAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import idv.tfp10207.nowclearnnow0818.R;
import idv.tfp10207.nowclearnnow0818.market.ShoppingCarMerch;


public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ShoppingListViewHolder>{

    //欄位 : Context物件、選項資料物件
    private Context context;
    public List<ShoppingCarMerch> shoppingList;

    //區分頁面
    public int pageState;

    //建構子 : 2個參數(Context型態、選項資料的型態)，用來初始化2欄位
    public ShoppingListAdapter(Context context, List<ShoppingCarMerch> shoppingList) {
        this.context = context;
        this.shoppingList = shoppingList;
    }

    @Override
    public int getItemCount() {
        return shoppingList == null ? 0 : shoppingList.size();
    }

    @NonNull
    @Override
    public ShoppingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //宣告ItemView，並載⼊選項容器元件的外觀
        View itemView = LayoutInflater.from(context).inflate(R.layout.shoplist_item_view, parent, false);
        return new ShoppingListViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ShoppingListViewHolder holder, int position) {

        ShoppingCarMerch shoppingCarMerch = shoppingList.get(position);

        holder.tv_shoplistSeller_05.setText(shoppingCarMerch.getSeller());
        holder.iv_shoplistMerch_05.setImageResource(shoppingCarMerch.getDrawableID());
        holder.tv_shoplistMerch_05.setText(shoppingCarMerch.getMerchName());
        holder.tv_shoplistMerchPrice_05.setText("$ " + shoppingCarMerch.getMerchPrice());
        holder.tv_ShopListMerchBuyNum_05.setText("購買數量 " + shoppingCarMerch.getMerchNumber());

        if(shoppingCarMerch.getSeller().equals("")){
            holder.cb_shoplistSeller_05.setVisibility(View.INVISIBLE);
        }

      /*
        holder.iv_Detergent_05.setOnClickListener(view ->{

            Bundle bundle = new Bundle();
            bundle.putInt("merchPoto", merchInfo.getDrawableID());
            bundle.putString("merchName", merchInfo.getMerchName());
            bundle.putInt("merchPrice", merchInfo.getMerchPrice());
            bundle.putInt("merchNumber", merchInfo.getMerchNumber());
            bundle.putString("merchBrand", merchInfo.getMerchBrand());
            bundle.putString("merchContent", merchInfo.getMerchContent());

            NavController navController = Navigation.findNavController(view);



                navController.navigate(R.id.action_market_homeFragment_to_merch_DesFragment, bundle);

                navController.navigate(R.id.action_searchFragment_to_merch_DesFragment2, bundle);


        });  */
    }


    //內部類別:⾃定義ViewHolder類別
    class ShoppingListViewHolder extends RecyclerView.ViewHolder{

        private CheckBox cb_shoplistSeller_05, cb_shoplistMerch_05;
        private TextView tv_shoplistMerch_05, tv_shoplistMerchPrice_05, tv_shoplistSeller_05, tv_ShopListMerchBuyNum_05;
        private ImageView iv_shoplistMerch_05;
        private Button bt_shoplistRemove_05;
        //private EditText etn_ShopListMerchBuyNum_05;

        public ShoppingListViewHolder(@NonNull View itemView) {
            super(itemView);

            cb_shoplistSeller_05 = itemView.findViewById(R.id.cb_shoplistSeller_05);
            cb_shoplistMerch_05 = itemView.findViewById(R.id.cb_shoplistMerch_05);
            tv_shoplistMerch_05 = itemView.findViewById(R.id.tv_shoplistMerch_05);
            tv_shoplistMerchPrice_05 = itemView.findViewById(R.id.tv_shoplistMerchPrice_05);
            iv_shoplistMerch_05 = itemView.findViewById(R.id.iv_shoplistMerch_05);
            bt_shoplistRemove_05 = itemView.findViewById(R.id.bt_shoplistRemove_05);
            tv_shoplistSeller_05 = itemView.findViewById(R.id.tv_shoplistSeller_05);
            tv_ShopListMerchBuyNum_05 = itemView.findViewById(R.id.tv_ShopListMerchBuyNum_05);
        }
    }


}
