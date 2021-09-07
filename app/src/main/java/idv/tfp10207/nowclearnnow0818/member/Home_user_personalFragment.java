package idv.tfp10207.nowclearnnow0818.member;

import android.app.Activity;
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
import android.widget.Toast;

import idv.tfp10207.nowclearnnow0818.R;


public class Home_user_personalFragment extends Fragment {
    private Activity activity;
    private ImageView iv_cl14_01, iv_cl15_01, iv_back_01;
    private TextView tv_st_01;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_user_personal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        handleImageView();
    }

    private void findViews(View view) {
        iv_cl14_01 = view.findViewById(R.id.iv_cl14_01);
        iv_cl15_01 = view.findViewById(R.id.iv_cl15_01);
        iv_back_01 = view.findViewById(R.id.iv_back_01);
        tv_st_01 = view.findViewById(R.id.tv_st_01);


    }

    private void handleImageView() {
        iv_cl14_01.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.passwordFragment);
        });
        iv_cl15_01.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.introductionFragment);
        });
        iv_back_01.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.homeUserFragment);
        });
        tv_st_01.setOnClickListener(v -> {
            Toast.makeText(activity, "變更成功", Toast.LENGTH_SHORT).show();
        });
    }
}