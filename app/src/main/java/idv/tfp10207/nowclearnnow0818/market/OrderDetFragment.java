package idv.tfp10207.nowclearnnow0818.market;

import android.app.Activity;
import android.content.Context;
import android.media.tv.TvView;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import idv.tfp10207.nowclearnnow0818.R;
import idv.tfp10207.nowclearnnow0818.market.MarketHomeAdapter.OrderDetAdapter;
import idv.tfp10207.nowclearnnow0818.market.MarketHomeAdapter.ShoppingListAdapter;

public class OrderDetFragment extends Fragment {

    private static final String TAG = "TAG_OrderDet_Fragment";
    private static final String SHOPPINGCARLIST = "shoppingCarList";

    private Activity activity;
    private TextView tv_OrderDetPayWay1Fm_05, tv_OrderDetMoney1Fm_05;
    private RecyclerView rv_OrderDetFm_05;
    private ImageView iv_OrderDetToolbarBack_05, iv_OrderDetToolbarShopMall_05;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity(); //取得Activity參考
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_det, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        handleOrderDetRecyclerView();
        handleOrderDetTextView();
        handleToolBar();
    }

    private void findViews(View view) {
        tv_OrderDetPayWay1Fm_05 = view.findViewById(R.id.tv_OrderDetPayWay1Fm_05);
        tv_OrderDetPayWay1Fm_05.setText("GooglePay");
        rv_OrderDetFm_05 = view.findViewById(R.id.rv_OrderDetFm_05);
        tv_OrderDetMoney1Fm_05 = view.findViewById(R.id.tv_OrderDetMoney1Fm_05);
        iv_OrderDetToolbarBack_05 = view.findViewById(R.id.iv_OrderDetToolbarBack_05);
        iv_OrderDetToolbarShopMall_05 = view.findViewById(R.id.iv_OrderDetToolbarShopMall_05);
    }

    private void handleOrderDetRecyclerView() {

        List<ShoppingCarMerch> shoppingCarMerch = orderDetLoadShoppingCarFile();

        List<ShoppingCarMerch> orderDet = new ArrayList<>();

        //將勾選的商品，帶到Adapter

        for (int i = 0 ; i < shoppingCarMerch.size() ; i++){
            int j = 0;
            if( shoppingCarMerch.get(i).getMerchCheckBox()  ){
               orderDet.add(j,shoppingCarMerch.get(i));
               j++;
            }
        }


        //調整位置
        Collections.sort(orderDet, new Comparator<ShoppingCarMerch>() {
            @Override
            public int compare(ShoppingCarMerch o1, ShoppingCarMerch o2) {
                return o1.getMemberId().compareTo(o2.getMemberId());
            }
        });

//        //存檔 TODO
//        orderDetSaveShoppingCarMerchAllFile(orderDet);

        rv_OrderDetFm_05.setAdapter(new OrderDetAdapter(activity, orderDet));
        rv_OrderDetFm_05.setLayoutManager(new LinearLayoutManager(activity));

    }


    private void handleOrderDetTextView() {

        List<ShoppingCarMerch> shoppingCarMerch = orderDetLoadShoppingCarFile();

        int orderPrice = 0;

        for(int i = 0 ; i < shoppingCarMerch.size() ; i++){
            if(shoppingCarMerch.get(i).getMerchCheckBox()) {
                orderPrice = orderPrice + ((shoppingCarMerch.get(i).getMerchPrice()) * (shoppingCarMerch.get(i).getMerchNumber()));
            }
        }

        tv_OrderDetMoney1Fm_05.setText("$ " + orderPrice);

    }

    private void handleToolBar() {

        iv_OrderDetToolbarBack_05.setOnClickListener(view -> {
            NavController navController = Navigation.findNavController(view);//返回
            navController.popBackStack();
        });

        //返品至購物首頁
        iv_OrderDetToolbarShopMall_05.setOnClickListener( view -> {

            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_orderDetFragment_to_market_homeFragment2);
        });

    }




    /**
     * 讀檔
     */

    public List<ShoppingCarMerch> orderDetLoadShoppingCarFile() {
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
    private void orderDetSaveShoppingCarMerchAllFile(final List<ShoppingCarMerch> shoppingCarMerch) {
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