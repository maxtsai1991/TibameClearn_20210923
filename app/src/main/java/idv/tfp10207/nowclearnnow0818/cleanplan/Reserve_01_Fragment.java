package idv.tfp10207.nowclearnnow0818.cleanplan;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Arrays;
import java.util.List;

import idv.tfp10207.nowclearnnow0818.R;
import idv.tfp10207.nowclearnnow0818.cleanplan.CPorder.CleanplanReserve;
import idv.tfp10207.nowclearnnow0818.cleanplan.CPreserve.ReserveOrderStateAcceptFragment;
import idv.tfp10207.nowclearnnow0818.cleanplan.CPreserve.ReserveOrderStateApplyFragment;
import idv.tfp10207.nowclearnnow0818.cleanplan.CPreserve.ReserveOrderStateFinshFragment;

//1.table連動
//2.RecyclerView 資料更新
//3.預約buttom 連動 訂單狀態
//4.推播 button

public class Reserve_01_Fragment extends Fragment {
    private static final String TAG = "TAG_reserve_01_Fragment";
    private Activity activity;

    //1.table連動
    private TabLayout tabLayout_11;
    private ViewPager2 viewPager2_11;
    private int tabIndex;


    //Toolbar
    private ImageView righthomeicon;
    private ImageView leftarrowicon;
    private TextView tvprojectname;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (AppCompatActivity) getActivity();
//        ?
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reserve_01_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findview(view);
        handletoolbar(view);

        handleTabLayout();
        handleViewPager2();

    }

    private void findview(View view) {
        tabLayout_11 = view.findViewById(R.id.tabLayout_11);
        viewPager2_11 = view.findViewById(R.id.viewPager2_11);
    }

    private void handleTabLayout() {
        // 監聽當下選擇的頁籤，存值給 tabIndex，讓 ViewPager2 根據得到的值顯示對應的 fragment
        tabLayout_11.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText().equals("媒合中")) {
                    tabIndex = 0;
//                    Toast.makeText(getActivity(), "媒合中", Toast.LENGTH_SHORT).show();
                } else if (tab.getText().equals("已預約")) {
                    tabIndex = 1;
//                    Toast.makeText(getActivity(), "已預約", Toast.LENGTH_SHORT).show();
                } else if (tab.getText().equals("已完成")) {
                    tabIndex = 2;
//                    Toast.makeText(getActivity(), "已完成", Toast.LENGTH_SHORT).show();
//                } else {
//                    tabIndex = 3;
//                    Toast.makeText(getActivity(), "已取消", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void handleViewPager2() {
        // 將自訂義的 PageAdapter 藉由 viewPager2 載入
        viewPager2_11.setAdapter(new PageAdapter(activity));
        // 使用 TabLayoutMediator 將 tabLayout & viewPager2 結合
        TabLayoutMediator tab = new TabLayoutMediator(tabLayout_11, viewPager2_11, (tab1, position) -> {
            // 根據頁籤位置設定頁籤名稱
            switch (position) {
                case 0:
                    tab1.setText("媒合中");
                    break;
                case 1:
                    tab1.setText("已預約");
                    break;
                case 2:
                    tab1.setText("已完成");
                    break;

            }
        });
        // 將剛剛設定好的 TabLayoutMediator 依附在 viewPager2 上
        tab.attach();

    }


    public class PageAdapter extends FragmentStateAdapter {
        public PageAdapter(@NonNull Activity fragmentActivity) {
            super((FragmentActivity) fragmentActivity);
        }

        // 回傳有幾個頁籤
        @Override
        public int getItemCount() {
            return 3;
        }

        // 根據當前所在的頁籤呈現 fragment
        @Override
        public Fragment createFragment(int position) {
            // 獲取當前所在的頁籤位置藉由 setCurrentItem() 設定 position
            if (tabIndex == 0) {
                viewPager2_11.setCurrentItem(0);
            } else if (tabIndex == 1) {
                viewPager2_11.setCurrentItem(1);
            } else  {
                viewPager2_11.setCurrentItem(2);
            }
            // 藉由 position 的值判斷要載入哪個 fragment
            switch (position) {
                case 0:
                    return new ReserveOrderStateApplyFragment();
                case 1:
                    return new ReserveOrderStateAcceptFragment();
                default:
                    return new ReserveOrderStateFinshFragment();
//                case 2:
//                    return new OrderStateReceivedFragment();
//                default:
//                    return new OrderStateCanceledFragment();
            }
        }

    }



    //    客製Toolbar
    private void handletoolbar(View view) {
        //    抓按鍵
        leftarrowicon = view.findViewById(R.id.iv_arrow_back_11);
        righthomeicon = view.findViewById(R.id.iv_home_11);
        //    改標題
        tvprojectname = view.findViewById(R.id.tv_project_name_11);


        //    按鍵(需更改導覽路線的ID)

        leftarrowicon.setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack(R.id.reserve_01_Fragment, true);
        });


        righthomeicon.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack(R.id.homePageFragment072, false);
        });

        //    標題
        tvprojectname.setText("預約狀態");

    }
}