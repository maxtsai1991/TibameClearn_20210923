package idv.tfp10207.nowclearnnow0818;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import idv.tfp10207.nowclearnnow0818.market.GooglePay_05Fragment;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    //private GooglePay_05Fragment.OnPaymentCompleteListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        handleBottomNavigationView();

    }

    private void findViews() {

    }

    private void handleBottomNavigationView() {

    }

//    public void setListener(GooglePay_05Fragment.OnPaymentCompleteListener listener) {
//        this.listener = listener;
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        // .....
//        if (listener != null) {
//            listener.onPaymentComplete(requestCode, resultCode, data);
//        }
//    }
}