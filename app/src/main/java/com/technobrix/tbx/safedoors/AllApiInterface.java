package com.technobrix.tbx.safedoors;


import com.technobrix.tbx.safedoors.AccountPOJO.AccountBean;
import com.technobrix.tbx.safedoors.AddFamilyPOJO.AddFamilyBean;
import com.technobrix.tbx.safedoors.Create_MeetingPOJO.CreateBean;
import com.technobrix.tbx.safedoors.FacilityPOJO.Bean;
import com.technobrix.tbx.safedoors.FamilyPOJO.FamilyBean;
import com.technobrix.tbx.safedoors.ForgotPOJO.ForgotBean;
import com.technobrix.tbx.safedoors.GetNotificationPOJO.NotificationBean;
import com.technobrix.tbx.safedoors.HelpDeskPOJO.helpDeskBeam;
import com.technobrix.tbx.safedoors.InventryListPOJO.InventoryBean;
import com.technobrix.tbx.safedoors.LoginPOJO.LoginBean;
import com.technobrix.tbx.safedoors.MeetingPOJO.MeetingBean;
import com.technobrix.tbx.safedoors.NoticeListPOJO.NoticeBean;
import com.technobrix.tbx.safedoors.ProfilePOJO.ProfileBean;
import com.technobrix.tbx.safedoors.ProfilePOJO.SetFamilyBean;
import com.technobrix.tbx.safedoors.ProfilePOJO.SetProfileBean;
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


    @Multipart
    @POST("app_api/set_profile.php")
    Call<SetProfileBean> setprofile(@Part("userid") String id , @Part("socity") String society, @Part("gender") String gender , @Part("dob") String dob , @Part("address") String add ,@Part("age") String age );


    @Multipart
    @POST("app_api/set_family_data.php")
    Call<SetFamilyBean> setfamily(@Part("userid") String id , @Part("family_id") String family, @Part("name") String name , @Part("gender") String gender , @Part("age") String age , @Part("relation") String relation );

    @Multipart
    @POST("app_api/get_family_info.php")
    Call<FamilyBean> family(@Part("userid") String id);

    @Multipart
    @POST("app_api/add_family_info.php")
    Call<AddFamilyBean> add(@Part("userid") String id ,@Part("name") String name , @Part("gender") String gender , @Part("age") String age , @Part("relation") String relation );

    @Multipart
    @POST("app_api/create_viewentryuser.php")
    Call<SetFamilyBean> setfamily (@Part("gateid") String gate , @Part("socity_id") String  society ,@Part("house_id") String  house , @Part("member_name") String  mem , @Part("visitorname") String  vistor , @Part("purpuse") String  purpuse , @Part("date") String  date , @Part("time") String  time , @Part("profile") String  profile );


    @Multipart
    @POST("app_api/get_all_view_notification.php")
    Call<NotificationBean> notify(@Part("socity_id") String society , @Part("house_id") String id);

    @Multipart
    @POST("app_api/lates_notify.php")
    Call<NotificationBean> bean(@Part("socity_id") String society , @Part("house_id") String id);


    @Multipart
    @POST("app_api/viewnitifybyid.php")
    Call<NotificationBean> notification(@Part("socity_id") String society , @Part("house_id") String id , @Part("notify_id") String notify);

    @Multipart
    @POST("app_api/account_category.php")
    Call<AccountBean> account(@Part("socity_id") String id);


}

