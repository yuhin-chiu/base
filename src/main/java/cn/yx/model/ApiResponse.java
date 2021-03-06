package cn.yx.model;

import cn.yx.enums.ApiResponseEnum;

public class ApiResponse {
    private Integer code;
    private String msg;
    private Object data;
    private String description;

    private Integer total;
    private Integer currentPage;
    private Integer pageSize;
    private boolean hasMore;

    public void setEnum(ApiResponseEnum en) {
        this.code = en.getCode();
        this.msg = en.getContent();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public ApiResponse setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ApiResponse setData(Object data) {
        this.data = data;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ApiResponse setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public ApiResponse setTotal(Integer total) {
        this.total = total;
        return this;
    }

    
    /**
     * 返回参数为空
     * @return
     */
    public static ApiResponse paramIsNullResponse() {
        ApiResponse response = new ApiResponse();
        response.setCode(ApiResponseEnum.BAD_REQUEST.getCode());
        response.setMsg(ApiResponseEnum.BAD_REQUEST.getContent());
        return response;
    }
    
    /**
     * 返回成功
     * @return
     */
    public static ApiResponse successResponse() {
        ApiResponse response = new ApiResponse();
        response.setCode(ApiResponseEnum.SUCCESS.getCode());
        response.setMsg(ApiResponseEnum.SUCCESS.getContent());
        return response;
    } 
    
    /**
     * 返回异常
     * @return
     */
    public static ApiResponse exceptionResponse() {
        ApiResponse response = new ApiResponse();
        response.setCode(ApiResponseEnum.INTERNAL_ERROR.getCode());
        response.setMsg(ApiResponseEnum.INTERNAL_ERROR.getContent());
        return response;
    }
    
    public static ApiResponse unLoginResponse() {
        ApiResponse response = new ApiResponse();
        response.setCode(ApiResponseEnum.NON_LOGIN_IN.getCode());
        response.setMsg(ApiResponseEnum.NON_LOGIN_IN.getContent());
        return response;
    }
    
    public static ApiResponse checkFailResponse() {
        ApiResponse response = new ApiResponse();
        response.setCode(ApiResponseEnum.SIGN_NOT_MATCH.getCode());
        response.setMsg(ApiResponseEnum.SIGN_NOT_MATCH.getContent());
        return response;
    }
    
    public static ApiResponse fileSaveError() {
        ApiResponse response = new ApiResponse();
        response.setCode(ApiResponseEnum.FILE_SAVE_FAILED.getCode());
        response.setMsg(ApiResponseEnum.FILE_SAVE_FAILED.getContent());
        return response;
    }
    
    public static ApiResponse fileSaveEmpty() {
        ApiResponse response = new ApiResponse();
        response.setCode(ApiResponseEnum.FILE_SAVE_EMPTY.getCode());
        response.setMsg(ApiResponseEnum.FILE_SAVE_EMPTY.getContent());
        return response;
    }

    public static ApiResponse noMapping() {
        ApiResponse response = new ApiResponse();
        response.setCode(ApiResponseEnum.PAGE_NOT_FOUND.getCode());
        response.setMsg(ApiResponseEnum.PAGE_NOT_FOUND.getContent());
        return response;
    }
    
    public static ApiResponse otherError() {
        ApiResponse response = new ApiResponse();
        response.setCode(ApiResponseEnum.COMMON_ERROR.getCode());
        response.setMsg(ApiResponseEnum.COMMON_ERROR.getContent());
        return response;
    }

    public Boolean isSuccess() {
        if(this.getCode() != null && this.getCode().equals(ApiResponseEnum.SUCCESS.getCode())) {
            return true;
        }
        return false;
    }
    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }
}
