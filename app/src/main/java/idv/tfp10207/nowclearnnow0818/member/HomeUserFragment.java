package idv.tfp10207.nowclearnnow0818.member;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import idv.tfp10207.nowclearnnow0818.R;


public class HomeUserFragment extends Fragment {
    private Activity activity;
    private ImageView iv_direction_01, iv_home_01;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);
        handleImageview();
    }

    private void findView(View view) {
        iv_home_01 = view.findViewById(R.id.iv_home_01);
        iv_direction_01 = view.findViewById(R.id.iv_direction_01);


    }

    private void handleImageview() {
        iv_direction_01.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.home_user_personalFragment);
        });
        iv_home_01.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.homePageFragment072);
        });
    }
}