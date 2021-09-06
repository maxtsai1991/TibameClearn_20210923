package idv.tfp10207.nowclearnnow0818.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import idv.tfp10207.nowclearnnow0818.R;

public class MessageListFragment07 extends Fragment {
    private ImageView iv_message_return;
    private TextView tv_readmessage_person07;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_message_list07, container, false);
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);
        handleButton();
    }

    private void findViews(View view) {
        iv_message_return = view.findViewById(R.id.iv_message_return);
        tv_readmessage_person07 = view.findViewById(R.id.tv_readmessage_person07);
    }

    private void handleButton() {
        iv_message_return.setOnClickListener(v -> {
            Navigation.findNavController(v)
                    .navigate(R.id.homePageFragment072);
        });
        tv_readmessage_person07.setOnClickListener(v -> {
            Navigation.findNavController(v)
                    .navigate(R.id.messageFragment07);
        });
    }


}