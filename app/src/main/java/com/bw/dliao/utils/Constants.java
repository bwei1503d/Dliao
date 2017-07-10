package com.bw.dliao.utils;

/**
 * Created by muhanxi on 17/7/6.
 */

public class Constants {

    // 1 登录
//    http://qhb.2dyt.com/MyInterface/userAction_login.action
//      必须
//    user.phone , user.password ,  user.sign , user.secretkey 可选 user.lat , user.lng

//    2 注册
// http://qhb.2dyt.com/MyInterface/userAction_add.action
//
//    必须
//      user.phone , user.password ,  user.nickname, user.gender,
//    user.area   user.age , user.introduce ,user.sign , user.secretkey
//  可选 user.lat , user.lng

//    3 上传形象照
//    http://qhb.2dyt.com/MyInterface/userAction_uploadImage.action
//    必须
//      user.file  user.currenttimer  user.sign user.picWidth , user.picHeight

//    4 将照片上传到相册中去
//    http://qhb.2dyt.com/MyInterface/userAction_uploadPhotoAlbum.action
    //    必须
//        user.file  user.currenttimer  user.sign , user.picWidth , user.picHeight
//
//    5 查询用户基本信息
//   http://qhb.2dyt.com/MyInterface/userAction_selectUserById.action
    //    必须
//   user.userId,user.sign
//    6 获取用户列表
//      http://qhb.2dyt.com/MyInterface/userAction_selectAllUser.action
//    pageIndex  pageSize sign
//


    //

    public static final int RESIZE_PIC = 720 ;


    public static final String LOGIN_ACTION = "http://qhb.2dyt.com/MyInterface/userAction_login.action" ;
    public static final String REGISTER_ACTION = "http://qhb.2dyt.com/MyInterface/userAction_add.action" ;

    public static final String ALL_USER = "http://qhb.2dyt.com/MyInterface/userAction_selectAllUser.action" ;

}
