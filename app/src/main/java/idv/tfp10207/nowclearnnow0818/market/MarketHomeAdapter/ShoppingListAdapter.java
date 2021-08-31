package idv.tfp10207.nowclearnnow0818.market.MarketHomeAdapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import idv.tfp10207.nowclearnnow0818.R;
import idv.tfp10207.nowclearnnow0818.market.Merch_DesFragment;
import idv.tfp10207.nowclearnnow0818.market.ShoppingCarMerch;


public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ShoppingListViewHolder>{

    private static final String TAG = "TAG_Shopping_List_Ad";
    private static final String SHOPPINGCARLIST = "shoppingCarList";
    //private String memberID = "";


    //欄位 : Context物件、選項資料物件
    private Context context;
    public List<ShoppingCarMerch> shoppingList;
    //public boolean checkAllItem;

    //建構子 : 2個參數(Context型態、選項資料的型態)，用來初始化2欄位
    public ShoppingListAdapter(Context context, List<ShoppingCarMerch> shoppingList){//, boolean checkAllItem) {
        this.context = context;
        this.shoppingList = shoppingList;
        //this.checkAllItem = checkAllItem;

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

        if(shoppingCarMerch.getFirstMerchItem() == false){
            holder.cb_shoplistSeller_05.setVisibility(View.INVISIBLE);
            holder.tv_shoplistSeller_05.setVisibility(View.INVISIBLE);
        }
        else {
            holder.cb_shoplistSeller_05.setVisibility(View.VISIBLE);
            holder.tv_shoplistSeller_05.setVisibility(View.VISIBLE);
        }

//        if(shoppingCarMerch.getMerchCheckBox() == true){
//            holder.cb_shoplistMerch_05.setChecked(true);
//
//        }
//        if(shoppingCarMerch.getSellerCheckBox() == true){
//            holder.cb_shoplistSeller_05.setChecked(true);
//        }
//
//        else if(shoppingCarMerch.getMerchCheckStateAll()  == false){
//            holder.cb_shoplistSeller_05.setChecked(false);
//            holder.cb_shoplistMerch_05.setChecked(false);
//        }

        holder.cb_shoplistMerch_05.setChecked(shoppingCarMerch.getMerchCheckBox());
        holder.cb_shoplistSeller_05.setChecked(shoppingCarMerch.getSellerCheckBox());

//        if(shoppingCarMerch.getSellerCheckBox() == true){
//            holder.cb_shoplistSeller_05.setChecked(true);
//        }
//
//        if(shoppingCarMerch.getMerchCheckBox() == true){
//            holder.cb_shoplistMerch_05.setChecked(true);
//        }


//        shoppingCarMerch.setMerchCheckBox(holder.cb_shoplistMerch_05.isChecked());
//        shoppingCarMerch.setSellerCheckBox(holder.cb_shoplistSeller_05.isChecked());

        /*if(shoppingCarMerch.getSeller().contains("$")){
            holder.cb_shoplistSeller_05.setVisibility(View.INVISIBLE);
            holder.tv_shoplistSeller_05.setVisibility(View.INVISIBLE);
        }

        if(memberID.equals(shoppingCarMerch.getMemberId())){
            holder.cb_shoplistSeller_05.setVisibility(View.VISIBLE);
            holder.tv_shoplistSeller_05.setVisibility(View.VISIBLE);
            memberID = "";
        }*/


        //TODO 移除購物車上的商品
        holder.bt_shoplistRemove_05.setOnClickListener( view -> {

//            String removeMerchSeller = shoppingList.get(position).getSeller();
//
//            if( !(removeMerchSeller.contains("$")) ){
//                memberID = shoppingList.get(position).getMemberId();
//            }

            //if(shoppingList.get(position + 1).getSeller().substring(0,2).equals("$$")){
                //String merchName = shoppingList.get(position + 1).getSeller().substring(2);
                //shoppingList.get(position + 1).setSeller(merchName);
                //存檔
                //shoppingListAdapterSaveShoppingCarMerchAllFile(shoppingList);

            //}

            if( shoppingList.get(position).getFirstMerchItem() == true && shoppingList.size() > 1 ){

                if( shoppingList.size() - 1 !=  position ){
                    shoppingList.get(position + 1 ).setFirstMerchItem(true);

                    //存檔
                    shoppingListAdapterSaveShoppingCarMerchAllFile(shoppingList);
                }

            }


            //移除
            removeShoppingList(position);


        });

        //TODO 監聽購物車賣家的checkbox
        holder.cb_shoplistSeller_05.setOnCheckedChangeListener( (view, isCheck) -> {

            //TODO 控制外層的全選按鈕
//            if(!isCheck){
//                cb_shoplistSelectAllFm_05.
//            }

            if( shoppingCarMerch.getFirstMerchItem() == true ){

                //讀入
                shoppingList = shoppingListAdapterloadShoppingCarMerchAllFile();

                for(int i = 0 ; i < shoppingList.size() ; i++ ){
                    if(shoppingCarMerch.getMemberId().equals(shoppingList.get(i).getMemberId())){
                        shoppingList.get(i).setMerchCheckBox(isCheck);
                        shoppingList.get(i).setSellerCheckBox(isCheck);
                    }
                }
                notifyDataSetChanged();

                shoppingListAdapterSaveShoppingCarMerchAllFile(shoppingList);

            }
        });

        //TODO 監聽購物車商品的checkbox
        holder.cb_shoplistMerch_05.setOnCheckedChangeListener((view, isCheck) -> {
            //讀入
            shoppingList = shoppingListAdapterloadShoppingCarMerchAllFile();
            shoppingList.get(position).setMerchCheckBox(isCheck);
            shoppingListAdapterSaveShoppingCarMerchAllFile(shoppingList);
        });




        if( shoppingCarMerch == null){
            notifyDataSetChanged();
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
            cb_shoplistMerch_05 = itemView.findViewById(R.id.cb_shoplistMerch_05);

        }
    }


    /**
     * 讀檔
     */

    public List<ShoppingCarMerch> shoppingListAdapterloadShoppingCarMerchAllFile() {
        try (
                // 取得FileInputStream物件
                FileInputStream fis = context.openFileInput(SHOPPINGCARLIST);
                // Java I/O相關程式
                ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            return (List<ShoppingCarMerch>) ois.readObject();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return null;
    }


    /**
     * 存檔
     */
    private void shoppingListAdapterSaveShoppingCarMerchAllFile(final List<ShoppingCarMerch> shoppingCarMerch) {
        try (
                // 取得FileOutputStream物件
                FileOutputStream fos = context.openFileOutput(SHOPPINGCARLIST, Context.MODE_PRIVATE);
                // Java I/O相關程式
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(shoppingCarMerch);
            oos.flush();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }


    public void removeShoppingList(int position){

        //讀入
        shoppingList = shoppingListAdapterloadShoppingCarMerchAllFile();

        shoppingList.remove(position);

        notifyItemRemoved(position);

        notifyDataSetChanged();

        //存檔
        shoppingListAdapterSaveShoppingCarMerchAllFile(shoppingList);
    }


}
