package com.fanyao.alibaba.usercenter.msg;

import com.fanyao.alibaba.usercenter.dao.bonus.BonusMapper;
import com.fanyao.alibaba.usercenter.dao.user.UserMapper;
import com.fanyao.alibaba.usercenter.domain.dto.msg.UserAddBonusMsgDTO;
import com.fanyao.alibaba.usercenter.domain.entity.Bonus;
import com.fanyao.alibaba.usercenter.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * @author: bugProvider
 * @date: 2020/3/18 14:51
 * @description: 消费MQ消息
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RocketMQMessageListener(consumerGroup = "consumer-group", topic = "add-bonus")
// 必须填写 consumerGroup 监听MQ
// 其他MQ用其他注解
public class AddBonusMQListener implements RocketMQListener<UserAddBonusMsgDTO> {

    private final UserMapper userMapper;
    private final BonusMapper bonusMapper;

    @Override
    public void onMessage(UserAddBonusMsgDTO userAddBonusMsgDTO) {
        // 收到消息时的业务
        log.info("接收到MQ消息  {}", userAddBonusMsgDTO.toString());
        // 1.添加积分
        User user = userMapper.selectByPrimaryKey(userAddBonusMsgDTO.getUserId());
        if (Objects.nonNull(user)) {
            Integer totalBonus = user.getBonus() + userAddBonusMsgDTO.getBonus();
            user.setBonus(totalBonus);
            userMapper.updateByPrimaryKey(user);
            // 2.记录日志
            Bonus bonus = Bonus.builder()
                    .userId(user.getId())
                    .value(userAddBonusMsgDTO.getBonus())
                    .createTime(new Date())
                    .event("投稿")
                    .description("添加积分")
                    .build();
            bonusMapper.insert(bonus);
        }
    }
}
