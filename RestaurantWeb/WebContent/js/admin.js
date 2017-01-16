var app = angular.module("AdminApp", ["ngRoute","angularUtils.directives.dirPagination","angular-md5"]); 

app.config(function($routeProvider) {
	$routeProvider
	.when('/login', {
		templateUrl : 'login.html',
		controller: 'LoginCtrl'
	})
	.when('/forgotPassword', {
		templateUrl : 'forgotpassword.html'	
	})
	.when('/home', {
		templateUrl : 'home.html'	
	})
	.otherwise({
	       redirectTo: '/login'
	});
	
});

app.factory('Auth', function(){
	var employee;
	return{
	    setEmployee : function(aEmployee){
	    	employee = aEmployee;
	    },
	    isLoggedIn : function(){	    	
	    	return(employee)? employee : false;
	    }
	  }
});

app.run(['$rootScope', '$location', 'Auth', function ($rootScope, $location, Auth) {
    $rootScope.$on('$routeChangeStart', function (event) {

        if (!Auth.isLoggedIn()) {
            //event.preventDefault();
            $location.path('/login')
        }
        else {
            $location.path('/home');
        }
    });
}]);


app.directive('format', ['$filter', function ($filter) {
    return {
        require: '?ngModel',
        link: function (scope, elem, attrs, ctrl) {
            if (!ctrl) return;


            ctrl.$formatters.unshift(function (a) {
                return $filter(attrs.format)(ctrl.$modelValue)
            });


            ctrl.$parsers.unshift(function (viewValue) {
                              
          elem.priceFormat({
            prefix: '',
            centsSeparator: ',',
            thousandsSeparator: '.'
        });                
                         
                return elem[0].value;
            });
        }
    };
}]);

app.directive("formatPhone", function() {
	return {
	    link : function(scope, element, attrs) {
	        var options = {
	        	onKeyPress: function(val, e, field, options) {
	        		$(element).mask('(00) 00000-0000', options);
	            }
	        }
	        element.bind('blur', function() {
	            adjustMask();
	          });
	 
	        $(element).mask('(00) 00000-0000', options);
	 
	        function adjustMask() {
	            var mask;
	            var cleanVal = element[0].value.replace(/\D/g, '');//pega o valor sem mascara
	            if(cleanVal.length < 11) {//verifica a quantidade de digitos.
	                mask = "(00) 0000-0000";
	                $(element).mask(mask, options);//aplica a mascara novamente
	            }
	            
	        }
	       
	    }
	  }
});


app.directive("formatDocument", function() {
	return {
	    link : function(scope, element, attrs) {
	        var options = {
	        	onKeyPress: function(val, e, field, options) {
	        		$(element).mask('000.000.000/0000-00', options);
	            }
	        }
	        element.bind('blur', function() {
	            adjustMask();
	        });
	 
	        $(element).mask('000.000.000/0000-00', options);
	 
	        function adjustMask() {
	            var mask;
	            var cleanVal = element[0].value.replace(/\D/g, '');//pega o valor sem mascara
	            if(cleanVal.length < 12) {//verifica a quantidade de digitos.
	                mask = "000.000.000-00";
	                $(element).mask(mask, options);//aplica a mascara novamente
	            }
	            
	        }
	       
	    }
	  }
});


app.directive('validPassword', function() {
	  return {
	    require: 'ngModel',
	    scope: {

	      reference: '=validPassword'

	    },
	    link: function(scope, elm, attrs, ctrl) {
	      ctrl.$parsers.unshift(function(viewValue, $scope) {

	        var noMatch = viewValue != scope.reference
	        ctrl.$setValidity('noMatch', !noMatch);
	        return (noMatch)?noMatch:!noMatch;
	      });

	      scope.$watch("reference", function(value) {;
	        ctrl.$setValidity('noMatch', value === ctrl.$viewValue);

	      });
	    }
	  }
	});

app.controller('LoginCtrl', function($scope,$http,$location,md5,Auth) {
	$scope.user = "";
	$scope.password = "";
	
	
	$scope.login = function() {
		
		$http.post(url_validate_user,{user:$scope.user,password:md5.createHash($scope.password)}).
		then(function(response) {
    		var employee = response.data;
    		if(employee.id != null) {
    			Auth.setEmployee(employee);    	
    			$location.path( "/home" );
    		} else {    			
    			alert('Usuario e/ou senha invalido!');
    		}
    	});    	
		

	}
}); 

app.controller('HomeCtrl',function ($scope,$http, Auth) {
	
	$scope.page = "";
	
	$scope.menuPage = function(vpage) {
		
		$scope.page = "pages/"+vpage+".html";    	
		

	}
	
});


