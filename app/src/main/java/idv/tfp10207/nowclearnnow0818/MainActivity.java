package idv.tfp10207.nowclearnnow0818;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        handleBottomNavigationView();

    }

    private void findViews() {
        // 3.1 取得BottomNavigationView參考
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }

    private void handleBottomNavigationView() {
        // 3.2 取得NavHostFragment物件
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        // 3.3 取得NavController物件
        NavController navController = navHostFragment.getNavController();
        // 3.4 加入 導覽功能 至 頁籤導覽元件
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

    }
}