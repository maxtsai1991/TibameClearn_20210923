package idv.tfp10207.nowclearnnow0818.home;
// test1
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import idv.tfp10207.nowclearnnow0818.R;

/**
 * 1. email 登入
 * 2. 帳號密碼不能為空值
 * 3. 測試帳號密碼: "abc@gmail.com" & "12345678" & "abi@gmail.com" & "123456" & "abl@gmail.com" & "654321"
 * 4. 王大明(需求者): "aaa@gmail.com" & "98745612" & 李心潔(家事者):  "le55@gmail.com" & "85209888"
 * 5. 黃永珠(家事者): "wong96@gmail.com" & "456852wa"
 */
public class LoginFragment extends Fragment {
    private final static String TAG = "TAG_MainFragment";
    private Activity activity;
    private FirebaseAuth auth;
    private EditText etAccount, etPassword;
    private Button btlogin, btRegisterHouseworker, btRegisterMember;
    private TextView tvLoginMessage;
    private ImageView iv_skip07,iv_member_login07,iv_homework_login07;
    private ViewFlipper vf_speak07;
    int images[] = {R.drawable.image_login_speak1, R.drawable.image_login_speak2, R.drawable.image_login_speak3};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login07, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view); //註冊元件
        handleLogin(); //登入處理
        handleRegisterHouseworker(); // 跳轉家事者畫面
        handleRegisterMember();// 跳轉註冊畫面
        handleViewFlipper(images);

    }

    private void findViews(View view) {
        etAccount = view.findViewById(R.id.et_login_Account); //帳號(email)
        etPassword = view.findViewById(R.id.et_login_Password); // 密碼
        btlogin = view.findViewById(R.id.bt_login);// (登入)
        btRegisterHouseworker = view.findViewById(R.id.bt_houseworker);// (註冊家事者)
        tvLoginMessage = view.findViewById(R.id.tv_Message); //檢查帳號密碼資訊
        iv_member_login07 = view.findViewById(R.id.iv_member_login07); // debug
        btRegisterMember = view.findViewById(R.id.bt_register);// 註冊
        iv_skip07 = view.findViewById(R.id.iv_skip07);
        iv_homework_login07 = view.findViewById(R.id.iv_homework_login07);
        vf_speak07 = view.findViewById(R.id.vf_speak07);
    }

    private void handleLogin() {//取得帳號密碼轉成字串
        btlogin.setOnClickListener(v -> {
            String email = etAccount.getText().toString();
            String password = etPassword.getText().toString();
            signIn(email, password);
        });
        iv_member_login07.setOnClickListener(v -> { // 快速貼上需求者假資料帳號密碼
            etAccount.setText("aaa@gmail.com");
            etPassword.setText("98745612");
        });

        iv_homework_login07.setOnClickListener(v -> { // 快速貼上家事者假資料帳號密碼
            etAccount.setText("wong96@gmail.com");
            etPassword.setText("456852wa");
        });

        iv_skip07.setOnClickListener(v -> { // 跳過登入
            Navigation.findNavController(v)
                    .navigate(R.id.homePageFragment072);
        });

    }

    private void signIn(String email, String password) {
        if (isEmailOrPasswordEmpty(email, password)) {// 判斷帳密不能為空Method
            return;
        }
        // 利用user輸入的email與password登入 // 主執行緒到這裡會開新執行緒
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, task -> { //背後開新執行緒
                    // 登入成功轉至下頁；失敗則顯示錯誤訊息
                    if (task.isSuccessful()) { //結果成功 下一頁
                        Navigation.findNavController(etAccount)
                                .navigate(R.id.homePageFragment072);
                    } else {
                        String message;
                        Exception exception = task.getException();
                        if (exception == null) {
                            message = "Sign in fail.";
                        } else {
                            String exceptionType;
                            // FirebaseAuthInvalidCredentialsException代表帳號驗證不成功，例如email格式不正確
                            if (exception instanceof FirebaseAuthInvalidCredentialsException) {
                                exceptionType = "Invalid Credential";
                            }
                            // FirebaseAuthInvalidUserException代表無此user，例如帳密錯誤
                            else if (exception instanceof FirebaseAuthInvalidUserException) {
                                exceptionType = "Invalid User";
                            } else {
                                exceptionType = exception.getClass().toString();
                            }
                            message = exceptionType + ": " + exception.getLocalizedMessage();
                        }
                        Log.e(TAG, message);
                        tvLoginMessage.setText(message);
                    }
                });
    }

    private boolean isEmailOrPasswordEmpty(String email, String password) {//帳號密碼是否正確(去掉空格)
        if (email.trim().isEmpty() || password.trim().isEmpty()) {
            tvLoginMessage.setText("帳號或密碼不可為空值");
            return true;
        } else {
            tvLoginMessage.setText("歡迎回來!");
            return false;
        }
    }

    private void handleRegisterMember() {
        btRegisterMember.setOnClickListener(v -> {
            Navigation.findNavController(v)
                    .navigate(R.id.registerMemberFragment);
            Toast.makeText(activity, "請填寫會員資料欄位", Toast.LENGTH_SHORT).show();
        });

    }

    private void handleRegisterHouseworker() {
        btRegisterHouseworker.setOnClickListener(v -> {//不用action方式 跳轉到註冊家事者頁面
            Navigation.findNavController(v)
                    .navigate(R.id.registerHouseworkInfoFragment);
            Toast.makeText(activity, "請填寫家事者資料欄位", Toast.LENGTH_SHORT).show();
        });
    }
    private void handleViewFlipper(int[] images) {
        for(int img :images) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(img);
            vf_speak07.addView(imageView);
        }
        vf_speak07.setFlipInterval(2000);
        vf_speak07.setAutoStart(true);
        //animation
        vf_speak07.setInAnimation(getContext(), android.R.anim.slide_in_left );
        vf_speak07.setOutAnimation(getContext(),android.R.anim.slide_out_right);

    }

}





