package com.geektech.android3_hw3.data.storage;

import android.util.Log;

import com.geektech.android3_hw3.data.model.Publish;
import com.geektech.android3_hw3.data.remote.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PublishStorage {

    public void getPublishList(PublishCallback<List<Publish>> callback) {
        RetrofitBuilder.getInstance().getPublishes().enqueue(new Callback<List<Publish>>() {
            @Override
            public void onResponse(Call<List<Publish>> call, Response<List<Publish>> response) {
                if (response.isSuccessful() && response.body() != null)
                    callback.onResponse(response.body());
                else Log.d("TAG", "onResponse getPublishList(): Request error");
            }

            @Override
            public void onFailure(Call<List<Publish>> call, Throwable t) {
                callback.onFailure(t.getLocalizedMessage());
            }
        });
    }

    public void getPublish(Integer id, PublishCallback<Publish> callback) {
        RetrofitBuilder.getInstance().getPublish(id).enqueue(new Callback<Publish>() {
            @Override
            public void onResponse(Call<Publish> call, Response<Publish> response) {
                if (response.isSuccessful() && response.body() != null)
                    callback.onResponse(response.body());
                else Log.d("TAG", "onResponse getPublish(): Request error");
            }

            @Override
            public void onFailure(Call<Publish> call, Throwable t) {
                callback.onFailure(t.getLocalizedMessage());
            }
        });
    }

    public void createPublish(Publish model) {
        RetrofitBuilder.getInstance().createPublish(model).enqueue(new Callback<Publish>() {
            @Override
            public void onResponse(Call<Publish> call, Response<Publish> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("TAG", "onResponse: Created");
                } else Log.d("TAG", "onResponse createPublish(): Request error");
            }

            @Override
            public void onFailure(Call<Publish> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    public void updatePublish(Integer id, Publish model) {
        RetrofitBuilder.getInstance().updatePublish(id, model).enqueue(new Callback<Publish>() {
            @Override
            public void onResponse(Call<Publish> call, Response<Publish> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("TAG", "onResponse: Updated");
                } else
                    Log.d("TAG", "onResponse updatePublish(): Request error " + response.errorBody() + " || " + response.message());
            }

            @Override
            public void onFailure(Call<Publish> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    public void deletePublish(Integer id, PublishCallback callback) {
        RetrofitBuilder.getInstance().deletePublish(id).enqueue(new Callback<Publish>() {
            @Override
            public void onResponse(Call<Publish> call, Response<Publish> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponse("Deleted");
                } else Log.d("TAG", "onResponse deletePublish(): Request error");
            }

            @Override
            public void onFailure(Call<Publish> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    public interface PublishCallback<ContentData> {
        void onResponse(ContentData data);

        default void onFailure(String errorMsg) {
            Log.d("TAG", "onFailure: " + errorMsg);
        }
    }
}
