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
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import org.jetbrains.annotations.NotNull;

import idv.tfp10207.nowclearnnow0818.R;


public class RoomTourFragment extends Fragment {
    private Activity activity;
    private WebView webView;
    private ImageView iv_roomtour_return;
    private String roomtourUrl = "https://www.youtube.com/results?search_query=room+tour";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();
        return inflater.inflate(R.layout.fragment_room_tour, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        handleWebView();
        handleReturn();
    }

    private void findViews(View view) {
        webView = view.findViewById(R.id.webView);
        iv_roomtour_return = view.findViewById(R.id.iv_roomtour_return);
    }

    private void handleReturn() {
        iv_roomtour_return.setOnClickListener(v -> {
            Navigation.findNavController(v)
                    .navigate(R.id.homePageFragment072);
        });
    }

    private void handleWebView() {
        // 啟用JavaScript
        webView.getSettings().setJavaScriptEnabled(true);

        // 載入網址
        webView.loadUrl(roomtourUrl);

        // 使用自訂的WebView來處理請求
        /**
         * 若沒有覆寫shouldOverrideUrlLoading()，並回傳false，
         * 使用者點擊任何連結，會使用裝置內建的瀏覽器來開啟
         */
        webView.setWebViewClient(new WebViewClient() {

            /** API 24-使用  */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            /** API 24(+使用  */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });
    }


}