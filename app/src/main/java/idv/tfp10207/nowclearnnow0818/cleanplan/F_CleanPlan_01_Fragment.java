package idv.tfp10207.nowclearnnow0818.cleanplan;

//
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import idv.tfp10207.nowclearnnow0818.R;
//  1.選擇日期
//  2.指定性別
//  3.選擇家事者(地區)
public class F_CleanPlan_01_Fragment extends Fragment {
    private static final String TAG = "TAG_F_CleanPlan_01_Fragment";
    private Activity activity;

//  1.選擇日期
//  2.指定性別
    private RadioGroup rg_CP01gender_11;
//  3.選擇家事者(地區)

    //next button
    private Button bt_cp01_next;
    //Toolbar
    private Button righthomeicon;
    private Button leftarrowicon;
    private TextView tvprojectname;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();
        return inflater.inflate(R.layout.fragment_f__clean_plan_01_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findview(view);
        handlenext(view);
        handletoolbar(view);

//      handlebutton(view);
        handleRadioGroup(view);
    }
    private void findview(View view) {
        bt_cp01_next = view.findViewById(R.id.bt_CP01next_11);
        rg_CP01gender_11 = view.findViewById(R.id.rg_CP01gender_11);
    }

//  2.指定性別
    private void handleRadioGroup(View view) {
        rg_CP01gender_11.setOnCheckedChangeListener((group, checkedId) -> {
            final RadioButton rb_CP01gender_11 = group.findViewById(checkedId);
 //            textView待修改為bundle
//            textView.setText("(RadioButton) " + rb_CP01gender_11.getText());
        });
    }


//  next button
    private void handlenext(View view) {
        bt_cp01_next.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.f_CleanPlan_02_Fragment);

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
            Navigation.findNavController(view).popBackStack(R.id.f_CleanPlan_01_Fragment, true);
        });


        righthomeicon.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.homePageFragment072);
        });

        //    標題
        tvprojectname.setText("清潔計畫");

    }

}