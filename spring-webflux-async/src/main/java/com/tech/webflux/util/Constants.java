package com.tech.webflux.util;

import com.tech.webflux.exception.WebFluxCommonException;

public class Constants {

    public static enum StatusCodes {
        SUCCESS, INTERNAL_SERVER_ERROR, BAD_REQUEST, NOT_FOUND;
        
    	public static String getStatusDesc(StatusCodes status) {
            String desc = null;

            switch (status) {

                case SUCCESS:
                    desc = "SUCCESS";
                    break;
                case INTERNAL_SERVER_ERROR:
                    desc = "INTERNAL_SERVER_ERROR";
                    break;
                case BAD_REQUEST:
                    desc = "BAD_REQUEST";
                    break;
                case NOT_FOUND:
                	desc = "NOT_FOUND";
                    break;
            }

            return desc;
        }
    }

    
    public static void fluxRuntimeException(boolean flag) {
        if (flag) {
            throw new WebFluxCommonException(ApplicationProperties.COMMON_ERRMOR);
        }
    }
}
