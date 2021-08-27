package idv.tfp10207.nowclearnnow0818.cleanplan;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import idv.tfp10207.nowclearnnow0818.R;

//評分 資料帶入

public class F_CleanplanFragment extends Fragment {
    private static final String TAG = "TAG_F_CleanplanFragment";
    private Activity activity;



//  常見問題與注意事項
    private Resources resources;

    private ConstraintLayout cl_question_notice;
    private ToggleButton tb_question_try;
    private TextView tv_question;
    private ConstraintLayout cl_question_try;
    private ToggleButton tb_notice;
    private ConstraintLayout cl_notice;

    private ImageView iv_CP00_11;
    //next button
    private Button bt_cp00_next;
    //Toolbar
    private ImageView righthomeicon;
    private ImageView leftarrowicon;
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

        resources = getResources();
        handlequestionnotice(view);

        handlenextbutton(view);
//        handlebutton();
        handletoolbar(view);
    }

    //next button
    private void handlenextbutton(View view) {
        bt_cp00_next.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.f_CleanPlan_01_Fragment);

        });
    }

    private void handlequestionnotice(View view) {
        cl_question_try.setVisibility(View.GONE);
        tb_question_try.setOnCheckedChangeListener((tb_question, isok) -> {
            int viewId = isok ? R.color.dark : R.color.light;
            cl_question_notice.setBackgroundColor(resources.getColor(viewId));
            if(viewId == R.color.dark){
                cl_question_try.setVisibility(View.VISIBLE);}
            else{
                cl_question_try.setVisibility(View.GONE);
            }

        });

        cl_notice.setVisibility(View.GONE);
        tb_notice.setOnCheckedChangeListener((tb_question, isok) -> {
            int viewId = isok ? R.color.dark : R.color.light;
            cl_question_notice.setBackgroundColor(resources.getColor(viewId));
            if(viewId == R.color.dark){
                cl_notice.setVisibility(View.VISIBLE);}
            else{
                cl_notice.setVisibility(View.GONE);
            }

        });
    }



    private void findview(View view) {
        bt_cp00_next = view.findViewById(R.id.bt_cp0_next);
        iv_CP00_11 = view.findViewById(R.id.iv_CP00_11);

//      常見問題與注意事項
        tb_question_try = view.findViewById(R.id.tb_question_try);
        cl_question_notice = view.findViewById(R.id.cl_question_notice);
        tv_question = view.findViewById(R.id.tv_question);
        cl_question_try = view.findViewById(R.id.cl_question_try);
        tb_notice = view.findViewById(R.id.tb_notice);
        cl_notice = view.findViewById(R.id.cl_notice);

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
            Navigation.findNavController(view).popBackStack(R.id.f_CleanplanFragment, true);
        });


        righthomeicon.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack(R.id.homePageFragment072, false);
        });

//    標題
        tvprojectname.setText("清潔計畫");

    }

}