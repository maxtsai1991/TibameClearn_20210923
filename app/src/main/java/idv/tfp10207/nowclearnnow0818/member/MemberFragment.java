package idv.tfp10207.nowclearnnow0818.member;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import idv.tfp10207.nowclearnnow0818.R;
import idv.tfp10207.nowclearnnow0818.member.bean.Condition;


public class MemberFragment extends Fragment {
    private Activity activity;
    private ImageView iv_back_01;
    private TextView tv_store5_01;
    private EditText et_condition_01;
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private Condition condition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        condition = new Condition();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_condition, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);
        handleToolbar(view);
        handleCondition(view);
    }

    private void findView(View view) {
        iv_back_01 = view.findViewById(R.id.iv_back_01);
        tv_store5_01 = view.findViewById(R.id.tv_store5_01);
        et_condition_01 = view.findViewById(R.id.et_condition_01);
    }

    private void handleCondition(View view) {
// 查詢指定集合
        db.collection("condition")
                .document(Objects.requireNonNull(auth.getCurrentUser()).getUid())
                // 獲取資料
                .get()
                // 設置網路傳輸監聽器
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        // 將獲取的資料存成自定義類別
                        DocumentSnapshot documentSnapshot = task.getResult();
                        condition = documentSnapshot.toObject(Condition.class);
                        assert condition != null;
                        et_condition_01.setText(condition.getCondition());
                    } else {
                        String message = task.getException() == null ?
                                "查無資料" :
                                task.getException().getMessage();
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    }

                });
        tv_store5_01.setOnClickListener(v -> {
            condition.setCondition(String.valueOf(et_condition_01.getText()));
            // 修改指定 ID 的文件
            db.collection("condition")
                    .document(auth.getCurrentUser().getUid()).set(condition)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            String message = "修改成功";
                            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                            Navigation.findNavController(view).navigate(R.id.introductionFragment);
                        } else {
                            String message = task.getException() == null ?
                                    "修改失敗" : task.getException().getMessage();
                            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }

    private void handleToolbar(View view) {
        iv_back_01.setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack(R.id.memberFragment, true);
        });

    }
}