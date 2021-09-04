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

import org.jetbrains.annotations.NotNull;

import idv.tfp10207.nowclearnnow0818.R;

public class QAFragment extends Fragment {
    private Activity activity;
    private TextView tv_join_01, tv_Store_01, tv_Cancel_01, tv_Plan_01;
    private ImageView iv_back_01;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        activity = getActivity();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_q_a, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);
        handleButton(view);
        handleTextView();
    }

    private void findView(View view) {
        iv_back_01 = view.findViewById(R.id.iv_back_01);
        tv_join_01 = view.findViewById(R.id.tv_join_01);
        tv_Plan_01 = view.findViewById(R.id.tv_Plan_01);
        tv_Store_01 = view.findViewById(R.id.tv_Store_01);
        tv_Cancel_01 = view.findViewById(R.id.tv_Cancel_01);
    }

    private void handleTextView() {
        tv_join_01.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.join_memberFragment);
        });
        tv_Plan_01.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.cleanPlanFragment);
        });
        tv_Store_01.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.marketRelatedFragment);
        });
        tv_Cancel_01.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.cancelFragment);
        });
    }


    private void handleButton(View view) {
        iv_back_01.setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack(R.id.QAFragment,true);
        });
    }
}