package pe.pragmma.store.store.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.pragmma.store.store.config.JwtConfig;

@Component
public class Constants {

    public static  String JWT_SECRET;
    public static String JWT_EXPIRATION;
    public static final String JWT_DEFAULT_SECRET = "aPKAOSPSSUNSNISISISisAAIISIASIKASISAmissaMIIMasMIsMIMSIASMIMISAMISMMISISAMMASI";
    public static final String JWT_HEADER = "Authorization";

    @Autowired
    public Constants(JwtConfig jwtConfig) {
        JWT_SECRET = jwtConfig.getJwtSecret();
        JWT_EXPIRATION = jwtConfig.getJwtExpiration();
    }
}
