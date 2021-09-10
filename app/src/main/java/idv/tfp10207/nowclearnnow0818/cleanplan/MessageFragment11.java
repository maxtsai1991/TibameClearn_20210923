package idv.tfp10207.nowclearnnow0818.cleanplan;

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

import idv.tfp10207.nowclearnnow0818.R;


public class MessageFragment11 extends Fragment {
    private Activity activity;
    private ImageView iv_messagereturn11, iv_message11, iv_message11_contant, iv_pic11, iv_send11;
    private TextView tv_message11_homewroktalk1,tv_message11_homewroktalk2, tv_message11_homewroktalk3, tv_message11_homewroktalk4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();
        return inflater.inflate(R.layout.fragment_message11, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        handleTakepictureButton(view); // 相機
        handleSendButton();
        handleReturn(view);
    }

    private void findViews(View view) {
        iv_messagereturn11 = view.findViewById(R.id.iv_messagereturn11);
        iv_pic11 = view.findViewById(R.id.iv_pic11);
        iv_message11_contant = view.findViewById(R.id.iv_message11_contant);
        iv_send11 = view.findViewById(R.id.iv_send11);
        tv_message11_homewroktalk3 =  view.findViewById(R.id.tv_message11_homewroktalk3);
        tv_message11_homewroktalk4 =  view.findViewById(R.id.tv_message11_homewroktalk4);
    }

    private void handleReturn(View view) {
        iv_messagereturn11.setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack(R.id.messageFragment11,true);


        });
    }

    // 相機
    private void handleTakepictureButton(View view) {
        ActivityResultLauncher<Void> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.TakePicturePreview(), bitmap -> iv_message11_contant.setImageBitmap(bitmap)
        );

        iv_pic11.setOnClickListener(v -> activityResultLauncher.launch(null));
    }
    private void handleSendButton() {
        tv_message11_homewroktalk3.setOnClickListener(v -> {
            tv_message11_homewroktalk3.setText("有事先出門,鑰匙放在警衛室,再麻煩你上樓整理!");
        });

        tv_message11_homewroktalk4.setOnClickListener(v -> {
            tv_message11_homewroktalk4.setText("已完成這次清潔,附圖參考");
        });

    }
}