package ee.janek24back.controller.order;

import ee.janek24back.controller.order.dto.OrderDto;
import ee.janek24back.infrastructure.error.ApiError;
import ee.janek24back.service.OrderService;
import ee.janek24back.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final UserService userService;
    private final OrderService orderService;

    @PostMapping("/order")
    @Operation(
            summary = "Lisab orderi",
            description = "Lisab orderi"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Order edukalt süsteemi lisatud"),
            @ApiResponse(responseCode = "400", description = "Vigased andmed", content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public void addOrder(@RequestParam Integer userId, @RequestBody @Valid OrderDto orderDto) {
        System.out.println("Order received: " + orderDto);
        orderService.addOrder(userId, orderDto);
    }
}
