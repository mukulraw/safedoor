package com.technobrix.tbx.safedoors;


import com.technobrix.tbx.safedoors.AddFamilyPOJO.AddFamilyBean;
import com.technobrix.tbx.safedoors.AddTpicPOJO.AddTopicBean;
import com.technobrix.tbx.safedoors.AddVehiclePOJO.AddBean;
import com.technobrix.tbx.safedoors.AllMemeberPOJO.MemberBean;
import com.technobrix.tbx.safedoors.BookFacilityPOJO.BookBean;
import com.technobrix.tbx.safedoors.BookPOJO.BookingBean;
import com.technobrix.tbx.safedoors.CategoryPOJO.CategoryBean;
import com.technobrix.tbx.safedoors.CommentListPOJO.CommentsBean;
import com.technobrix.tbx.safedoors.Create_MeetingPOJO.CreateBean;
import com.technobrix.tbx.safedoors.DeleteFamily.DeleteFamilyBean;
import com.technobrix.tbx.safedoors.DeletePOJO.DeleteBean;
import com.technobrix.tbx.safedoors.DeveloperPOJO.DeveloperBean;
import com.technobrix.tbx.safedoors.DisscusionPOJO.DisscusionBean;
import com.technobrix.tbx.safedoors.DocumentListPOJO.DoctorBean;
import com.technobrix.tbx.safedoors.EventBookPOJO.EventBookBean;
import com.technobrix.tbx.safedoors.EventDatePOJO.EventBean;
import com.technobrix.tbx.safedoors.FacilityPOJO.Bean;
import com.technobrix.tbx.safedoors.FamilyPOJO.FamilyBean;
import com.technobrix.tbx.safedoors.FeedbackPOJO.FeedbackBean;
import com.technobrix.tbx.safedoors.ForgotPOJO.ForgotBean;
import com.technobrix.tbx.safedoors.GetAllMeetingPOJO.GetAllBean;
import com.technobrix.tbx.safedoors.GetEventListPOJO.GetEventListBean;
import com.technobrix.tbx.safedoors.GetEventPOJO.GetEventBean;
import com.technobrix.tbx.safedoors.GetFamilyPOJO.GetFamilyBean;
import com.technobrix.tbx.safedoors.GetHelpPOJO.HelpBean;
import com.technobrix.tbx.safedoors.GetImagePOJO.GetBean;

