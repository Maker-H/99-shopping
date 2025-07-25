package kr.hhplus.be.server.point.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import kr.hhplus.be.server.point.domain.Amount;
import kr.hhplus.be.server.user.domain.UserId;

@Schema(description = "포인트 충전 요청")
public record ChargePointRequest(

        @Schema(description = "사용자 ID", example = "42")
        @NotNull(message = "userId는 null일 수 없습니다.")
        Long userId,

        @Schema(description = "충전할 포인트 금액", example = "1000")
        @NotNull(message = "amount는 null일 수 없습니다.")
        @Min(value = 1, message = "amount는 1 이상이어야 합니다.")
        Long amount
) {
        public Amount asAmount() {
                return new Amount(amount);
        }

        public UserId asUserId() {
                return new UserId(userId);
        }
}