app.controller('ProductTypeCtrl',function ($scope,$http) {
	
	var page_new = "pages/product/product_type_new.html";
	var page_list = "pages/product/product_type_list.html"; 	
	
	$scope.page  = page_list; 
	
	
	$scope.submitted = false;
	
	$scope.models = [];
	
	$http.get(url_product_type_get_all)
	.then(function(response) {
		$scope.models = response.data;		
	});    	
	
	
	$scope.ordenar = function(keyname){
        $scope.sortKey = keyname;
        $scope.reverse = !$scope.reverse;
    };
	
	$scope.new_ = function(vpage) {
		$scope.model = newProductType();				
		
		$scope.page = page_new;    			
	}
	$scope.back = function() {		
		$http.get(url_product_type_get_all)
		.then(function(response) {
			$scope.models = response.data;		
		});    	
		$scope.page  = page_list;    			
	}
	$scope.edit = function(vmodel) {
		
		$scope.model = vmodel;
		$scope.page = page_new;
	}
	$scope.delete_ = function(vmodel) {
		if(confirm("Deseja realmente excluir esse registro ("+vmodel.id+" - "+vmodel.description+")?")) {
			$http.get(url_product_type_delete+vmodel.id).
    		then(function(response) {
    			if(response.data == true) {
    				$scope.back();
    				alert('Registro deletado com sucesso!');
    			} else {
    				alert('Erro ao deletar o registro, por gentileza, entre em contato com seu suporte');
    			}
    		});
		}
	}
	$scope.save = function() {
		
		$scope.submitted = true;
		
		if($scope.model.description != null && $scope.model.description.length > 0) {
			
			
			$http.post(url_product_type_save,$scope.model)
			.then(function(response) {
				if(response.data == true) {
					console.log(response.data);
					$scope.submitted = false;
					$scope.back();
				} else {
					alert('Erro ao salvar registro, por gentileza, contate o seu suporte.');
				} 		
				
			});    	
			
		}
		
	}
	
}); 



app.controller('ProductItemCtrl',function ($scope,$http) {
	
	var page_list = "pages/product/product_item_list.html";
	var page_new = "pages//product/product_item_new.html";
	
	$scope.page  = page_list;
	
	$scope.submitted = false;
	
	$scope.models = [];
	
	$scope.productTypes = [];
	
	$scope.model = newProductItem();
	
	
	$http.get(url_product_item_get_all)
	.then(function(response) {
		$scope.models = response.data;		
	});
	
	$http.get(url_product_type_get_all)
	.then(function(response) {
		$scope.productTypes = response.data;
		$scope.productTypes.unshift({id:"",description:"--Selecione--"})
	});    	
	
	
	$scope.ordenar = function(keyname){
        $scope.sortKey = keyname;
        $scope.reverse = !$scope.reverse;
    };
	
	$scope.new_ = function(vpage) {
		$scope.model = newProductItem();				
		
		$scope.page = page_new;    			
	}
	$scope.back = function() {		
		$http.get(url_product_item_get_all)
		.then(function(response) {
			$scope.models = response.data;		
		});    	
		$scope.page  = page_list;    			
	}
	$scope.edit = function(vmodel) {
		
		$scope.model = vmodel;
		
		$scope.page = page_new;
	}
	$scope.delete_ = function(vmodel) {
		if(confirm("Deseja realmente excluir esse registro ("+vmodel.id+" - "+vmodel.name+")?")) {
			$http.get(url_product_item_delete+vmodel.id).
    		then(function(response) {
    			if(response.data == true) {
    				$scope.back();
    				alert('Registro deletado com sucesso!');
    			} else {
    				alert('Erro ao deletar o registro, por gentileza, entre em contato com seu suporte');
    			}
    		});
		}
	}
	$scope.save = function() {
		
		$scope.submitted = true;
		
		console.log($scope.model.productType);
		if($scope.model.productType != null && $scope.model.productType.id != 0 &&
		   $scope.model.name != null && $scope.model.name.length > 0 &&
		   $scope.model.sale_price != null && $scope.model.sale_price.length > 0) {
			
			$scope.submitted = false;
			
			$http.post(url_product_item_save,$scope.model)			
			.then(function(response) {
				if(response.data == true) {
					
					$scope.back();
				} else {
					alert('Erro ao salvar registro, por gentileza, contate o seu suporte.');
				} 		
				
			});    	
			
		}
		
	}
	
}); 


