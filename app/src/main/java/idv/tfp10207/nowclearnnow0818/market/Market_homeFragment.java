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


        rv_Detergent_05.setAdapter(new MarketHomeAdapter(activity, listDetergent));
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
                new MerchInfo(R.drawable.test_images1,"拖把1","$100"),
                new MerchInfo(R.drawable.test_images2, "家具清潔劑1", "$200"),
                new MerchInfo(R.drawable.test_images3, "拖把2", "$300"),
                new MerchInfo(R.drawable.test_images4, "多功能清潔劑", "$400"),
                new MerchInfo(R.drawable.test_images1,"拖把3","$100"),
                new MerchInfo(R.drawable.test_images2, "拖把4", "$200"),
                new MerchInfo(R.drawable.test_images3, "拖把5", "$300"),
                new MerchInfo(R.drawable.test_images4, "掃把", "$400"),
                new MerchInfo(R.drawable.test_images1,"家具清潔劑2","$600"),
                new MerchInfo(R.drawable.test_images2, "家具清潔劑3", "$300"),
                new MerchInfo(R.drawable.test_images3, "吸水抹布", "$300"),
                new MerchInfo(R.drawable.test_images4, "家具清潔劑4", "$500")
        );
    }


}

