package com.tribes_backend.tribes.userErrorService;

/*import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
<<<<<<< HEAD
=======

>>>>>>> 00664bd380a8b5d864007bf72d6f1e5edf856cff
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor*/

public class ErrorResponseModel {
    String status;
    String errorMessage;

    public ErrorResponseModel() {
    }

    public ErrorResponseModel(String status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String error) {
        this.status = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "Error Message from ErrorResponseModel class: " +
                "error='" + status + '\'' +
                ", errorMessage='" + errorMessage;
    }
}