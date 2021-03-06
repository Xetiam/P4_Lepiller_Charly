package com.example.mareu.ui.view_reservation_detail;

public class ViewReservationState {}
class ViewReservationStateListUpdated extends ViewReservationState {
    private final String formatedListPart;
    private final Boolean isMyMail;

    public ViewReservationStateListUpdated(String formatedListPart, Boolean isMyMail){
        this.formatedListPart = formatedListPart;
        this.isMyMail = isMyMail;
    }

    public String getFormatedListPart() {
        return formatedListPart;
    }

    public Boolean isMyMail() {
        return isMyMail;
    }
}
class ViewReservationStateInit extends ViewReservationState {
    private final Boolean isMyMail;

    public ViewReservationStateInit(Boolean isMyMail){
        this.isMyMail = isMyMail;
    }

    public Boolean isMyMail() {
        return isMyMail;
    }
}