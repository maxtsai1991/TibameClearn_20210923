package idv.tfp10207.nowclearnnow0818.market;

import android.app.Activity;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

import idv.tfp10207.nowclearnnow0818.R;
import idv.tfp10207.nowclearnnow0818.market.MarketHomeAdapter.ShoppingListAdapter;


public class ShoppingListFragment extends Fragment {

    private Activity activity;
    RecyclerView rv_shoplistFm_05;
    ImageView iv_ShoppingCarListToolbarBack_05, iv_ShoppingCarListToolbarShopMall_05;
    CheckBox cb_shoplistSelectAllFm_05;
    Button bt_shoplistRemoveFm_05, bt_shoplistBuyFm_05;
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
        hdndleToolBarShoppingList();

    }


    private void findViews(View view) {
        rv_shoplistFm_05 = view.findViewById(R.id.rv_shoplistFm_05);
        iv_ShoppingCarListToolbarBack_05 = view.findViewById(R.id.iv_ShoppingCarListToolbarBack_05);
        iv_ShoppingCarListToolbarShopMall_05 = view.findViewById(R.id.iv_ShoppingCarListToolbarShopMall_05);

    }

    private void handleShoppingListRecyclerView() {

        shoppingList = (List<ShoppingCarMerch>) getArguments().getSerializable("shoppingCarMerch"); //shoppingList = (ShoppingCarMerch)getArguments().getSerializable("shoppingCarMerch");


        /*shoppingCar.setDrawableID(bundle.getInt("shoppingListPoto"));
        shoppingCar.setSeller(bundle.getString("shoppingListSeller"));
        shoppingCar.setMerchName(bundle.getString("shoppingListMerchName"));
        shoppingCar.setMerchPrice(bundle.getInt("shoppintListMerchPrice"));
        shoppingCar.setMerchNumber(bundle.getInt("shoppingListMerchNumber"));*/

        //TODO 增加之後的寫法
        //nick//shoppingList.add(shoppingCar);

        rv_shoplistFm_05.setAdapter(new ShoppingListAdapter(activity, shoppingList));//nick//rv_shoplistFm_05.setAdapter(new ShoppingListAdapter(activity, shoppingList));
        rv_shoplistFm_05.setLayoutManager(new LinearLayoutManager(activity));
    }

    private void hdndleToolBarShoppingList() {
        iv_ShoppingCarListToolbarBack_05.setOnClickListener(view -> {
            NavController navController = Navigation.findNavController(view);//返回
            navController.popBackStack();
        });

        iv_ShoppingCarListToolbarShopMall_05.setOnClickListener(view ->{
            //TODO 回到商城首頁
        });



    }
}