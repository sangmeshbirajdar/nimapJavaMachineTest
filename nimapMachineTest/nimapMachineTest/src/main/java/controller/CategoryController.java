package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dao.ICategory;
import model.Category;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	ICategory category;


	@GetMapping("/")
	public List<Category> viewCategories() {
		return category.findAll();
	}

	@DeleteMapping("/{categoryId}")
	public String deleteCategpry(@PathVariable("categoryId") int categoryId) {
		category.deleteById(categoryId);
		return "Category Deleted";
	}

	@PutMapping("/{categoryId}")
	public String updateCategory(@RequestBody Category c, @PathVariable("categoryId") int categoryId) {
		category.findById(c.getCategoryId()).map(update -> {
			update.setCategoryId(c.getCategoryId());
			update.setCategoryName(c.getCategoryName());
			return category.save(update);
		});
		return "Category Updated";
	}

	@GetMapping("/{categoryId}")
	public String searchCategory(@PathVariable("categoryId") int categoryId) {
		return category.findById(categoryId).get().getCategoryName();
	}

	@PostMapping
	public String addCategory(@RequestBody Category c) {
		category.save(c);
		System.out.println("Add Invoked");
		return "Category Added";
	}
	
	@GetMapping
	public Page<Category> getAllCategories(
			@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		return category.findAll(pageable);
		}
}
