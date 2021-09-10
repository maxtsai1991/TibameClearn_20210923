package idv.tfp10207.nowclearnnow0818.market;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import idv.tfp10207.nowclearnnow0818.market.MarketHomeAdapter.MarketHomeAdapter;
import idv.tfp10207.nowclearnnow0818.R;


public class Market_homeFragment extends Fragment {
    private static final String TAG = "TAG_Market_Home";
    private Activity activity;
    private RecyclerView rv_Detergent_05; //, rv_Cleantool_05;
    private SearchView sv_Search_05;
    private ImageView iv_MerchDesToolbarShopcar_05, iv_ShoppingMallHomeToolbarBack_05;
    //private Bundle bundleShoppingList = new Bundle();
    private static final String SHOPPINGCARLIST = "shoppingCarList";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        activity = getActivity(); //取得Activity參考

        return inflater.inflate(R.layout.fragment_market_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        handleMarkHomeRecyclerView();
        handleMarkHomeSearchView();
        handleMarkHomeToolBar();
    }


    private void findViews(View view) {
        rv_Detergent_05 = view.findViewById(R.id.rv_Detergent_05);
        //rv_Cleantool_05 = view.findViewById(R.id.rv_Cleantool_05);

        sv_Search_05 = view.findViewById(R.id.sv_Search_05);
        iv_MerchDesToolbarShopcar_05 = view.findViewById(R.id.iv_MerchDesToolbarShopcar_05);
        iv_ShoppingMallHomeToolbarBack_05 = view.findViewById(R.id.iv_ShoppingMallHomeToolbarBack_05);
    }

    private void handleMarkHomeRecyclerView() {

        List<MerchInfo> listDetergent = getListMarketHome();/*Arrays.asList(   //list 商品照片與資訊
                new MerchInfo(R.drawable.test_images1,"拖把1","$100"),
                new MerchInfo(R.drawable.test_images2, "家具清潔劑", "$200"),
                new MerchInfo(R.drawable.test_images3, "拖把2", "$300"),
                new MerchInfo(R.drawable.test_images4, "多功能清潔劑", "$400"),
                new MerchInfo(R.drawable.test_images1,"拖把3","$100"),
                new MerchInfo(R.drawable.test_images2, "拖把4", "$200"),
                new MerchInfo(R.drawable.test_images3, "拖把5", "$300"),
                new MerchInfo(R.drawable.test_images4, "掃把", "$400")
        );*/

        /*List<MerchInfo> listCleantool = Arrays.asList(   //list 商品照片與資訊
                new MerchInfo(R.drawable.test_basketball, "掃把"),
                new MerchInfo(R.drawable.test_basketball, "抹布"),
                new MerchInfo(R.drawable.test_basketball, "水桶"),
                new MerchInfo(R.drawable.test_basketball, "拖把")
        );*/


        rv_Detergent_05.setAdapter(new MarketHomeAdapter(activity, listDetergent, 1));

        //LinearLayoutManager linearLayoutManagerDetergent = new LinearLayoutManager(activity);
        //linearLayoutManagerDetergent.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_Detergent_05.setLayoutManager(new LinearLayoutManager(activity));//(linearLayoutManagerDetergent);

        /*rv_Cleantool_05.setAdapter(new MarketHomeAdapter(activity, listCleantool));
        LinearLayoutManager linearLayoutManagerCleantool = new LinearLayoutManager(activity);
        linearLayoutManagerCleantool.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_Detergent_05.setLayoutManager(linearLayoutManagerCleantool);*/

    }

