package idv.tfp10207.nowclearnnow0818.home;

import android.app.Activity;
import android.media.tv.TvContentRating;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

import idv.tfp10207.nowclearnnow0818.R;
import idv.tfp10207.nowclearnnow0818.cleanplan.CPreserve.Cpreserveorder;

// TEST 
public class HomePageFragment07 extends Fragment {
    private static final String TAG = "TAG_HomePageFragment07";
    private Activity activity;

    private ImageView iv_reservepage;
    private ImageView iv_marketpage;
    private ImageView iv_memberpage;
    private ImageView iv_homepage_announcement0701,iv_message07,iv_homepage_clear_service0701,iv_premiumselectionlist0701,iv_roomtour0702,iv_bellnotify07,iv_homepage_logo_bell_07,iv_bell_add_one07,iv_preview07;
    private TextView tv_to_homeworkerMember07;

//  首頁訂單通知紅點
    private FirebaseFirestore db;
    private RecyclerView rc_homenotice;
    private List<Cpreserveorder> cpreserveorders;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();
        //  首頁訂單通知紅點
        db = FirebaseFirestore.getInstance();
        cpreserveorders = new ArrayList<>();
        return inflater.inflate(R.layout.fragment_home_page07, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        handleButtons(view);
        //  首頁訂單通知紅點
        showAllOrders();
    }

    private void findViews(View view) {

        iv_reservepage = view.findViewById(R.id.iv_reservepage);
        iv_marketpage = view.findViewById(R.id.iv_marketpage);
        iv_memberpage = view.findViewById(R.id.iv_memberpage);
        iv_homepage_announcement0701 = view.findViewById(R.id.iv_homepage_announcement0701);
        iv_message07 = view.findViewById(R.id.iv_message07);
        iv_homepage_clear_service0701 = view.findViewById(R.id.iv_homepage_clear_service0701);
        iv_premiumselectionlist0701 = view.findViewById(R.id.iv_premiumselectionlist0701);
        iv_roomtour0702 = view.findViewById(R.id.iv_roomtour0702);
        iv_bellnotify07 = view.findViewById(R.id.iv_bellnotify07);
        tv_to_homeworkerMember07 = view.findViewById(R.id.tv_to_homeworkerMember07);
        iv_homepage_logo_bell_07 = view.findViewById(R.id.iv_homepage_logo_bell_07);
        iv_bell_add_one07 = view.findViewById(R.id.iv_bell_add_one07);
        iv_preview07 = view.findViewById(R.id.iv_preview07);
        //  首頁訂單通知紅點
        rc_homenotice = view.findViewById(R.id.rc_homenotice);

    }


 // test
    private void handleButtons(View view) {

        iv_marketpage.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_homePageFragment072_to_market_homeFragment2);
        });

        iv_reservepage.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_homePageFragment072_to_reserve_01_Fragment);
        });

        iv_memberpage.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_homePageFragment072_to_memberCentreFragment);
        });

        iv_homepage_announcement0701.setOnClickListener(v -> {
            Navigation.findNavController(v)
                    .navigate(R.id.placardFragment07);
        });

        iv_message07.setOnClickListener(v -> {
            Navigation.findNavController(v)
                    .navigate(R.id.messageListFragment07);
        });

        iv_homepage_clear_service0701.setOnClickListener(v -> {
            Navigation.findNavController(v)
                    .navigate(R.id.f_CleanplanFragment);
        });

        iv_premiumselectionlist0701.setOnClickListener(v -> {
            Navigation.findNavController(v)
                    .navigate(R.id.f_Premiumselection_List_Fragment);
        });

        iv_roomtour0702.setOnClickListener(v -> {
            Navigation.findNavController(v)
                    .navigate(R.id.roomTourFragment);
        });

        iv_bellnotify07.setOnClickListener(v -> { // todo for 書涵FCM
            iv_bell_add_one07.setVisibility(View.GONE);
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.homenotice_11_Fragment);
        });

        tv_to_homeworkerMember07.setOnClickListener(v -> {
            Navigation.findNavController(v)
                    .navigate(R.id.homeUserFragment);
        });

        iv_bell_add_one07.setVisibility(View.GONE);
        iv_homepage_logo_bell_07.setOnClickListener(v -> {
            iv_bell_add_one07.setVisibility(View.VISIBLE);
        });

        iv_preview07.setOnClickListener(v -> {
            Navigation.findNavController(v)
                    .navigate(R.id.previewFragment);
        });
    }

    //  首頁訂單通知紅點
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
        HomePageFragment07.OrderAdapter orderAdapter = (HomePageFragment07.OrderAdapter) rc_homenotice.getAdapter();
        if (orderAdapter == null) {
            orderAdapter = new HomePageFragment07.OrderAdapter();
            rc_homenotice.setAdapter(orderAdapter);
        }
        rc_homenotice.setLayoutManager(new LinearLayoutManager(getActivity()));
        orderAdapter.setOrders(cpreserveorders);
    }



    private class OrderAdapter extends RecyclerView.Adapter<HomePageFragment07.OrderAdapter.MyOrderViewHolder> {
        private List<Cpreserveorder> cpreserveorders;

        public OrderAdapter() {

        }


        public void setOrders(List<Cpreserveorder> cpreserveorders) {
            this.cpreserveorders = cpreserveorders;
        }

        class MyOrderViewHolder extends RecyclerView.ViewHolder {


            public MyOrderViewHolder(View itemView) {
                super(itemView);

            }
        }

        @NonNull

        @Override
        public HomePageFragment07.OrderAdapter.MyOrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_itemcard_hometice_11, parent, false);
            return new HomePageFragment07.OrderAdapter.MyOrderViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(HomePageFragment07.OrderAdapter.MyOrderViewHolder holder, int position) {
            final Cpreserveorder cpreserveorder = cpreserveorders.get(position);
           }

        @Override
        public int getItemCount() {
            return cpreserveorders == null ? 0 : cpreserveorders.size();
        }
    } void setOrders(List<Cpreserveorder> cpreserveorders) {
        this.cpreserveorders = cpreserveorders;
    }


}