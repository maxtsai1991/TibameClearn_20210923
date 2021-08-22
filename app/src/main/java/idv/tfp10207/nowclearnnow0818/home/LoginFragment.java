package idv.tfp10207.nowclearnnow0818.home;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import idv.tfp10207.nowclearnnow0818.R;

/**
 * 1. email 登入
 * 2. 帳號密碼不能為空值
 * 3. 測試帳號密碼: "abc@gmail.com" & "12345678"
 * 4.
 */
public class LoginFragment extends Fragment {
    private final static String TAG = "TAG_MainFragment";
    private Activity activity;
    private FirebaseAuth auth;
    private EditText etAccount, etPassword;
    private Button btlogin;
    private TextView tvLoginMessage;

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
        findViews(view);
        handleLogin();

    }

    private void findViews(View view) {
        etAccount = view.findViewById(R.id.et_login_Account);
        etPassword = view.findViewById(R.id.et_login_Password);
        btlogin = view.findViewById(R.id.bt_login);
        tvLoginMessage = view.findViewById(R.id.tv_Message);
    }

    private void handleLogin() {
        btlogin.setOnClickListener(v -> {
            String email = etAccount.getText().toString();
            String password = etPassword.getText().toString();
            signIn(email, password);
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
                                .navigate(R.id.action_loginF_to_homepageF);
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

    private boolean isEmailOrPasswordEmpty(String email, String password) {
        if (email.trim().isEmpty() || password.trim().isEmpty()) {
            tvLoginMessage.setText("帳號或密碼不可為空值");
            return true;
        } else {
            return false;
        }
    }


}





