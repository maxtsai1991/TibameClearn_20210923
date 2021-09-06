package idv.tfp10207.nowclearnnow0818.market;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import java.util.List;

import idv.tfp10207.nowclearnnow0818.R;
import idv.tfp10207.nowclearnnow0818.market.MarketHomeAdapter.MarketHomeAdapter;
import idv.tfp10207.nowclearnnow0818.market.MarketHomeAdapter.OrderCompListAdapter;

public class OrderCompFragment extends Fragment {

    private static final String TAG = "TAG_OrderComp";
    private static final String SHOPPINGCARLIST = "shoppingCarList";
    private static final String ORDERNUMBER = "orderNumber";
    private Activity activity;
    private TextView tv_OrderComp_05, tv_OrderCompAddress_05, tv_OrderCompAddress1_05, tv_OrderCompPay_05, tv_OrderCompOrderNum1_05;
    private RecyclerView rv_ordercompFm_05;
    private Button bt_ordercompFm_05;
    private ImageView iv_OrderCompToolbarShopMall_05;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity(); //取得Activity參考

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_comp, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        handleOrderCompRecyclerView();
        handleButton();
        handleToolBar();
    }


    private void findViews(View view) {
        tv_OrderComp_05 = view.findViewById(R.id.tv_OrderComp_05);
        rv_ordercompFm_05 = view.findViewById(R.id.rv_ordercompFm_05);
        tv_OrderCompAddress_05 = view.findViewById(R.id.tv_OrderCompAddress_05);
        tv_OrderCompAddress1_05 =view.findViewById(R.id.tv_OrderCompAddress1_05);
        tv_OrderCompPay_05 = view.findViewById(R.id.tv_OrderCompPay_05);
        tv_OrderCompOrderNum1_05 = view.findViewById(R.id.tv_OrderCompOrderNum1_05);
        bt_ordercompFm_05 = view.findViewById(R.id.bt_ordercompFm_05);
        iv_OrderCompToolbarShopMall_05 = view.findViewById(R.id.iv_OrderCompToolbarShopMall_05);

        tv_OrderComp_05.setText("訂單完成");
        tv_OrderCompAddress_05.setText("運送地址");
        GooglePayMainActivity googlePayMainActivity = new GooglePayMainActivity();
        tv_OrderCompAddress1_05.setText(googlePayMainActivity.getMember().getAddress());
        tv_OrderCompPay_05.setText("付款方式      Google Pay");


        //todo 讀出訂單編號
        List<OrderNumber> orderNumberCompLoad = orderNumberCompLoadFile();

        if(orderNumberCompLoad != null){
            tv_OrderCompOrderNum1_05.setText( orderNumberCompLoad.get(orderNumberCompLoad.size() -1 ).getOrderNumber());
        }

    }


    private void handleOrderCompRecyclerView() {

        //商品讀入
        List<ShoppingCarMerch> shoppingCarFile = orderComploadMerchFile();
        List<ShoppingCarMerch> orderCompMerchFile = new ArrayList<>();

        for(int i = 0 ; i < shoppingCarFile.size() ; i++){
                        if(shoppingCarFile.get(i).getMerchCheckBox()){
                            orderCompMerchFile.add(shoppingCarFile.get(i));
                        }
                    }

        for(int i = 0 ; i < shoppingCarFile.size() ; i++){
            if(shoppingCarFile.get(i).getMerchCheckBox()){
                shoppingCarFile.remove(i);
            }
        }


        //商品存檔
        orderCompSaveMerchFile(shoppingCarFile);

        rv_ordercompFm_05.setAdapter(new OrderCompListAdapter(activity, orderCompMerchFile));
        rv_ordercompFm_05.setLayoutManager(new LinearLayoutManager(activity));
    }


    private void handleButton() {
        bt_ordercompFm_05.setOnClickListener( view -> {

            NavController navController = Navigation.findNavController(bt_ordercompFm_05);
            navController.navigate(R.id.action_orderCompFragment_to_market_homeFragment2);

        });

    }

    private void handleToolBar() {

        iv_OrderCompToolbarShopMall_05.setOnClickListener(view -> {
            NavController navController = Navigation.findNavController(iv_OrderCompToolbarShopMall_05);
            navController.navigate(R.id.action_orderCompFragment_to_market_homeFragment2);
        } );

    }




    /**
     * 讀檔訂單編號
     */
    private List<OrderNumber> orderNumberCompLoadFile() {
        try (
                // 取得FileInputStream物件
                FileInputStream fis = activity.openFileInput(ORDERNUMBER);
                // Java I/O相關程式
                ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            return (List<OrderNumber>) ois.readObject();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return null;
    }

    /**
     * 存檔訂單編號
     */
    private void orderNumberCompSaveFile(final List<OrderNumber> orderNumber) {
        try (
                // 取得FileOutputStream物件
                FileOutputStream fos = activity.openFileOutput(ORDERNUMBER, Context.MODE_PRIVATE);
                // Java I/O相關程式
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(orderNumber);
            oos.flush();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }


    /**
     * 商品讀檔
     */

    public List<ShoppingCarMerch> orderComploadMerchFile() {
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
     * 商品存檔
     */
    private void orderCompSaveMerchFile(final List<ShoppingCarMerch> shoppingCarMerch) {
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