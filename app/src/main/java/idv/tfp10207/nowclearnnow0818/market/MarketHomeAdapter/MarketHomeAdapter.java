package idv.tfp10207.nowclearnnow0818.market.MarketHomeAdapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import idv.tfp10207.nowclearnnow0818.R;
import idv.tfp10207.nowclearnnow0818.market.*;


public class MarketHomeAdapter extends RecyclerView.Adapter<MarketHomeAdapter.MarketHomeViewHolder>{

    //欄位 : Context物件、選項資料物件
    private Context context;
    public List<MerchInfo> list;

    //區分頁面
    public int pageState;

    //建構子 : 2個參數(Context型態、選項資料的型態)，用來初始化2欄位
    public MarketHomeAdapter(Context context, List<MerchInfo> list, int  pageState) {
        this.context = context;
        this.list = list;
        this.pageState = pageState;
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @NonNull
    @Override
    public MarketHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //宣告ItemView，並載⼊選項容器元件的外觀
        View itemView = LayoutInflater.from(context).inflate(R.layout.detergent_item_view, parent, false);
        return new MarketHomeViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MarketHomeAdapter.MarketHomeViewHolder holder, int position) {

        MerchInfo merchInfo = list.get(position);
        holder.tv_DetergentName_05.setText(merchInfo.getMerchName());
        holder.tv_DetergentPrice_05.setText("$ " + merchInfo.getMerchPrice());
        holder.iv_Detergent_05.setImageResource(merchInfo.getDrawableID());

        //holder.tv_CleantoolName_05.setText(merchInfo.getMerchName());
        //holder.iv_Cleantool_05.setImageResource(merchInfo.getDrawableID());



        //執行點擊圖片進入商品頁面  //nick navigation_graph_05有修改  Merch_DesFragment有修改
        holder.iv_Detergent_05.setOnClickListener(view ->{

            Bundle bundle = new Bundle();
            bundle.putInt("merchPoto", merchInfo.getDrawableID());
            bundle.putString("merchName", merchInfo.getMerchName());
            bundle.putInt("merchPrice", merchInfo.getMerchPrice());
            bundle.putInt("merchNumber", merchInfo.getMerchNumber());
            bundle.putString("merchBrand", merchInfo.getMerchBrand());
            bundle.putString("merchContent", merchInfo.getMerchContent());
            bundle.putString("sellerName", merchInfo.getSeller());
            bundle.putString("memberID", merchInfo.getMemberId());

            NavController navController = Navigation.findNavController(view);


            //區分是從shoppingMall或是search 頁面過來
            if(pageState == 1){
                navController.navigate(R.id.action_market_homeFragment2_to_merch_DesFragment, bundle);
            }
            else{
                navController.navigate(R.id.action_searchFragment_to_merch_DesFragment, bundle);
            }


        });
    }


    //內部類別:⾃定義ViewHolder類別
    class MarketHomeViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv_Detergent_05; //, iv_Cleantool_05;
        private TextView tv_DetergentName_05, tv_DetergentPrice_05; //, tv_CleantoolName_05;

        public MarketHomeViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_Detergent_05 = itemView.findViewById(R.id.iv_Detergent_05);
            tv_DetergentName_05 = itemView.findViewById(R.id.tv_DetergentName_05);
            tv_DetergentPrice_05 = itemView.findViewById(R.id.tv_DetergentPrice_05);

            //iv_Cleantool_05 = itemView.findViewById(R.id.iv_Cleantool_05);
            //tv_CleantoolName_05 = itemView.findViewById(R.id.tv_CleantoolName_05);

        }
    }


}
