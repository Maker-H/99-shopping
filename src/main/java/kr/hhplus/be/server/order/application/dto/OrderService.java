package kr.hhplus.be.server.order.application.dto;

import kr.hhplus.be.server.coupon.application.exception.NoSuchCoupon;
import kr.hhplus.be.server.coupon.application.exception.NotUsableCoupon;
import kr.hhplus.be.server.coupon.domain.CouponEntity;
import kr.hhplus.be.server.coupon.domain.CouponPolicyEntity;
import kr.hhplus.be.server.coupon.domain.DiscountAmount;
import kr.hhplus.be.server.coupon.infra.CouponJpaRepository;
import kr.hhplus.be.server.coupon.infra.infra.CouponPolicyJpaRepository;
import kr.hhplus.be.server.order.application.exception.InsufficientStockException;
import kr.hhplus.be.server.order.domain.OrderEntity;
import kr.hhplus.be.server.order.infra.OrderJpaRepository;
import kr.hhplus.be.server.point.application.InsufficientPointException;
import kr.hhplus.be.server.point.domain.Amount;
import kr.hhplus.be.server.point.domain.UserPointEntity;
import kr.hhplus.be.server.point.infra.PointJpaRepository;
import kr.hhplus.be.server.product.application.exception.NoSuchProductException;
import kr.hhplus.be.server.product.domain.ProductEntity;
import kr.hhplus.be.server.product.infra.ProductJpaRepository;
import kr.hhplus.be.server.user.domain.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CouponJpaRepository couponRepository;
    private final CouponPolicyJpaRepository couponPolicyRepository;
    private final OrderJpaRepository orderRepository;
    private final ProductJpaRepository productRepository;
    private final PointJpaRepository pointRepository;


    public CreateOrderResponse processOrder(String txKey, CreateOrderRequest request) {

        // 주문 생성
        UserId userId = request.asUserId();
        OrderEntity order = OrderEntity.createOrder(userId);

        // 쿠폰 상태 확인
        Long couponId = request.couponId();
        CouponEntity coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new NoSuchCoupon("user: " + userId.getValue() + " used invalid coupon"));

        if (coupon.isNotUsable()) {
            order.canceled();
            orderRepository.save(order);
            throw new NotUsableCoupon("couponId: " + couponId + " is not usable");
        }

        // 재고 확인
        request.orderItems().stream()
                .forEach(requestOrderItem -> {
                    Long productId = requestOrderItem.productId();

                    ProductEntity productEntity = productRepository.findById(productId)
                            .orElseThrow(() -> new NoSuchProductException("productId: " + productId + "does not exits"));

                    if (!productEntity.hasEnoughQuantity(requestOrderItem.productQuantity())) {
                        order.canceled();
                        orderRepository.save(order);
                        throw new InsufficientStockException("productId: " + productId + "does not exits");
                    }
                });

        // 할인 후 포인트 차감
        CouponPolicyEntity couponPolicy = couponPolicyRepository.findById(couponId)
                .orElseThrow(() -> new NoSuchCoupon("user: " + userId.getValue() + " used invalid coupon"));

        UserPointEntity userPoint = pointRepository.findByUserId(userId)
                .orElseThrow(() -> new InsufficientPointException("user: " + userId.getValue() + " point is insufficient"));

        DiscountAmount discountAmount = couponPolicy.getDiscountAmount();
        if (userPoint.isSufficient(discountAmount)) {
            order.canceled();
            orderRepository.save(order);
            new InsufficientPointException("user: " + userId.getValue() + " point is insufficient");
        }

        Amount discountedAmount = userPoint.use(discountAmount);

        order.success();
        OrderEntity savedOrder = orderRepository.save(order);

        return new CreateOrderResponse(
                userId.getValue(),
                savedOrder.getOrderId(),
                savedOrder.getOrderPayment().getOrderPaymentId(),
                savedOrder.getStatus(),
                discountAmount.getValue(),
                discountedAmount.getValue()
        );

    }
}
