package com.technobrix.tbx.safedoors;


import com.technobrix.tbx.safedoors.Create_MeetingPOJO.CreateBean;
import com.technobrix.tbx.safedoors.FacilityPOJO.Bean;
import com.technobrix.tbx.safedoors.ForgotPOJO.ForgotBean;
import com.technobrix.tbx.safedoors.HelpDeskPOJO.helpDeskBeam;
import com.technobrix.tbx.safedoors.InventryListPOJO.InventoryBean;
import com.technobrix.tbx.safedoors.LoginPOJO.LoginBean;
import com.technobrix.tbx.safedoors.MeetingPOJO.MeetingBean;
import com.technobrix.tbx.safedoors.NoticeListPOJO.NoticeBean;
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
    @POST("app_api/register.php")
    Call<RegisterBean> bean(@Part("username") String user, @Part("email") String email , @Part("phone") String phone , @Part("socity_id") String socity , @Part("house_id") String house , @Part("password") String pass);

    @Multipart
    @POST("app_api/login.php")
    Call<LoginBean> login(@Part("username") String user, @Part("password") String password);


    @Multipart
    @POST("app_api/forgot_password.php")
    Call<ForgotBean> forgot(@Part("email") String e);

    @GET("app_api/socity_list.php")
    Call<SocityBean> sb();

    @Multipart
    @POST("app_api/house_list.php")
    Call<flatBean> getFlats(@Part("socity_id") String e);

    @Multipart
    @POST("app_api/get_profile_info.php")
    Call<ProfileBean> getprofile(@Part("userid") String user);


    @Multipart
    @POST("app_api/facility_list.php")
    Call<Bean> bean(@Part("socity_id") String id);

    @Multipart
    @POST("app_api/inventry_list.php")
    Call<InventoryBean> inventory(@Part("socity_id") String id);

    @Multipart
    @POST("app_api/get_meeting_list.php")
    Call<MeetingBean> meeting(@Part("socity_id") String id);


    @Multipart
    @POST("app_api/create_meeting.php")
    Call<CreateBean> create(@Part("socity_id") String id, @Part("userid") String user , @Part("date") String date, @Part("time") String time , @Part("meeting_title") String meet , @Part("description") String des);

    @Multipart
    @POST("app_api/get_meeting_list.php")
    Call<MeetingBean> getMeetings(@Part("socity_id") String id);

    @Multipart
    @POST("app_api/view_metting_byid.php")
    Call<meetingDetailBean> getMeetingDetails(@Part("socity_id") String socId , @Part("meeting_id") String  meetId);


    @Multipart
    @POST("app_api/get_help.php")
    Call<helpDeskBeam> getHelpDesk(@Part("socity_id") String id);

}

