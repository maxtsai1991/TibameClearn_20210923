package idv.tfp10207.nowclearnnow0818.cleanplan;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import idv.tfp10207.nowclearnnow0818.R;

public class F_CleanplanFragment extends Fragment {
    private static final String TAG = "TAG_F_CleanplanFragment";
    private Activity activity;

    //next button
    private Button bt_cp00_next;
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
        return inflater.inflate(R.layout.fragment_f__cleanplan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findview(view);
//        handlebutton();
//        handletoolbar(view);
    }

    private void findview(View view) {
        bt_cp00_next = view.findViewById(R.id.bt_cp0_next);

    }

//展開的方式處理常見問題與注意事項
//    需要使用COLOR常數檔
//
//    // ToggleButton與 setVisibility 聯合
//
//    private void handlesc(View view) {
//        cl_question_try.setVisibility(View.GONE);
//        tb_question_try.setOnCheckedChangeListener((tb_question, isok) -> {
//            int viewId = isok ? R.color.dark : R.color.light;
//            sv_question_try.setBackgroundColor(resources.getColor(viewId));
//            if(viewId == R.color.dark){
//                cl_question_try.setVisibility(View.VISIBLE);}
//            else{
//                cl_question_try.setVisibility(View.GONE);
//            }
//
//        });
//
//        cl_notice.setVisibility(View.GONE);
//        tb_notice.setOnCheckedChangeListener((tb_question, isok) -> {
//            int viewId = isok ? R.color.dark : R.color.light;
//            sv_question_try.setBackgroundColor(resources.getColor(viewId));
//            if(viewId == R.color.dark){
//                cl_notice.setVisibility(View.VISIBLE);}
//            else{
//                cl_notice.setVisibility(View.GONE);
//            }
//
//        });
//    }


    //next button
    private void handlebutton(View view) {
        bt_cp00_next.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.f_CleanPlan_01_Fragment);

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
            Navigation.findNavController(view).popBackStack(R.id.f_CleanplanFragment, true);
        });


        righthomeicon.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.homePageFragment072);
        });

//    標題
        tvprojectname.setText("清潔計畫");

    }

}