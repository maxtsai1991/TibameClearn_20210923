package idv.tfp10207.nowclearnnow0818.member;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import idv.tfp10207.nowclearnnow0818.R;

public class MarketRelatedFragment extends Fragment {
    private static final String TAG = "MarketRelatedFragment";
    private Activity activity;
    private ImageView iv_back_01;
    private TextView tv_marketrelated_01;
    private AssetManager assetManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        activity = getActivity();
        assetManager = activity.getAssets();
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_market_related, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);
        handleButton(view);
        handleTextView();
    }

    private void findView(View view) {
        tv_marketrelated_01 = view.findViewById(R.id.tv_marketrelated_01);
        iv_back_01 = view.findViewById(R.id.iv_back_01);
    }

    private void handleTextView() {
        try (
                // 開啟檔案，並取得InputStream物件
                InputStream is = assetManager.open("Market Related");
                // Java I/O相關程式
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr)
        ) {
            StringBuilder text = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line)
                        .append("\n");
            }
            tv_marketrelated_01.setText(text);
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }
    }

    private void handleButton(View view) {
        iv_back_01.setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack(R.id.marketRelatedFragment,true);
        });

    }
}