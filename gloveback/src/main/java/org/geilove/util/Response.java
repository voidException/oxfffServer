package org.geilove.util;


public class Response<T> {

    private  Integer retcode;  // 2000成功  2002 返回数据并不是想要获取

    private T result;

    private String msg;



    public Integer getRetcode() {
        return retcode;
    }

    public void setRetcode(Integer success) {
        this.retcode = success;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void success(T result) {
        this.setRetcode(2000);
        this.setResult(result);
        this.setMsg("成功");
    }

    /**
     * 请求的数据为空时，不进行数据封装，直接返回失败信息
     * @param msg
     */
    public void failByNoInputData(String msg){
        this.setRetcode(2001);
        this.setResult(result);
        this.setMsg(msg);
    }
    public void failByNoData(){
        this.setRetcode(2002);
        this.setMsg("数据为空");
    }
    public void failByException(){
        this.setRetcode(2003);
        this.setMsg("出现异常");
    }
//
//    public void setError(String error) {
//        this.success = false;
//        this.error = error;
//    }
//
//    public static <T> Response<T> ok(T data) {
//        Response<T> resp = new Response<T>();
//        resp.setResult(data);
//        return resp;
//    }
//
//    public static <T> Response<T> ok() {
//        return Response.ok(null);
//    }
//
//    public static <T> Response<T> fail(String error) {
//        Response<T> resp = new Response<T>();
//        resp.setError(error);
//        return resp;
//    }
}


