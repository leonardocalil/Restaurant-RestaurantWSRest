var url_validate_user = 'http://localhost:8080/RestaurantWSRest/rest/employee/validateUser';


var url_product_type_get_all = 'http://localhost:8080/RestaurantWSRest/rest/producttype/getAll';
var url_product_type_get = 'http://localhost:8080/RestaurantWSRest/rest/producttype/get/';
var url_product_type_save = 'http://localhost:8080/RestaurantWSRest/rest/producttype/save';
var url_product_type_delete = 'http://localhost:8080/RestaurantWSRest/rest/producttype/delete/';

var url_product_item_get_all = 'http://localhost:8080/RestaurantWSRest/rest/product/getAllJson';
var url_product_item_get = 'http://localhost:8080/RestaurantWSRest/rest/product/get/';
var url_product_item_save = 'http://localhost:8080/RestaurantWSRest/rest/product/save';
var url_product_item_delete = 'http://localhost:8080/RestaurantWSRest/rest/product/delete/';

var url_employee_role_get_all = 'http://localhost:8080/RestaurantWSRest/rest/role/getAll';
var url_employee_role_get = 'http://localhost:8080/RestaurantWSRest/rest/role/get/';
var url_employee_role_save = 'http://localhost:8080/RestaurantWSRest/rest/role/save';
var url_employee_role_delete = 'http://localhost:8080/RestaurantWSRest/rest/role/delete/';

var url_employee_person_get_all = 'http://localhost:8080/RestaurantWSRest/rest/employee/getAll';
var url_employee_person_get = 'http://localhost:8080/RestaurantWSRest/rest/employee/get/';
var url_employee_person_save = 'http://localhost:8080/RestaurantWSRest/rest/employee/save';
var url_employee_person_delete = 'http://localhost:8080/RestaurantWSRest/rest/employee/delete/';

function newProductType() {
	return {id: 0, description:""};
}
function newProductItem() {
	return {id: 0, productType:{id:0,description:""},name:"",description:"",cost_price:"0",sale_price:"0"};
}

function newEmployeeRole() {
	return {id: 0, name:"",description:""};
}

function newEmployeePerson() {
	return {id: 0, name:"", document:"", phone:"", email:"", address_name:"",address_number:"",address_complement:"",zip_code:"",login:"",password:"",access_level:0,role:{},boss:{}};
}

