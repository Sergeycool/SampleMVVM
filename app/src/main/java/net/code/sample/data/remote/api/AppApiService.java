package net.code.sample.data.remote.api;

import com.google.gson.JsonObject;


import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AppApiService {
    @Headers("Content-Type: application/json;")
    @POST("user/validate_phone")
    Single<Response<ValidatePhoneNumberResponse>> validatePhoneNumber(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("user/validate_phone_code")
    Single<Response<NoDataResponse>> validatePhoneCode(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("user/signup")
    Single<Response<NoDataResponse>> signUp(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("user/login")
    Single<Response<LoginResponse>> login(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("user/resetPassword")
    Single<Response<NoDataResponse>> resetPassword(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("user/profile_picture/update")
    Single<Response<NoDataResponse>> updateAvatar(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("user/friends")
    Single<Response<GetFriendsResponse>> getFriends(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("user/check_friends")
    Single<Response<CheckFriendsResponse>> checkFriends(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("user/hi")
    Single<Response<NoDataResponse>> sayHiToFriend(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("user/feed_items_paginated")
    Single<Response<GetNewsNotificationsResponse>> getNewsNotifications(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("user/requests")
    Single<Response<GetNewsRequestsResponse>> getNewsRequests(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("user/request/update")
    Single<Response<NoDataResponse>> acceptRequest(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("albums")
    Single<Response<GetAlbumsResponse>> getAlbums(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("album_paginated")
    Single<Response<GetAlbumResponse>> getAlbum(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("album/add")
    Single<Response<AddAlbumResponse>> addAlbum(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("album/pic/add")
    Single<Response<AddPictureResponse>> addPicture(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("album/invite")
    Single<Response<NoDataResponse>> inviteToAlbum(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("album/generate_codes")
    Single<Response<GetGenerateInvitationCodeResponse>> generateInvitationCode(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("album/redeem_code")
    Single<Response<NoDataResponse>> checkCodeValidation(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("user/picture/comments")
    Single<Response<GetCommentsResponse>> getComments(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("user/picture/add_comment")
    Single<Response<NoDataResponse>> addComment(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("user/picture/like")
    Single<Response<NoDataResponse>> likePicture(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("user/picture/dislike")
    Single<Response<NoDataResponse>> dislikePicture(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("user/pic/reactions")
    Single<Response<GetPictureReactionsResponse>> getPictureReactions(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("user/album/define_main_picture")
    Single<Response<NoDataResponse>> makePictureAlbumCover(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("album/picture/delete")
    Single<Response<NoDataResponse>> removePictureFromAlbum(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("user/report_picture")
    Single<Response<NoDataResponse>> reportPicture(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("user/report_album")
    Single<Response<NoDataResponse>> reportAlbum(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("album/users")
    Single<Response<GetAlbumUsersResponse>> getAlbumUsers(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("album/leave")
    Single<Response<GetAlbumUsersResponse>> leaveAlbum(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("album/delete")
    Single<Response<NoDataResponse>> deleteAlbum(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("album/update")
    Single<Response<NoDataResponse>> updateAlbum(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("user/push_token")
    Single<Response<NoDataResponse>> sendFcmToken(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("user/update")
    Single<Response<NoDataResponse>> updateProfile(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("album/picture/secure")
    Single<ResponseBody> downloadPicture(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("user/avatar/secure")
    Single<ResponseBody> downloadAvatar(@Body JsonObject data);

    @Headers("Content-Type: application/json;")
    @POST("user/app/version")
    Single<Response<NoDataResponse>> sendAppInfo(@Body JsonObject data);
}