app.controller('EmployeeRoleCtrl',function ($scope,$http) {
	
	var page_list = "pages/employee/employee_role_list.html";
	var page_new = "pages/employee/employee_role_new.html";
	
	$scope.page  = page_list; 
	
	$scope.submitted = false;
	
	$scope.models = [];
	
	$http.get(url_employee_role_get_all)
	.then(function(response) {
		$scope.models = response.data;		
	});    	
	
	
	$scope.ordenar = function(keyname){
        $scope.sortKey = keyname;
        $scope.reverse = !$scope.reverse;
    };
	
	$scope.new_ = function(vpage) {
		$scope.model = newEmployeeRole();				
		
		$scope.page = page_new;    			
	}
	$scope.back = function() {		
		$http.get(url_employee_role_get_all)
		.then(function(response) {
			$scope.models = response.data;		
		});    	
		$scope.page  = page_list;    			
	}
	$scope.edit = function(vmodel) {
		
		$scope.model = vmodel;
		$scope.page = page_new;
		
	}
	$scope.delete_ = function(vmodel) {
		if(confirm("Deseja realmente excluir esse registro ("+vmodel.id+" - "+vmodel.name+")?")) {
			$http.get(url_employee_role_delete+vmodel.id).
    		then(function(response) {
    			if(response.data == true) {
    				$scope.back();
    				alert('Registro deletado com sucesso!');
    			} else {
    				alert('Erro ao deletar o registro, por gentileza, entre em contato com seu suporte');
    			}
    		});
		}
	}
	$scope.save = function() {
		
		$scope.submitted = true;
		
		if($scope.model.name != null && $scope.model.name.length > 0) {
			
			
			$http.post(url_employee_role_save,$scope.model)
			.then(function(response) {
				if(response.data == true) {
					console.log(response.data);
					$scope.submitted = false;
					$scope.back();
				} else {
					alert('Erro ao salvar registro, por gentileza, contate o seu suporte.');
				} 		
				
			});    	
			
		}
		
	}
	
}); 


app.controller('EmployeePersonCtrl',function ($scope,$http) {
	
	var page_list = "pages/employee/employee_person_list.html";
	var page_new = "pages/employee/employee_person_new.html";
	
	$scope.page  = page_list;
	
	$scope.submitted = false;
	
	$scope.models = [];
	
	$scope.roles = [];
	
	$scope.persons = [];
	
	
	$scope.model = newEmployeePerson();
	
		
	$scope.getSupervisors = function () {
		$http.get(url_employee_person_get_all)
		.then(function(response) {
			$scope.persons = response.data;
			$scope.persons.unshift({id:"0",name:"--N/A--"})
			if($scope.model.id != 0) {
				angular.forEach($scope.persons, function(value, key){
					if(value.id == $scope.model.id) {
						$scope.persons.splice(splice);	
					}
		    		
		    		    		
		    	});
			}
			
			
		});
	}
	
	$http.get(url_employee_role_get_all)
	.then(function(response) {
		$scope.roles = response.data;		
		$scope.roles.unshift({id:"",name:"--Selecione--"})
	});
	
	$http.get(url_employee_person_get_all)
	.then(function(response) {
		$scope.models = response.data;		
	});
	
	    	
	$scope.getSupervisors();
	
	$scope.ordenar = function(keyname){
        $scope.sortKey = keyname;
        $scope.reverse = !$scope.reverse;
    };
	
	$scope.new_ = function(vpage) {
		$scope.model = newEmployeePerson();				
		
		$scope.page = page_new;    			
	}
	$scope.back = function() {		
		$http.get(url_employee_person_get_all)
		.then(function(response) {
			$scope.models = response.data;		
		});    	
		$scope.page  = page_list;    			
	}
	$scope.edit = function(vmodel) {
		
		$scope.model = vmodel;
		
		$scope.page = page_new;
	}
	$scope.delete_ = function(vmodel) {
		if(confirm("Deseja realmente excluir esse registro ("+vmodel.id+" - "+vmodel.name+")?")) {
			$http.get(url_product_item_delete+vmodel.id).
    		then(function(response) {
    			if(response.data == true) {
    				$scope.back();
    				alert('Registro deletado com sucesso!');
    			} else {
    				alert('Erro ao deletar o registro, por gentileza, entre em contato com seu suporte');
    			}
    		});
		}
	}
	$scope.save = function() {
		
		$scope.submitted = true;
		
		if($scope.model.name != null &&
		   $scope.model.document != null && ($scope.model.document.length == 14 || $scope.model.document.length == 19) && 
		   $scope.model.address_name != null &&
		   $scope.model.address_number != null &&
		   $scope.model.address_complement != null &&
		   $scope.model.role.id != null && $scope.model.role.id != 0 &&
		   $scope.model.boss.id != null ) {
			
			$scope.submitted = false;
			console.log("employee-person:save!!");
			
			$http.post(url_employee_person_save,$scope.model)			
			.then(function(response) {
				if(response.data == true) {
					
					$scope.back();
				} else {
					alert('Erro ao salvar registro, por gentileza, contate o seu suporte.');
				} 		
				
			});    
			
		}
		
	}
	$scope.update_access = function() {
		console.log("employee-person:save!!");
	}
	
	
}); 
