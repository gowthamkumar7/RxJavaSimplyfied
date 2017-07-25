package gtm.com.rxjavasimplyfied.api;

import gtm.com.rxjavasimplyfied.model.UserDetailModel;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RestClientApi {

    @GET("/users/gowthamkumar7")
    Observable<UserDetailModel> getUserDetails();

    @GET("/users/gowthamkumar7")
    Observable<UserDetailModel> getSecondUserDetails();
}
