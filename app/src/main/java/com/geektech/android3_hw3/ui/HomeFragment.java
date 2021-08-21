package com.geektech.android3_hw3.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.geektech.android3_hw3.R;
import com.geektech.android3_hw3.data.model.Publish;
import com.geektech.android3_hw3.data.storage.PublishStorage;
import com.geektech.android3_hw3.databinding.FragmentHomeBinding;
import com.geektech.android3_hw3.ui.base.BaseFragment;
import com.geektech.android3_hw3.utils.CustomAlertDialog;
import com.geektech.android3_hw3.utils.Keys;

import java.util.List;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> {

    private HomeAdapter adapter;
    private final PublishStorage storage = new PublishStorage();

    @Override
    public ViewBinding binding() {
        return FragmentHomeBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        getData();
        setClickListeners();
    }

    private void setClickListeners() {
        adapter.setItemClickListener(this::openDescription);
        adapter.setOnLongItemClickListener(id -> CustomAlertDialog.showDialog(requireContext(), () -> storage.deletePublish(id, o -> Toast.makeText(requireContext(), o.toString(), Toast.LENGTH_SHORT).show())));
        ui.addBtn.setOnClickListener(v -> openForm());
        ui.mySwipeRefreshLayout.setOnRefreshListener(() -> {
            getData();
            ui.mySwipeRefreshLayout.setRefreshing(false);
        });
    }

    private void openDescription(Integer id) {
        DescriptionFragment fragment = new DescriptionFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Keys.getPublishKey(), id);
        fragment.setArguments(bundle);
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void initViews() {
        RecyclerView recyclerView = ui.recycler;
        adapter = new HomeAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void getData() {
        storage.getPublishList(this::setData);
    }

    private void setData(List<Publish> publishes) {
        ui.progressBar.setVisibility(View.GONE);
        adapter.updateList(publishes);
    }


    private void openForm() {
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new FormFragment())
                .addToBackStack(null)
                .commit();

    }

}