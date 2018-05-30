package com.example.mithilesh.meeshoassignment.data.remote;

import com.google.gson.annotations.SerializedName;


public class ApiErrorResponse {
    private String message;
    private String error;
    private int statusCode;
    private ErrorNewUpdate body;

    public ApiErrorResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public ErrorNewUpdate getBody() {
        return body;
    }

    public void setBody(ErrorNewUpdate body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "ApiErrorResponse{" +
                "body=" + body +
                ", message='" + message + '\'' +
                ", error='" + error + '\'' +
                ", statusCode=" + statusCode +
                '}';
    }

    public class ErrorNewUpdate {
        @SerializedName("version")
        private String version;
        @SerializedName("url")
        private String url;
        @SerializedName("updateText")
        private String updateText;

        public String getUpdateText() {
            return updateText;
        }

        public void setUpdateText(String updateText) {
            this.updateText = updateText;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        @Override
        public String toString() {
            return "ErrorNewUpdate{" +
                    "updateText='" + updateText + '\'' +
                    ", version='" + version + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

}
