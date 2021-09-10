package idv.tfp10207.nowclearnnow0818.cleanplan;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.text.SimpleDateFormat;

import idv.tfp10207.nowclearnnow0818.R;
import idv.tfp10207.nowclearnnow0818.cleanplan.CPorder.OrderConstants;


public class CpOrderSuccessFragment extends Fragment {
    private static final String TAG = "TAG_CpOrderSuccess";
    private Activity activity;

    private TextView tv_ordersuccess;
    private TextView tv_ordersuccess_number;
    private Button cp_ordersuccess_ordercontact;
    private Button cp_ordersuccess_reserve;

    //  7.資料放入OrderConstants帶到下一頁
    private OrderConstants orderconstants;

    //  8.資料傳進資料庫
    private FirebaseFirestore db;
    private FirebaseStorage storage;
    private File file;
    private Uri contentUri; // 拍照需要的Uri
    private Uri cropImageUri; // 截圖的Uri
    private boolean pictureTaken;

    //next button
    private Button bt_cp04_next;
    //Toolbar
    private ImageView righthomeicon;
    private ImageView leftarrowicon;
    private TextView tvprojectname;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();

        //取得Firebasestore物件
        db = FirebaseFirestore.getInstance();
        // 取得FirebaseStorage物件
        storage = FirebaseStorage.getInstance();
        // 7.實例化Bundle物件
        orderconstants = new OrderConstants();
        return inflater.inflate(R.layout.fragment_cp_order_success, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findview(view);

        handleSendorder(view);
        handleReserve(view);
    }

    private void handleReserve(View view) {
        cp_ordersuccess_reserve.setOnClickListener(v -> {
            Log.d("TAG_date=", orderconstants.getOnedate());
            Log.d("TAG_date=", orderconstants.getManydate());
            Log.d("TAG_gender=", orderconstants.getGender());
            Log.d("TAG_CpArea=", orderconstants.getCparea());
            Log.d("TAG_Cpservice=", orderconstants.getCpservice());

            Log.d("TAG_People=", orderconstants.getPeoplenumber());
            Log.d("TAG_Ping=", orderconstants.getPing());
            Log.d("TAG_Time=", orderconstants.getTime());
            Log.d("TAG_Picture", orderconstants.getPicture());
            Log.d("TAG_Remark", orderconstants.getRemark());

            Log.d("TAG_servicename=", orderconstants.getServicename());
            Log.d("TAG_servicephone=", orderconstants.getServicephone());
            Log.d("TAG_serviceemail=", orderconstants.getServiceemail());
            Log.d("TAG_serviceaddress=", orderconstants.getServiceaddress());
            Log.d("TAG_paymoney=", orderconstants.getCppaymoney());

            Log.d("TAG_CpPay=", orderconstants.getCppay());
            Log.d("TAG_Paypersonname=", orderconstants.getPayname());
            Log.d("TAG_Payphone=", orderconstants.getPayphone());
            Log.d("TAG_Payemail=", orderconstants.getPayemail());
            Log.d("TAG_Payaddress=", orderconstants.getPayaddress());

            Log.d("TAG_Cpordernumber=", orderconstants.getCpordernumber());

            orderconstants.setCporderstate("0");
            Log.d("TAG_Cporderstate=", orderconstants.getCporderstate());

            Navigation.findNavController(view).navigate(R.id.reserve_01_Fragment);

        });
    }

    private void findview(View view) {
        tv_ordersuccess = view.findViewById(R.id.tv_ordersuccess);
        tv_ordersuccess_number = view.findViewById(R.id.tv_ordersuccess_number);
        cp_ordersuccess_ordercontact = view.findViewById(R.id.cp_ordersuccess_ordercontact);
        cp_ordersuccess_reserve = view.findViewById(R.id.cp_ordersuccess_reserve);
    }


    private void handleSendorder(View view) {

        cp_ordersuccess_ordercontact.setOnClickListener(v -> {
            // 先取得插入document的ID
            final String id = db.collection("orderconstants").document().getId();
            orderconstants.setCpordernumber(id);

            String success = tv_ordersuccess.getText().toString().trim();
            if (success.length() <= 0) {
                Toast.makeText(activity, R.string.textNameIsInvalid,
                        Toast.LENGTH_SHORT).show();
                return;
            }

//      訂單狀態
            orderconstants.setCporderstate("0");

            Log.d("TAG_Cporderstate=", orderconstants.getCporderstate());


            // 如果有拍照，上傳至Firebase storage
            if (pictureTaken) {
                // document ID成為image path一部分，避免與其他圖檔名稱重複
                final String imagePath = getString(R.string.app_name) + "/images/" + orderconstants.getCpordernumber();
//                將檔案放到要的路徑
                storage.getReference().child(imagePath).putFile(cropImageUri)
                        .addOnCompleteListener(task -> {
//                            上傳成功
                            if (task.isSuccessful()) {
                                Log.d(TAG, getString(R.string.textImageUploadSuccess));
                                // 圖檔新增成功再將圖檔路徑存入spot物件所代表的document內
                                orderconstants.setPicture(imagePath);
//                              先把圖新增成功firestores拿到路徑  再新增圖檔的路徑(因為DB無法存圖檔)
//                                因為先新增firestores有可能圖檔沒有新增成功拿到的資料會錯誤
                                addOrReplace(orderconstants);
                            } else {
                                String message = task.getException() == null ?
                                        getString(R.string.textImageUploadFail) :
                                        task.getException().getMessage();
                                Log.e(TAG, "message: " + message);
//                                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                addOrReplace(orderconstants);
            }
            Navigation.findNavController(view).navigate(R.id.f_CleanPlan_04_Fragment);

        });

    }

    // 新增或修改Firestore上的景點
    private void addOrReplace(final OrderConstants orderconstants) {
        // 如果Firestore沒有該ID的Document就建立新的，已經有就更新內容
        db.collection("cleanplanorder").document(orderconstants.getCpordernumber()).set(orderconstants)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String message = getString(R.string.textInserted)
                                + " with ID: " + orderconstants.getCpordernumber();
                        Log.d(TAG, message);
//                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();

                    } else {
                        String message = task.getException() == null ?
                                getString(R.string.textInsertFail) :
                                task.getException().getMessage();
                        Log.e(TAG, "message: " + message);
//                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }



    //    客製Toolbar
    private void handletoolbar(View view) {
//    抓按鍵
        leftarrowicon = view.findViewById(R.id.iv_arrow_back_11);
        righthomeicon = view.findViewById(R.id.iv_home_11);
//    改標題
        tvprojectname = view.findViewById(R.id.tv_project_name_11);


//    按鍵(需更改導覽路線的ID)

        leftarrowicon.setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack(R.id.cpOrderSuccessFragment, true);
        });


        righthomeicon.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack(R.id.homePageFragment072, false);
        });

//    標題
        tvprojectname.setText("清潔計畫");

    }

}