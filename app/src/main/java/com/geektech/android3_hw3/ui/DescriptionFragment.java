package com.geektech.android3_hw3.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geektech.android3_hw3.R;
import com.geektech.android3_hw3.data.model.Publish;
import com.geektech.android3_hw3.data.storage.PublishStorage;
import com.geektech.android3_hw3.databinding.FragmentDescriptionBinding;
import com.geektech.android3_hw3.ui.base.BaseFragment;
import com.geektech.android3_hw3.utils.Keys;

public class DescriptionFragment extends BaseFragment<FragmentDescriptionBinding> {

    private final PublishStorage storage = new PublishStorage();
    private Integer id;

    @Override
    public ViewBinding binding() {
        return FragmentDescriptionBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        checkData();
        onChangeClick();
    }

    private void onChangeClick() {
        ui.change.setOnClickListener(v -> {
            FormFragment formFragment = new FormFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(Keys.getPublishKey(), id);
            formFragment.setArguments(bundle);
            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, formFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

    private void checkData() {
        if (getArguments() != null) {
            id = getArguments().getInt(Keys.getPublishKey());
            getData(id);
        }
    }

    private void getData(Integer id) {
        ui.progressBar.setVisibility(View.GONE);
        storage.getPublish(id, this::setData);
    }

    private void setData(Publish model) {
        ui.title.setText(model.getTitle());
        ui.content.setText(model.getContent());
        ui.userId.setText(String.valueOf(model.getUserId()));
        ui.group.setText(String.valueOf(model.getGroup()));
    }
}