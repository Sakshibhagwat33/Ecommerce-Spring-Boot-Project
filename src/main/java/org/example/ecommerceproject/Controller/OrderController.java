package org.example.ecommerceproject.Controller;

import org.example.ecommerceproject.Entity.Order;
import org.example.ecommerceproject.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/order")
    public String showOrderForm(Model model) {
        model.addAttribute("order", new Order());
        return "order";  // Ensure you have an "order-form.html" in src/main/resources/templates
    }

    @PostMapping("/data")
    public String placeOrder(@ModelAttribute Order order) {
        System.out.println("Received Order: " + order);
        orderRepository.save(order);
        System.out.println("Order saved successfully!");
        return "redirect:/order-success";
    }

    @GetMapping("/order-success")
    public String showSuccessPage() {
        return "redirect:/orders";  // Ensure you have an "order-success.html"
    }

    @GetMapping("/orders/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderRepository.deleteById(id);
        return "redirect:/orders"; // Reloads order list after deletion
    }

    @GetMapping("/orders")
    public String viewOrders(Model model) {
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "order-list";  // This points to "order-list.html"
    }

}


