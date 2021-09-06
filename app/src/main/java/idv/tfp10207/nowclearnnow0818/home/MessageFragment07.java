package idv.tfp10207.nowclearnnow0818.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import org.jetbrains.annotations.NotNull;

import idv.tfp10207.nowclearnnow0818.R;


public class MessageFragment07 extends Fragment {
    private Activity activity;
    private ImageView iv_pic07,iv_viewpic07,iv_send07,iv_messagereturn07;
    private TextView tv_needer_talk07,tv_homewroktalk07;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();
        return inflater.inflate(R.layout.fragment_message07, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        handleTakepictureButton(view); // 相機
        handleSendButton();
        handleReturn();
    }

    private void findViews(View view) {
        iv_pic07 = view.findViewById(R.id.iv_pic07);
        iv_viewpic07 = view.findViewById(R.id.iv_viewpic07);
        tv_needer_talk07 = view.findViewById(R.id.tv_needer_talk07);
        tv_homewroktalk07 = view.findViewById(R.id.tv_homewroktalk07);
        iv_send07 =  view.findViewById(R.id.iv_pic07);
        iv_messagereturn07 =  view.findViewById(R.id.iv_messagereturn07);
    }

    private void handleReturn() {
        iv_messagereturn07.setOnClickListener(v -> {
                Navigation.findNavController(v)
                        .navigate(R.id.messageListFragment07);

        });
    }

    // 相機
    private void handleTakepictureButton(View view) {
        ActivityResultLauncher<Void> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.TakePicturePreview(), bitmap -> iv_viewpic07.setImageBitmap(bitmap)
        );

        iv_pic07.setOnClickListener(v -> activityResultLauncher.launch(null));
    }
    private void handleSendButton() {
        tv_needer_talk07.setOnClickListener(v -> {
            tv_needer_talk07.setText("有事先出門,鑰匙放在警衛室,再麻煩你上樓整理!");
        });

        tv_homewroktalk07.setOnClickListener(v -> {
            tv_homewroktalk07.setText("已完成這次清潔,附圖參考");
        });

    }
}