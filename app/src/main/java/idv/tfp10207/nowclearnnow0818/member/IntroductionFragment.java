package idv.tfp10207.nowclearnnow0818.member;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import idv.tfp10207.nowclearnnow0818.R;

public class IntroductionFragment extends Fragment {
    private ImageView iv_clear12_01, iv_clear13_01, iv_back_01;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_introduction, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);
        handleButton(view);
        handleImageView();

    }

    private void findView(View view) {
        iv_clear12_01 = view.findViewById(R.id.iv_clear12_01);
        iv_clear13_01 = view.findViewById(R.id.iv_clear13_01);
        iv_back_01 = view.findViewById(R.id.iv_back_01);
    }

    private void handleImageView() {
        iv_clear12_01.setOnClickListener(view ->{
            Navigation.findNavController(view).navigate(R.id.goodatFragment);
        });

        iv_clear13_01.setOnClickListener(view ->{
            Navigation.findNavController(view).navigate(R.id.memberFragment);
        });
    }

    private void handleButton(View view) {
        iv_back_01.setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack(R.id.introductionFragment,true);
        });
    }

}