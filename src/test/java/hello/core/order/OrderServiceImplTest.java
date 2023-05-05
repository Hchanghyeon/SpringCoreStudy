package hello.core.order;

import hello.core.CoreApplication;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new RateDiscountPolicy());
        MemberServiceImpl memberService = new MemberServiceImpl(new MemoryMemberRepository());

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(CoreApplication.class);
        String[] beanNames = ac.getBeanDefinitionNames();
        for(String beanName : beanNames){
            System.out.println("beanName = " + beanName);
        }

        Member member = new Member(1L, "창현", Grade.VIP);
        memberService.join(member);
        Order order = orderService.createOrder(1L, "itemA", 10000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}