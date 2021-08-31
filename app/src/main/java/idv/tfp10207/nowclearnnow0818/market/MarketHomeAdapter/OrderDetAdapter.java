package idv.tfp10207.nowclearnnow0818.market.MarketHomeAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import idv.tfp10207.nowclearnnow0818.R;
import idv.tfp10207.nowclearnnow0818.market.ShoppingCarMerch;


public class OrderDetAdapter extends RecyclerView.Adapter<OrderDetAdapter.OrderDetViewHolder>{

    //private static final String TAG = "TAG_Shopping_List_Ad";
    //private static final String SHOPPINGCARLIST = "shoppingCarList";


    //欄位 : Context物件、選項資料物件
    private Context context;
    public List<ShoppingCarMerch> shoppingList;

    //建構子 : 2個參數(Context型態、選項資料的型態)，用來初始化2欄位
    public OrderDetAdapter(Context context, List<ShoppingCarMerch> shoppingList){
        this.context = context;
        this.shoppingList = shoppingList;
    }

    @Override
    public int getItemCount() {
        return shoppingList == null ? 0 : shoppingList.size();
    }

    @NonNull
    @Override
    public OrderDetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //宣告ItemView，並載⼊選項容器元件的外觀
        View itemView = LayoutInflater.from(context).inflate(R.layout.orderdet_item_view, parent, false);
        return new OrderDetViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull OrderDetViewHolder holder, int position) {

        ShoppingCarMerch shoppingCarMerch = shoppingList.get(position);

        holder.tv_OrderDetItviewMerchPrice_05.setText("$ " + shoppingCarMerch.getMerchPrice());
        holder.tv_OrderDetItviewSeller_05.setText(shoppingCarMerch.getSeller());
        holder.tv_OrderDetItviewNumber_05.setText("購買數量 " + shoppingCarMerch.getMerchNumber());
        holder.tv_OrderDetItviewMerch_05.setText(shoppingCarMerch.getMerchName());
        holder.iv_OrderDetItviewMerch_05.setImageResource(shoppingCarMerch.getDrawableID());






    }



    //內部類別:⾃定義ViewHolder類別
    class OrderDetViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_OrderDetItviewMerchPrice_05, tv_OrderDetItviewSeller_05, tv_OrderDetItviewNumber_05, tv_OrderDetItviewMerch_05;
        private ImageView iv_OrderDetItviewMerch_05;
        public OrderDetViewHolder(@NonNull View itemView) {

            super(itemView);

            tv_OrderDetItviewMerchPrice_05 = itemView.findViewById(R.id.tv_OrderDetItviewMerchPrice_05);
            tv_OrderDetItviewSeller_05 = itemView.findViewById(R.id.tv_OrderDetItviewSeller_05);
            tv_OrderDetItviewNumber_05 = itemView.findViewById(R.id.tv_OrderDetItviewNumber_05);
            tv_OrderDetItviewMerch_05 = itemView.findViewById(R.id.tv_OrderDetItviewMerch_05);
            iv_OrderDetItviewMerch_05 = itemView.findViewById(R.id.iv_OrderDetItviewMerch_05);



        }
    }


    /**
     * 讀檔
     */

//    public List<ShoppingCarMerch> shoppingListAdapterloadShoppingCarMerchAllFile() {
//        try (
//                // 取得FileInputStream物件
//                FileInputStream fis = context.openFileInput(SHOPPINGCARLIST);
//                // Java I/O相關程式
//                ObjectInputStream ois = new ObjectInputStream(fis)
//        ) {
//            return (List<ShoppingCarMerch>) ois.readObject();
//        } catch (Exception e) {
//            Log.e(TAG, e.toString());
//        }
//        return null;
//    }


    /**
     * 存檔
     */
//    private void shoppingListAdapterSaveShoppingCarMerchAllFile(final List<ShoppingCarMerch> shoppingCarMerch) {
//        try (
//                // 取得FileOutputStream物件
//                FileOutputStream fos = context.openFileOutput(SHOPPINGCARLIST, Context.MODE_PRIVATE);
//                // Java I/O相關程式
//                ObjectOutputStream oos = new ObjectOutputStream(fos)
//        ) {
//            oos.writeObject(shoppingCarMerch);
//            oos.flush();
//        } catch (Exception e) {
//            Log.e(TAG, e.toString());
//        }
//    }
//



}
