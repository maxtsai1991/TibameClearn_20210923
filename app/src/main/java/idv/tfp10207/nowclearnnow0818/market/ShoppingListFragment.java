package idv.tfp10207.nowclearnnow0818.market;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import idv.tfp10207.nowclearnnow0818.R;
import idv.tfp10207.nowclearnnow0818.market.MarketHomeAdapter.ShoppingListAdapter;


public class ShoppingListFragment extends Fragment {

    private static final String TAG = "TAG_Shopping_List_Fg";
    private static final String SHOPPINGCARLIST = "shoppingCarList";

    private Activity activity;
    private RecyclerView rv_shoplistFm_05;
    private ImageView iv_ShoppingCarToolbarBack_05, iv_ShoppingCarToolbarShopMall_05;
    private CheckBox cb_shoplistSelectAllFm_05, cb_shoplistSeller_05;
    private Button bt_shoplistRemoveFm_05, bt_shoplistBuyFm_05;
    private List<ShoppingCarMerch> shoppingList = new ArrayList<>();
    //private MerchInfo shoppingCar = new MerchInfo();
    //nick//private ShoppingCarMerch shoppingCar = new ShoppingCarMerch();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        activity = getActivity(); //取得Activity參考

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopping_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        handleShoppingListRecyclerView();
        handleToolBarShoppingList();
        handleButtonView();

    }

    private void findViews(View view) {
        rv_shoplistFm_05 = view.findViewById(R.id.rv_shoplistFm_05);
        iv_ShoppingCarToolbarBack_05 = view.findViewById(R.id.iv_ShoppingCarToolbarBack_05);
        iv_ShoppingCarToolbarShopMall_05 = view.findViewById(R.id.iv_ShoppingCarToolbarShopMall_05);
        bt_shoplistBuyFm_05 = view.findViewById(R.id.bt_shoplistBuyFm_05);
        cb_shoplistSelectAllFm_05 = view.findViewById(R.id.cb_shoplistSelectAllFm_05);
        cb_shoplistSeller_05 = view.findViewById(R.id.cb_shoplistSeller_05);
        bt_shoplistRemoveFm_05 = view.findViewById(R.id.bt_shoplistRemoveFm_05);

    }

    private void handleShoppingListRecyclerView() {

        ///確認/shoppingList = (List<ShoppingCarMerch>) getArguments().getSerializable("shoppingCarMerch"); //shoppingList = (ShoppingCarMerch)getArguments().getSerializable("shoppingCarMerch");
        shoppingList = shoppingListIsCheckloadShoppingCarMerchAllFile();

        /*shoppingCar.setDrawableID(bundle.getInt("shoppingListPoto"));
        shoppingCar.setSeller(bundle.getString("shoppingListSeller"));
        shoppingCar.setMerchName(bundle.getString("shoppingListMerchName"));
        shoppingCar.setMerchPrice(bundle.getInt("shoppintListMerchPrice"));
        shoppingCar.setMerchNumber(bundle.getInt("shoppingListMerchNumber"));*/

        //TODO 增加之後的寫法
        //nick//shoppingList.add(shoppingCar);

        rv_shoplistFm_05.setAdapter(new ShoppingListAdapter(activity, shoppingList));//, false));//nick//rv_shoplistFm_05.setAdapter(new ShoppingListAdapter(activity, shoppingList));
        rv_shoplistFm_05.setLayoutManager(new LinearLayoutManager(activity));


    }

    private void handleToolBarShoppingList() {

        iv_ShoppingCarToolbarBack_05.setOnClickListener(view -> {

            List<ShoppingCarMerch> shoppingList = shoppingListIsCheckloadShoppingCarMerchAllFile();

            if(shoppingList != null){
                for( int i = 0 ; i < shoppingList.size(); i++){
                    shoppingList.get(i).setMerchCheckBox(false);
                    shoppingList.get(i).setSellerCheckBox(false);
                }
            }

            shoppingListIsCheckSaveShoppingCarMerchAllFile(shoppingList);

            NavController navController = Navigation.findNavController(view);//返回
            navController.popBackStack();
        });

        iv_ShoppingCarToolbarShopMall_05.setOnClickListener(view ->{
            //TODO 回到商城首頁  要寫上面的四行




        });

    }

    private void handleButtonView() {

        bt_shoplistBuyFm_05.setOnClickListener( view -> {

            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_shoppingListFragment_to_orderDetFragment);

        });

        cb_shoplistSelectAllFm_05.setOnCheckedChangeListener( (buttonView, isChecked) -> {


            List<ShoppingCarMerch> shoppingList = shoppingListIsCheckloadShoppingCarMerchAllFile();


            if(isChecked == true){

                //rv_shoplistFm_05.setAdapter(new ShoppingListAdapter(activity, shoppingList, isChecked));
                //cb_shoplistSeller_05.setChecked(true);

                for( int i = 0 ; i < shoppingList.size(); i++){
                    shoppingList.get(i).setSellerCheckBox(true);
                    shoppingList.get(i).setMerchCheckBox(true);
                }

            }
            else if (isChecked == false){
                //rv_shoplistFm_05.setAdapter(new ShoppingListAdapter(activity, shoppingList, isChecked));

                for( int i = 0 ; i < shoppingList.size(); i++){
                    shoppingList.get(i).setSellerCheckBox(false);
                    shoppingList.get(i).setMerchCheckBox(false);
                }

            }

            //存檔
            shoppingListIsCheckSaveShoppingCarMerchAllFile(shoppingList);

            rv_shoplistFm_05.setAdapter(new ShoppingListAdapter(activity, shoppingList));

        });

        //全部移除
        bt_shoplistRemoveFm_05.setOnClickListener( view -> {

            if(cb_shoplistSelectAllFm_05.isChecked() == true){
                List<ShoppingCarMerch> shoppingList = shoppingListIsCheckloadShoppingCarMerchAllFile();
                shoppingList.clear();
                shoppingListIsCheckSaveShoppingCarMerchAllFile(shoppingList);
                cb_shoplistSelectAllFm_05.setChecked(false);
                rv_shoplistFm_05.setAdapter(new ShoppingListAdapter(activity, shoppingList));
            }
        });






    }




    /**
     * 讀檔
     */

    public List<ShoppingCarMerch> shoppingListIsCheckloadShoppingCarMerchAllFile() {
        try (
                // 取得FileInputStream物件
                FileInputStream fis = activity.openFileInput(SHOPPINGCARLIST);
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
    private void shoppingListIsCheckSaveShoppingCarMerchAllFile(final List<ShoppingCarMerch> shoppingCarMerch) {
        try (
                // 取得FileOutputStream物件
                FileOutputStream fos = activity.openFileOutput(SHOPPINGCARLIST, Context.MODE_PRIVATE);
                // Java I/O相關程式
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(shoppingCarMerch);
            oos.flush();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }




}