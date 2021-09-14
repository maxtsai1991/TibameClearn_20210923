package idv.tfp10207.nowclearnnow0818.cleanplan.CPreserve;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import idv.tfp10207.nowclearnnow0818.R;

public class ReserveOrderStateFinshFragment extends Fragment {
    private static final String TAG = "TAG_OrderStateFinsh";
    private AppCompatActivity activity;
    private FirebaseFirestore db;

    private FirebaseStorage storage;

    private RecyclerView rv_reserveorder_finsh_11;
    private List<Cpreserveorder> cpreserveorders;
//    資料隨時更動監聽器
    private ListenerRegistration registration;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (AppCompatActivity) getActivity();
        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        cpreserveorders = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reserve_order_state_finsh, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        showAllOrders();

        //        可以非常即時但卻會造成資料的傳輸量會變大   >>>>資料庫收費變高
        listenToSpots();
    }

    private void findViews(View view) {
        rv_reserveorder_finsh_11 = view.findViewById(R.id.rv_reserveorder_finsh_11);
    }

    /** 取得所有訂單資訊後顯示 */
    private void showAllOrders() {
        db.collection("cleanplanorder")
                .whereEqualTo("cporderstate", "011")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        // 先清除舊資料後再儲存新資料
                        if (!cpreserveorders.isEmpty()) {
                            cpreserveorders.clear();
                        }
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            cpreserveorders.add(document.toObject(Cpreserveorder.class));
                        }
                        // 顯示訂單
                        showOrders();
                    } else {
                        String message = task.getException() == null ?
                                "Not Found" :
                                task.getException().getMessage();
                        Log.e(TAG, "exception message: " + message);
//                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void showOrders() {
        ReserveOrderStateFinshFragment.OrderAdapter orderAdapter = (ReserveOrderStateFinshFragment.OrderAdapter) rv_reserveorder_finsh_11.getAdapter();
        if (orderAdapter == null) {
            orderAdapter = new ReserveOrderStateFinshFragment.OrderAdapter();
            rv_reserveorder_finsh_11.setAdapter(orderAdapter);
        }
        rv_reserveorder_finsh_11.setLayoutManager(new LinearLayoutManager(getActivity()));
        orderAdapter.setOrders(cpreserveorders);
    }


    private  class OrderAdapter extends RecyclerView.Adapter<ReserveOrderStateFinshFragment.OrderAdapter.MyOrderViewHolder> {
        private List<Cpreserveorder> cpreserveorders;

        public OrderAdapter() {

        }

        public void setOrders(List<Cpreserveorder> cpreserveorders) {
            this.cpreserveorders = cpreserveorders;
        }

        class MyOrderViewHolder extends RecyclerView.ViewHolder {
            ImageView iv_reserve_image_11;
            TextView tv_reserve_date_11;
            TextView tv_reserve_time_11;
            TextView tv_reserve_name_11;
            TextView tv_reserve_name_2;
            TextView tv_reserve_title_number;

            public MyOrderViewHolder(View itemView) {
                super(itemView);
                iv_reserve_image_11 = itemView.findViewById(R.id.iv_reserve_image_11);
                tv_reserve_date_11 = itemView.findViewById(R.id.tv_reserve_date_11);
                tv_reserve_time_11 = itemView.findViewById(R.id.tv_reserve_time_11);
                tv_reserve_name_11 = itemView.findViewById(R.id.tv_reserve_name_11);
                tv_reserve_name_2 = itemView.findViewById(R.id.tv_reserve_name_2);
                tv_reserve_title_number = itemView.findViewById(R.id.tv_reserve_title_number);
            }
        }

        @Override
        public ReserveOrderStateFinshFragment.OrderAdapter.MyOrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_itemcard_reserve, parent, false);
            return new ReserveOrderStateFinshFragment.OrderAdapter.MyOrderViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ReserveOrderStateFinshFragment.OrderAdapter.MyOrderViewHolder holder, int position) {
            final Cpreserveorder cpreserveorder = cpreserveorders.get(position);

            //            圖片路徑是空值，給一個沒有圖片的圖
            if (cpreserveorder.getPicture() == null || cpreserveorder.getPicture().equals("null")) {
                holder.iv_reserve_image_11.setImageResource(R.drawable.no_image);
            } else {
//                有圖片路徑就呼叫showImage
                showImage(holder.iv_reserve_image_11, cpreserveorder.getPicture());
            }


            holder.tv_reserve_date_11.setText("預約日期： "+cpreserveorder.getOnedate() );
            holder.tv_reserve_time_11.setText("預約時間： " + cpreserveorder.getTime()+" 9:00~12:00");
            holder.tv_reserve_name_11.setText("家事者姓名： " + cpreserveorder.getCpservice());
            holder.tv_reserve_name_2.setText("服務對象姓名："+cpreserveorder.getServicename());
            holder.tv_reserve_title_number.setText(cpreserveorder.getCpordernumber());


            // 點選會開啟修改頁面
            holder.itemView.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putSerializable("cpreserveorders", cpreserveorder);
                Navigation.findNavController(v)
                        .navigate(R.id.f_CleanPlan_07_Fragment, bundle);
//                reserveOrderContantFragment
            });
        }

        @Override
        public int getItemCount() {
            return cpreserveorders == null ? 0 : cpreserveorders.size();
        }
    }



    // 下載Firebase storage的照片並顯示在ImageView上
    private void showImage(final ImageView iv_reserve_image_11, final String path) {
        final int ONE_MEGABYTE = 1024 * 1024;
        StorageReference imageRef = storage.getReference().child(path);
//        對應的路徑下載相應的圖檔，傳進來
        imageRef.getBytes(ONE_MEGABYTE)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        byte[] bytes = task.getResult();
//                        轉成bitmap
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//                        顯示在imageView
                        iv_reserve_image_11.setImageBitmap(bitmap);
                    } else {
                        String message = task.getException() == null ?
                                getString(R.string.textImageDownloadFail) + ": " + path :
                                task.getException().getMessage() + ": " + path;
                        Log.e(TAG, message);
//                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(Throwable::printStackTrace);
    }


    /**
     * 監聽資料是否發生異動，有則同步更新。
     * 開啟2台模擬器，一台新增/修改/刪除；另一台畫面會同步更新
     * 但自己做資料異動也會觸發監聽器
     */
    private void listenToSpots() {
        if (registration == null) {
//            一旦監聽到有異動
            //                一旦有刪除立刻開始動作
            registration = db.collection("cleanplanorder").addSnapshotListener((snapshots, e) -> {
                Log.d(TAG, "event happened");
                if (e == null) {
                    List<Cpreserveorder> spots = new ArrayList<>();
                    if (snapshots != null) {
//                        snapshots所有資料
//                        snapshots.getDocumentChanges()有異動的資料
                        for (DocumentChange dc : snapshots.getDocumentChanges()) {
                            Cpreserveorder spot = dc.getDocument().toObject(Cpreserveorder.class);
                            switch (dc.getType()) {
                                case ADDED:
                                    Log.d(TAG, "Added spot: " + spot.getCpordernumber());
                                    break;
                                case MODIFIED:
                                    Log.d(TAG, "Modified spot: " + spot.getCpordernumber());
                                    break;
                                case REMOVED:
                                    Log.d(TAG, "Removed spot: " + spot.getCpordernumber());
                                    break;
                                default:
                                    break;
                            }
                        }
//                      有監聽異則重新取得
                        for (DocumentSnapshot document : snapshots.getDocuments()) {

//                            並放入實體變數spots
                            this.cpreserveorders = spots;
//                        因為加了監聽器全程監踢(ListenToSpoys())，所以List會自動依據更動去更新
//                            就不需要在像以前依要先刪除以前的資料
                            showAllOrders();
                        }
                    } else {
                        Log.e(TAG, e.getMessage(), e);
                    }
                }
            });
        }
    }

}