package tabiMax.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tabiMax.contraint.MaxPageItem;
import tabiMax.dto.ProductDTO;
import tabiMax.entity.ProductEntity;
import tabiMax.modelMapper.entityToDto;
import tabiMax.paging.pageRequest;
import tabiMax.service.FileUpload;
import tabiMax.service.ICategoryCommonService;
import tabiMax.service.ICategoryService;
import tabiMax.service.IProductService;
import tabiMax.utils.tranfromListToMap;

@Controller
@RequestMapping("/admin/")
public class ProductController {
	@Autowired
	private FileUpload fileUpload;
	@Autowired
	private IProductService productService;
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private ICategoryCommonService categoryCommonService;
	@GetMapping(value = "product")
	public String pageProduct(@RequestParam(required = false) Long categoryId,
			@RequestParam(required = false) Integer status,@ModelAttribute("paging") pageRequest paging, Model model) {
		Pageable pageable = null;	
		if (paging.getPage() != null) {
			pageable =   new PageRequest(paging.getPage(),  MaxPageItem.maxPageItem_product);
		}
		else if(paging.getPage() == null) {
			pageable =  new PageRequest(MaxPageItem.pageStart, MaxPageItem.maxPageItem_product);
		}
		List<ProductDTO> listProduct = null;
		Page<ProductEntity> pageProduct = null;
		Map<String,Object> response = null; 
		
		if (categoryId == null && status == null) {
			response = productService.findAll(pageable);
			System.out.println(response);
			model.addAttribute("listProduct", response.get("products"));
			model.addAttribute("pageable",response.get("pagination"));
			pageRequest pag = (pageRequest) response.get("pagination");
			model.addAttribute("categoryCommon", categoryCommonService.findAll());
			model.addAttribute("category", categoryService.findAll());
			System.out.println(pag.getTotalsPage());
		} else {
			ProductDTO product = new ProductDTO();
			product.setCategoryId(categoryId);
			product.setStatus(status);
			pageProduct = productService.findByCategoryOrStatus(product,pageable);
			paging.setTotalsPage(pageProduct.getTotalPages());
			paging.setPage(pageProduct.getNumber());
			model.addAttribute("pageable", paging);
			model.addAttribute("categoryId", categoryId);
			model.addAttribute("categoryCommon", categoryCommonService.findAll());
			model.addAttribute("category", categoryService.findAll());
			listProduct = entityToDto.entityToDto(pageProduct, ProductDTO.class);
			model.addAttribute("listProduct", listProduct);
		}
		
		
		return "admin/product";
	}

	@PostMapping(value = "product")
	public String addProduct(@ModelAttribute("product") ProductDTO product) {
		System.out.println(product.getName());
		try {
			if (product.getFileImage() != null) {
				String url = fileUpload.uploadFile(product.getFileImage());
				product.setImage(url);
			}
			
			product.setSizeQuantityMap(tranfromListToMap.toMap(product.getSize(), product.getAmount()));
			System.out.println(product.getSizeQuantityMap());
			productService.save(product);
			return "redirect:/admin/product";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/admin-trang-chu";
	}

//	@PostMapping(value = "product",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
//	public String addProduct(@RequestParam("file") String file) {
////		try {
////			String url = fileUpload.uploadFile(file);
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		return "redirect:/admin-trang-chu";
//	}

}
