package gtm.com.rxjavasimplyfied.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import gtm.com.rxjavasimplyfied.R;
import gtm.com.rxjavasimplyfied.api.RestClientApi;
import gtm.com.rxjavasimplyfied.model.UserDetailModel;
import gtm.com.rxjavasimplyfied.utils.Constants;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        compositeDisposable = new CompositeDisposable();

        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Constants.BASE_URL).build();

        RestClientApi restClientApi = retrofit.create(RestClientApi.class);

        Disposable objectObservable = restClientApi.getUserDetails().
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(userdetails -> updateUI(userdetails));
        compositeDisposable.add(objectObservable);

    }

    private void updateUI(UserDetailModel v) {
        Toast.makeText(MainActivity.this, "" + v.getName(), Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
}
