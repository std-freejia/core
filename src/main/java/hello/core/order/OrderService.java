package hello.core.order;

public interface OrderService {

    // 주문 정보를 넣고, 최종 order결과를 반환.
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
