package com.example.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.bean.Category;
import com.example.bean.OneEntity;
import com.example.bean.Programmer;
import com.example.bean.Project;
import com.example.bean.Registration;
import com.example.bean.StockEntity;
import com.example.bean.TwoEntity;
import com.example.bean.User;
import com.example.dto.ProjectDTO;
import com.example.dto.RegistrationDTO;
import com.example.repository.BidirectionMappingExampleForOneToMAnySAveRepositoy;
import com.example.repository.BidirectionMappingExampleRepositoy;
import com.example.repository.ManyManyRepositoryForCategory;
import com.example.repository.ManyManyRepositoryForStock;
import com.example.repository.ProgrammerRepository;
import com.example.repository.ProjectRepository;
import com.example.repository.RegistrationRepository;
import com.example.repository.UserRepository;

@RestController
@RequestMapping("/many")
public class ManyToMayController {

	@Autowired
	private ManyManyRepositoryForStock manyManyRepository;

	@Autowired
	private ManyManyRepositoryForCategory manyManyRepositoryForCategory;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RegistrationRepository registrationRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	
	@Autowired
	private ProgrammerRepository programmerRepository;
	
	
	@Autowired
	private BidirectionMappingExampleRepositoy bidirectionMappingExampleRepositoy;
	
	@Autowired
	private BidirectionMappingExampleForOneToMAnySAveRepositoy bidirectionMappingExampleForOneToMAnySAveRepositoy;

	@Transactional
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String Add(HttpServletRequest request) {

		StockEntity stockEntity = new StockEntity();
		stockEntity.setStockCode("123");
		stockEntity.setStockName("testStock");

		Category category = new Category();
		category.setCategoryName("testCategory");
		category.setDesc("testDesc");

		Category category1 = new Category();
		category1.setCategoryName("testCategory_1");
		category1.setDesc("testDesc_1");

		List<Category> categories = new ArrayList<>();
		categories.add(category1);
		categories.add(category);
		stockEntity.setCategory(categories);

		manyManyRepository.save(stockEntity);
		return "success";
	}

	@Transactional
	@RequestMapping(value = "AddCategory", method = RequestMethod.GET)
	public String AddCategory(HttpServletRequest request) {

		Category category = new Category();
		category.setCategoryName("testasparent");
		category.setDesc("testDescasparent");

		StockEntity stockEntity = new StockEntity();
		stockEntity.setStockCode("123");
		stockEntity.setStockName("testStock");

		StockEntity stockEntity1 = new StockEntity();
		stockEntity1.setStockCode("123_1");
		stockEntity1.setStockName("testStock_1");

		List<StockEntity> stockEntities = new ArrayList<>();
		stockEntities.add(stockEntity);
		stockEntities.add(stockEntity1);

		category.setStock(stockEntities);

		manyManyRepositoryForCategory.save(category);
		return "success";
	}

	@Transactional
	@RequestMapping(value = "getStockEntityByd", produces = "application/json", method = RequestMethod.GET)
	public StockEntity getStockEntityByd(HttpServletRequest request) {
		return manyManyRepository.getStockDetailsById(1L);
	}

	@RequestMapping(value = "getUserByID", method = RequestMethod.GET, produces = "application/json")
	public User getUserByID() {
		return userRepository.findOne(1l);
	}

	@RequestMapping(value = "getRegistrationByID", method = RequestMethod.GET, produces = "application/json")
	public RegistrationDTO getRegistrationByID() {
		Registration registration = registrationRepository.findOne(1l);
		RegistrationDTO registrationDTO = registration.convertTo();
		return registrationDTO;
	}
	
	@ResponseBody
	@RequestMapping(value = "oneTwoManyBiDirectional", method = RequestMethod.GET)
	public String oneTwoManyBiDirectional (HttpServletRequest request) {
		
		
		OneEntity oneEntity = new OneEntity();
		oneEntity.setOne("one");
		
		TwoEntity twoEntity = new TwoEntity();
		twoEntity.setTwo("two1");
		twoEntity.setOneEntity(oneEntity);
		
		TwoEntity twoEntity1 = new TwoEntity();
		twoEntity1.setTwo("two2");
		twoEntity1.setOneEntity(oneEntity);
		
		bidirectionMappingExampleRepositoy.save(twoEntity);
		bidirectionMappingExampleRepositoy.save(twoEntity1);
		return "succees";
		
	}
	
