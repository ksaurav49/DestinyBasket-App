package nmdl.express.ecomm.Firebase;

import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import nmdl.express.ecomm.ApiUrl.ApiInterface;
import nmdl.express.ecomm.ApiUrl.ApiUrl;
import nmdl.express.ecomm.SharedPrefManager;
import nmdl.express.ecomm.response.Noti;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {

        String token = FirebaseInstanceId.getInstance().getToken();

        registerToken(token);
    }
    private void registerToken(String token) {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(ApiUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiService=retrofit.create(ApiInterface.class);

        Call<Noti> call=apiService.salary(token);
        call.enqueue(new Callback<Noti>() {
            @Override
            public void onResponse(Call<Noti> call, Response<Noti> response) {
                Toast.makeText(FirebaseInstanceIDService.this, "ho gya", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Noti> call, Throwable t) {

            }
        });

    }
}
