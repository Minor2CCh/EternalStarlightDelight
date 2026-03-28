package com.minor2cch.eternalstarlightdelight.config;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ESDConfig {
    private OverrideList overrideList;
    private Boolean boomerangUsableKnife;
    private Boolean mendableAmaramberToolsInDispenser;
    private ESDConfig() {
    }
    public static ESDConfig defaultESDConfig(){
        ESDConfig config = new ESDConfig();
        config.overrideList = OverrideList.defaultOverrideList();
        config.boomerangUsableKnife = true;
        config.mendableAmaramberToolsInDispenser = true;
        return config;
    }
    public static class OverrideList {
        private Boolean ESDSoupStackOverride;
        private Boolean ExtraESDSoupEffect;

        private OverrideList() {
        }
        private boolean nullToDefault(){
            boolean bl = false;
            if(ESDSoupStackOverride == null){
                ESDSoupStackOverride = defaultOverrideList().ESDSoupStackOverride;
                bl = true;
            }
            if(ExtraESDSoupEffect == null){
                ExtraESDSoupEffect = defaultOverrideList().ExtraESDSoupEffect;
                bl = true;
            }
            return bl;
        }
        private static OverrideList defaultOverrideList(){
            OverrideList list = new OverrideList();
            list.ESDSoupStackOverride = true;
            list.ExtraESDSoupEffect = true;
            return list;
        }
        public boolean getESDSoupStackOverride(){
            return getOrDefault(this.ESDSoupStackOverride, defaultOverrideList().ESDSoupStackOverride);
        }
        public boolean getExtraESDSoupEffect(){
            return getOrDefault(this.ExtraESDSoupEffect, defaultOverrideList().ExtraESDSoupEffect);
        }
    }
    public boolean fillDefaults() {
        boolean bl;
        if(overrideList == null){
            overrideList = OverrideList.defaultOverrideList();
            bl = true;
        }else{
            bl = overrideList.nullToDefault();

        }
        if(boomerangUsableKnife == null){
            boomerangUsableKnife = defaultESDConfig().boomerangUsableKnife;
            bl = true;
        }
        if(mendableAmaramberToolsInDispenser == null){
            mendableAmaramberToolsInDispenser = defaultESDConfig().mendableAmaramberToolsInDispenser;
            bl = true;
        }
        return bl;
    }
    public OverrideList getOverrideList(){
        return getOrDefault(this.overrideList, defaultESDConfig().overrideList);
    }
    public Boolean getBoomerangUsableKnife(){
        return getOrDefault(this.boomerangUsableKnife, defaultESDConfig().boomerangUsableKnife);
    }
    public Boolean getMendableAmaramberToolsInDispenser(){
        return getOrDefault(this.mendableAmaramberToolsInDispenser, defaultESDConfig().mendableAmaramberToolsInDispenser);
    }
    @NotNull
    private static <T> T getOrDefault(@Nullable T value, @NotNull T defaultValue){
        return Optional.ofNullable(value).orElse(defaultValue);
    }
}
