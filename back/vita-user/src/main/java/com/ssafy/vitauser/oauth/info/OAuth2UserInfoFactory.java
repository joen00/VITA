package com.ssafy.vitauser.oauth.info;


import com.ssafy.vitauser.oauth.entity.ProviderType;
import com.ssafy.vitauser.oauth.info.impl.FacebookOAuth2UserInfo;
import com.ssafy.vitauser.oauth.info.impl.GoogleOAuth2UserInfo;
import com.ssafy.vitauser.oauth.info.impl.KakaoOAuth2UserInfo;
import com.ssafy.vitauser.oauth.info.impl.NaverOAuth2UserInfo;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(ProviderType providerType, Map<String, Object> attributes) {
        switch (providerType) {
            case GOOGLE: return new GoogleOAuth2UserInfo(attributes);
            case FACEBOOK: return new FacebookOAuth2UserInfo(attributes);
            case NAVER: return new NaverOAuth2UserInfo(attributes);
            case KAKAO: return new KakaoOAuth2UserInfo(attributes);
            default: throw new IllegalArgumentException("Invalid Provider Type.");
        }
    }
}
