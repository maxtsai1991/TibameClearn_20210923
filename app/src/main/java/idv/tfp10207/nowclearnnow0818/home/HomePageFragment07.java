package idv.tfp10207.nowclearnnow0818.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import idv.tfp10207.nowclearnnow0818.R;


public class HomePageFragment07 extends Fragment {
    private Activity activity;


    private ImageView iv_reservepage;
    private ImageView iv_marketpage;
    private ImageView iv_memberpage;
    private ImageView iv_homepage_announcement0701,iv_message07,iv_homepage_clear_service0701,iv_premiumselectionlist0701,iv_roomtour0702,iv_bellnotify07;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();
        return inflater.inflate(R.layout.fragment_home_page07, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        handleButtons(view);
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

        iv_bellnotify07.setOnClickListener(v -> {
            Navigation.findNavController(v)
                    .navigate(R.id.homePageFragment072); // todo for 書涵FCM
        });

    }

}