package spring.mapper;

import org.springframework.stereotype.Component;
import spring.dto.ResponseLimitDTO;
import spring.entity.UserLimit;

@Component
public class LimitMapper {

    public ResponseLimitDTO fromEntity(UserLimit userLimit, String message) {
        return new ResponseLimitDTO(
            userLimit.getUserId(),
            userLimit.getRemainingDailyLimit(),
            message
        );
    }

}
