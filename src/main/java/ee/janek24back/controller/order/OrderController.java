package ee.janek24back.controller.order;

import ee.janek24back.controller.order.dto.OrderDto;
import ee.janek24back.infrastructure.error.ApiError;
import ee.janek24back.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @PostMapping("/order")
    @Operation(
            summary = "Lisab orderi",
            description = "Lisab orderi")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "order edukalt süsteemi lisatud"),
            @ApiResponse(responseCode = "400", description = "Vigased andmed", content = @Content(schema = @Schema(implementation = ApiError.class)))})

    public void addOrder(@RequestParam Integer userId, @RequestBody @Valid OrderDto orderDto) {
        orderService.addOrder(userId, orderDto);
    }

    @GetMapping("/orders")
    @Operation(summary = "Tagastab kasutaja tellimused")
    public List<OrderDto> getUserOrders(@RequestParam Integer userId) {
        return orderService.getUserOrders(userId);
    }



}
