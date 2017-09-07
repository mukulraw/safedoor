package com.technobrix.tbx.safedoors;


import com.technobrix.tbx.safedoors.ForgotPOJO.ForgotBean;
import com.technobrix.tbx.safedoors.LoginPOJO.LoginBean;
import com.technobrix.tbx.safedoors.ProfilePOJO.ProfileBean;
import com.technobrix.tbx.safedoors.RegisterPOJO.RegisterBean;
import com.technobrix.tbx.safedoors.SocityPOJO.SocityBean;
import com.technobrix.tbx.safedoors.flatPOJO.flatBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface AllApiInterface {

    @Multipart
    @POST("http://safedoors.in/app_api/register.php")
    Call<RegisterBean> bean(@Part("username") String user, @Part("email") String email , @Part("phone") String phone , @Part("socity_id") String socity , @Part("house_id") String house , @Part("password") String pass);

    @Multipart
    @POST("http://safedoors.in/app_api/login.php")
    Call<LoginBean> login(@Part("username") String user, @Part("password") String password);


    @Multipart
    @POST("http://safedoors.in/app_api/forgot_password.php")
    Call<ForgotBean> forgot(@Part("email") String e);

    @GET("http://safedoors.in/app_api/socity_list.php")
    Call<SocityBean> sb();

    @Multipart
    @POST("http://safedoors.in/app_api/house_list.php")
    Call<flatBean> getFlats(@Part("socity_id") String e);

    @Multipart
    @POST("http://safedoors.in/app_api/get_profile_info.php")
    Call<ProfileBean> getprofile(@Part("userid") String user);




}
