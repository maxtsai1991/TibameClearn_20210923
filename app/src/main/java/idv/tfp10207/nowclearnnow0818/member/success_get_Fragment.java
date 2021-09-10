package idv.tfp10207.nowclearnnow0818.member;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import idv.tfp10207.nowclearnnow0818.R;
import idv.tfp10207.nowclearnnow0818.market.ShoppingCarMerch;

public class success_get_Fragment extends Fragment {
    private static final String TAG = "success_get_Fragment";
    private static final String SHOPPINGCARLIST = "shoppingCarList";
    private Activity activity;
    private ImageView iv_back_01;
    private TextView tv_balance_01, tv_homecash_01, tv_allcash_01;
    private Button bt_pull_01;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        activity = getActivity();
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_success_get, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        handleButton(view);
        handleOrderDetTextView();
    }

    private void findViews(View view) {
        iv_back_01 = view.findViewById(R.id.iv_back_01);
        tv_balance_01 = view.findViewById(R.id.tv_balance_01);
        bt_pull_01 = view.findViewById(R.id.bt_pull_01);
        tv_homecash_01 = view.findViewById(R.id.tv_homecash_01);
        tv_allcash_01 = view.findViewById(R.id.tv_allcash_01);
    }


    private void handleButton(View view) {
        iv_back_01.setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack(R.id.success_get_Fragment, true);
        });
        bt_pull_01.setOnClickListener(v -> {
            new AlertDialog.Builder(getActivity())                    // 實例化AlertDialog.Builder物件
                    .setTitle("領取")                                 // 設定標題文字
                    //               用基本費用與規模去做計算
                    .setMessage("確定要領取?")             // 設定訊息文字
                    // 設定確定按鈕-顯示文字及監聽器
                    .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Navigation.findNavController(view).popBackStack(R.id.success_get_Fragment, true);
                            dialog.dismiss();
                        }
                    })
                    .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setCancelable(true)                               // 設定是否可點擊對話框以外之處離開對話框
                    .show();
        });
    }

    private void handleOrderDetTextView() {

        List<ShoppingCarMerch> shoppingCarMerch = orderDetLoadShoppingCarFile();

        int orderPrice = 0;

        for (int i = 0; i < shoppingCarMerch.size(); i++) {
            if (shoppingCarMerch.get(i).getMerchCheckBox()) {
                orderPrice = orderPrice + ((shoppingCarMerch.get(i).getMerchPrice()) * (shoppingCarMerch.get(i).getMerchNumber())) + 7450;
            }
        }

        tv_balance_01.setText("$ " + 25 );
        tv_homecash_01.setText("$ " + 7450);
        tv_allcash_01.setText("$ " + 7475);
    }

    /**
     * 讀檔
     */

    public List<ShoppingCarMerch> orderDetLoadShoppingCarFile() {
        try (
                // 取得FileInputStream物件
                FileInputStream fis = activity.openFileInput(SHOPPINGCARLIST);
                // Java I/O相關程式
                ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            return (List<ShoppingCarMerch>) ois.readObject();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return null;
    }


    /**
     * 存檔
     */
    private void orderDetSaveShoppingCarMerchAllFile(final List<ShoppingCarMerch> shoppingCarMerch) {
        try (
                // 取得FileOutputStream物件
                FileOutputStream fos = activity.openFileOutput(SHOPPINGCARLIST, Context.MODE_PRIVATE);
                // Java I/O相關程式
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(shoppingCarMerch);
            oos.flush();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }
}