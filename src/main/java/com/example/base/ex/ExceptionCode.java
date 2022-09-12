package com.example.base.ex;

import lombok.Getter;
@Getter
public enum ExceptionCode {
    INVALID_INPUT(1000, "error1000"),
    DUPLICATE_MOVIE(1001, "error1001"),
    NOT_FOUND_MOVIE(1002, "error1002"),
    MOVIE_USED(1003, "error1003"),
    NOT_FOUND_OC_APPROVAL(1004, "error1004"),
    NOT_FOUND_INTEREST_TERM_APPROVAL(1005, "error1005"),
    DUPLICATE_INVERTMENT_BONDS(2001, "error2001"),
    NOT_FOUND_INVERTMENT_BONDS(2002, "error2002"),
    INVERTMENT_BONDS_USED(2003, "error2003"),
    NOT_FOUND_PORTFOLIO(3000, "error3000"),
    PORTFOLIO_USED(3001, "error3001"),
    ABBREVIATIONS_PORTFOLIO_IS_USED(3002, "error3002"),
    NOT_FOUND_PRIMARY(4000, "error4000"),
    NOT_FOUND_BONDCODE(4002 , "error40002"),
    DUPLICATE_OC(5001, "error5001"),
    NOT_FOUND_OC(5002, "error5002"),
    OC_USED(5003, "error5003"),
    OC_ACTIVE(5004,"error5004"),
    INVALID_STATUS(5005,"error5005"),
    INVALID_DATE(5006,"error5006"),
    NOT_FOUND_INTEREST_TERM(5007,"error5007"),
    INTEREST_TERM_USED(5008,"error5008"),
    NOT_FOUND_TERM (5009,"error5009"),
    NOT_FOUND_PRIMARY_BOND_APPROVAL(2004, "error2004"),
    PRIMARY_BOND_NOT_ALLOWED_APPROVAL(2005, "error2005"),
    PRIMARY_BOND_NOT_ALLOWED_REJECT(2006, "error2006"),
    PRIMARY_BOND_LIMIT_NOT_FOUND(2007,"error2007"),
    VALIDATE_LIMIT(2008,"error2008"),
    INTEREST_TRACKING_NOT_FOUND(6000, "error6000"),
    CHANGE_DATE_NOT_VALID(6001, "error6001"),
    INTEREST_TRACKING_IS_NOT_DELETE(6002, "error6002"),
    INTEREST_TRACKING_LIST_IS_CREATED(6003, "error6003");
    private int status;
    private String error;
    ExceptionCode(int status, String error) {
        this.status = status;
        this.error = error;
    }
}
