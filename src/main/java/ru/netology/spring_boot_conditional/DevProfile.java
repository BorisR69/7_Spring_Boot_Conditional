package ru.netology.spring_boot_conditional;

import org.springframework.stereotype.Component;

@Component
public class DevProfile implements SystemProfile {
    @Override
    public String getProfile() {
        return "Current profile is dev";
    }
}