import com.technobrix.tbx.safedoors.GetProfilePOJO.GetProfileBean;
import com.technobrix.tbx.safedoors.GetVehiIdPOJO.IdVehicleBean;
import com.technobrix.tbx.safedoors.GetVehiclePOJO.VehicleBean;
import com.technobrix.tbx.safedoors.HelpDeskPOJO.helpDeskBeam;
import com.technobrix.tbx.safedoors.InventoryPhonePOJO.InventoryPhoneBean;
import com.technobrix.tbx.safedoors.InventryListPOJO.InventoryBean;
import com.technobrix.tbx.safedoors.LoginPOJO.LoginBean;
import com.technobrix.tbx.safedoors.MeetingDatePOJO.MeetingDateBean;
import com.technobrix.tbx.safedoors.MeetingPOJO.MeetingBean;
import com.technobrix.tbx.safedoors.NoticeListPOJO.NoticeBean;
import com.technobrix.tbx.safedoors.NotificationListPOJO.NotifiBean;
import com.technobrix.tbx.safedoors.ProfilePOJO.SetFamilyBean;
import com.technobrix.tbx.safedoors.ProfilePOJO.SetProfileBean;
import com.technobrix.tbx.safedoors.RegisterPOJO.RegisterBean;
import com.technobrix.tbx.safedoors.RemovePOJO.RemoveBean;
import com.technobrix.tbx.safedoors.SocityPOJO.SocityBean;
import com.technobrix.tbx.safedoors.StaffListPOJO.staffListBean;
import com.technobrix.tbx.safedoors.StaffPOJO.StaffBean;
import com.technobrix.tbx.safedoors.TicketListPOJO.TicketListBean;
import com.technobrix.tbx.safedoors.TopicListPOJO.TopicBean;
import com.technobrix.tbx.safedoors.TopicListPOJO.TopicList;
import com.technobrix.tbx.safedoors.TopicPOJO.TopiBean;
import com.technobrix.tbx.safedoors.UpdateVehiclePOJO.UpdateBean;
import com.technobrix.tbx.safedoors.ViewFamilyPOJO.ViewFamilyBean;
import com.technobrix.tbx.safedoors.ViewMeetingPOJO.ViewBean;
import com.technobrix.tbx.safedoors.flatPOJO.flatBean;
import com.technobrix.tbx.safedoors.visitorListPOJO.visitorListBean;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface AllApiInterface {

    @Multipart
    @POST("app_api/register.php")
    Call<RegisterBean> bean
            (@Part("username") String user,
             @Part("email") String email,
             @Part("phone") String phone,
             @Part("socity_id") String socity,
             @Part("house_id") String house,
             @Part("password") String pass,
             @Part("owner_name") String p
    );

    @Multipart
    @POST("app_api/login.php")
    Call<LoginBean> login
            (@Part("username") String user,
             @Part("password") String password);


    @Multipart
    @POST("app_api/forgot_password.php")
    Call<ForgotBean> forgot
            (@Part("email") String e);

    @GET("app_api/socity_list.php")
    Call<SocityBean> sb();

    @Multipart
    @POST("app_api/house_list.php")
    Call<flatBean> getFlats
            (@Part("socity_id") String e);

    @Multipart
    @POST("app_api/get_profile_info.php")
    Call<GetProfileBean> getprofile
            (@Part("userid") String id);



    @Multipart
    @POST("app_api/facility_list.php")
    Call<Bean> bean(@Part("socity_id") String id , @Part("date") String date);

    @Multipart
    @POST("app_api/inventry_list.php")
    Call<InventoryBean> inventory
            (@Part("socity_id") String id);

    @Multipart
    @POST("app_api/get_meeting_list.php")
    Call<MeetingBean> meeting
            (@Part("socity_id") String id);


    @Multipart
    @POST("app_api/create_meeting.php")
    Call<CreateBean> creat
            (@Part("socity_id") String id,
             @Part("userid") String user,
             @Part("date") String date,
             @Part("time") String time,
             @Part("meeting_title") String meet,
             @Part("description") String des);

    @Multipart
    @POST("app_api/get_meeting_list.php")
    Call<MeetingBean> getMeetings
            (@Part("socity_id") String id);

    @Multipart
    @POST("app_api/view_metting_byid.php")
    Call<meetingDetailBean> getMeetingDetails
            (@Part("socity_id") String socId,
             @Part("meeting_id") String meetId);


    @Multipart
    @POST("app_api/get_help.php")
    Call<helpDeskBeam> getHelpDesk
            (@Part("socity_id") String id);


    @Multipart
    @POST("app_api/set_profile.php")
    Call<SetProfileBean> setprofile
            (@Part("userid") String id,
             @Part("socity") String society,
             @Part("gender") String gender,
             @Part("dob") String dob,
             @Part("address") String add,
             @Part("age") String age,
             @Part("parking") String pa,
             @Part("house_no") String h,
             @Part("fullname") String f);


    @Multipart
    @POST("app_api/set_family_data.php")
    Call<com.technobrix.tbx.safedoors.SetFamilyPOJO.SetFamilyBean> setfamily1
            (@Part("userid") String id,
             @Part("gender") String family,
             @Part("age") String name,
             @Part("relation") String gender,
             @Part("house_no") String age,
             @Part("family_id") String vv,
             @Part("socity_id") String relation,
             @Part("fullname") String fn
            );

    @Multipart
    @POST("app_api/get_family_info.php")
    Call<GetFamilyBean> family
            (@Part("house_no") String id);



    @Multipart
    @POST("app_api/staff_add.php")
    Call<StaffBean> staff
            (@Part("socity_id") String id,
            @Part("staff_id") String dhgf,
            @Part("house_no") String fkljdsf
             );



    @Multipart
    @POST("app_api/add_family_info.php")
    Call<AddFamilyBean> add
            (
                    @Part("userid") String id,
                    @Part("name") String name,
                    @Part("gender") String gender,
                    @Part("age") String age,
                    @Part("relation") String relation,
                    @Part("socity") String socity,
                    @Part("house_no") String house,
                    @Part("email") String email,
                    @Part("password") String pass,
                    @Part("fullname") String full,
                    @Part("phone") String phone
            );

    @Multipart
    @POST("app_api/create_viewentryuser.php")
    Call<SetFamilyBean> setfamily
            (@Part("gateid") String gate,
             @Part("socity_id") String society,
             @Part("house_id") String house,
             @Part("member_name") String mem,
             @Part("visitorname") String vistor,
             @Part("purpuse") String purpuse,
             @Part("date") String date,
             @Part("time") String time,
             @Part MultipartBody.Part file);


    @Multipart
    @POST("app_api/get_all_view_notification.php")
    Call<NotifiBean> notify
            (@Part("socity_id") String society,
             @Part("house_id") String id);


    @Multipart
    @POST("app_api/get_eventbydate.php")
    Call<EventBean> event
            (@Part("socity_id") String id,
             @Part("date") String date,
             @Part("userid") String userId
            );

    @Multipart
    @POST("app_api/meeting_notification.php")
    Call<EventBean> viewevent
            (@Part("socity_id") String id,
             @Part("meeting_id") String meet,
             @Part("userid") String user);


    @Multipart
    @POST("app_api/view_metting_byid.php")
    Call<ViewBean> viewbean
            (@Part("socity_id") String id,
             @Part("meeting_id") String meet);

    @Multipart
    @POST("app_api/notice_board_list.php")
    Call<NoticeBean> notice
            (@Part("socity_id") String id,
             @Part("date") String date);

    @Multipart
    @POST("app_api/get_vehicle.php")
    Call<VehicleBean> vehiclebean
            (@Part("house_no") String id);


    @Multipart
    @POST("app_api/add_vehical.php")
    Call<AddFamilyBean> addbean
            (@Part("userid") String id,
             @Part("house_no") String hno,
             @Part("name") String name,
             @Part("no_of_vehicle") String no,
             @Part("socity_id") String soc,
             @Part("rf_id") String vn,
             @Part("vehicle_no") String d,
             @Part("vehical_type") String vt
            );

    @Multipart
    @POST("app_api/vehical_category.php")
    Call<CategoryBean> category
            (
                    @Part("socity_id") String hno
            );



    @Multipart
    @POST("app_api/delete_document.php")
    Call<AddFamilyBean> deleteDoc
            (
                    @Part("house_no") String hno,
                    @Part("document_id") String docId,
                    @Part("socity_id") String soc
            );



    @Multipart
    @POST("app_api/set_vehical.php")
    Call<UpdateBean> update
            (@Part("userid") String id,
             @Part("name") String name,
             @Part("no_of_vehicle") String n,
             @Part("vehicle_no") String vehicle,
             @Part("vehicl_id") String vehi);


    @Multipart
    @POST("app_api/vehiclbyid.php")
    Call<IdVehicleBean> id
            (@Part("userid") String id,
             @Part("vehicl_id") String vehi);

    @Multipart
    @POST("app_api/get_meeting_list.php")
    Call<GetEventBean> getbena
            (@Part("socity_id") String id);


    @Multipart
    @POST("app_api/get_help.php")
    Call<HelpBean> help
            (@Part("socity_id") String id);

    @Multipart
    @POST("app_api/get_all_member.php")
    Call<DisscusionBean> diss
            (@Part("socity_id") String id);


    @Multipart
    @POST("app_api/get_all_topic.php")
    Call<TopicBean> topic
            (@Part("socity_id") String id,
             @Part("user_id") String uid);

    @Multipart
    @POST("app_api/create_topic.php")
    Call<AddTopicBean> addtopic
            (@Part("socity_id") String id,
             @Part("house_id") String h,
             @Part("member_id") String m,
             @Part("subject") String s,
             @Part("detail") String d);

    @Multipart
    @POST("app_api/get_all_comment.php")
    Call<CommentsBean> comment
            (@Part("socity_id") String s,
             @Part("topic_id") String t,
             @Part("user_id") String u
            );


    @Multipart
    @POST("app_api/topic_comment.php")
    Call<TopiBean> topi
            (@Part("socity_id") String s,
             @Part("topic_id") String t,
             @Part("userid") String u,
             @Part("comment") String c);

    @Multipart
    @POST("app_api/get_document.php")
    Call<DoctorBean> doc
            (@Part("house_no") String hno,
             @Part("socity_id") String soc);


    @Multipart
    @POST("app_api/get_profileimg.php")
    Call<GetBean> get
            (@Part("userid") String id);


    @Multipart
    @POST("app_api/update_profile_img.php")
    Call<com.technobrix.tbx.safedoors.UpdateProfilePOJO.UpdateBean> update
            (@Part("userid") String d,
             @Part("hidprofile") String h,
             @Part MultipartBody.Part file,
            @Part("socity_id") String i,
             @Part("house_no") String fgjh
            );

    @Multipart
    @POST("app_api/update_bacimg.php")
    Call<com.technobrix.tbx.safedoors.UpdateProfilePOJO.UpdateBean> ground
            (@Part("userid") String d,
             @Part("hidbackgimg") String h,
             @Part MultipartBody.Part file,
             @Part("socity_id") String i,
             @Part("house_no") String fgjh
            );



    @Multipart
    @POST("app_api/get_all_member.php")
    Call<MemberBean> member
            (@Part("socity_id") String id);


    @Multipart
    @POST("app_api/delete_vehicle.php")
    Call<AddFamilyBean> remove
            (@Part("vehicl_id") String vid,
             @Part("house_no") String hno,
             @Part("socity_id") String soc);

    @Multipart
    @POST("app_api/get_all_ataff.php")
    Call<staffListBean> getStaffList
            (@Part("socity_id") String id);

    @Multipart
    @POST("app_api/staffusernotify.php")
    Call<staffListBean> getStaffList2
            (
                    @Part("socity_id") String id,
                    @Part("house_id") String hid
            );


    @Multipart
    @POST("app_api/visitor_entry.php")
    Call<entryBean> regularIn
            (@Part("socity_id") String s,
             @Part("comment") String c,
             @Part("gateid") String g,
             @Part("visitor_type") String v,
             @Part("staff_id") String sta
            );




    @Multipart
    @POST("app_api/staff_remove.php")
    Call<DeleteBean> delete
            (@Part("socity_id") String id,
            @Part("staff_id") String i,
            @Part("house_no") String rd
    );


    @Multipart
    @POST("app_api/staff_add.php")
    Call<DeleteBean> assign
            (@Part("socity_id") String id,
             @Part("staff_id") String i,
             @Part("house_no") String rd
            );


    @Multipart
    @POST("app_api/feedbackmail.php")
    Call<DeveloperBean> developer
            (@Part("name") String s,
             @Part("email") String c,
             @Part("phone") String g,
             @Part("key") String v,
             @Part("description") String sta
            );

    @Multipart
    @POST("app_api/visitor_entry.php")
    Call<entryBean> regularNew
            (@Part("socity_id") String s,
             @Part("comment") String c,
             @Part("gateid") String g,
             @Part("visitor_type") String v,
             @Part("visitor_name") String vn,
             @Part("house_no") String hn,
             @Part MultipartBody.Part file,
             @Part MultipartBody.Part file1,
             @Part("car_no") String h

            );


    @Multipart
    @POST("app_api/add_document.php")
    Call<addDocBean> addDoc
            (@Part("userid") String user,
             @Part("title") String title,
             @Part("house_no") String house,
             @Part("socity_id") String socityId,
             @Part("document_no") String dno,
             @Part MultipartBody.Part file
            );


    @Multipart
    @POST("app_api/book_facility.php")
    Call<bookFacilityBean> bookFacility
            (@Part("socity_id") String s,
             @Part("house_no") String c,
             @Part("facility_id") String g,
             @Part("userid") String v,
             @Part("date") String vn,
             @Part("price") String price,
             @Part("from_time") String time1,
             @Part("to_time") String time2
            );


    @Multipart
    @POST("app_api/get_all_visitor.php")
    Call<visitorListBean> getVisitors
            (@Part("socity_id") String id);


    @Multipart
    @POST("app_api/visitor_out.php")
    Call<String> out
            (@Part("socity_id") String s,
             @Part("visitor_id") String v);


    @Multipart
    @POST("app_api/familydata.php")
    Call<ViewFamilyBean> fam
            (@Part("userid") String id,
             @Part("family_id") String family
            );


    @Multipart
    @POST("app_api/delete_family_info.php")
    Call<DeleteFamilyBean> del
            (@Part("socity_id") String s,
             @Part("user_id") String u,
             @Part("house_no") String h,
             @Part("family_id") String family
            );

    @Multipart
    @POST("app_api/get_meeting_list.php")
    Call<GetAllBean> me
            (@Part("socity_id") String d);


    @Multipart
    @POST("app_api/get_meeting_bydate.php")
    Call<GetAllBean> mdb
            (@Part("socity_id") String s,
             @Part("date") String d
            );


    @Multipart
    @POST("app_api/facility_list.php")
    Call<BookBean> book
            (@Part("socity_id") String s,
             @Part("date") String d
            );


    @Multipart
    @POST("app_api/event_book.php")
    Call<EventBookBean> eventbook
            (@Part("socity_id") String s,
             @Part("house_no") String h,
             @Part("userid") String u,
             @Part("event_id") String e,
             @Part("price") String p,
             @Part("qty") String q
            );


    @Multipart
    @POST("app_api/ticket_listby_userid.php")
    Call<TicketListBean> ticketbean
            (@Part("socity_id") String s,
             @Part("userid") String u

            );

    @Multipart
    @POST("app_api/inventry_phone.php")
    Call<InventoryPhoneBean> inventoryphone
            (@Part("socity_id") String s


            );


    @Multipart
    @POST("app_api/get_event_list.php")
    Call<GetEventListBean> getevent
            (@Part("socity_id") String s,
             @Part("userid") String d
            );


    @Multipart
    @POST("app_api/facility_booking_list.php")
    Call<BookingBean> boo
            (@Part("socity_id") String s,
             @Part("house_no") String d
            );


    @Multipart
    @POST("app_api/feedback.php")
    Call<FeedbackBean> feedback
            (@Part("socity_id") String s,
             @Part("name") String hjf,
             @Part("email") String ugh,
             @Part("phone") String ehj,
             @Part("description") String pfgh
            );


    @GET
    @Streaming
    Call<ResponseBody> getFile(@Url String url);



}

