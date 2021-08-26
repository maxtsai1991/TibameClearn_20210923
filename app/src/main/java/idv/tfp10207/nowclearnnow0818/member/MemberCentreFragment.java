package idv.tfp10207.nowclearnnow0818.member;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import idv.tfp10207.nowclearnnow0818.R;

public class MemberCentreFragment extends Fragment {
    private Activity activity;
    private ImageView iv_clear1_01, iv_clear2_01, iv_clear3_01, iv_clear4_01, iv_clear5_01, iv_clear6_01;
    private ImageView iv_home_01, iv_question_01 ;
    private TextView tv_clear8_01;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();
        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.fragment_member_centre, container, false);
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        handleImageView();

    }

    private void findViews(View view) {
        iv_clear1_01 = view.findViewById(R.id.iv_clear1_01);
        iv_clear2_01 = view.findViewById(R.id.iv_clear2_01);
        iv_clear3_01 = view.findViewById(R.id.iv_clear3_01);
        iv_clear4_01 = view.findViewById(R.id.iv_clear4_01);
        iv_clear5_01 = view.findViewById(R.id.iv_clear5_01);
        iv_clear6_01 = view.findViewById(R.id.iv_clear6_01);
        iv_home_01 = view.findViewById(R.id.iv_home_01);
        tv_clear8_01 = view.findViewById(R.id.tv_clear8_01);
        iv_question_01 = view.findViewById(R.id.iv_question_01);

    }

    private void handleImageView() {
        iv_clear1_01.setOnClickListener(view ->{
            Navigation.findNavController(view).navigate(R.id.personalFragment);
        });
        iv_clear4_01.setOnClickListener(view ->{
            Navigation.findNavController(view).navigate(R.id.orderFragment);
        });
        iv_clear5_01.setOnClickListener(view ->{
            Navigation.findNavController(view).navigate(R.id.success_get_Fragment);
        });
        iv_clear6_01.setOnClickListener(view ->{
            Navigation.findNavController(view).navigate(R.id.evaluationFragment);
        });
        iv_question_01.setOnClickListener(view ->{
            Navigation.findNavController(view).navigate(R.id.QAFragment);
        });
        iv_home_01.setOnClickListener(view ->{
            Navigation.findNavController(view).navigate(R.id.homePageFragment072);
        });
        }

    }