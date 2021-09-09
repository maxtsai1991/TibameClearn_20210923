package idv.tfp10207.nowclearnnow0818.home;

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
import android.widget.ViewFlipper;

import org.jetbrains.annotations.NotNull;

import idv.tfp10207.nowclearnnow0818.R;


public class PreviewFragment extends Fragment {
    private Activity activity;
    private ImageView iv_return_homepage07;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();
        return inflater.inflate(R.layout.fragment_preview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        handleReturn();
    }

    private void findViews(View view) {
        iv_return_homepage07 = view.findViewById(R.id.iv_return_homepage07);
    }

    private void handleReturn() {
        iv_return_homepage07.setOnClickListener(v -> {
            Navigation.findNavController(v)
                    .navigate(R.id.homePageFragment072);
        });
    }


}