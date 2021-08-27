package idv.tfp10207.nowclearnnow0818.market;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import idv.tfp10207.nowclearnnow0818.market.MarketHomeAdapter.MarketHomeAdapter;
import idv.tfp10207.nowclearnnow0818.R;

public class Market_homeFragment extends Fragment {

    private Activity activity;
    private RecyclerView rv_Detergent_05; //, rv_Cleantool_05;
    private SearchView sv_Search_05;

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
    }


    private void findViews(View view) {
        rv_Detergent_05 = view.findViewById(R.id.rv_Detergent_05);
        //rv_Cleantool_05 = view.findViewById(R.id.rv_Cleantool_05);

        sv_Search_05 = view.findViewById(R.id.sv_Search_05);
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
                new MerchInfo(R.drawable.test_images1,"拖把1",100, 10,"好用牌", "超強力拖把，真好用", "王小明", "m111"),
                new MerchInfo(R.drawable.test_images2, "家具清潔劑1", 200, 20,"XXX-1牌", "真的很好用哦1", "張三豐", "m222"),
                new MerchInfo(R.drawable.test_images3, "拖把2", 300, 30,"XXX-2牌", "真的很好用哦2", "王小明", "m111"),
                new MerchInfo(R.drawable.test_images4, "多功能清潔劑", 400, 40,"XXX-3牌", "真的很好用哦3", "張無忌","m333"),
                new MerchInfo(R.drawable.test_images1,"拖把3",100, 50, "XXX-4牌", "真的很好用哦4", "五月天","m444"),
                new MerchInfo(R.drawable.test_images2, "拖把4", 200, 60,"XXX-5牌", "真的很好用哦5", "周傑倫","m666"),
                new MerchInfo(R.drawable.test_images3, "拖把5", 300, 70,"XXX-6牌", "真的很好用哦6", "張三豐","m222"),
                new MerchInfo(R.drawable.test_images4, "掃把", 400, 80,"XXX-7牌", "真的很好用哦7", "蕭敬騰","m555"),
                new MerchInfo(R.drawable.test_images1,"家具清潔劑2",600, 90,"XXX-8牌", "真的很好用哦8", "五月天","m444"),
                new MerchInfo(R.drawable.test_images2, "家具清潔劑3", 300, 100,"XXX-9牌", "真的很好用哦9", "陳阿姨","m777"),
                new MerchInfo(R.drawable.test_images3, "吸水抹布", 300, 110,"XXX-10牌", "真的很好用哦10", "鄭先生","m888"),
                new MerchInfo(R.drawable.test_images4, "家具清潔劑4", 500, 120,"XXX-11牌", "真的很好用哦11", "周傑倫","m666")
        );
    }


}

