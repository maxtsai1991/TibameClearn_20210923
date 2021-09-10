package idv.tfp10207.nowclearnnow0818.cleanplan;

import android.app.Activity;
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

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import idv.tfp10207.nowclearnnow0818.R;
import idv.tfp10207.nowclearnnow0818.cleanplan.CPreserve.Cpreserveorder;
import idv.tfp10207.nowclearnnow0818.cleanplan.CPreserve.ReserveOrderStateAcceptFragment;

public class Homenotice_11_Fragment extends Fragment {

    private static final String TAG = "TAG_Homenotice_11";
    private Activity activity;
    private FirebaseFirestore db;

    private RecyclerView rv_homenotice_11;
    private List<Cpreserveorder> cpreserveorders;

    //Toolbar
    private ImageView righthomeicon;
    private ImageView leftarrowicon;
    private TextView tvprojectname;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (AppCompatActivity) getActivity();
        db = FirebaseFirestore.getInstance();
        cpreserveorders = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();
        return inflater.inflate(R.layout.fragment_homenotice_11_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        handletoolbar(view);
        showAllOrders();
    }

    private void findViews(View view) {
        rv_homenotice_11 = view.findViewById(R.id.rv_homenotice_11);

    }

    /** 取得所有訂單資訊後顯示 */
    private void showAllOrders() {
        db.collection("cleanplanorder")
                .whereEqualTo("cporderstate", "0")
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
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void showOrders() {
        Homenotice_11_Fragment.OrderAdapter orderAdapter = (Homenotice_11_Fragment.OrderAdapter) rv_homenotice_11.getAdapter();
        if (orderAdapter == null) {
            orderAdapter = new Homenotice_11_Fragment.OrderAdapter();
            rv_homenotice_11.setAdapter(orderAdapter);
        }
        rv_homenotice_11.setLayoutManager(new LinearLayoutManager(getActivity()));
        orderAdapter.setOrders(cpreserveorders);
    }


    private static class OrderAdapter extends RecyclerView.Adapter<Homenotice_11_Fragment.OrderAdapter.MyOrderViewHolder> {
        private List<Cpreserveorder> cpreserveorders;

        public OrderAdapter() {

        }

        public void setOrders(List<Cpreserveorder> cpreserveorders) {
            this.cpreserveorders = cpreserveorders;
        }

        private static class MyOrderViewHolder extends RecyclerView.ViewHolder {

            TextView tv_homenotice_ordertitle_11;
            TextView tv_homenotice_ordernumber_11;
            TextView tv_homenotice_name_11;
            TextView tv_homenotice_date_11;


            public MyOrderViewHolder(View itemView) {
                super(itemView);
                tv_homenotice_ordertitle_11 = itemView.findViewById(R.id.tv_homenotice_ordertitle_11);
                tv_homenotice_ordernumber_11 = itemView.findViewById(R.id.tv_homenotice_ordernumber_11);
                tv_homenotice_name_11 = itemView.findViewById(R.id.tv_homenotice_name_11);
                tv_homenotice_date_11 = itemView.findViewById(R.id.tv_homenotice_date_11);

            }
        }

        @Override
        public Homenotice_11_Fragment.OrderAdapter.MyOrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_itemcard_notice11, parent, false);
            return new Homenotice_11_Fragment.OrderAdapter.MyOrderViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(Homenotice_11_Fragment.OrderAdapter.MyOrderViewHolder holder, int position) {
            final Cpreserveorder cpreserveorder = cpreserveorders.get(position);
            holder.tv_homenotice_ordertitle_11.setText("訂單編號" );
            holder.tv_homenotice_ordernumber_11.setText(cpreserveorder.getCpordernumber());
            holder.tv_homenotice_name_11.setText("服務對象姓名： " + cpreserveorder.getServicename());
            holder.tv_homenotice_date_11.setText("預約日期： " + cpreserveorder.getOnedate());



            // 點選會開啟修改頁面
            holder.itemView.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putSerializable("cpreserveorders", cpreserveorder);
                Navigation.findNavController(v)
                        .navigate(R.id.reserve_01_Fragment, bundle);
//                reserveOrderContantFragment
            });
        }

        @Override
        public int getItemCount() {
            return cpreserveorders == null ? 0 : cpreserveorders.size();
        }
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
            Navigation.findNavController(view).popBackStack(R.id.homenotice_11_Fragment, true);
        });


        righthomeicon.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack(R.id.homePageFragment072, false);
        });

//    標題
        tvprojectname.setText("通知");

    }


}