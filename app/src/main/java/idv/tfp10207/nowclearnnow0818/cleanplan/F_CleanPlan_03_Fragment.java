package idv.tfp10207.nowclearnnow0818.cleanplan;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import idv.tfp10207.nowclearnnow0818.R;

//  0.前面訂單的資料  >>>bundle
//  1.選擇付款方式
//  2.信用卡資訊
//  3.付款人資訊  與 同會員資料
//  4.彈跳視窗: 確認媒合>>>訂單送出
//  5.訂單資訊回傳給資料庫
//  6.推撥給家事者

//

public class F_CleanPlan_03_Fragment extends Fragment {
    private static final String TAG = "TAG_F_CleanPlan_03_Fragment";
    private Activity activity;

    //next button
    private Button bt_cp03_next;
    //Toolbar
    private Button righthomeicon;
    private Button leftarrowicon;
    private TextView tvprojectname;

//  4.彈跳視窗: 確認媒合>>>訂單送出

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();
        return inflater.inflate(R.layout.fragment_f__clean_plan_03_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findview(view);
        handlenext(view);
        handletoolbar(view);

//        handlebutton(view);
    }

    private void findview(View view) {
        bt_cp03_next = view.findViewById(R.id.bt_CP03next_11);
    }


    //  next button
    private void handlenext(View view) {
        bt_cp03_next.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.f_CleanPlan_04_Fragment);

        });
    }



    //    客製Toolbar
    private void handletoolbar(View view) {
//    抓按鍵
        leftarrowicon = view.findViewById(R.id.iv_arrow_back);
        righthomeicon = view.findViewById(R.id.iv_home);
//    改標題
        tvprojectname = view.findViewById(R.id.tv_project_name);


//    按鍵(需更改導覽路線的ID)

        leftarrowicon.setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack(R.id.f_CleanPlan_03_Fragment, true);
        });


        righthomeicon.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.homePageFragment072);
        });

//    標題
        tvprojectname.setText("清潔計畫");

    }

}