package com.kawaragi.mainservice.utils;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class ContextUtil {

    public static String getAuthorizedUserName(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            System.out.println(username + "14");

        } else {
            String username = principal.toString();
            System.out.println(username+"18");

        }
        //SecurityContextHolder.getContext().getAuthentication().
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
