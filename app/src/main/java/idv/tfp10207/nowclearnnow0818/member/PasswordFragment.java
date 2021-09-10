package idv.tfp10207.nowclearnnow0818.member;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

import idv.tfp10207.nowclearnnow0818.R;
import idv.tfp10207.nowclearnnow0818.member.bean.Password;

public class PasswordFragment extends Fragment {
    private Activity activity;
    private ImageView iv_back_01;
    private EditText et_clear0_01, et_clear1_01, et_clear2_01;
    private TextView tv_clear15_01, tv_clear16_01;
    private Button bt_confirm_01;
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private Password password;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity =(AppCompatActivity) getActivity();
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        password = new Password();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);
        handleToolbar(view);
        handleImageView();
        handleButton(view);
    }

    private void findView(View view) {
        et_clear0_01 = view.findViewById(R.id.et_clear0_01);
        et_clear1_01 = view.findViewById(R.id.et_clear1_01);
        et_clear2_01 = view.findViewById(R.id.et_clear2_01);
        iv_back_01 = view.findViewById(R.id.iv_back_01);
        bt_confirm_01 = view.findViewById(R.id.bt_confirm_01);
        et_clear0_01 = view.findViewById(R.id.et_clear0_01);
        et_clear1_01 = view.findViewById(R.id.et_clear1_01);
        et_clear2_01 = view.findViewById(R.id.et_clear2_01);
        tv_clear15_01 = view.findViewById(R.id.tv_clear15_01);
        tv_clear16_01 = view.findViewById(R.id.tv_clear16_01);
    }

    private void handleImageView() {
        tv_clear15_01.setOnClickListener(v -> {
            et_clear0_01.setText("98745612");
            et_clear1_01.setText("5674321");
            et_clear2_01.setText("5674321");

        });
        tv_clear16_01.setOnClickListener(v -> {
            et_clear0_01.setText("5674321");
            et_clear1_01.setText("98745612");
            et_clear2_01.setText("98745612");

        });
    }

    private void handleButton(View view) {
        // 查詢指定集合
        db.collection("Password")
                .document(Objects.requireNonNull(auth.getCurrentUser()).getUid())
                // 獲取資料
                .get()
                // 設置網路傳輸監聽器
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        // 將獲取的資料存成自定義類別
                        DocumentSnapshot documentSnapshot = task.getResult();
                        password = documentSnapshot.toObject(Password.class);
                    } else {
                        String message = task.getException() == null ?
                                "查無資料" :
                                task.getException().getMessage();
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    }

                });
        bt_confirm_01.setOnClickListener(v -> {
        final String oldPassword = String.valueOf(et_clear0_01.getText());
        final String newPassword = String.valueOf(et_clear1_01.getText());
        final String confirmNewPassword = String.valueOf(et_clear2_01.getText());

        // 判斷舊密碼是否和 db 中的密碼相同
        if (!oldPassword.equals(password.getPassword())) {
            et_clear0_01.setError("舊密碼錯誤");
        }

        // 判斷新密碼不可和舊密碼相同
        if (oldPassword.equals(newPassword)) {
            et_clear0_01.setError("新密碼不可和舊密碼相同");
            et_clear1_01.setError("新密碼不可和舊密碼相同");
        }

        // 判斷新密碼和確認新密碼不相同
        if (!newPassword.equals(confirmNewPassword)) {
            et_clear2_01.setError("新密碼和確認新密碼不相同");
        }

        // 判斷舊密碼不可為空
        if (oldPassword.isEmpty()) {
            et_clear0_01.setError("請輸入舊密碼");
        }

        // 判斷新密碼不可為空
        if (newPassword.isEmpty()) {
            et_clear1_01.setError("請輸入新密碼");
        }

        // 判斷確認新密碼不可為空
        if (confirmNewPassword.isEmpty()) {
            et_clear2_01.setError("請輸入確認新密碼");
        }

        if (oldPassword.equals(newPassword)) {
            et_clear0_01.setError("舊密碼不可和新密碼相同");
            et_clear1_01.setError("舊密碼不可和新密碼相同");
        }

        if (!newPassword.equals(confirmNewPassword)) {
            et_clear1_01.setError("新密碼和確認新密碼不相同");
            et_clear2_01.setError("新密碼和確認新密碼不相同");
        } else {
            auth.getCurrentUser().updatePassword(newPassword);
            password.setPassword(newPassword);
            // 修改指定 ID 的文件
            db.collection("Password")
                    .document(auth.getCurrentUser().getUid()).set(password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            String message = "修改成功";
                            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                            // 跳轉至修改成功頁面
                            Navigation.findNavController(view).navigate(R.id.personalFragment);
//                            NavController navController = Navigation.findNavController(view);
//                            navController.navigate(R.id.);
                        } else {
                            String message = task.getException() == null ?
                                    "修改失敗" : task.getException().getMessage();
                            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    });
    }

    private void handleToolbar(View view) {
        iv_back_01.setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack(R.id.passwordFragment,true);
        });
    }
}