	@RequestMapping(value = "saveDataWithAsOneToMany", method = RequestMethod.GET)
	public String saveDataWithAsOneToMany(HttpServletRequest request) {
		
		
		OneEntity oneEntity = new OneEntity();
		oneEntity.setOne("one11");
		
		List<TwoEntity> twoEntities = new ArrayList<TwoEntity>();
		
		TwoEntity twoEntity = new TwoEntity();
		twoEntity.setTwo("oneToMany1");
		twoEntities.add(twoEntity);
		
		TwoEntity twoEntity1 = new TwoEntity();
		twoEntity1.setTwo("oneToMany1");
		twoEntities.add(twoEntity1);
		
		oneEntity.setTwoEntity(twoEntities);
		bidirectionMappingExampleForOneToMAnySAveRepositoy.save(oneEntity);
		
		return "succees";	
	}
	
	@ResponseBody
	@RequestMapping(value = "getOneEntityDetails", method = RequestMethod.GET,produces = "application/json")
	public OneEntity getOneEntityDetails(HttpServletRequest  request) {
		OneEntity oneEntity = bidirectionMappingExampleForOneToMAnySAveRepositoy.findOne(2l);
		return oneEntity;	
	}
	
	
	
	@RequestMapping(value = "addProgrammers", method  = RequestMethod.GET)
	public String addProgrammers() {
		
		// create parent objs (Programmer)
				Programmer p1=new Programmer();
				p1.setPid(1);p1.setPname("raja"); p1.setSalary(9000);
				
		/*
		 * Programmer p2=new Programmer(); p2.setPid(2);p2.setPname("ravi");
		 * p2.setSalary(9000);
		 * 
		 * Programmer p3=new Programmer(); p3.setPid(3);p3.setPname("anil");
		 * p3.setSalary(9000);
		 */
				
				//create child objs (Project)
				Project proj1=new Project();
				proj1.setProid(1001);proj1.setProname("proj1"); 
				
				Project proj2=new Project();
				proj2.setProid(1002);proj2.setProname("proj2");
				
				//add childs to parent 
				p1.getProjects().add(proj1); p1.getProjects().add(proj2);
				
				//p2.getProjects().add(proj1);
				
				//p3.getProjects().add(proj1); p3.getProjects().add(proj2);
		
				
				programmerRepository.save(p1); 
				//programmerRepository.save(p2); 
				//programmerRepository.save(p3);
				
				return "success";
		
	}
	
	
	@RequestMapping(value = "getProjectDetails", method = RequestMethod.GET)
	public ProjectDTO getProjectDetails() {
		Project project  = projectRepository.findByproid(1001);
		return convertTOEmployeeDTO(project);
		
	}
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ProjectDTO convertTOEmployeeDTO(Project employee){
		ProjectDTO employeeDTO = modelMapper.map(employee, ProjectDTO.class);
		return employeeDTO;
	}
	
	@RequestMapping(value = "addProject", method = RequestMethod.GET)
	public String addProject(HttpServletRequest request) {
		
		Set<Programmer> programmers= new HashSet<>();
		
		Programmer programmer = new Programmer();
		programmer.setPid(2121);
		programmer.setPname("ten");
		programmer.setSalary(213134);
		programmers.add(programmer);
		

		Project p1 = new Project();
		p1.setProid(2);
		p1.setProname("ten");
		p1.setProgrammers(programmers);
		
		projectRepository.save(p1);
		return "String";
	}
	
	@RequestMapping(value = "restTemplateTest", method = RequestMethod.GET)
	public void restTemplateTest(HttpServletRequest request) {
		
		
		
		
		
		
	}
}
