package idv.tfp10207.nowclearnnow0818.market;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import idv.tfp10207.nowclearnnow0818.market.MarketHomeAdapter.MarketHomeAdapter;
import idv.tfp10207.nowclearnnow0818.R;


public class SearchFragment extends Fragment {

    private static final int PRICE_STATE_UP = 1;


    private AppCompatActivity activity;
    private RecyclerView rv_searchMerchFm_05;
    private SearchView  sv_Search_05;
    private TextView tv_Sellwell_05,tv_price_05;
    private ImageView iv_up_05, iv_down_05, iv_SearchToolbarBack_05, iv_SearchToolbarShop_05;
    private List<MerchInfo> searchMerchInfoList = new ArrayList<>();
    private List<MerchInfo> searchedMerchInfoList = new ArrayList<>();
    private int priceState = 0;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        activity = (AppCompatActivity) getActivity(); //取得Activity參考

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        handleSearchMerchRecyclerView();
        handleSearchMerchSearchView();
        handlePriceTextView();
        hdndleToolBarImageView();
    }

    private void findViews(View view) {
        rv_searchMerchFm_05 = view.findViewById(R.id.rv_searchMerchFm_05);
        sv_Search_05 = view.findViewById(R.id.sv_Search_05);
        tv_Sellwell_05 = view.findViewById(R.id.tv_Sellwell_05);
        tv_price_05 = view.findViewById(R.id.tv_price_05);
        iv_up_05 = view.findViewById(R.id.iv_up_05);
        iv_down_05 = view.findViewById(R.id.iv_down_05);
        iv_SearchToolbarBack_05 = view.findViewById(R.id.iv_SearchToolbarBack_05);
        iv_SearchToolbarShop_05 = view.findViewById(R.id.iv_SearchToolbarShop_05);
    }

    private void handleSearchMerchRecyclerView() {

        Bundle bundle = getArguments();

        if(bundle != null) {

            Market_homeFragment market_homeFragment = new Market_homeFragment();

            List<MerchInfo> list = market_homeFragment.getListMarketHome();

            for(MerchInfo merchInfo : list){
                String name = merchInfo.getMerchName();
                if(name.contains(bundle.getString("searchMerchName"))){
                    searchMerchInfoList.add(merchInfo);
                    searchedMerchInfoList.add(merchInfo);
                }
            }

            rv_searchMerchFm_05.setAdapter(new MarketHomeAdapter(activity, searchMerchInfoList));
            rv_searchMerchFm_05.setLayoutManager(new LinearLayoutManager(activity));

            MarketHomeAdapter adapter = (MarketHomeAdapter) rv_searchMerchFm_05.getAdapter();
            adapter.list = searchMerchInfoList;

            adapter.notifyDataSetChanged();
        }
    }

    private void handleSearchMerchSearchView() {
        sv_Search_05.setOnQueryTextListener( new SearchView.OnQueryTextListener(){

            private String searchText;

            @Override
            public boolean onQueryTextSubmit(String query) { //輸入enter才會更新

                priceState = 0;
                iv_up_05.setVisibility(View.VISIBLE);
                iv_down_05.setVisibility(View.VISIBLE);

                Market_homeFragment market_homeFragment = new Market_homeFragment();//原本全部的商回資訊
                List<MerchInfo> list = market_homeFragment.getListMarketHome();
                List<MerchInfo> searchNewlist = new ArrayList<>();

                //更新search頁
                MarketHomeAdapter adapter = (MarketHomeAdapter)rv_searchMerchFm_05.getAdapter();

                if(searchText.isEmpty()){
                    adapter.list = searchMerchInfoList;
                }
                else{
                    searchedMerchInfoList.clear();
                    for(MerchInfo merchInfo : list){
                        String name = merchInfo.getMerchName();
                        if(name.contains(searchText)){
                            searchNewlist.add(merchInfo);
                            searchedMerchInfoList.add(merchInfo);
                        }
                    }
                    adapter.list = searchNewlist;

                }

                adapter.notifyDataSetChanged();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                searchText = newText;

                return true;
            }
        });
    }

    private void handlePriceTextView() {  //點擊價格上下圖示變動
        tv_price_05.setOnClickListener( view -> {

            priceState = priceState + 1;
            MarketHomeAdapter adapter = (MarketHomeAdapter)rv_searchMerchFm_05.getAdapter();

            if(priceState == PRICE_STATE_UP){
                Collections.sort(searchedMerchInfoList, new Comparator<MerchInfo>() {
                    @Override
                    public int compare(MerchInfo o1, MerchInfo o2) {
                        return o2.getMerchPrice().compareTo(o1.getMerchPrice());
                    }
                });

                adapter.list = searchedMerchInfoList;
                iv_up_05.setVisibility(View.VISIBLE);
                iv_down_05.setVisibility(View.INVISIBLE);
            }
            else{
                Collections.sort(searchedMerchInfoList, new Comparator<MerchInfo>() {
                    @Override
                    public int compare(MerchInfo o1, MerchInfo o2) {
                        return o1.getMerchPrice().compareTo(o2.getMerchPrice());
                    }
                });

                adapter.list = searchedMerchInfoList;
                iv_down_05.setVisibility(View.VISIBLE);
                iv_up_05.setVisibility(View.INVISIBLE);

                priceState = 0;
            }

            adapter.notifyDataSetChanged();
        });

    }


    private void hdndleToolBarImageView() {

        iv_SearchToolbarBack_05.setOnClickListener(view -> {
            NavController navController = Navigation.findNavController(view);//返回
            navController.popBackStack();
        });



    }
}