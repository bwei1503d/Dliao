package com.bw.dliao.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 */

public class FActionFriendBean {


    /**
     * result_message : success
     * data : [{"area":"安徽省-安庆市-枞阳县","picWidth":720,"createtime":1500016251283,"picHeight":720,"gender":"女","lng":0,"introduce":"兔兔","imagePath":"http://qhb.2dyt.com/MyInterface/images/3c878e19-4c5a-4c59-9df4-eba244b6757e.jpg","userId":34,"yxpassword":"","relation":0,"password":"","lasttime":1500016275649,"phone":"18222222222","nickname":"傻傻的","age":"25","lat":0},{"area":"北京市 北京市 海淀区","picWidth":720,"createtime":1500014344277,"picHeight":720,"gender":"女","lng":116.293469,"introduce":"帅帅帅","imagePath":"http://qhb.2dyt.com/MyInterface/images/bccc29ee-4876-4460-adb3-fc935aeccd03.jpg","userId":25,"yxpassword":"","relation":0,"password":"","lasttime":1500016040581,"phone":"13111111112","nickname":"美少女","age":"24","lat":40.039006}]
     * result_code : 200
     */

    private String result_message;
    private int result_code;
    private List<DataBean1> data;

    public String getResult_message() {
        return result_message;
    }

    public void setResult_message(String result_message) {
        this.result_message = result_message;
    }

    public int getResult_code() {
        return result_code;
    }

    public void setResult_code(int result_code) {
        this.result_code = result_code;
    }

    public List<DataBean1> getData() {
        return data;
    }

    public void setData(List<DataBean1> data) {
        this.data = data;
    }


}
