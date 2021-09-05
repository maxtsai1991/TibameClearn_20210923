package idv.tfp10207.nowclearnnow0818.home;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import idv.tfp10207.nowclearnnow0818.R;


public class DisinfectStepFragment07 extends Fragment {
    private ImageView iv_disinfect_return07;
    private TextView tv_No1_07,tv_No2_07,tv_No3_07,tv_No4_07;
    private ToggleButton tbNo1_07,tbNo2_07,tbNo3_07,tbNo4_07;
    private Resources resources;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_disinfect_step07, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        resources = getResources();
        findViews(view);
        handleReturn();
        handleToggleButton();
    }

    private void findViews(View view) {
        iv_disinfect_return07 = view.findViewById(R.id.iv_disinfect_return07);
        tbNo1_07 = view.findViewById(R.id.tbNo1_07);
        tbNo2_07 = view.findViewById(R.id.tbNo2_07);
        tbNo3_07 = view.findViewById(R.id.tbNo3_07);
        tbNo4_07 = view.findViewById(R.id.tbNo4_07);

        tv_No1_07 = view.findViewById(R.id.tv_No1_07);
        tv_No2_07 = view.findViewById(R.id.tv_No2_07);
        tv_No3_07 = view.findViewById(R.id.tv_No3_07);
        tv_No4_07 = view.findViewById(R.id.tv_No4_07);

    }

    private void handleReturn() {
        iv_disinfect_return07.setOnClickListener(v -> {
            Navigation.findNavController(v)
                    .navigate(R.id.homePageFragment072);
        });
    }

    private void handleToggleButton() {
        tv_No1_07.setVisibility(View.GONE);
        tbNo1_07.setOnCheckedChangeListener((tb_question, isok) -> {
            int viewId = isok ? R.color.dark : R.color.light;
            tv_No1_07.setBackgroundColor(resources.getColor(viewId));
            if(viewId == R.color.dark){
                tv_No1_07.setVisibility(View.VISIBLE);}
            else{
                tv_No1_07.setVisibility(View.GONE);
            }
        });

        tv_No2_07.setVisibility(View.GONE);
        tbNo2_07.setOnCheckedChangeListener((tb_question, isok) -> {
            int viewId = isok ? R.color.dark : R.color.light;
            tv_No2_07.setBackgroundColor(resources.getColor(viewId));
            if(viewId == R.color.dark){
                tv_No2_07.setVisibility(View.VISIBLE);}
            else{
                tv_No2_07.setVisibility(View.GONE);
            }
        });

        tv_No3_07.setVisibility(View.GONE);
        tbNo3_07.setOnCheckedChangeListener((tb_question, isok) -> {
            int viewId = isok ? R.color.dark : R.color.light;
            tv_No3_07.setBackgroundColor(resources.getColor(viewId));
            if(viewId == R.color.dark){
                tv_No3_07.setVisibility(View.VISIBLE);}
            else{
                tv_No3_07.setVisibility(View.GONE);
            }
        });

        tv_No4_07.setVisibility(View.GONE);
        tbNo4_07.setOnCheckedChangeListener((tb_question, isok) -> {
            int viewId = isok ? R.color.dark : R.color.light;
            tv_No4_07.setBackgroundColor(resources.getColor(viewId));
            if(viewId == R.color.dark){
                tv_No4_07.setVisibility(View.VISIBLE);}
            else{
                tv_No4_07.setVisibility(View.GONE);
            }
        });

    }

}