package com.minor2cch.eternalstarlightdelight.config;

public class ESDConfig {
    public ESDConfig() {
        this.overrideList = new OverrideList();
        this.boomerangUsableKnife = true;
    }
    public static class OverrideList {
        private Boolean ESDSoupStackOverride;
        private Boolean ExtraESDSoupEffect;

        private OverrideList() {
            this.ESDSoupStackOverride = true;
            this.ExtraESDSoupEffect = true;
        }
        public void nullToDefault(){
            if(ESDSoupStackOverride == null){
                ESDSoupStackOverride = new OverrideList().ESDSoupStackOverride;
            }
            if(ExtraESDSoupEffect == null){
                ExtraESDSoupEffect = new OverrideList().ExtraESDSoupEffect;
            }
        }
        public boolean getESDSoupStackOverride(){
            return this.ESDSoupStackOverride;
        }
        public boolean getExtraESDSoupEffect(){
            return this.ExtraESDSoupEffect;
        }
    }
    private OverrideList overrideList;
    private Boolean boomerangUsableKnife;
    public void fillDefaults() {
        if(overrideList == null){
            overrideList = new OverrideList();
        }else{
            overrideList.nullToDefault();
        }
        if(boomerangUsableKnife == null){
            boomerangUsableKnife = true;
        }
    }
    public OverrideList getOverrideList(){
        return this.overrideList;
    }
    public Boolean getBoomerangUsableKnife(){
        return this.boomerangUsableKnife;
    }
}
