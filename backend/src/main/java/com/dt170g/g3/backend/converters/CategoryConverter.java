//package com.dt170g.g3.backend.converters;
//
//
//import com.dt170g.g3.backend.entities.Category;
//
//import com.dt170g.g3.backend.services.CategoryService;
//import jakarta.annotation.PostConstruct;
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.faces.component.UIComponent;
//import jakarta.faces.context.FacesContext;
//import jakarta.faces.convert.Converter;
//import jakarta.faces.convert.FacesConverter;
//import jakarta.inject.Inject;
//import jakarta.inject.Named;
//import jakarta.persistence.EntityManager;
//import jakarta.validation.constraints.Null;
//
////
////@Named("categoryConverter")
////@ApplicationScoped
//@FacesConverter(value = "categoryConverter", managed = true)
//public class CategoryConverter implements Converter<Category> {
//    @Inject
//    CategoryService categoryService;
//
//
//
//
//
//
//    @Override
//    public Category getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
//        EntityManager entityManager;
//        if(s == null){
//            return null;
//        }
//        System.out.println("Hej");
//        return categoryService.findById(Integer.valueOf(s));
//    }
//
//    @Override
//    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Category category) {
//        if(category == null || category.getId() == null){
//            return "";
//        }
//
//        return String.valueOf(category.getId());
//
//
//
//    }
//}
