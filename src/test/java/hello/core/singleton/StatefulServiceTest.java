package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

class StatefulServiceTest {
    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // 쓰레드1: 사용자 A가 10000원 주문.
        int priceA = statefulService1.order("userA", 10000);
        // 쓰레드2: 사용자 B가 20000원 주문.
        int priceB = statefulService2.order("userB", 20000);
        
        // 사용자 A가 주문금액 조회
//        int price = statefulService1.getPrice();
        System.out.println("priceA = " + priceA);
        System.out.println("priceB = " + priceB);

        System.out.println("statefulService1 = " + statefulService1);
        System.out.println("statefulService2 = " + statefulService2);

//        assertThat(statefulService1.getPrice()).isEqualTo(20000);
        assertThat(priceA).isNotEqualTo(priceB);
        assertThat(statefulService1).isSameAs(statefulService2);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}