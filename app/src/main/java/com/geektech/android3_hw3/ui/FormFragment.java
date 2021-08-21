package com.geektech.android3_hw3.ui;

import android.graphics.Color;
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
import com.geektech.android3_hw3.databinding.FragmentFormBinding;
import com.geektech.android3_hw3.ui.base.BaseFragment;
import com.geektech.android3_hw3.utils.Keys;

public class FormFragment extends BaseFragment<FragmentFormBinding> {

    private Publish model;
    private final PublishStorage storage = new PublishStorage();

    @Override
    public ViewBinding binding() {
        return FragmentFormBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        checkData();
        onClick();
    }

    private void onClick() {
        ui.continueBtn.setOnClickListener(v -> {
            String title = ui.titleEdt.getText().toString();
            String content = ui.contentEdt.getText().toString();
            String group = ui.groupEdt.getText().toString();
            String userId = ui.userEdt.getText().toString();
            if (!title.equals("") && !content.equals("") && !group.equals("") && !userId.equals("")) {
                if (model != null) {
                    model.setTitle(title);
                    model.setContent(content);
                    model.setUserId(Integer.valueOf(userId));
                    model.setGroup(Integer.valueOf(group));
                    storage.updatePublish(model.getId(), model);
                } else {
                    model = new Publish(title, content, Integer.valueOf(userId), Integer.valueOf(group));
                    storage.createPublish(model);
                }
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new HomeFragment())
                        .addToBackStack(null)
                        .commit();
            } else {
                ui.contentEdt.setHintTextColor(Color.RED);
                ui.titleEdt.setHintTextColor(Color.RED);
                ui.userEdt.setHintTextColor(Color.RED);
                ui.groupEdt.setHintTextColor(Color.RED);
            }
        });
    }

    private void checkData() {
        if (getArguments() != null) {
            Integer id = getArguments().getInt(Keys.getPublishKey());
            getData(id);
        }
    }

    private void getData(Integer id) {
        storage.getPublish(id, this::setData);
    }

    private void setData(Publish model) {
        this.model = model;
        ui.titleEdt.setText(model.getTitle());
        ui.contentEdt.setText(model.getContent());
        ui.userEdt.setText(String.valueOf(model.getUserId()));
        ui.groupEdt.setText(String.valueOf(model.getGroup()));
    }

}