package low.level.truecaller.model.common;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocialInfo {
    private Map<SocialProfileType, String> socialInfo = new HashMap<>();
}