    private void handleMarkHomeSearchView() {

        sv_Search_05.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            private String searchText;

            @Override
            public boolean onQueryTextSubmit(String query) {

                /*List<MerchInfo> list = getListMarketHome();//
                List<MerchInfo> newlist = new ArrayList<>();

                for(MerchInfo merchInfo : list){
                    String name = merchInfo.getMerchName();
                    if(name.contains(searchText)){
                        newlist.add(merchInfo);
                    }
                }*/


                //Intent intent = new Intent();
                //intent.setClass(activity, SearchFragment.class);
                //intent.putExtra("SearchMerchList", (Serializable) newlist);
                //startActivity(intent);

                Bundle bundle = new Bundle();
                bundle.putString("searchMerchName", searchText);

                NavController navController = Navigation.findNavController(rv_Detergent_05);
                navController.navigate(R.id.action_market_homeFragment2_to_searchFragment, bundle);




                return true;
            }


            @Override
            public boolean onQueryTextChange(String newText) {  //援尋出來的商品，使用bundle帶至下一頁

                searchText = newText;

                /*List<MerchInfo> list = getListMarketHome();
                List<MerchInfo> newlist = new ArrayList<>();

                for(MerchInfo merchInfo : list){
                    String name = merchInfo.getMerchName();
                    if(name.contains(newText)){
                        newlist.add(merchInfo);
                    }
                }


                Intent intent = new Intent();
                intent.setClass(activity, SearchFragment.class);
                intent.putExtra("SearchMerchList", (Serializable) newlist);
                startActivity(intent);*/

                //更新search頁
                //MarketHomeAdapter adapter = (MarketHomeAdapter)rv_Detergent_05.getAdapter();
                //adapter.list = newlist;

                //adapter.notifyDataSetChanged();

                //Bundle bundle = new Bundle();
                //bundle.putString("searchMerchName", newText);



                return true;
            }
        });
    }

    public List<MerchInfo> getListMarketHome(){
        return Arrays.asList(   //list 商品照片與資訊
                new MerchInfo(R.drawable.m2, "高樂氏廚房清潔劑", 10, 10,"高樂氏", "輕鬆瓦解頑強汙垢，多表面適用", "陳小明", "a002"),
                new MerchInfo(R.drawable.m3, "妙管家地板清潔劑", 15, 15,"妙管家", "分解油垢清爽不黏腳", "陳明漢","a006"),
                new MerchInfo(R.drawable.m1, "高樂氏去汙清潔劑", 5, 20,"高樂氏", "美國製造，原裝進口", "陳小明", "a002"),
                new MerchInfo(R.drawable.m5, "魔術靈玻璃清潔劑", 25, 22,"魔術靈", "極淨晶亮 不留擦痕", "黃永珠","a005"),
                new MerchInfo(R.drawable.m4, "妙管家窗戶清潔劑", 20, 12,"妙管家", "含特殊木質潤澤成份", "陳明漢","a006"),
                new MerchInfo(R.drawable.test_images4, "超力牌萬用清潔劑", 1, 6,"好用牌", "萬用去汙劑", "陳小明", "a002")
                /*new MerchInfo(R.drawable.test_images4, "多功能清潔劑", 4, 40,"XXX-3牌", "真的很好用哦3", "陳小明","a002"),
                new MerchInfo(R.drawable.test_images1,"拖把3",1, 50, "XXX-4牌", "真的很好用哦4", "張小慧","a003"),
                new MerchInfo(R.drawable.test_images2, "拖把4", 2, 60,"XXX-5牌", "真的很好用哦5", "李心潔","a004"),
                new MerchInfo(R.drawable.test_images3, "拖把5", 3, 70,"XXX-6牌", "真的很好用哦6", "張小慧","a003"),
                new MerchInfo(R.drawable.test_images1,"家具清潔劑2",6, 90,"XXX-8牌", "真的很好用哦8", "王大明","a001"),
                new MerchInfo(R.drawable.test_images3, "吸水抹布", 3, 110,"XXX-10牌", "真的很好用哦10", "周小美","a007"),
                new MerchInfo(R.drawable.test_images4, "家具清潔劑4", 5, 120,"XXX-11牌", "真的很好用哦11", "周小美", "a007")*/
        );
    }

    private void handleMarkHomeToolBar() {

        iv_MerchDesToolbarShopcar_05.setOnClickListener( view -> {

            //讀入
            List<ShoppingCarMerch> shoppingCarMerchLoad = makerHomeloadShoppingCarMerchAllFile();

            Bundle bundleShoppingList = new Bundle();
            bundleShoppingList.putSerializable("shoppingCarMerch", (Serializable)shoppingCarMerchLoad);

            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_market_homeFragment_to_shoppingListFragment, bundleShoppingList);


        });

        iv_ShoppingMallHomeToolbarBack_05.setOnClickListener( view -> {
            NavController navController = Navigation.findNavController(view);//返回
            navController.navigate(R.id.action_market_homeFragment2_to_homePageFragment072);
            //navController.popBackStack();
        });

    }



    /**
     * 讀檔
     */

    public List<ShoppingCarMerch> makerHomeloadShoppingCarMerchAllFile() {
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



}

